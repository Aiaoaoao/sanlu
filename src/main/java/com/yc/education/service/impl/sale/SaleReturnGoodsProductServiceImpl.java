package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleReturnGoodsProductMapper;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleReturnGoodsProductServiceImpl extends BaseService<SaleReturnGoodsProduct> implements ISaleReturnGoodsProductService {

    @Autowired
    private SaleReturnGoodsProductMapper mapper;

    @Override
    public int updateSaleReturnGoodsProductInboundNum(String orderNo, String productNo, String num) {
        try {
            return mapper.updateSaleReturnGoodsProductInboundNum(orderNo,productNo,num);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public SaleReturnGoodsProduct getSaleReturnGoodsProductBySaleNum(String orderNo, String productNo) {
        try {
            return mapper.getSaleReturnGoodsProductBySaleNum(orderNo,productNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleReturnGoodsProduct> listTimeWhere(String state,String ben, String end,String customerNo) {
        try {
            return mapper.listTimeWhere(state,ben,end,customerNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SaleReturnGoodsProduct> listReturnGoodsProduct(String orderid) {
        try {
            return  mapper.listSaleReturnGoodsProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleReturnGoodsProduct> listReturnGoodsProduct(String orderid, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSaleReturnGoodsProduct(orderid);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteSaleReturnGoodsProductByParentId(String orderid) {
        try {
            return  mapper.deleteSaleReturnGoodsProductByParentId(orderid);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SaleReturnGoodsProduct> selectSaleReturnGoodsProductdByProductNameAndStartTimeAndEndTime(String productNum, String startTime, String endTime) {
        try {
            return mapper.selectSaleReturnGoodsProductdByProductNameAndStartTimeAndEndTime(productNum, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }
}
