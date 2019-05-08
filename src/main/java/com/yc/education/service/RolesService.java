package com.yc.education.service;

import com.yc.education.model.Roles;

import java.util.List;

/**
 * @ClassName RolesService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/28 9:54
 * @Version 1.0
 */
public interface RolesService extends  IService<Roles> {

    List<Roles> findRoles();

    Roles findRolesByRoleName(String roleName);

}
