package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.dao.ClientMapperX;
import com.javaee6.cgret.dao.ClientShoppingAddressMapper;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.ClientExample;
import com.javaee6.cgret.model.ClientShoppingAddress;
import com.javaee6.cgret.model.ClientShoppingAddressExample;
import com.javaee6.cgret.service.IMainClientService;
import com.mysql.cj.jdbc.interceptors.ConnectionLifecycleInterceptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class IMainClientServiceImpl implements IMainClientService {

    @Resource
    private ClientMapper clientMapper;

    @Resource
    private ClientMapperX clientMapperX;

    @Resource
    private ClientShoppingAddressMapper clientShoppingAddressMapper;

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
    public void insertDefaultAddress(HttpSession session, String detailAddress, String province, String city, String district) {

        ClientShoppingAddressExample example = new ClientShoppingAddressExample();
        Long addressNum = clientShoppingAddressMapper.countByExample(example);
        System.out.println(addressNum);
        Long addressId = addressNum+1;

        String contactor = (String) session.getAttribute("clientname");
        Long clientTel = (Long) session.getAttribute("clientTel");
        System.out.println("contactor" + contactor + clientTel);

        Long clientId = (Long) session.getAttribute("clientId");

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
