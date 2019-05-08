package com.yc.education.service;

import com.yc.education.model.RoleEmployee;

import java.util.List;

/**
 * @ClassName RoleEmployeeService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/28 9:54
 * @Version 1.0
 */
public interface RoleEmployeeService extends IService<RoleEmployee> {

    List<RoleEmployee> findRoleEmployeeByIdnum(String idnum);

    //根据角色名称查询权限组下的用户
    List<RoleEmployee> findRoleEmployeeByRoleName(String roleName);

}
