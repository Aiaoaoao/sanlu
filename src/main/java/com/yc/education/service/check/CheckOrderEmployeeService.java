package com.yc.education.service.check;

import com.yc.education.model.check.CheckOrderEmployee;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckOrderEmployeeService
 * @Description TODO 申请人
 * @Author QuZhangJing
 * @Date 2019/2/14 14:50
 * @Version 1.0
 */
public interface CheckOrderEmployeeService extends IService<CheckOrderEmployee> {

    /**
     * 根据请假表编号查询申请人
     * @param checkid
     * @return
     */
    List<CheckOrderEmployee> findCheckOrderEmployees(long checkid);


}
