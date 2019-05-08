package com.yc.education.service.impl.check;

import com.yc.education.mapper.check.CheckDataEmployeeMapper;
import com.yc.education.model.check.CheckDataEmployee;
import com.yc.education.service.check.CheckDataEmployeeService;
import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckDataEmployeeServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/2/22 11:20
 * @Version 1.0
 */
@Service("CheckDataEmployeeService")
public class CheckDataEmployeeServiceImpl extends BaseService<CheckDataEmployee> implements CheckDataEmployeeService {

    @Autowired
    private CheckDataEmployeeMapper checkDataEmployeeMapper;

    @Override
    public List<CheckDataEmployee> findCheckDataEmployee(long checkDataid) {
        try {
            return checkDataEmployeeMapper.findCheckDataEmployee(checkDataid);
        }catch (Exception e){
            return null;
        }
    }

}
