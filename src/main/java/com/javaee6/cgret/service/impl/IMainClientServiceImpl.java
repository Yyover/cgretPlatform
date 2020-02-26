package com.javaee6.cgret.service.impl;


import com.javaee6.cgret.dao.*;
import com.javaee6.cgret.model.*;
import com.javaee6.cgret.service.IMainClientService;
import com.javaee6.cgret.service.IShopCartService;

import com.javaee6.cgret.util.CookieUtil;
import com.javaee6.cgret.util.JedisUtil;
import com.javaee6.cgret.util.SerializeUtil;
import com.sun.deploy.util.SessionState;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.awt.SunHints;

import javax.annotation.Resource;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class IMainClientServiceImpl implements IMainClientService {

    @Resource
    private ClientMapper clientMapper;

    @Resource
    private ClientMapperX clientMapperX;

    @Resource
    private ClientShoppingAddressMapper clientShoppingAddressMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public Client getById(Long id) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andClientIdEqualTo(id);
        List<Client> clientList = clientMapper.selectByExample(example);
        if(clientList.size() == 1) {
            return clientList.get(0);
        }
        return null;
    }

    /**
     * 在用户设置界面写入默认地址
     * 插入地址表进行表数据的增加，同时去更新用户表的默认地址值
     * @param detailAddress
     * @param province
     * @param city
     * @param district
     */
    @Override
    public void insertDefaultAddress(HttpServletRequest request, String detailAddress, String province, String city, String district) {

        Client client = (Client) request.getSession().getAttribute("clientInfo");

        ClientShoppingAddressExample example = new ClientShoppingAddressExample();
        Long addressNum = clientShoppingAddressMapper.countByExample(example);
        System.out.println(addressNum);
        Long addressId = addressNum+1;

        String contactor = client.getClientName();
        Long clientTel = client.getTelephone();
        System.out.println("contactor" + contactor + clientTel);

        Long clientId = client.getClientId();

        ClientShoppingAddress csAddress = new ClientShoppingAddress();
        csAddress.setAddressId(addressId);
        csAddress.setProvince(province);
        csAddress.setCity(city);
        csAddress.setDistrict(district);
        csAddress.setDetail(detailAddress);
        csAddress.setContactor(contactor);
        csAddress.setTelephone(clientTel);


        clientShoppingAddressMapper.insert(csAddress);
        System.out.println("用户地址表添加成功");

        // 接下来更新用户表的默认地址值
        clientMapperX.updateDefAddress(addressId,
                province + city + district + detailAddress,
                clientId);

        System.out.println("更新用户表地址成功");
        // 更新成功

    }


}
