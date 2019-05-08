package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleGoodsProductMapper;
import com.yc.education.mapper.sale.SaleReturnGoodsProductMapper;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleGoodsProductServiceImpl extends BaseService<SaleGoodsProduct> implements ISaleGoodsProductService {

    @Autowired
    private SaleGoodsProductMapper mapper;

    @Override
    public int updateSaleGoodsProductOutboundNum(String orderNo, String productNo, String num) {
        try {
            return  mapper.updateSaleGoodsProductOutboundNum(orderNo,productNo,num);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public SaleGoodsProduct getSaleGoodsProductBySaleNum(String orderNo, String productNo) {
        try {
            return  mapper.getSaleGoodsProductBySaleNum(orderNo,productNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoodsProduct> listTimeWhereNoTicket(String state,String ben, String end, String customerNo) {
        try {
            return mapper.listTimeWhereNoTicket(state,ben,end,customerNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoodsProduct> listTimeWhere(String ben, String end) {
        try {
            return mapper.listTimeWhere(ben,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleGoodsProduct> listSaleGoodsProduct(String orderid) {
        try {
            return  mapper.listSaleGoodsProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoodsProduct> listSaleGoodsProduct(String orderid, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSaleGoodsProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleGoodsProduct> listSaleGoodsProductDiscount( int page, int rows,String customerNo, String customerNoEnd, String productNo, String productNoEnd, String productName, String productNameEnd, String saleDateStr, String saleDateEndStr, String customerName, String customerNameEnd, String discountStr) {
        try {
            PageHelper.startPage(  page,  rows);
            return  mapper.listSaleGoodsProductDiscount(customerNo, customerNoEnd, productNo, productNoEnd, productName, productNameEnd, saleDateStr, saleDateEndStr, customerName, customerNameEnd, discountStr);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSaleGoodsProductByParentId(String orderid) {
        try {
            return  mapper.deleteSaleGoodsProductByParentId(orderid);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SaleGoodsProduct> selectSaleGoodsProdutByProductNameAndStartTimeAndEndTime(String productName, String startTime, String endTime) {
        try {
            return mapper.selectSaleGoodsProdutByProductNameAndStartTimeAndEndTime(productName, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }
}
