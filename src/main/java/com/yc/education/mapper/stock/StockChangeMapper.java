package com.yc.education.mapper.stock;

import com.yc.education.model.stock.StockChange;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 库存异动
 *@Author QuZhangJing
 *@Date 10:36  2018/11/5
 *@Version 1.0
 */
public interface StockChangeMapper extends MyMapper<StockChange> {


    /**
     * @param currentDate
     * @return
     */
    String selectMaxIdnum(String currentDate);


    //查询所有库存异动
    List<StockChange> findStockChange();


    List<StockChange> findStockChangeByOrders(@Param("orders")String orders);

    /**
     * 查询未审核的库存异动作业
     * @return
     */
    List<StockChange> findStockChangeNotSh();

    /**
     * 根据订单编号查询单据信息
     * @param orders
     * @return
     */
    StockChange findStockChangeOrders(@Param("orders")String orders);

}