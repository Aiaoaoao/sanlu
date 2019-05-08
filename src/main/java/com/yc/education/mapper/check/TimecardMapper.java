package com.yc.education.mapper.check;

import com.yc.education.model.check.Timecard;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 原始考勤资料
 *@Author QuZhangJing
 *@Date 11:43  2019/2/18
 *@Version 1.0
 */
public interface TimecardMapper extends MyMapper<Timecard> {

    /**
     * 根据员工编号和考勤日期查询考勤员工
     * @param startOrder 开始员工编号
     * @param endOrder   结束员工编号
     * @param startTime  开始考勤时间
     * @param endTime    结束考勤时间
     * @return
     */
    List<Timecard> findTimecardByUserOrderAndTime(@Param("status")int status,@Param("startOrder")String startOrder,@Param("endOrder")String endOrder,@Param("startTime")String startTime ,@Param("endTime")String endTime);


}