package com.javaee6.cgret.service;

import com.javaee6.cgret.model.Cgret;
import com.javaee6.cgret.model.OrderCart;

import java.util.List;

public interface IShopCartService {

    /**
     * 通过id获得cgret
     * @param productid
     * @return
     */
    Cgret getCgretById(Integer productid);

    /**
     * 添加商品到购物车
     * @param list
     * @param productId
     * @param count
     */
    List<OrderCart> addItemToCart(List<OrderCart> list , Integer productId, Integer count);


    /**
     * 从redis中获取cart数据
     * @param clientId
     * @return
     */
    List<OrderCart> findCartFromRedis(Long clientId);

    /**
     * 合并cookie和redis的购物车数据
     * @param l1
     * @param l2
     * @return
     */
    List<OrderCart> mergeCartList(List<OrderCart> l1, List<OrderCart> l2);

    /**
     * 保存cart数据到redis中
     * @param clientId
     * @param carts
     */
    void saveCartToRedis(Long clientId, List<OrderCart> carts);

    /**
     * 从mysql数据库中获取购物车
     * @param clientId
     * @return
     */
    List<OrderCart> findCartFromMysql(Long clientId);

    /**
     * 通过购物车商品获得cgret
     * @param carts
     * @return
     */
    List<Cgret> findCgretByOrderCart(List<OrderCart> carts);

    /**
     * 购物车中删除商品
     * @param productId
     */
    void deleteItemByProductId(Integer productId);

    /**
     * 要结算了，删除购物车中被选中的商品
     */
    void deleteItemsByProductIdList(List<Integer> idList);

    /**
     * 将商品信息保存进入redis
     */
    void saveCgretIntoRedis(Integer productId);

    /**
     * 从redis取商品信息
     * @param productId
     * @return
     */
    Cgret getCgretFromRedis(Integer productId);

    /**
     * 通过idlist获得cartList
     * @param idList
     * @return
     */
    List<OrderCart> getCartListByIdList(List<Integer> idList);



    int testUpdate2(Integer version);
}
