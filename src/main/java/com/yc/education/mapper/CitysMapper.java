package com.yc.education.mapper;

import com.yc.education.model.Citys;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *@Description TODO 省、市、区
 *@Author QuZhangJing
 *@Date 11:10  2018/11/14
 *@Version 1.0
 */
public interface CitysMapper extends MyMapper<Citys> {


    /**
     * 根据父级Code查询    省的 father_code == 0000
     * @param fatherCode
     * @return
     */
    List<Citys> selectCitysByFatherCode(@Param("fatherCode")String fatherCode);

    /**
     * 根据名称查询Code
     * @param name
     * @return
     */
    Citys selectCitysByAddrName(@Param("name")String name,@Param("type")String type);



}