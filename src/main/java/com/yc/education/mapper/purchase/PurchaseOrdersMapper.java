package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO   采购订单
 *@Author QuZhangJing
 *@Date 17:37  2018/10/9
 *@Version 1.0
 */
public interface PurchaseOrdersMapper extends MyMapper<PurchaseOrders> {

    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders();


    List<PurchaseOrders> findPurchaseOrdersBySearchOrders(@Param("orders")String orders);


    List<PurchaseOrders> findPurchaseOrderByStatus(@Param("status")int status);
    //根据来源单号查询订单
    PurchaseOrders findPurchaseBySeeOrder(@Param("orders")String orders);
    //根据订单编号查询订单
    PurchaseOrders findPurchaseByOrders(@Param("orders")String orders);
    //交易单据
    List<PurchaseOrders> findPurchaseOrdersBySupplier(@Param("supplier")String supplier,@Param("startTime")String startTime,@Param("endTime")String endTime);

    /**
     * 查询没有审核的采购订单
     * @return
     */
    List<PurchaseOrders> findPurchaseOrderNotSh();


}