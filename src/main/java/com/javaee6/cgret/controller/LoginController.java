package com.javaee6.cgret.controller;


import com.javaee6.cgret.Interceptor.LoginInterceptor;
import com.javaee6.cgret.model.Client;
import com.javaee6.cgret.service.ILoginService;
import com.javaee6.cgret.util.ClientThreadLocal;
import com.javaee6.cgret.util.OnlineCount;
import com.javaee6.cgret.util.SessionSave;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 实现登录唯一性
 * @author Hyao
 */
@Controller
@RequestMapping("/Login")
public class LoginController {

    @Resource
    public ILoginService loginService;

    @Resource
    private HttpServletRequest request;

    /**
     * @return
     * 进入登录界面
     */
    @RequestMapping("/login")
    public String login(){
        System.out.println("============进入登陆界面============");
        // 判断一下是否有用户登录了
        String sessionId = request.getRequestedSessionId();

        // 实现一个浏览器只能由一个用户登录
        if(SessionSave.getSessionIdMap().containsValue(sessionId)){
            Client client = ClientThreadLocal.get();
            if(client.getIdentitytype().equals("admin")){
                return "Admin_main";
            }else {
                return "Client_lookShop";
            }
        }
        return "Login";
    }

    @RequestMapping("/logOut")
    public String logOut(){

        ClientThreadLocal.remove();
        return "redirect:/login";
    }

    /**
     * 去到403页面
     * @return
     */
    @RequestMapping("/four0Three")
    public String to403(){
        return "403";
    }

    /**
     * 去到404页面
     * @return
     */
    @RequestMapping("/cas404")
    public String to404(){
        return "404";
    }

    /**
     * @param loginName
     * @param loginPwd
     * @param request
     * @return
     * 用户登录校验
     */
    @RequestMapping(value = "/buyerCheck")
    @ResponseBody
    public String buyerCheck(String loginName, String loginPwd, HttpServletRequest request){
        System.out.println("登录校验");
        if(loginService.checkBuyerLogin(loginName, loginPwd,  request)){

            System.out.println("登录校验正确");

            return  "match";
        } else{
            System.out.println("登录校验错误");
            return "unmatch";
        }
    }

    @RequestMapping("/subLogin")
    @ResponseBody
    public String subLogin(String loginName, String loginPwd, boolean rememberMe,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);

        try{
            // 设置自动登录
            token.setRememberMe(rememberMe);
            subject.login(token);
        }catch (AuthenticationException e){
            return e.getMessage();
        }
        /*if (subject.hasRole("admin")) {
            return "有admin权限";
        }
        return "无admin权限";*/
        Client client = loginService.getUserByUserName(loginName);

        request.getSession().setAttribute("clientInfo", client);

        if( subject.hasRole("user")){
            return "user";
        }else {
            return "admin";
        }

    }

    /**
     * @param
     * @return
     * 进入用户主界面
     */
    @RequestMapping("/admin_main")
    public String welcomeToBase(HttpServletRequest request){

        Client client = ClientThreadLocal.get();

        // 获取用于id，作为sessionId的key
        String userId = client.getClientId().toString();
        // 获取sessionId作为value
        String sessionId = request.getRequestedSessionId();

        // 如果sessionIdMap中没有此用户的登录状态
        if(!SessionSave.getSessionIdMap().containsKey(userId)){
            SessionSave.getSessionIdMap().put(userId, sessionId);
        }else if(SessionSave.getSessionIdMap().containsKey(userId) && !sessionId.equals( SessionSave.getSessionIdMap().get(userId))){
            SessionSave.getSessionIdMap().remove(userId);
            SessionSave.getSessionIdMap().put(userId, sessionId);
        }
        System.out.println("欢迎用户" + client.getClientName() + "带着他/她的信息进入主界面了");

        return "Admin_main";
    }


    @RequiresRoles("admin")
    @RequestMapping("/testRole")
    @ResponseBody
    public String testRole(){
        System.out.println("有admin权限");
        return "testRole success";
    }

    @RequiresRoles("user")
    @RequestMapping("/testRole2")
    @ResponseBody
    public String testRole2(){
        System.out.println("有user权限");
        return "testRole success";
    }


}
