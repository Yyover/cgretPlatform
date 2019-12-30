package com.javaee6.cgret.dao;

import org.apache.ibatis.annotations.Select;

public interface ClientMapperX {

    //获取Client的数量
    @Select(value = {"select count(*) from client "})
    int numOfClient();
}
