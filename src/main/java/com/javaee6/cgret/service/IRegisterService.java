package com.javaee6.cgret.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;


public interface IRegisterService {

    String checkEmail(String reg_email);

    String checkUserName(String reg_name);

    String checkTel(String reg_tel);

    boolean checkPwd(String reg_pwd);

    void sendEmail(String reg_email, String reg_name) throws AddressException, MessagingException, UnsupportedEncodingException;

    boolean checkCode(String userName, String actiCode);

    void updateData(String reg_name, String reg_pwd, String reg_email, String reg_tel);

    void createDate(String reg_name);

    String createActiCode(String reg_name);

    boolean checkActiCode(String reg_name);

    boolean isName(String reg_name);

    boolean isTel(String reg_tel);

    boolean isEmail(String reg_email);

    String getEmail();
}
