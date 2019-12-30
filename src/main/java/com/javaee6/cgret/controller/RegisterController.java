package com.javaee6.cgret.controller;

import com.javaee6.cgret.service.IRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * 客户注册的Controller
 * @author Hyao
 */
@Controller
@RequestMapping
public class RegisterController {

    @Resource
    public IRegisterService registerService;

    /**
     * @param regName
     * @param regPwd
     * @param confPwd
     * @param regEmail
     * @param regTel
     * @param session
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * 注册检查
     */
    @RequestMapping(value = "Register",produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String register(String regName, String regPwd, String confPwd,
                           String regEmail, String regTel, HttpSession session) throws MessagingException, UnsupportedEncodingException {

        System.out.println("开始注册检查");
        System.out.println(regName + "," + regPwd);

        // 用户名注册的几种状态
        String aboutName = registerService.checkUserName(regName);
        System.out.println(aboutName);

        String aboutEmail = registerService.checkEmail(regEmail);
        System.out.println(aboutEmail);

        String aboutTel = null;

        // 创建发送邮件的线程
        SendMail sendMail = new SendMail(regEmail, regName);
        String nameRight = "用户名正确";
        
        if(aboutName.equals(nameRight)){

            if(registerService.checkPwd(regPwd)){

                String emailRight = "邮箱正确";
                if(aboutEmail.equals(emailRight)){

                    String telRight = "手机号正确";
                    aboutTel = registerService.checkTel(regTel);
                    if(aboutTel.equals(telRight)){

                        // 调用函数将注册数据写入数据库,测试注册时请注释以下两行
                        registerService.updateData(regName, regPwd, regEmail, regTel);

                        registerService.createDate(regName);
                        // 将用户名和邮箱作为发送邮件时的参数存入session
                        session.setAttribute("regEmail", regEmail);
                        session.setAttribute("regName", regName);
                        return "注册成功";
                    }
                    return aboutTel;
                }
                return aboutEmail;
            }
            return "密码长度需在8-20以内";
        }
        System.out.println(aboutName);
        return aboutName;
    }

    /**
     * @return
     * 注册成功页面，可发送邮件
     */
    @RequestMapping("resendMail")
    public String resendMail(){
        return "Resgister_resendMail";
    }

    /**
     * @param regEmail
     * @param regName
     * @return
     * 异步发送邮件
     */
    @RequestMapping(value = "reSends", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String reSend(String regEmail, String regName){
        System.out.println(regName+regEmail+"===================");
        if(registerService.checkActiCode(regName)){
            SendMail sendMail = new SendMail(regEmail, regName);
            sendMail.start();
            return "发送成功，请注意查收！";
        }
        return "账号已激活，不再发送激活邮件！";
    }


    /**
     * 由于发送邮件需要一定时间，此线程用于注册时的发送邮件以增加用户体验
     */
    class SendMail extends Thread{

        private String regEmail;
        private String regName;
        public SendMail(String regEmail, String regName){
            this.regEmail = regEmail;
            this.regName = regName;
    }

    @Override
    public void run() {
        try {
            registerService.sendEmail(regEmail, regName);
        }

        catch (MessagingException m){
        }
        catch (UnsupportedEncodingException u){
        }
        catch (Exception e){
        }
        }
    }

    /**
     * @param username
     * @param actiCode
     * @return
     * 用户点击验证链接后，检查actiCode激活码
     */
    @RequestMapping("checkCode")
    public String checkActiCode(String username, String actiCode){
        if(registerService.checkCode(username, actiCode)){
            return "Resgister_checkCodeSuccess";
        }
           
        return "Resgister_checkCodeFail";
    }

}
