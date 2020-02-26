package com.javaee6.cgret.controller;

import com.javaee6.cgret.dao.CgretMapper;
import com.javaee6.cgret.model.Cgret;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.OrderCart;
import com.javaee6.cgret.service.IOrderService;
import com.javaee6.cgret.service.IShopCartService;
import com.javaee6.cgret.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 **/

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private JedisUtil jedisUtil;

    @RequestMapping("/testRedis")
    public void testRedis(){
        RedisUtils.getJedis().set("name", "liming");
        /*RedisUtils.getJedis().del("name");*/
        System.out.println(RedisUtils.getJedis().get("name"));
    }

    @RequestMapping("/listAll")
    @NotNull
    public void listAll(){

        Long id = 1111L;
        OrderCart orderCart = new OrderCart();
        orderCart.setProductId(199);
        orderCart.setClientId(id);

        List<OrderCart> carts = new ArrayList<>();

        if(orderCart != null){
            carts.add(orderCart);
        }

        jedisUtil.putObject("orderCart", SerializeUtil.serialize(carts));

        System.out.println("i have saved a data into redis");

        Object listFromRedis = jedisUtil.getObject("orderCart");

        List<OrderCart> carts1 = null;

        if(listFromRedis != null){
            System.out.println("redis缓存存在");
            carts1 = (List<OrderCart>) SerializeUtil.deserialize((byte[]) listFromRedis);
            System.out.println(carts1.get(0).getProductId() + "," + carts1.get(0).getClientId());
        }else {
            System.out.println("redis缓存不存在");
            jedisUtil.putObject("orderCart", SerializeUtil.serialize(carts));
            System.out.println("put a data into redis");
        }
    }

    @RequestMapping("/removeCart")
    public void removeCart(){
        Client client = ClientThreadLocal.get();

        String userID = "user" + client.getClientId();

        jedisUtil.removeObject(userID);
        System.out.println("去除" + client.getClientId() +"的购物车成功");
    }

    @Resource
    CgretMapper cgretMapper;

    @Resource
    IShopCartService shopCartService;

    @Resource
    IOrderService orderService;

    @RequestMapping("/updateCgret/{id}/{version}")
    public void updateCgret(@PathVariable("id") Integer id, @PathVariable("version") int version){

        orderService.testUpdate(id,1000,version);

        /*System.out.println("这个更新操作是无法完成的，看看update返回的值是什么:" + i);*/
        // 证明update是会返回值得

        /*int j = shopCartService.testUpdate2(version);
        System.out.println("看看j的值:" + j);*/
        // 证明update返回值是 受影响的行数 afectedRow

        // 经过了在spring-mybatis.xml的配置文件中，对事物管理器进行了配置就可以实现手动事务了


    }

    @RequestMapping("/ws")
    public String wsTest(){
        return "Client_talkRoom";
    }

    @RequestMapping("/aliPay")
    public String aliPay(){
        return "index";
    }
}
