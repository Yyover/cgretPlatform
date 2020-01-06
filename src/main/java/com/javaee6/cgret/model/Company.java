package com.javaee6.cgret.model;

/**
 * @author hy
 * 这个类暂时报废
 */
@Deprecated
public class Company {
    private Integer companyId;

    private String companyName;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}