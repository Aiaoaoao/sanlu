package com.yc.education.service;

import com.yc.education.model.PermissionsEmployee;

import java.util.List;

/**
 * @ClassName PermissionsEmployeeService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/3/1 10:22
 * @Version 1.0
 */
public interface PermissionsEmployeeService extends IService<PermissionsEmployee> {

    List<PermissionsEmployee> findPermissionsEmployee();


    PermissionsEmployee findPermissionsEmployeeByUname(String uname);

}
