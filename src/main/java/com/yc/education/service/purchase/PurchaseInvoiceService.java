package com.yc.education.service.purchase;

import com.yc.education.model.purchase.PurchaseInvoice;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName PurchaseInvoiceService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/12/10 15:19
 * @Version 1.0
 */
public interface PurchaseInvoiceService extends IService<PurchaseInvoice> {



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
    List<PurchaseInvoice> findPurchaseInvoice(String supplierOrderStart,
                                              String supplierOrderEnd,
                                              String invoiceNumberStart,
                                              String invoiceNumberEnd,
                                              String invoiceDateStart,
                                              String invoiceDateEnd
    );


    List<PurchaseInvoice> findPurchaseInvoice(String supplierOrderStart,
                                              String supplierOrderEnd,
                                              String invoiceNumberStart,
                                              String invoiceNumberEnd,
                                              String invoiceDateStart,
                                              String invoiceDateEnd,
                                              int pageNum,
                                              int pageSize
    );


    //进项发票号
    List<PurchaseInvoice> findInvoiceNumber();

    //进项发票号
    List<PurchaseInvoice> findInvoiceNumber(int pageNum,int pageSize);

}
