package com.javaee6.cgret.dao;

import com.javaee6.cgret.model.Cgret;
import com.javaee6.cgret.model.CgretExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CgretMapper {
    long countByExample(CgretExample example);

    int deleteByExample(CgretExample example);

    int deleteByPrimaryKey(Integer cgretid);

    int insert(Cgret record);

    int insertSelective(Cgret record);

    List<Cgret> selectByExample(CgretExample example);

    Cgret selectByPrimaryKey(Integer cgretid);

    int updateByExampleSelective(@Param("record") Cgret record, @Param("example") CgretExample example);

    int updateByExample(@Param("record") Cgret record, @Param("example") CgretExample example);

    int updateByPrimaryKeySelective(Cgret record);

    int updateByPrimaryKey(Cgret record);
}