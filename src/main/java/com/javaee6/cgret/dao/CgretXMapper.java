package com.javaee6.cgret.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface CgretXMapper {

    @Update(value = {"update cgret set salenum = salenum + #{buynum}, leftnum = leftnum - #{buynum}, version = version + 1 " +
            "where productid = #{productid} and version = #{version} "})
    int updateStockByProductId(@Param("productid") Integer productid,
                                @Param("buynum") Integer buyNum,
                                @Param("version") Integer version);

    @Update(value = {"update cgret set salenum = salenum + #{buynum}, leftnum = leftnum - #{buynum}, version = version + 1 " +
            "where productid = #{productid} and version = 1 "})
    int testRollBack(@Param("productid") Integer productid,
                               @Param("buynum") Integer buyNum,
                               @Param("version") Integer version);

    @Update(value = {"update cgret set version = #{version} where version != 1"})
    int updateTest(@Param("version") Integer version);

    @Update(value = {"update cgret set leftnum = leftnum -100 where productid = #{productid}"})
    int updateTest2(@Param("productid") Integer productid);
}
