package com.yc.education.mapper;

import com.yc.education.model.RoleEmployee;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleEmployeeMapper extends MyMapper<RoleEmployee> {

    List<RoleEmployee> findRoleEmployeeByIdnum(@Param("idnum")String idnum);

    //根据角色名称查询权限组下的用户
    List<RoleEmployee> findRoleEmployeeByRoleName(@Param("roleName")String roleName);


}