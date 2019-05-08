package com.yc.education.service.stock;

import com.yc.education.model.stock.CheckStockProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 * @ClassName CheckStockProductService
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/9 9:58
 * @Version 1.0
 */
public interface CheckStockProductService extends IService<CheckStockProduct> {

    //根据盘库作业编号查询盘库作业产品
    List<CheckStockProduct> findCheckStockProduct(long checkid);


    List<CheckStockProduct> findCheckStockProductByProductOrderAndProductType(
           String proTextStart,
           String proTextEnd,
           int changeTypeStart,
           int changeTypeEnd,
            String changeDateStart,
            String changeDateEnd
    );


    List<CheckStockProduct> findCheckStockProductByProductOrderAndProductType(
            String proTextStart,
            String proTextEnd,
            int changeTypeStart,
            int changeTypeEnd,
            String changeDateStart,
            String changeDateEnd,
            int pageNum,
            int pageSize
    );


}
