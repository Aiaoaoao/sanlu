package com.yc.education.mapper.basic;

import com.yc.education.model.basic.RegionBasic;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RegionBasicMapper extends MyMapper<RegionBasic> {



    /**
     * 查询所有区域设定
     * @return
     */
    List<RegionBasic> selectProductBasic();


    List<RegionBasic> selectProductBasicByOrderAndName(@Param("OrderAndName")String OrderAndName);


    /**
     * 根据编号查询区域设定
     * @param isnum
     * @return
     */
    RegionBasic selectProductBasicByIsnum(@Param("isnum") String isnum);




}