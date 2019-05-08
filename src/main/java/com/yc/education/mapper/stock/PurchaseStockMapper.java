package com.yc.education.mapper.stock;

import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseStockMapper extends MyMapper<PurchaseStock> {


    /**
     * @Description 查找未成本核算的采购入库单
     * @param orders 入库单号
     * @Author BlueSky
     * @Date 17:27 2019/5/4
     **/
    List<PurchaseStock> listPurchaseStock(@Param("orders") String orders);

    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询所有采购入库单
    List<PurchaseStock> findPurchaseStock();

    //供应商交易单据
    List<PurchaseStock> findPurchaseStockBySupplier(@Param("supplier") String supplier, @Param("startTime")String startTime, @Param("endTime")String endTime);

    /**
     * 根据入库单号查询
     * @param orderno 入库单号
     * @return
     */
    PurchaseStock findPurchaseStockByNo(@Param("orderno") String orderno);


    List<PurchaseStock> findPurchaseStockByOrders(@Param("orders") String orders);

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
    List<PurchaseStock> listPurchaseStockByPayment(@Param("supplier")String supplier, @Param("supplierEnd")String supplierEnd, @Param("invoice")String invoice, @Param("invoiceEnd")String invoiceEnd, @Param("invoiceDate")String invoiceDate, @Param("invoiceDateEnd")String invoiceDateEnd);

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
    PurchaseStock findPurchanseStockBoxNum(@Param("boxNum")String boxNum);


}