package com.yc.education.service.purchase;

import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName PurchaseProducctService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/10 16:49
 * @Version 1.0
 */
public interface PurchaseProductService extends IService<PurchaseProduct> {


    //根据采购订单编号查询采购产品
    List<PurchaseProduct> findPurchaseProduct(long purchaseid);

    //查询采购未入库产品
    List<PurchaseProduct> findPurchaseProductNotStock();

    List<PurchaseProduct> findPurchaseProductNotStock(int pageNum,int pageSize);

    //根据采购订单编号和产品订单查询采购产品
    PurchaseProduct findPurchaseProductByPronum(long purchaseid,String pronum);


    List<PurchaseProduct> findPurchaseProductNew(String sisnum,String eisnum,
                                                 String sproname,String eproname,
                                                 String stype,String etype,
                                                 String sdate,String edate);

    List<PurchaseProduct> findPurchaseProductNew(String sisnum,String eisnum,
                                                 String sproname,String eproname,
                                                 String stype,String etype,
                                                 String sdate,String edate,int pageNum,int pageSize);

    Double findPurchaseProductPriceSUM(long purchaseid);

    /**
     * 根据产品名称和筛选时间查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<PurchaseProduct> findPurchaseProductAndProductNameAndStartTimeAndEndTime(String productName,String startTime,String endTime);


    /**
     * 根据采购订单编号和采购订单产品编号查询采购产品
     * @param purchaseOrders
     * @param productOrders
     * @return
     */
    PurchaseProduct findPurchaseProductByOrdersAndProductName(String purchaseOrders,String productOrders);

}
