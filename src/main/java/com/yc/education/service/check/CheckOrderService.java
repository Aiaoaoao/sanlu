package com.yc.education.service.check;

import com.yc.education.model.check.CheckOrder;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckOrderService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/22 16:42
 * @Version 1.0
 */
public interface CheckOrderService extends IService<CheckOrder> {

    //查询订单号
    String selectMaxIdnum();


    //查询所有请假/加班/出差申请单
    List<CheckOrder> findCheckOrder();

    List<CheckOrder> findCheckOrder(int pageNum,int pageSize);

}
