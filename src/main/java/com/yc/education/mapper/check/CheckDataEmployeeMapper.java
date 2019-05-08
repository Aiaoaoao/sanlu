package com.yc.education.mapper.check;

import com.yc.education.model.check.CheckDataEmployee;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface CheckDataEmployeeMapper extends MyMapper<CheckDataEmployee> {


    List<CheckDataEmployee> findCheckDataEmployee(long checkDataid);


}