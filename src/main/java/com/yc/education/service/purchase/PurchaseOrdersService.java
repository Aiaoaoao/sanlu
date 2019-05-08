package com.yc.education.service.purchase;

import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName PurchaseOrdersService
 * @Description TODO 采购订单
 * @Author QuZhangJing
 * @Date 2018/10/9 17:42
 * @Version 1.0
 */
public interface PurchaseOrdersService extends IService<PurchaseOrders> {


    /**
     * 查询最大编号
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders();

    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders(int pageNum,int pageSize);


    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders(String orders);

    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders(String orders,int pageNum,int pageSize);


    //根据编号查询订单
    PurchaseOrders findPurchaseBySeeOrder(String orders);

    //交易单据
    List<PurchaseOrders> findPurchaseOrdersBySupplier(String supplier,String startTime,String endTime);

    List<PurchaseOrders> findPurchaseOrdersBySupplier(String supplier,String startTime,String endTime,int pageNum,int pageSize);


    //查询采购订单
    List<PurchaseOrders> findPurchaseOrders(int status,int pageNum,int pageSize);

    //根据订单编号查询订单
    PurchaseOrders findPurchaseByOrders(String orders);

    /**
     * 查询没有审核的采购订单
     * @return
     */
    List<PurchaseOrders> findPurchaseOrderNotSh();

}
