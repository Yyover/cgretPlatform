package com.javaee6.cgret.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaee6.cgret.model.OrderCart;
import com.javaee6.cgret.model.OrderInfo;
import com.javaee6.cgret.model.OrderProduct;
import com.javaee6.cgret.service.IOrderService;
import com.javaee6.cgret.service.IShopCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 购物车系统
 * @author Administrator
 **/

@RequestMapping("/cart")
@Controller
public class CartController {

    @Resource
    private IShopCartService shopCartService;

    @Resource
    private IOrderService orderService;

    /**
     * 移除购物车内的商品
     */
    @RequestMapping("/delete")
    @ResponseBody
    public void deleteItem(Integer productId){
        shopCartService.deleteItemByProductId(productId);
    }

    /**
     * 结算
     * 需要先清空购物车
     */
    @RequestMapping("/calTotal")
    @ResponseBody
    public Object calTotal(String[] idList, ModelMap map){

        List<Integer> idIntList = new ArrayList<>();
        OrderInfo newOrder = new OrderInfo();
        List<OrderProduct> newOrderDetail = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

        for(String string : idList){
            idIntList.add(Integer.parseInt(string));
        }

        // 订单内的商品
        List<OrderCart> orderItems = shopCartService.getCartListByIdList(idIntList);

        // 更新库存，有一个事物的回滚处理
        int condition = orderService.updateStock(orderItems);

        if(condition == 1){

            /*map.put("condition", condition);*/
            jsonObject.put("condition",condition);
            return jsonObject;
        }else if(condition == 2){
            // 第二种情况，需要继续结算，只是在排队等待别的线程释放锁，就重新调用就好了
            calTotal(idList, map);
        }else {
            // 第三种情况了，就继续做结算的操作

            // 创建订单表
            newOrder = orderService.createOrder(orderItems);

            // 创建订单明细表
            newOrderDetail = orderService.createOrderDetail(orderItems, newOrder.getOrderId());
            // 删除被选中的需要结算的商品
            shopCartService.deleteItemsByProductIdList(idIntList);

            System.out.println("新的订单编号为：" + newOrder.getOrderId());

            jsonObject.put("newOrder",newOrder);
            jsonObject.put("newOrderDetail",newOrderDetail);
            jsonObject.put("condition",newOrderDetail);
        }

        // 进入结算界面
        return jsonObject;
    }

    @RequestMapping("/enterOrderDetail")
    public String enterOrderDetail(){
        return "Client_endCalculate";
    }


    /**
     * 增加购物车某商品的数量
     */
    @RequestMapping("/plus")
    @ResponseBody
    public void plusItemNum(Integer productId){

    }

    /**
     * 减少购物车某商品的数量
     * @param productId
     */
    @RequestMapping("/substract")
    @ResponseBody
    public void substractItemNum(Integer productId){

    }
}
