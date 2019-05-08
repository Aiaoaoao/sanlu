package com.yc.education.service.check;

import com.yc.education.model.check.CheckDataLeaveearly;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckDataLeaveearlyService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/22 11:26
 * @Version 1.0
 */
public interface CheckDataLeaveearlyService extends IService<CheckDataLeaveearly> {

    List<CheckDataLeaveearly> findCheckDataLeaveearly(long checkDataid);

}
