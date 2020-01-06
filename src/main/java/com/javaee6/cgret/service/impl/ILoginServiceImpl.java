package com.javaee6.cgret.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.model.ClientExample;
import com.javaee6.cgret.service.ILoginService;
import com.javaee6.cgret.util.JSONUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class ILoginServiceImpl implements ILoginService {

    @Resource
    private ClientMapper mapper;

    private Jedis jedis;

    private String REDIS_SESSION_KEY;

    private Integer SESSION_EXPIRE;

    @Override
    public boolean checkBuyerLogin(String loginName, String loginPwd, HttpSession session) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();

        List<Client> clientList;
        criteria.andClientNameEqualTo(loginName);
        clientList = mapper.selectByExample(example);


        Client client = clientList.get(0);
        if(clientList.size() == 1){
            if(client.getPassword().equals(loginPwd) && client.getArticleCode().equals("0000")){
                // 登陆成功
                //写cokkie
                storeInSession(clientList, session);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @param clientList
     * @param session
     * 还未设置过期时间
     * 这里只写了买家，还有一个卖家
     * 将登录的用户信息写入session中
     */
    private void storeInSession(List<Client> clientList, HttpSession session) {

        session.setAttribute("clientId", clientList.get(0).getClientId());
        session.setAttribute("clientName", clientList.get(0).getClientName());
        session.setAttribute("clientEmail", clientList.get(0).getEmail());
        session.setAttribute("clientTel", clientList.get(0).getTelephone());
        session.setAttribute("clientPwd", clientList.get(0).getPassword());
    }



}
