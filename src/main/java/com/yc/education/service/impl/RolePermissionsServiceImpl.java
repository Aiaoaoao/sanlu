package com.yc.education.service.impl;

import com.yc.education.mapper.RolePermissionsMapper;
import com.yc.education.model.RolePermissions;
import com.yc.education.service.RolePermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RolePermissionsServiceImpl
 * @Description TODO 角色 - 权限
 * @Author QuZhangJing
 * @Date 2019/3/1 16:01
 * @Version 1.0
 */
@Service("RolePermissionsService")
public class RolePermissionsServiceImpl extends BaseService<RolePermissions> implements RolePermissionsService {

    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;


    @Override
    public List<String> selectRolePermissions(String role) {
        try {
            return rolePermissionsMapper.selectRolePermissions(role);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> selectRolePermissionsByEmployee(String username) {
        try {
            return rolePermissionsMapper.selectRolePermissionsByEmployee(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RolePermissions selectRolePermissionsByIdAndRoleid(long roleid, long permissionscodes) {
        try {
            return rolePermissionsMapper.selectRolePermissionsByIdAndRoleid(roleid,permissionscodes);
        } catch (Exception e) {
            return null ;
        }
    }

    @Override
    public RolePermissions selectRolePermissionsByUidAndPermiss(long uid, long permissionscodes) {
        try {
            return rolePermissionsMapper.selectRolePermissionsByUidAndPermiss(uid, permissionscodes);
        } catch (Exception e) {
            return null;
        }
    }
}
