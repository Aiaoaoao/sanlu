package com.yc.education.service.impl.check;

import com.yc.education.mapper.check.CheckDataLeaveearlyMapper;
import com.yc.education.model.check.CheckDataLeaveearly;
import com.yc.education.service.check.CheckDataLeaveearlyService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckDataLeaveearlyServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/22 11:27
 * @Version 1.0
 */
@Service("CheckDataLeaveearlyService")
public class CheckDataLeaveearlyServiceImpl extends BaseService<CheckDataLeaveearly> implements CheckDataLeaveearlyService {

    @Autowired
    private CheckDataLeaveearlyMapper checkDataLeaveearlyMapper;

    @Override
    public List<CheckDataLeaveearly> findCheckDataLeaveearly(long checkDataid) {
        try {
            return checkDataLeaveearlyMapper.findCheckDataLeaveearly(checkDataid);
        }catch (Exception e){
            return null;
        }
    }


}
