package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleQuotationMapper;
import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleQuotationServiceImpl extends BaseService<SaleQuotation> implements ISaleQuotationService {

    @Autowired
    private SaleQuotationMapper mapper;

    @Override
    public List<SaleQuotation> listOrderNoLike(String orderNo) {
        try {
            return  mapper.listOrderNoLike(orderNo);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public String getMaxOrderNo() {
        try {
            return  mapper.getMaxOrderNo();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleQuotation> listTimeWhere(String ben, String end) {
        try {
            return  mapper.listTimeWhere(ben,end);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleQuotation> listSaleQuotationAll(String text,String audit,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSaleQuotationAll(text,audit);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SaleQuotation getSaleQuotation(String offerno) {
        try {
            return  mapper.getSaleQuotation(offerno);
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public int getSaleQuotationCount() {
        try {
            return  mapper.getSaleQuotationCount();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SaleQuotation> listSaleQuotationAllByStatus(int status, int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listSaleQuotationAllByStatus(status);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<SaleQuotation> listSaleQuotationNotSh() {
        try {
            return  mapper.listSaleQuotationNotSh();
        }catch (Exception e){
            return null;
        }
    }
}
