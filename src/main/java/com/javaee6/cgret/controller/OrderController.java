package com.javaee6.cgret.controller;

import com.javaee6.cgret.model.OrderInfo;
import com.javaee6.cgret.service.IOrderService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 订单系统
 * 0待支付 1待发货 2待收货 3评价 4退货/售后
 * @author Administrator
 **/

@RequestMapping("/Order")
@Controller
public class OrderController {

    @Resource
    private IOrderService orderService;

    @RequestMapping("/orderHome")
    public String showOrder(){
        return "Client_endCalculate";
    }

    @RequestMapping("/cancelOrder")
    @ResponseBody
    public String cancelOrder(String orderId){

        // 通过传过来的orderId数据，取消相应订单
        OrderInfo orderInfo = orderService.getOrderInfoByOrderId(orderId);

        orderService.cancelOrderInfo(orderInfo);
        return "success";
    }

    @RequiresRoles("user")
    @RequestMapping("/goForPay")
    public String goForPay(String orderId){

        orderService.updateOrderStatus(orderId);
        // 这里不会是返回页面的，肯定是返回点什么东西的
        return "goPay";
    }

    @RequestMapping("/paySuccess")
    public String paySuccess(){
        return "paySuccess";
    }


    @RequestMapping("/enterMyOrder")
    public String enterMyOrder(){
        return "Client_lookMyOrder";
    }

    @RequestMapping("/notify_url")
    public String notify_url(){
        return "notify_url";
    }

    @RequestMapping("/return_url")
    public String return_url(){
        return "return_url";
    }

    @RequestMapping("/tradePagePay")
    public String tradePagePay(){
        return "alipay.trade.page.pay";
    }

    @RequestMapping("/tradeClose")
    public String tradeClose(){
        return "alipay.trade.close";
    }

    @RequestMapping("/tradeFastPayRefundQuery")
    public String tradeFastPayRedundQuery(){
        return "alipay.trade.fastpay.refund.query";
    }

    @RequestMapping("/tradeQuery")
    public String tradeQuery(){
        return "alipay.trade.query";
    }

}
