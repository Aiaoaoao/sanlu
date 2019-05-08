package com.yc.education.service.stock;

import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName PurchaseStockService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/24 19:47
 * @Version 1.0
 */
public interface PurchaseStockService extends IService<PurchaseStock>{

    /**
     * @Description 查找未成本核算的采购入库单
     * @Author BlueSky
     * @Date 17:27 2019/5/4
     **/
    List<PurchaseStock> listPurchaseStock(String orders,int pageNum,int pageSize);

    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);

    //查询所有采购入库单
    List<PurchaseStock> findPurchaseStock();


    List<PurchaseStock> findPurchaseStock(int pageNum,int pageSize);


    List<PurchaseStock> findPurchaseStock(String orders,int pageNum,int pageSize);


    //供应商交易单据
    List<PurchaseStock> findPurchaseStockBySupplier( String supplier,String startTime,String endTime);

    List<PurchaseStock> findPurchaseStockBySupplier( String supplier,String startTime,String endTime,int pageNum,int pageSize);

    /**
     * 根据入库单号查询
     * @param orderno 入库单号
     * @return
     */
    PurchaseStock findPurchaseStockByNo( String orderno);

    /**
     * 应付账款条件查询
     * @param supplier 供应商编号
     * @param supplierEnd 供应商编号
     * @param invoice 进项发票号
     * @param invoiceEnd 进项发票号
     * @param invoiceDate 发票日期
     * @param invoiceDateEnd 发票日期
     * @return
     */
    List<PurchaseStock> listPurchaseStockByPayment(String supplier,String supplierEnd, String invoice,String invoiceEnd, String invoiceDate, String invoiceDateEnd);



    /**
     * 查询为审核的单据
     * @return
     */
    List<PurchaseStock> findPurchanseStockNotSh();


    /**
     * 根据在途库存编号查询入库单
     * @param boxNum
     * @return
     */
    PurchaseStock findPurchanseStockBoxNum(String boxNum);

}
