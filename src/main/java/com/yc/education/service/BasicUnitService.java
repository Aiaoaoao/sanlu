package com.yc.education.service;

import com.yc.education.model.BasicUnit;

import java.util.List;

/**
 * @ClassName BasicUnitService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/20 17:46
 * @Version 1.0
 */
public interface BasicUnitService extends IService<BasicUnit> {

    /**
     * 查询所有基本单位
     * @return
     */
    List<BasicUnit> findBasicUnitAll();

}
