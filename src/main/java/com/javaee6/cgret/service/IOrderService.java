package com.javaee6.cgret.service;

import com.javaee6.cgret.model.OrderCart;
import com.javaee6.cgret.model.OrderInfo;
import com.javaee6.cgret.model.OrderProduct;

import javax.print.DocFlavor;
import java.util.List;

/**
 * 订单
 */
public interface IOrderService {

    /**
     * update stock by productId
     * @param productId
     * @param buyNum
     * @param version
     * @return
     */
    int updateStockByProductId(Integer productId, Integer buyNum, Integer version);

    /**
     * 更新库存
     * @param orderItems
     */
    int updateStock(List<OrderCart> orderItems);

    void testUpdate(Integer productId, Integer buyNum, Integer version);

    /**
     * 生成订单表
     * @param orderItems
     */
    OrderInfo createOrder(List<OrderCart> orderItems);

    /**
     * 生成订单明细表
     * @param orderItems
     * @return
     */
    List<OrderProduct> createOrderDetail(List<OrderCart> orderItems, String orderId);

    /**
     * 取消订单
     * @param orderInfo
     */
    void cancelOrderInfo(OrderInfo orderInfo);

    /**
     * 通过orderId获取order
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfoByOrderId(String orderId);

    /**
     * 支付后修改订单的状态，由0待支付改为1待发货
     * @param orderId
     */
    void updateOrderStatus(String orderId);
}
