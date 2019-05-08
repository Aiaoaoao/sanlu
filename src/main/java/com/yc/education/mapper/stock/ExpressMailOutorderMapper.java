package com.yc.education.mapper.stock;

import com.yc.education.model.stock.ExpressMailOutorder;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpressMailOutorderMapper extends MyMapper<ExpressMailOutorder> {

    List<ExpressMailOutorder> findExpressMailOutorders(@Param("mailid") long mailid);

}