package com.javaee6.cgret.service;

import java.util.Map;

public interface IItemService {

    /**
     * 通过商品id获得详细信息
     * @param id
     * @return
     */
    Map<String, Object> getData(Long id);


}
