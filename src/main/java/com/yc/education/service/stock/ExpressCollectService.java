package com.yc.education.service.stock;

import com.yc.education.model.stock.ExpressCollect;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ExpressCollectService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/9 17:33
 * @Version 1.0
 */
public interface ExpressCollectService extends IService<ExpressCollect> {



    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询查询所有快递收件
    List<ExpressCollect> findExpressCollect();


    //查询查询所有快递收件
    List<ExpressCollect> findExpressCollect(int pageNum,int pageSize);


    List<ExpressCollect> findExpressCollect(String orders,int pageNum,int pageSize);


    /**
     * 根据单号查询快递收件
     * @param orders
     * @return
     */
    ExpressCollect findExpressCollectOrders(String orders);

}
