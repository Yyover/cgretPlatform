package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.dao.CgretMapper;
import com.javaee6.cgret.dao.CgretXMapper;
import com.javaee6.cgret.dao.OrderCartMapper;
import com.javaee6.cgret.model.*;
import com.javaee6.cgret.service.IShopCartService;
import com.javaee6.cgret.util.ClientThreadLocal;
import com.javaee6.cgret.util.JedisUtil;
import com.javaee6.cgret.util.SerializeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import sun.nio.cs.FastCharsetProvider;
import sun.plugin2.os.windows.FLASHWINFO;

import javax.annotation.Resource;
import javax.mail.Flags;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 **/

@Service
public class IShopCartServiceImpl implements IShopCartService {

    @Resource
    CgretMapper cgretMapper;

    @Resource
    CgretXMapper cgretXMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private IShopCartService iShopCartService;

    @Resource
    private OrderCartMapper orderCartMapper;

    @Resource
    private JedisUtil jedisUtil;

    /**
     * 通过id获得cgret
     * @param productid
     * @return
     */
    @Override
    public Cgret getCgretById(Integer productid) {

        return cgretMapper.selectByPrimaryKey(productid);
    }

    /**
     * 添加商品到购物车
     *
     * @param productId
     * @param count
     */
    public List<OrderCart> addItemToCart(List<OrderCart> list, Integer productId, Integer count) {


        System.out.println("id是:" + productId + "================");

        Client client ;
        client = (Client) request.getSession().getAttribute("clientInfo");

        if(client == null){
            client = new Client();
            throw new RuntimeException("未登录");
        }else {
            Cgret cgret = cgretMapper.selectByPrimaryKey(productId);

            if(cgret.getProductid() == null){
                throw new RuntimeException("商品不存在");
            }

            // 如果存在此商品，数量增加，没有则新建添加
            boolean flag = true;

            for(OrderCart cart : list){
                // 是因为== 还是 equal的原因吗
                if(cart.getProductId().equals(productId)){
                    System.out.println("相同id就加在一起");
                    flag = false;
                    cart.setCommodityNum( cart.getCommodityNum() + count );
                    break;
                }
            }

            // 否则新建ordercart对象，添加此商品
            if(flag){
                System.out.println("id不同，所以新建");
                OrderCart orderCart = new OrderCart();

                // 之前这里没有设置，导致很多地方出现bug
                orderCart.setProductId(productId);
                orderCart.setClientId(client.getClientId());
                orderCart.setCommodityNum(count);
                orderCart.setCommodityName(cgret.getProductname());
                orderCart.setCommodityStatus(1L);
                orderCart.setCommodityPrice(cgret.getPrice());

                list.add(orderCart);

            }

        }
        return list;
    }


    /**
     * 从redis中获取cart数据
     * @param clientId
     * @return
     */
    @Override
    public List<OrderCart> findCartFromRedis(Long clientId) {

        System.out.println("正在从redis中读取数据");


        List<OrderCart> carts = new ArrayList<>();

        String userID = "user" + clientId;

        Object obj = jedisUtil.getObject(userID);
        if(obj != null){
            System.out.println("缓存存在");
            carts = (List<OrderCart>) SerializeUtil.deserialize((byte[])obj);

        }else {
            System.out.println("缓存不存在");
            return carts;
        }

        return carts;

    }

    /**
     * 合并cookie和redis的购物车数据
     *
     * @param redis_cartList
     * @param cookie_cartList
     * @return
     */
    @Override
    public List<OrderCart> mergeCartList(List<OrderCart> redis_cartList, List<OrderCart> cookie_cartList) {

        System.out.println("合并两个list ----ing");

        for(OrderCart orderCart : cookie_cartList){
            System.out.println("productId:" + orderCart.getProductId() + "commodityNum:" + orderCart.getCommodityNum());
            if(orderCart.getProductId() != null){
                System.out.println("productId:" + orderCart.getProductId() + "commodityNum:" + orderCart.getCommodityNum());
                redis_cartList = addItemToCart(redis_cartList, orderCart.getProductId(), orderCart.getCommodityNum() );
            }
        }

        System.out.println("合并完成 ---- ");
        return redis_cartList;
    }

