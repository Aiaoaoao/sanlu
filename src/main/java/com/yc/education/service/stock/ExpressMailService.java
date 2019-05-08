package com.yc.education.service.stock;

import com.yc.education.model.stock.ExpressMail;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName ExpressMailService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/12 15:57
 * @Version 1.0
 */
public interface ExpressMailService extends IService<ExpressMail> {
    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询所有快递寄件
    List<ExpressMail> findExpressMail();


    List<ExpressMail> findExpressMail(int pageNum,int pageSize);

    List<ExpressMail> findExpressMail(String  orders,int pageNum,int pageSize);

    /**
     *
     * @param orders
     * @return
     */
    ExpressMail findExpressMailOrder(String orders);

}
