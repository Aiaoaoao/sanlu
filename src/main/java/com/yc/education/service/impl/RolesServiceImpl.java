package com.yc.education.service.impl;

import com.yc.education.mapper.RolesMapper;
import com.yc.education.model.Roles;
import com.yc.education.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RolesServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/28 9:56
 * @Version 1.0
 */
@Service("RolesService")
public class RolesServiceImpl extends BaseService<Roles> implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public List<Roles> findRoles() {
        try {
            return rolesMapper.findRoles();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Roles findRolesByRoleName(String roleName) {
        try {
            return rolesMapper.findRolesByRoleName(roleName);
        }catch (Exception e){
            return null;
        }
    }
}
