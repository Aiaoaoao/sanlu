package com.yc.education.mapper.sale;


import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOfferProductMapper extends MyMapper<SaleOfferProduct> {

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleOfferProduct> listTimeWhere(@Param("ben")String ben,@Param("end")String end);

    /**
     * 根据报价单id查询产品报价商品
     * @param quotationid 报价单id
     * @return
     */
    List<SaleOfferProduct> listSaleOfferProduct(@Param("quotationid") Long quotationid);

    /**
     * 根据报价单ID删除
     * @param quotationid 报价单id
     * @return
     */
    int deleteSaleOfferProduct(@Param("quotationid") Long quotationid);


    /**
     * 根据产品编号和筛选时间查询报价单产品
     * @param productNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleOfferProduct> selectSaleOfferProductsByProductNumAndStartTimeAndEndTime(@Param("productNum")String productNum,
                                                                                     @Param("startTime")String startTime,
                                                                                     @Param("endTime")String endTime);

}