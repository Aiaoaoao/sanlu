package com.yc.education.mapper;

import com.yc.education.model.Roles;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolesMapper extends MyMapper<Roles> {

    List<Roles> findRoles();

    Roles findRolesByRoleName(@Param("roleName")String roleName);

}