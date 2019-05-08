package com.yc.education.mapper.stock;

import com.yc.education.model.stock.ExpressMail;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 快递寄件
 *@Author QuZhangJing
 *@Date 15:46  2018/11/12
 *@Version 1.0
 */
public interface ExpressMailMapper extends MyMapper<ExpressMail> {


    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询所有快递寄件
    List<ExpressMail> findExpressMail();


    List<ExpressMail>  findExpressMailByOrder(@Param("orders")String orders);

    /**
     *
     * @param orders
     * @return
     */
    ExpressMail findExpressMailOrder(@Param("orders")String orders);


}