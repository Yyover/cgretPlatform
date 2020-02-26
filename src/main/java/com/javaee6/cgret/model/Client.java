package com.javaee6.cgret.model;

import java.util.Date;

public class Client {
    private Long clientId;

    private String clientName;

    private String password;

    private String email;

    private Long telephone;

    private Date createTime;

    private String articleCode;

    private String haedUrl;

    private Long defAddressId;

    private String defAddress;

    private String identitytype;

    private boolean rememberMe;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getHaedUrl() {
        return haedUrl;
    }

    public void setHaedUrl(String haedUrl) {
        this.haedUrl = haedUrl;
    }

    public Long getDefAddressId() {
        return defAddressId;
    }

    public void setDefAddressId(Long defAddressId) {
        this.defAddressId = defAddressId;
    }

    public String getDefAddress() {
        return defAddress;
    }

    public void setDefAddress(String defAddress) {
        this.defAddress = defAddress;
    }

    public String getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(String identitytype) {
        this.identitytype = identitytype;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}