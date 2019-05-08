package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SalePurchaseOrderProductMapper;
import com.yc.education.model.sale.SalePurchaseOrderProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISalePurchaseOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SalePurchaseOrderProductServiceImpl extends BaseService<SalePurchaseOrderProduct> implements ISalePurchaseOrderProductService {

    @Autowired
    private SalePurchaseOrderProductMapper mapper;

    @Override
    public List<SalePurchaseOrderProduct> listSalePurchaseOrderProductByOrderNotPins(int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSalePurchaseOrderProductByOrderNotPins();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SalePurchaseOrderProduct getSalePurchaseOrderProductBySaleNum(String orderNo, String productNo) {
        try {
            return  mapper.getSalePurchaseOrderProductBySaleNum(orderNo,productNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int updateSalePurchaseOrderProductSaleNum(String orderNo, String productNo, String num) {
        try {
            return  mapper.updateSalePurchaseOrderProductSaleNum(orderNo,productNo,num);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SalePurchaseOrderProduct> listPurchaseOrderProduct(String orderid) {
        try {
            return  mapper.listPurchaseOrderProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SalePurchaseOrderProduct> listPurchaseOrderProduct(String orderid, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listPurchaseOrderProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSalePurchaseOrderProductByParentId(String orderid) {
        try {
            return  mapper.deleteSalePurchaseOrderProductByParentId(orderid);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SalePurchaseOrderProduct> selectSalePurchaseOrderProductByProductNameAndStartTimeAndEndTime(String productName, String startTime, String endTime) {
        try {
            return  mapper.selectSalePurchaseOrderProductByProductNameAndStartTimeAndEndTime(productName, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }
}
