package com.yc.education.service.impl.check;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.check.TimecardMapper;
import com.yc.education.model.check.Timecard;
import com.yc.education.service.check.TimecardService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TimecardServiceImpl
 * @Description TODO  原始考勤资料
 * @Author QuZhangJing
 * @Date 2019/2/18 14:04
 * @Version 1.0
 */
@Service("TimecardService")
public class TimecardServiceImpl extends BaseService<Timecard> implements TimecardService {


    @Autowired
    private TimecardMapper timecardMapper;


    @Override
    public List<Timecard> findTimecardByUserOrderAndTime(int status,String startOrder, String endOrder, String startTime, String endTime) {
        try {
            return timecardMapper.findTimecardByUserOrderAndTime(status,startOrder, endOrder, startTime, endTime);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Timecard> findTimecardByUserOrderAndTime(int status,String startOrder, String endOrder, String startTime, String endTime, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return timecardMapper.findTimecardByUserOrderAndTime(status,startOrder, endOrder, startTime, endTime);
        }catch (Exception e){
            return null;
        }
    }


}
