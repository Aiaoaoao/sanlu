package com.yc.education.mapper;

import com.yc.education.model.RolePermissions;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionsMapper extends MyMapper<RolePermissions> {


    /**
     *
     * @param role
     * @return
     */
    List<String> selectRolePermissions(@Param("role")String role);


    /**
     * 根据用户查询权限
     * @param username
     * @return
     */
    List<String> selectRolePermissionsByEmployee(@Param("username")String username);


    /**
     *
     * @param roleid
     * @param permissionscodes
     * @return
     */
    RolePermissions selectRolePermissionsByIdAndRoleid(@Param("roleid")long roleid,@Param("permissionscodes")long permissionscodes);


    RolePermissions selectRolePermissionsByUidAndPermiss(@Param("uid")long uid,@Param("permissionscodes")long permissionscodes);



}