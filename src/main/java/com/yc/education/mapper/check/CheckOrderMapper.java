package com.yc.education.mapper.check;

import com.yc.education.model.check.CheckOrder;
import com.yc.education.util.MyMapper;

import java.util.List;

/**
 *@Description TODO  请假/加班/出差申请单
 *@Author QuZhangJing
 *@Date 16:30  2018/11/22
 *@Version 1.0
 */
public interface CheckOrderMapper extends MyMapper<CheckOrder> {



    String selectMaxIdnum();


    //查询所有请假/加班/出差申请单
    List<CheckOrder> findCheckOrder();


}