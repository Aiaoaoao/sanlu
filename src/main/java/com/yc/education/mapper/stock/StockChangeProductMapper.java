package com.yc.education.mapper.stock;

import com.yc.education.model.stock.StockChangeProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 库存异动产品
 *@Author QuZhangJing
 *@Date 20:34  2018/11/5
 *@Version 1.0
 */
public interface StockChangeProductMapper extends MyMapper<StockChangeProduct> {


    //根据库存异动id查询库存异动产品
    List<StockChangeProduct> findStockChangeProduct(long stockChangeId);

    /**
     *  根据产品编号 和 异动类型  和 异动日期查询异动产品
     * @param proTextStart
     * @param proTextEnd
     * @param changeTypeStart
     * @param changeTypeEnd
     * @param changeDateStart
     * @param changeDateEnd
     * @return
     */
    List<StockChangeProduct> findStockChangeProductByProductNumAndChangeTypeAndChangeDate(
            @Param("proTextStart")String proTextStart,
            @Param("proTextEnd")String proTextEnd,
            @Param("changeTypeStart")int changeTypeStart,
            @Param("changeTypeEnd")int changeTypeEnd,
            @Param("changeDateStart")String changeDateStart,
            @Param("changeDateEnd")String changeDateEnd
    );


}