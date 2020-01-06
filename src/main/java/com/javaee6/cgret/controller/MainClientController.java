package com.javaee6.cgret.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaee6.cgret.service.IMainClientService;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.resource.spi.RetryableUnavailableException;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * 客户的主界面
 **/

@Controller
public class MainClientController {

    @Resource
    private IMainClientService iMainClientService;

    /**
     * 进入设置个人信息界面
     * @param session
     * @return
     */
    @RequestMapping("/personalSettings")
    public String setPersonInfo(HttpSession session){

        System.out.println((String)session.getAttribute("clientName") + "开始个人设置");
        if((String)session.getAttribute("clientName") == null) {
            return "Login";
        }
        System.out.println((String) session.getAttribute("clientName"));
        return "Client_personalSettings";
    }

    @RequestMapping("/setDefaultAddress")
    @ResponseBody
    public String setDefaultAddress(String detailAddress, String province, String city, String district , HttpSession session){

        iMainClientService.insertDefaultAddress(session, detailAddress, province, city, district );

        return "保存成功";

    }


    /**
     * 多此一举了，存在了session，就可以在客户端直接通过
     * var a = "${sessionScope.sessionKey}"的方式获取到session内数据了
     * 这个方法暂时先留着
     * 得到个人信息，然后去做相应的设置
     * @param session
     * @return
     */
    @RequestMapping("/getClientInfo")
    @ResponseBody
    public Object getPersonInfo(HttpSession session){
        String clientName = (String) session.getAttribute("clientName");
        Long clientId = (Long) session.getAttribute("clientId");
        String clientEmail = (String) session.getAttribute("clientEmail");
        Long clientTel = (Long) session.getAttribute("clientTel");

        System.out.println(clientEmail + clientName + clientId + clientTel);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", clientId);
        jsonObject.put("client_name", clientName);
        jsonObject.put("client_email", clientEmail);
        jsonObject.put("client_tel",clientTel);

        return jsonObject;
    }




}
