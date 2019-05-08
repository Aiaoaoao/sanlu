package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.PurchaseStockMapper;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.PurchaseStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PurchaseStockServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/24 19:50
 * @Version 1.0
 */
@Service("PurchaseStockService")
public class PurchaseStockServiceImpl extends BaseService<PurchaseStock> implements PurchaseStockService {

    @Autowired
    private PurchaseStockMapper purchaseStockMapper;

    @Override
    public List<PurchaseStock> listPurchaseStock(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return purchaseStockMapper.listPurchaseStock(orders);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String selectMaxIdnum(String currentDate) {
        try {
            return purchaseStockMapper.selectMaxIdnum(currentDate);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchaseStock() {
        try {
            return purchaseStockMapper.findPurchaseStock();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchaseStock(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return purchaseStockMapper.findPurchaseStock();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchaseStock(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return purchaseStockMapper.findPurchaseStockByOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchaseStockBySupplier(String supplier, String startTime, String endTime) {
        try {
            return purchaseStockMapper.findPurchaseStockBySupplier(supplier, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchaseStockBySupplier(String supplier, String startTime, String endTime, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return purchaseStockMapper.findPurchaseStockBySupplier(supplier, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PurchaseStock findPurchaseStockByNo(String orderno) {
        try {
            return purchaseStockMapper.findPurchaseStockByNo(orderno);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> listPurchaseStockByPayment(String supplier, String supplierEnd, String invoice, String invoiceEnd, String invoiceDate, String invoiceDateEnd) {
        try {
            return purchaseStockMapper.listPurchaseStockByPayment(supplier, supplierEnd, invoice, invoiceEnd, invoiceDate, invoiceDateEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStock> findPurchanseStockNotSh() {
        try {
            return purchaseStockMapper.findPurchanseStockNotSh();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PurchaseStock findPurchanseStockBoxNum(String boxNum) {
        try {
            return purchaseStockMapper.findPurchanseStockBoxNum(boxNum);
        } catch (Exception e) {
            return null;
        }
    }
}
