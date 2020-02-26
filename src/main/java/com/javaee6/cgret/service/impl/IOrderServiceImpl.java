package com.javaee6.cgret.service.impl;

import com.alipay.api.domain.NewsfeedMediaImg;
import com.javaee6.cgret.dao.*;
import com.javaee6.cgret.model.*;
import com.javaee6.cgret.service.IOrderService;
import com.javaee6.cgret.service.IShopCartService;
import com.javaee6.cgret.util.ClientThreadLocal;
import com.javaee6.cgret.util.OrderCodeFactory;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 **/

@Service
public class IOrderServiceImpl implements IOrderService {

    @Resource
    private CgretXMapper cgretXMapper;

    @Resource
    private IShopCartService shopCartService;

    @Resource
    private CgretMapper cgretMapper;

    /**
     * update stock by productId
     *
     * @param productId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int updateStockByProductId(Integer productId, Integer buyNum, Integer version) {

        return cgretXMapper.updateStockByProductId(productId, buyNum, version);
    }

    /**
     * 更新库存
     *
     * @param orderItems
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateStock(List<OrderCart> orderItems) {

        // 订单是否可以成功生成取决于是否足够，如果其中一件商品的库存不够就不可以生成订单
        int flag = 0;

        // 判断商品库存是否足够
        for(OrderCart orderCart : orderItems){
            Cgret cgret = shopCartService.getCgretById(orderCart.getProductId());
            int leftNum = cgret.getLeftnum();
            int version = cgret.getVersion();

            // if stock is enough,then we judge
            // 两种情况：1、某商品的库存查出来直接不够，那么回滚，并且返回false，不能创建订单
            // 2、多个请求同时发送过来时， 查出来的库存是够的，但是update的操作由于乐观锁的原因，因此update失败，返回的值是0，这时候也不能创建订单
            // 第一种情况，回滚后就直接返回库存不足，购买失败，而第二种情况是因为商品被占用了，是需要继续下单的，这种情况处理
            if(leftNum >= orderCart.getCommodityNum()){
                int affectedRow = updateStockByProductId(orderCart.getProductId(), orderCart.getCommodityNum(), version);
                if(affectedRow <= 0){
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    flag = 2;
                    return flag;
                }
            }else {
                flag = 1;
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return flag;

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testUpdate(Integer productId, Integer buyNum, Integer version) {

        System.out.println("更新开始");

        cgretXMapper.updateTest(version);

        for(int i = 0 ; i < 10 ; i++){
            cgretXMapper.updateTest2(i);
            if(i == 9){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        int i = cgretXMapper.testRollBack(productId, buyNum, version);
        System.out.println(i + "=========");
        i--;
    }

    @Resource
    private OrderInfoMapper orderInfoMapper;

    private OrderCodeFactory orderCodeFactory;

    @Resource
    private OrderHistoryMapper orderHistoryMapper;

    @Resource
    private OrderProductMapper orderProductMapper;

    /**
     * 生成订单
     *
     * @param orderItems
     */
    @Override
    public OrderInfo createOrder(List<OrderCart> orderItems) {

        Client client = ClientThreadLocal.get();

        Integer orderId = UUID.randomUUID().toString().hashCode();
        if(orderId < 0){
            orderId = -orderId;
        }

        float totalFee = 0;
        for(OrderCart carts : orderItems){
            totalFee += carts.getCommodityNum()*carts.getCommodityPrice();
        }
        DecimalFormat df = new DecimalFormat(".00");
        totalFee = Float.parseFloat(df.format(totalFee)) ;

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setClientId(client.getClientId());
        orderInfo.setOrderaddress(client.getDefAddress());
        orderInfo.setOrderId(orderId.toString());

        orderInfo.setOrderTotalFee(totalFee);
        // 下单，未支付状态
        orderInfo.setOrderStatus(0);
        // ?? 暂时包邮
        orderInfo.setDeliveryFee((float) 0);
        // ?? 暂时顺丰，没得选
        orderInfo.setDeliveryType("sf");

        Date now = new Date();
        orderInfo.setOrderTime (now);
        // ?? 价格暂时先这样
        orderInfo.setActuallyTotalFee(totalFee);

        // 下单成功，生成新的订单信息，insert到数据库中
        orderInfoMapper.insert(orderInfo);

        return orderInfo;
    }

    /**
     * 生成订单明细表
     * @param orderItems
     * @return
     */
    @Override
    public List<OrderProduct> createOrderDetail(List<OrderCart> orderItems, String orderId) {
        List<OrderProduct> orderProducts = new ArrayList<>();

        for(OrderCart cart : orderItems){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(orderId);
            orderProduct.setProductid(cart.getProductId());
            orderProduct.setCommodityName(cart.getCommodityName());
            orderProduct.setCommodityCount(cart.getCommodityNum());
            orderProduct.setCommoditySinglePrice(cart.getCommodityPrice());

            orderProducts.add(orderProduct);

            orderProductMapper.insert(orderProduct);
        }

        return orderProducts;
    }

    /**
     * 取消订单表
     * 需要把订单插入到历史记录
     * 并且库存需要加回去
     * @param orderInfo
     */
    @Override
    public void cancelOrderInfo(OrderInfo orderInfo) {

        String orderId = orderInfo.getOrderId();

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderid(orderId);
        orderHistory.setClientid(orderInfo.getClientId());
        orderHistory.setCreatetime(orderInfo.getOrderTime());

        OrderProductExample orderProductExample = new OrderProductExample();
        OrderProductExample.Criteria criteria = orderProductExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        // 订单号为这个的商品List
        List<OrderProduct> orderProducts = orderProductMapper.selectByExample(orderProductExample);

        // 将这些取消后的商品信息插入到history中
        // 将这些商品的库存信息更新
        for(OrderProduct orderProduct : orderProducts){
            orderHistory.setProductnum(orderProduct.getCommodityCount());
            orderHistory.setSingleprice(orderProduct.getCommoditySinglePrice());
            orderHistory.setPaytime(orderInfo.getPayTime());
            orderHistory.setProductid(orderProduct.getProductid());

            Cgret cgret = new Cgret();
            CgretExample cgretExample = new CgretExample();
            CgretExample.Criteria criteria1 = cgretExample.createCriteria();
            criteria1.andProductidEqualTo(orderProduct.getProductid());

            cgret.setLeftnum( cgret.getLeftnum() + orderProduct.getCommodityCount());
            cgretMapper.updateByPrimaryKey(cgret);

            // insert into order_History
            orderHistoryMapper.insert(orderHistory);

            // 删除order_product中的相应数据
            orderProductMapper.deleteByExample(orderProductExample);
        }

        // 删除orderInfo中的被取消的订单
        orderInfoMapper.deleteByPrimaryKey(orderId);
    }

    /**
     * 通过orderId获取order
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderInfoByOrderId(String orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }

    /**
     * 支付后修改订单的状态，由0待支付改为1待发货
     *
     * @param orderId
     */
    @Override
    public void updateOrderStatus(String orderId) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        // get corresponding orderInfo Object by orderId
        OrderInfo orderInfo = orderInfoMapper.selectByExample(example).get(0);

        // and set the object's status to 1
        orderInfo.setOrderStatus(1);
        /*orderInfo.setDeliveryType();
        orderInfo.setDeliveryFee();
        orderInfo.setPayTime();
        orderInfo.setPaytoolNumber();*/

        // update data in mysql
        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }
}
