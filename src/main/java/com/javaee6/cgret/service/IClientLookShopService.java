package com.javaee6.cgret.service;

import org.springframework.ui.ModelMap;

public interface IClientLookShopService {

    /**
     * 用户看到的第一页商品(无搜索条件)
     * @param map
     */
    public void firstPageCgret (ModelMap map);
}
