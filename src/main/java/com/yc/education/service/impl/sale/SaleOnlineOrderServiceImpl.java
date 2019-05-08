package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SaleOnlineOrderMapper;
import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISaleOnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SaleOnlineOrderServiceImpl extends BaseService<SaleOnlineOrder> implements ISaleOnlineOrderService {

    @Autowired
    private SaleOnlineOrderMapper mapper;

    @Override
    public String getMaxOrderNo() {
        try {
            return  mapper.getMaxOrderNo();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleOnlineOrder> listSaleOnlineOrderAll() {
        try {
            return  mapper.listSaleOnlineOrderAll("","");
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SaleOnlineOrder> listSaleOnlineOrderByPage(String text,String audit,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSaleOnlineOrderAll(text,audit);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SaleOnlineOrder getSaleOnlineOrder(String orderno) {
        try {
            return  mapper.getSaleOnlineOrder(orderno);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SaleOnlineOrder getLastSaleOnlineOrder() {
        try {
            return  mapper.getLastSaleOnlineOrder();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SaleOnlineOrder getFirstSaleOnlineOrder() {
        try {
            return  mapper.getFirstSaleOnlineOrder();
        }catch (Exception e) {
            return null;
        }
    }
    @Override
    public SaleOnlineOrder getSaleOnlineOrderByPage(int page, int rows) {
        try {
            return  mapper.getSaleOnlineOrderByPage(page,rows);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int getSaleOnlineOrderCount() {
        try {
            return  mapper.getSaleOnlineOrderCount();
        }catch (Exception e){

            return 0;
        }
    }

    @Override
    public List<SaleOnlineOrder> listSaleOnlineOrderNotSh() {
        try {
            return  mapper.listSaleOnlineOrderNotSh();
        }catch (Exception e){
            return null;
        }
    }

}
