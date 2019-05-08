package com.yc.education.mapper.basic;

import com.yc.education.model.basic.RegionEmployee;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionEmployeeMapper extends MyMapper<RegionEmployee> {


    /**
     * 根据区域编号查询区域下的员工
     * @param regionid
     * @return
     */
    List<RegionEmployee> selectRegionEmployeeByRegionid(@Param("regionid")long regionid);



}