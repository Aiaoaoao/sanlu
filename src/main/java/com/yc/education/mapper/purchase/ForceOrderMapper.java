package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.ForceOrder;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO  强制结案
 *@Author QuZhangJing
 *@Date 14:55  2018/10/15
 *@Version 1.0
 */
public interface ForceOrderMapper extends MyMapper<ForceOrder> {


    //查询强制结案
    List<ForceOrder> findForceOrder();
    //根据采购订单查询该订单下强制结案的订单
    List<ForceOrder> findForceOrderByPurchaseOrder(@Param("purchaseOrder")String purchaseOrder);


    //交易单据
    List<ForceOrder> findForceOrderBySupplier(@Param("supplier")String supplier,@Param("startTime")String startTime,@Param("endTime")String endTime);


}