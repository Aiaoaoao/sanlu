package com.yc.education.service.basic;

import com.yc.education.model.basic.RegionEmployee;
import com.yc.education.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName RegionEmployeeService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/6 19:59
 * @Version 1.0
 */
public interface RegionEmployeeService extends IService<RegionEmployee> {


    /**
     * 根据区域编号查询区域下的员工
     * @param regionid
     * @return
     */
    List<RegionEmployee> selectRegionEmployeeByRegionid(@Param("regionid")long regionid);



}
