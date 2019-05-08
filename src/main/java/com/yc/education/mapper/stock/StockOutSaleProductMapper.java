package com.yc.education.mapper.stock;

import com.yc.education.model.stock.StockOutSaleProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StockOutSaleProductMapper extends MyMapper<StockOutSaleProduct> {

    /**
     * 根据外键id删除
     * @param orderid
     * @return
     */
    int deleteStockOutSaleProductByParentId( String orderid);

    /**
     * 根据销货出库单id查询出库产品
     * @param orderid
     * @return
     */
    List<StockOutSaleProduct> listStockOutSaleProduct(@Param("orderid") String orderid);

    /**
     * 根据产品名称和筛选时间查询销货出库
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<StockOutSaleProduct> selectStockOutSaleProductByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);

}