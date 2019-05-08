package com.yc.education.service.impl.purchase;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.purchase.PurchaseInvoiceMapper;
import com.yc.education.model.purchase.PurchaseInvoice;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.purchase.PurchaseInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PurchaseInvoiceServiceImpl
 * @Description TODO  应付账款
 * @Author QuZhangJing
 * @Date 2018/12/10 15:22
 * @Version 1.0
 */
@Service("PurchaseInvoiceService")
public class PurchaseInvoiceServiceImpl extends BaseService<PurchaseInvoice> implements PurchaseInvoiceService {

    @Autowired
    private PurchaseInvoiceMapper purchaseInvoiceMapper;


    @Override
    public List<PurchaseInvoice> findPurchaseInvoice(String supplierOrderStart, String supplierOrderEnd, String invoiceNumberStart, String invoiceNumberEnd, String invoiceDateStart, String invoiceDateEnd) {
        try {
            return purchaseInvoiceMapper.findPurchaseInvoice(supplierOrderStart, supplierOrderEnd, invoiceNumberStart, invoiceNumberEnd, invoiceDateStart, invoiceDateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseInvoice> findPurchaseInvoice(String supplierOrderStart, String supplierOrderEnd, String invoiceNumberStart, String invoiceNumberEnd, String invoiceDateStart, String invoiceDateEnd, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return purchaseInvoiceMapper.findPurchaseInvoice(supplierOrderStart, supplierOrderEnd, invoiceNumberStart, invoiceNumberEnd, invoiceDateStart, invoiceDateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseInvoice> findInvoiceNumber() {
        try {
            return purchaseInvoiceMapper.findInvoiceNumber();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseInvoice> findInvoiceNumber(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return purchaseInvoiceMapper.findInvoiceNumber();
        } catch (Exception e) {
            return null;
        }
    }
}
