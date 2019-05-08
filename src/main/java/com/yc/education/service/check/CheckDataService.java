package com.yc.education.service.check;

import com.yc.education.model.check.CheckData;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckDataService
 * @Description TODO 考勤资料结转
 * @Author QuZhangJing
 * @Date 2019/2/20 16:30
 * @Version 1.0
 */
public interface CheckDataService extends IService<CheckData> {


    //查询资料结转
    List<CheckData> findCheckData();


    List<CheckData> findCheckData(int pageNum,int pageSize);





}
