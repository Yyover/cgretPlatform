package com.javaee6.cgret.model;

import java.util.Date;

public class Cgret {
    private Integer productid;

    private String productname;

    private Integer brandid;

    private Integer branddetailid;

    private String typeid;

    private Integer jiaoyouid;

    private String barcode;

    private Float price;

    private String imageurl;

    private Integer leftnum;

    private String status;

    private Date updateTime;

    private Integer deletestatus;

    private Integer version;

    private Integer salenum;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getBranddetailid() {
        return branddetailid;
    }

    public void setBranddetailid(Integer branddetailid) {
        this.branddetailid = branddetailid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public Integer getJiaoyouid() {
        return jiaoyouid;
    }

    public void setJiaoyouid(Integer jiaoyouid) {
        this.jiaoyouid = jiaoyouid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getLeftnum() {
        return leftnum;
    }

    public void setLeftnum(Integer leftnum) {
        this.leftnum = leftnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Integer deletestatus) {
        this.deletestatus = deletestatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSalenum() {
        return salenum;
    }

    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }
}