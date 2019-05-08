package com.yc.education.mapper.stock;

import com.yc.education.model.stock.CheckStockProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 盘库作业产品
 *@Author QuZhangJing
 *@Date 19:48  2018/11/7
 *@Version 1.0
        */
public interface CheckStockProductMapper extends MyMapper<CheckStockProduct> {

    //根据盘库作业编号查询盘库作业产品
    List<CheckStockProduct> findCheckStockProduct(@Param("checkid")long checkid);


    List<CheckStockProduct> findCheckStockProductByProductOrderAndProductType(
            @Param("proTextStart")String proTextStart,
            @Param("proTextEnd")String proTextEnd,
            @Param("changeTypeStart")int changeTypeStart,
            @Param("changeTypeEnd")int changeTypeEnd,
            @Param("changeDateStart")String changeDateStart,
            @Param("changeDateEnd")String changeDateEnd
    );


}