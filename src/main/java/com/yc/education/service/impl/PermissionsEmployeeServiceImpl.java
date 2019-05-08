package com.yc.education.service.impl;

import com.yc.education.mapper.PermissionsEmployeeMapper;
import com.yc.education.model.PermissionsEmployee;
import com.yc.education.service.PermissionsEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PermissionsEmployeeServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/1 10:22
 * @Version 1.0
 */
@Service("PermissionsEmployeeService")
public class PermissionsEmployeeServiceImpl extends BaseService<PermissionsEmployee> implements PermissionsEmployeeService {

    @Autowired
    private PermissionsEmployeeMapper permissionsEmployeeMapper;

    @Override
    public List<PermissionsEmployee> findPermissionsEmployee() {
        try {
            return permissionsEmployeeMapper.findPermissionsEmployee();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PermissionsEmployee findPermissionsEmployeeByUname(String uname) {
        try {
            return permissionsEmployeeMapper.findPermissionsEmployeeByUname(uname);
        } catch (Exception e) {
            return null;
        }
    }
}