    /**
     * 保存cart数据到redis中
     * @param clientId
     * @param carts
     */
    @Override
    public void saveCartToRedis(Long clientId, List<OrderCart> carts) {

        String userId = "user" + clientId;
        jedisUtil.putObject(userId, SerializeUtil.serialize(carts));
    }

    /**
     * 从mysql数据库中获取购物车
     *
     * @param clientId
     * @return
     */
    @Override
    public List<OrderCart> findCartFromMysql(Long clientId) {

        OrderCartExample example = new OrderCartExample();
        OrderCartExample.Criteria criteria = example.createCriteria();
        criteria.andClientIdEqualTo(clientId);

        List<OrderCart> carts = orderCartMapper.selectByExample(example);

        return carts;
    }

    /**
     * 得到每个购物商品对应的imageUrl，并存入表中
     *
     * @param carts
     * @return
     */
    @Override
    public List<Cgret> findCgretByOrderCart(List<OrderCart> carts) {

        List<Cgret> cgretList = new ArrayList<>();

        for(OrderCart orderCart : carts){
            Integer productId = orderCart.getProductId();

            Cgret cgret = new Cgret();
            CgretExample example = new CgretExample();
            CgretExample.Criteria criteria = example.createCriteria();
            criteria.andProductidEqualTo(productId);

            cgret = cgretMapper.selectByExample(example).get(0);

            cgretList.add(cgret);
        }

        return cgretList;
    }

    @Override
    public void deleteItemByProductId(Integer productId) {
        Client client = ClientThreadLocal.get();

        List<OrderCart> carts = findCartFromRedis(client.getClientId());

        for(OrderCart orderCart : carts){
            // 又是equal的原因？
            if(orderCart.getProductId().equals(productId)){
                carts.remove(orderCart);
                break;
            }
        }
        System.out.println( "删除后购物车大小为：" + carts.size());
        saveCartToRedis(client.getClientId(), carts);
    }

    /**
     * 要结算了，删除购物车中被选中的商品
     *
     * @param idList
     */
    @Override
    public void deleteItemsByProductIdList(List<Integer> idList) {
        Client client = ClientThreadLocal.get();

        List<OrderCart> carts = findCartFromRedis(client.getClientId());
        Iterator<OrderCart> iterator = carts.iterator();

        // 这一段代码会发生ConcurrentModificationException
       /* for(OrderCart orderCart : carts){
            for(int id : idList){
                if(orderCart.getProductId().equals(id)){
                    carts.remove(orderCart);
                    break;
                }
            }
        }*/
        while (iterator.hasNext()){
            OrderCart orderCart = iterator.next();
            for(int id : idList){
                if(orderCart.getProductId().equals(id)){
                    iterator.remove();
                    break;
                }
            }
        }
        System.out.println("结算后的购物车大小为:" + carts.size());
        saveCartToRedis(client.getClientId(), carts);
    }

    /**
     * 将商品信息保存进入redis
     *
     * @param productId
     */
    @Override
    public void saveCgretIntoRedis(Integer productId) {

    }

    /**
     * 从redis取商品信息
     *
     * @param productId
     * @return
     */
    @Override
    public Cgret getCgretFromRedis(Integer productId) {
        return null;
    }


    /**
     * 通过idlist获得需要结算的商品信息列表cartList
     * @param idList
     * @return
     */
    @Override
    public List<OrderCart> getCartListByIdList(List<Integer> idList) {

        List<OrderCart> endCarts = new ArrayList<>();

        Client client = ClientThreadLocal.get();
        List<OrderCart> carts = findCartFromRedis(client.getClientId());

        for(OrderCart orderCart : carts){
            for(int i : idList){
                if(i == orderCart.getProductId()){
                    endCarts.add(orderCart);
                    break;
                }
            }
        }

        return endCarts;
    }



    public int testUpdate2(Integer version){
        return cgretXMapper.updateTest(version);
    }
}
