package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.test.Client;
import com.javaee6.cgret.model.ClientExample;
import com.javaee6.cgret.service.IMainClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMainClientServiceImpl implements IMainClientService {

    public ClientMapper clmapper;


    @Override
    public Client getById(Integer id) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<Client> clientList = clmapper.selectByExample(example);
        if(clientList.size() == 1) {
            return clientList.get(0);
        }
        return null;
    }
}
