package com.yc.education.service.impl.stock;

import com.yc.education.mapper.stock.PurchaseStockProductMapper;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.PurchaseStockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PurchaseStockProductServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/25 15:30
 * @Version 1.0
 */
@Service("PurchaseStockProduct")
public class PurchaseStockProductServiceImpl extends BaseService<PurchaseStockProduct> implements PurchaseStockProductService {

    @Autowired
    private PurchaseStockProductMapper purchaseStockProductMapper;

    @Override
    public List<PurchaseStockProduct> listNotOutboundPurchaseStockProduct(String productNo) {
        try {
            return purchaseStockProductMapper.listNotOutboundPurchaseStockProduct(productNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<PurchaseStockProduct> listPurchaseStockProductAndPurchaseStockByProdutNo(String stockOrder) {
        try {
            return purchaseStockProductMapper.listPurchaseStockProductAndPurchaseStockByProdutNo(stockOrder);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public PurchaseStockProduct getProductAddressByProductNo(String productNo) {
        try {
            return purchaseStockProductMapper.getProductAddressByProductNo(productNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<String> listPurchaseStockProductMoreStockByproductNo(String productNo) {
        try {
            return purchaseStockProductMapper.listPurchaseStockProductMoreStockByproductNo(productNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<PurchaseStockProduct> findStockProductBypurchaseStockId(long id) {
        try {
            return purchaseStockProductMapper.findStockProductBypurchaseStockId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStockProduct> findPurchaseStockProductByPurchaseOrder(String purchaseOrder) {
        try {
            return purchaseStockProductMapper.findPurchaseStockProductByPurchaseOrder(purchaseOrder);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Double findPurchaseStockProductPriceSUM(long id) {
        try {
            return purchaseStockProductMapper.findPurchaseStockProductPriceSUM(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<PurchaseStockProduct> findPurchaseStockProductByProductNameAndStartTimeAndEndTime(String productName, String startTime, String endTime) {
        try {
            return purchaseStockProductMapper.findPurchaseStockProductByProductNameAndStartTimeAndEndTime(productName, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }
}
