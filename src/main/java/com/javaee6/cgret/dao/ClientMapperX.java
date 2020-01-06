package com.javaee6.cgret.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ClientMapperX {

    /**
     * 用户数量
     * @return
     */
    //获取Client的数量
    @Select(value = {"select count(*) from client "})
    Long numOfClient();

    /**
     * 更新用户信息中的地址
     * @param defAddId
     * @param defAddress
     * @param clientId
     */
    @Update(value = {"update client set def_address_id = #{defAddId}, def_address = #{defAddress} " +
            "where client_id = #{clientId} "})
    void updateDefAddress(@Param("defAddId") Long defAddId,
                          @Param("defAddress") String defAddress, @Param("clientId") Long clientId);
}
