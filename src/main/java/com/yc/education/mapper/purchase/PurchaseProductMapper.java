package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseProductMapper extends MyMapper<PurchaseProduct> {


    //根据采购订单编号查询采购产品
    List<PurchaseProduct> findPurchaseProduct(@Param("purchaseid")long purchaseid);

    //查询采购未入库产品
    List<PurchaseProduct> findPurchaseProductNotStock();

    //根据采购订单编号和产品订单查询采购产品
    PurchaseProduct findPurchaseProductByPronum(@Param("purchaseid")long purchaseid,@Param("pronum")String pronum);

    /**
     * 根据采购订单编号和采购订单产品编号查询采购产品
     * @param purchaseOrders
     * @param productOrders
     * @return
     */
    PurchaseProduct findPurchaseProductByOrdersAndProductName(@Param("purchaseOrders")String purchaseOrders,@Param("productOrders")String productOrders);


    List<PurchaseProduct> findPurchaseProductNew(@Param("sisnum")String sisnum,@Param("eisnum")String eisnum,
                                                 @Param("sproname")String sproname,@Param("eproname")String eproname,
                                                 @Param("stype")String stype,@Param("etype")String etype,
                                                 @Param("sdate")String sdate,@Param("edate")String edate);


    Double findPurchaseProductPriceSUM(@Param("purchaseid")long purchaseid);

    /**
     * 根据产品名称和筛选时间查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<PurchaseProduct> findPurchaseProductAndProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);


}