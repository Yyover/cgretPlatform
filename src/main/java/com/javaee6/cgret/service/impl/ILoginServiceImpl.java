package com.javaee6.cgret.service.impl;

import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.test.Client;
import com.javaee6.cgret.model.ClientExample;
import com.javaee6.cgret.service.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ILoginServiceImpl implements ILoginService {

    @Resource
    private ClientMapper mapper;

    @Override
    public boolean checkBuyerLogin(String loginName, String loginPwd, HttpSession session) {
        ClientExample example = new ClientExample();
        ClientExample.Criteria criteria = example.createCriteria();

        List<Client> clientList;
        criteria.andNameEqualTo(loginName);
        clientList = mapper.selectByExample(example);

        if(clientList.size() == 1){
            if(clientList.get(0).getPassword().equals(loginPwd) && clientList.get(0).getArticleCode().equals("0000")){
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
     * 这里只写了买家，还有一个卖家
     * 将登录的用户信息写入session中
     */
    private void storeInSession(List<Client> clientList, HttpSession session) {
        session.setAttribute("buyerId", clientList.get(0).getId());
        session.setAttribute("buyerName", clientList.get(0).getName());
        session.setAttribute("buyerEmail", clientList.get(0).getEmail());
        session.setAttribute("buyerTel", clientList.get(0).getTelephone());
        session.setAttribute("buyerPwd", clientList.get(0).getPassword());
    }



}
