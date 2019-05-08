package com.yc.education.service.check;

import com.yc.education.model.check.Timecard;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName TimecardService
 * @Description TODO  原始考勤资料
 * @Author QuZhangJing
 * @Date 2019/2/18 14:02
 * @Version 1.0
 */
public interface TimecardService extends IService<Timecard> {



    /**
     * 根据员工编号和考勤日期查询考勤员工
     * @param startOrder 开始员工编号
     * @param endOrder   结束员工编号
     * @param startTime  开始考勤时间
     * @param endTime    结束考勤时间
     * @return
     */
    List<Timecard> findTimecardByUserOrderAndTime(int status,String startOrder , String endOrder, String startTime , String endTime);

    List<Timecard> findTimecardByUserOrderAndTime(int status,String startOrder , String endOrder, String startTime , String endTime,int pageNum,int pageSize);


}
