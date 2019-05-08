package com.yc.education.mapper;

import com.yc.education.model.BasicUnit;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface BasicUnitMapper extends MyMapper<BasicUnit> {


    /**
     * 查询所有基本单位
     * @return
     */
    List<BasicUnit> findBasicUnitAll();




}