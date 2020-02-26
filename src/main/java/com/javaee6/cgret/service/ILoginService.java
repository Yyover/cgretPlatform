package com.javaee6.cgret.service;

import com.javaee6.cgret.model.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ILoginService {

    boolean checkBuyerLogin(String loginName, String loginPwd, HttpServletRequest request);

    Client getUserByUserName(String userName);

    String queryRoleByUserName(String userName);

}
