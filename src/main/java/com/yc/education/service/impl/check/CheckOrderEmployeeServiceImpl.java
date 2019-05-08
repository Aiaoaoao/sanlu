package com.yc.education.service.impl.check;

import com.yc.education.mapper.check.CheckOrderEmployeeMapper;
import com.yc.education.model.check.CheckOrderEmployee;
import com.yc.education.service.check.CheckOrderEmployeeService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckOrderEmployeeServiceImpl
 * @Description TODO 考勤管理（请假/加班/出差） 申请人
 * @Author QuZhangJing
 * @Date 2019/2/14 14:51
 * @Version 1.0
 */
@Service("CheckOrderEmployeeService")
public class CheckOrderEmployeeServiceImpl extends BaseService<CheckOrderEmployee> implements CheckOrderEmployeeService  {

    @Autowired
    private CheckOrderEmployeeMapper checkOrderEmployeeMapper;

    @Override
    public List<CheckOrderEmployee> findCheckOrderEmployees(long checkid) {
        try {
            return checkOrderEmployeeMapper.findCheckOrderEmployees(checkid);
        }catch (Exception e){
            return null;
        }
    }
}
