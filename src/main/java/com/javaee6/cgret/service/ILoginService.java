package com.javaee6.cgret.service;

import com.javaee6.cgret.model.Client;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ILoginService {

    boolean checkBuyerLogin(String loginName, String loginPwd, HttpSession session);

}
