package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.CheckStockMapper;
import com.yc.education.model.stock.CheckStock;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.CheckStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CheckStockServiceImpl
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/8 9:24
 * @Version 1.0
 */
@Service("CheckStockService")
public class CheckStockServiceImpl extends BaseService<CheckStock> implements CheckStockService {

    @Autowired
    private CheckStockMapper checkStockMapper;

    @Override

    public String selectMaxIdnum(String currentDate) {
        try {
            return checkStockMapper.selectMaxIdnum(currentDate);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStock> findCheckStock() {
        try {
            return checkStockMapper.findCheckStock();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStock> findCheckStock(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return checkStockMapper.findCheckStock();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStock> findCheckStock(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return checkStockMapper.findCheckStockByOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CheckStock> findCheckStockNotSh() {
        try {
            return checkStockMapper.findCheckStockNotSh();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CheckStock findCheckStockOrders(String orders) {
        try {
            return checkStockMapper.findCheckStockOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }
}
