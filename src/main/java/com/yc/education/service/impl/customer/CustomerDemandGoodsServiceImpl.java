package com.yc.education.service.impl.customer;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.customer.CustomerDemandGoodsMapper;
import com.yc.education.model.customer.CustomerDemandGoods;
import com.yc.education.service.customer.ICustomerDemandGoodsService;

import com.yc.education.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: BlueSky
 * @Date: 2018/8/20 11:24
 */
@Service
public class CustomerDemandGoodsServiceImpl extends BaseService<CustomerDemandGoods> implements ICustomerDemandGoodsService {

    @Autowired
    private CustomerDemandGoodsMapper mapper;

    @Override
    public List<CustomerDemandGoods> listCustomerDemandGoodsByManyCondition(int page,int rows,String createNoBen, String createNoEnd, String createDateBen, String createDateEnd, String customerNoBen, String customerNoEnd) {
        try {
            PageHelper.startPage(page ,rows );
            return mapper.listCustomerDemandGoodsByManyCondition(createNoBen,createNoEnd,createDateBen,createDateEnd,customerNoBen,customerNoEnd);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CustomerDemandGoods> listCustomerDemandGoods(String customerName,int page,int rows) {
        try {
            PageHelper.startPage(page,rows);
            return mapper.listCustomerDemandGoods(customerName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean getCheckCustomerDemandGoodsIsStorage(long createno) {
        try {
            return mapper.getCheckCustomerDemandGoodsIsStorage(createno)>0?true:false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CustomerDemandGoods getLastCustomerDemandGoods() {
        try {
            return mapper.getLastCustomerDemandGoods();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CustomerDemandGoods getFirstCustomerDemandGoods() {
        try {
            return mapper.getFirstCustomerDemandGoods();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CustomerDemandGoods getCustomerDemandGoodsByPage(int page, int rows) {
        try {
            return mapper.getCustomerDemandGoodsByPage(page, rows);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getCustomerDemandGoodsCount() {
        try {
            return mapper.getCustomerDemandGoodsCount();
        } catch (Exception e) {
            return 0;
        }
    }
}
