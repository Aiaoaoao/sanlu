package com.yc.education.mapper.check;

import com.yc.education.model.check.CheckOrderEmployee;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO  申请人
 *@Author QuZhangJing
 *@Date 14:24  2019/2/14
 *@Version 1.0
 */
public interface CheckOrderEmployeeMapper extends MyMapper<CheckOrderEmployee> {

    /**
     * 根据请假表编号查询申请人
     * @param checkid
     * @return
     */
    List<CheckOrderEmployee> findCheckOrderEmployees(@Param("checkid")long checkid);

}