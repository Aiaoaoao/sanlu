package com.yc.education.mapper.check;

import com.yc.education.model.check.CheckData;
import com.yc.education.util.MyMapper;

import java.util.List;

public interface CheckDataMapper extends MyMapper<CheckData> {

    //查询资料结转
    List<CheckData> findCheckData();

}