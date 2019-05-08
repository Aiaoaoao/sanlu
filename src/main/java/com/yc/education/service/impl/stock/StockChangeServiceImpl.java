package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.StockChangeMapper;
import com.yc.education.model.stock.StockChange;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.StockChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StockChangeServiceImpl
 * @Description TODO 库存异动
 * @Author QuZhangJing
 * @Date 2018/11/5 10:54
 * @Version 1.0
 */
@Service("StockChangeService")
public class StockChangeServiceImpl extends BaseService<StockChange> implements StockChangeService {

    @Autowired
    private StockChangeMapper stockChangeMapper;

    @Override
    public String selectMaxIdnum(String currentDate) {
        try {
            return stockChangeMapper.selectMaxIdnum(currentDate);
        } catch (Exception e) {
        return null;
    }
}

    @Override
    public List<StockChange> findStockChange() {
        try {
            return stockChangeMapper.findStockChange();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<StockChange> findStockChange(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return stockChangeMapper.findStockChange();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<StockChange> findStockChange(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return stockChangeMapper.findStockChangeByOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<StockChange> findStockChangeNotSh() {
        try {
            return stockChangeMapper.findStockChangeNotSh();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public StockChange findStockChangeOrders(String orders) {
        try {
            return stockChangeMapper.findStockChangeOrders(orders);
        } catch (Exception e) {
            return null;
        }
    }
}
