package com.yc.education.mapper.purchase;

import com.yc.education.model.purchase.PurchaseInvoice;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 应付账款（采购账款）
 *@Author QuZhangJing
 *@Date 14:47  2018/12/10
 *@Version 1.0
 */
public interface PurchaseInvoiceMapper extends MyMapper<PurchaseInvoice> {


    /**
     *  查询应付账款
     *
     * @param supplierOrderStart  供应编号开始
     * @param supplierOrderEnd   结束
     * @param invoiceNumberStart  进项发票号开始
     * @param invoiceNumberEnd  结束
     * @param invoiceDateStart  发票日期开始
     * @param invoiceDateEnd   结束
     * @Author QuZhangJing
     * @Date 14:47  2018/12/10
     * @Version 1.0
     * @return
     */
    List<PurchaseInvoice> findPurchaseInvoice(@Param("supplierOrderStart")String supplierOrderStart,
                                              @Param("supplierOrderEnd")String supplierOrderEnd,
                                              @Param("invoiceNumberStart")String invoiceNumberStart,
                                              @Param("invoiceNumberEnd")String invoiceNumberEnd,
                                              @Param("invoiceDateStart")String invoiceDateStart,
                                              @Param("invoiceDateEnd")String invoiceDateEnd
                                              );


    //进项发票号
    List<PurchaseInvoice> findInvoiceNumber();




}