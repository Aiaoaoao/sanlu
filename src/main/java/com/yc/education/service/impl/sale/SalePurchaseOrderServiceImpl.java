package com.yc.education.service.impl.sale;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.sale.SalePurchaseOrderMapper;
import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.sale.ISalePurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: BlueSky
 * @Date: 2018/9/26 15:24
 */
@Service
public class SalePurchaseOrderServiceImpl extends BaseService<SalePurchaseOrder> implements ISalePurchaseOrderService {

    @Autowired
    private SalePurchaseOrderMapper mapper;

    @Override
    public List<SalePurchaseOrder> listSalePurchaseOrderByOrderNotPins(int page, int rows) {
        try {
//            PageHelper.startPage(page,rows);
            return  mapper.listSalePurchaseOrderByOrderNotPins();
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
    public List<SalePurchaseOrder> listSalePurchaseOrderAll() {
        try {
            return  mapper.listSalePurchaseOrderAll("","");
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<SalePurchaseOrder> listSalePurchaseOrderByPage(String text,String audit,int page, int rows) {
        try {
            PageHelper.startPage(page,rows);
            return  mapper.listSalePurchaseOrderAll(text,audit);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SalePurchaseOrder getSalePurchaseOrder(String orderno) {
        try {
            return  mapper.getSalePurchaseOrder(orderno);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SalePurchaseOrder getLastSalePurchaseOrder() {
        try {
            return  mapper.getLastSalePurchaseOrder();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SalePurchaseOrder getFirstSalePurchaseOrder() {
        try {
            return  mapper.getFirstSalePurchaseOrder();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SalePurchaseOrder getSalePurchaseOrderByPage(int page, int rows) {
        try {
            return  mapper.getSalePurchaseOrderByPage(page,rows);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int getSalePurchaseOrderCount() {
        try {
            Example example = new Example(SalePurchaseOrder.class);
            example.createCriteria();
            mapper.selectByExample(example);
            return  mapper.selectCount(new SalePurchaseOrder());
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SalePurchaseOrder> listSalePurchanseOrderNotSh() {
        try {
            return  mapper.listSalePurchanseOrderNotSh();
        }catch (Exception e){
            return null;
        }
    }
}
