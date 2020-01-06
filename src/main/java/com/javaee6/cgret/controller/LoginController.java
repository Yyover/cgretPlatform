package com.javaee6.cgret.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.service.ILoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author Hyao
 */
@Controller
@RequestMapping
public class LoginController {

    @Resource
    public ILoginService loginService;

    /**
     * @return
     * 进入登录界面
     */
    @RequestMapping("/login")
    public String login(){
        System.out.println("============进入登陆界面============");
        return "Login";
    }

    /**
     * @param loginName
     * @param loginPwd
     * @param session
     * @return
     * 用户登录校验
     */
    @RequestMapping("/buyerCheck")
    @ResponseBody
    public String buyerCheck(String loginName, String loginPwd, HttpSession session){
        System.out.println("登录校验");
        if(loginService.checkBuyerLogin(loginName, loginPwd, session)){
            System.out.println("登录校验正确");
            return  "match";
        } else{
            System.out.println("登录校验错误");
            return "unmatch";
        }
    }

    /**
     * @param session
     * @return
     * 进入用户主界面
     */
    @RequestMapping("/admin_main")
    public String welcomeToBase(HttpSession session){
        System.out.println("欢迎用户" + session.getAttribute("clientName") + "带着他/她的信息进入主界面了");
        return "Admin_main";
    }



}
