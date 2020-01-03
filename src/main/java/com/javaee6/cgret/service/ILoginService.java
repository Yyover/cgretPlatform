package com.javaee6.cgret.service;

import javax.servlet.http.HttpSession;

public interface ILoginService {

    boolean checkBuyerLogin(String loginName, String loginPwd, HttpSession session);

}
