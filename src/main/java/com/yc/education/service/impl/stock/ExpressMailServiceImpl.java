package com.yc.education.service.impl.stock;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.stock.ExpressMailMapper;
import com.yc.education.model.stock.ExpressMail;
import com.yc.education.service.impl.BaseService;
import com.yc.education.service.stock.ExpressMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ExpressMailServiceImpl
 * @Description TODO 快递寄件
 * @Author QuZhangJing
 * @Date 2018/11/12 15:58
 * @Version 1.0
 */
@Service("ExpressMailServiceImpl")
public class ExpressMailServiceImpl extends BaseService<ExpressMail> implements ExpressMailService {

    @Autowired
    private ExpressMailMapper expressMailMapper; //快递寄件

    @Override
    public String selectMaxIdnum(String currentDate) {
         try {
            return expressMailMapper.selectMaxIdnum(currentDate);
         }catch (Exception e){
             return null;
         }
    }

    @Override
    public List<ExpressMail> findExpressMail() {
        try {
            return expressMailMapper.findExpressMail();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ExpressMail> findExpressMail(int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return expressMailMapper.findExpressMail();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ExpressMail> findExpressMail(String orders, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return expressMailMapper.findExpressMailByOrder(orders);
        }catch (Exception e){
            return null;
        }
}

    @Override
    public ExpressMail findExpressMailOrder(String orders) {
        try {
            return expressMailMapper.findExpressMailOrder(orders);
        }catch (Exception e){
            return null;
        }
    }
}
