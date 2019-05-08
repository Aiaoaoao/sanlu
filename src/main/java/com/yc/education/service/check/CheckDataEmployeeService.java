package com.yc.education.service.check;

import com.yc.education.model.check.CheckDataEmployee;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckDataEmployeeService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/22 11:16
 * @Version 1.0
 */
public interface CheckDataEmployeeService extends IService<CheckDataEmployee> {

    List<CheckDataEmployee> findCheckDataEmployee(long checkDataid);

}
