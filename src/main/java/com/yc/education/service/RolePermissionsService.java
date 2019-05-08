package com.yc.education.service;

import com.yc.education.model.RolePermissions;

import java.util.List;

/**
 * @ClassName RolePermissionsService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/1 16:00
 * @Version 1.0
 */
public interface RolePermissionsService extends IService<RolePermissions> {


    /**
     * 根据用户角色查询权限
     * @param role
     * @return
     */
    List<String> selectRolePermissions(String role);


    /**
     * 根据用户查询权限
     * @param username
     * @return
     */
    List<String> selectRolePermissionsByEmployee(String username);


    /**
     *
     * @param roleid
     * @param permissionscodes
     * @return
     */
    RolePermissions selectRolePermissionsByIdAndRoleid(long roleid,long permissionscodes);


    RolePermissions selectRolePermissionsByUidAndPermiss(long uid,long permissionscodes);

}
