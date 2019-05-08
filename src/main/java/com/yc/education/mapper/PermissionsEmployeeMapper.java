package com.yc.education.mapper;

import com.yc.education.model.PermissionsEmployee;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *@Description TODO 权限员工
 *@Author QuZhangJing
 *@Date 10:12  2019/3/1
 *@Version 1.0
 */
public interface PermissionsEmployeeMapper extends MyMapper<PermissionsEmployee> {


    List<PermissionsEmployee> findPermissionsEmployee();

    PermissionsEmployee findPermissionsEmployeeByUname(@Param("uname")String uname);


}