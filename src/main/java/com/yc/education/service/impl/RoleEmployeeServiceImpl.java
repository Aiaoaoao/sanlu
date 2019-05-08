package com.yc.education.service.impl;

import com.yc.education.mapper.RoleEmployeeMapper;
import com.yc.education.model.RoleEmployee;
import com.yc.education.service.RoleEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleEmployeeServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/28 9:58
 * @Version 1.0
 */
@Service("RoleEmployeeService")
public class RoleEmployeeServiceImpl extends BaseService<RoleEmployee> implements RoleEmployeeService {

    @Autowired
    private RoleEmployeeMapper roleEmployeeMapper;


    @Override
    public List<RoleEmployee> findRoleEmployeeByIdnum(String idnum) {
        try {
            return roleEmployeeMapper.findRoleEmployeeByIdnum(idnum);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<RoleEmployee> findRoleEmployeeByRoleName(String roleName) {
        try {
            return roleEmployeeMapper.findRoleEmployeeByRoleName(roleName);
        } catch (Exception e) {
            return null;
        }
    }
}
