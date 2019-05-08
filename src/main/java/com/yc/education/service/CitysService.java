package com.yc.education.service;

import com.yc.education.model.Citys;

import java.util.List;

/**
 * @ClassName CitysService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/14 11:19
 * @Version 1.0
 */
public interface CitysService extends IService<Citys> {

    /**
     * 根据父级Code查询    省的 father_code == 0000
     * @param fatherCode
     * @return
     */
    List<Citys> selectCitysByFatherCode(String fatherCode);


    /**
     * 根据名称查询Code
     * @param name
     * @return
     */
    Citys selectCitysByAddrName(String name,String type);


}
