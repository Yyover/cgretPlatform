package com.javaee6.cgret.service;

import com.javaee6.cgret.model.Client;

import javax.servlet.http.HttpSession;

public interface IMainClientService {

    Client getById(Long id);

    /**
     * 在用户设置界面写入默认地址
     */
    void insertDefaultAddress(HttpSession session, String detailAddress, String province, String city, String district );

}
