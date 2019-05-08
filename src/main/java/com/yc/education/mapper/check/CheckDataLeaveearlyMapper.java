package com.yc.education.mapper.check;

import com.yc.education.model.check.CheckDataLeaveearly;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface CheckDataLeaveearlyMapper extends MyMapper<CheckDataLeaveearly> {


    List<CheckDataLeaveearly> findCheckDataLeaveearly(long checkDataid);

}