package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.ExpressCollectMapper;
import com.yc.education.model.stock.ExpressCollect;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.ExpressCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ExpressCollectServiceImpl
 * @Description TODO 快递收件
 * @Author QuZhangJing
 * @Date 2018/11/9 17:35
 * @Version 1.0
*/
@Service("ExpressCollectService")
public class ExpressCollectServiceImpl extends BaseService<ExpressCollect> implements ExpressCollectService {

    @Autowired
    private ExpressCollectMapper expressCollectMapper;

    @Override
    public String selectMaxIdnum(String currentDate) {
        try {
            return expressCollectMapper.selectMaxIdnum(currentDate);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ExpressCollect> findExpressCollect() {
        try {
            return expressCollectMapper.findExpressCollect();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ExpressCollect> findExpressCollect(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return expressCollectMapper.findExpressCollect();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ExpressCollect> findExpressCollect(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return expressCollectMapper.findExpressCollectByOrders(orders);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public ExpressCollect findExpressCollectOrders(String orders) {
        try {
            return expressCollectMapper.findExpressCollectOrders(orders);
        }catch (Exception e) {
            return null;
        }
    }
}
