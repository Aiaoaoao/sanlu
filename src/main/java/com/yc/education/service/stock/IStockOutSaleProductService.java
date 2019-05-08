package com.yc.education.service.stock;


import com.yc.education.model.stock.StockOutSaleProduct;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface IStockOutSaleProductService extends IService<StockOutSaleProduct> {

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
    List<StockOutSaleProduct> listStockOutSaleProduct( String orderid);

    /**
     * 根据产品名称和筛选时间查询销货出库
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<StockOutSaleProduct> selectStockOutSaleProductByProductNameAndStartTimeAndEndTime(String productName,String startTime,String endTime);

}
