package com.yc.education.service.sale;


import com.yc.education.model.sale.ReportRemark;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.service.IService;

import java.util.List;

/**
 *  报价产品
 * @author BlueSky
 * @Description:
 * @Date 15:16 2018-08-24
 */
public interface ISaleOfferProductService extends IService<SaleOfferProduct>{

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleOfferProduct> listTimeWhere(String ben,String end);

    /**
     * 根据报价单id查询产品报价商品
     * @param quotationid 报价单id
     * @return
     */
    List<SaleOfferProduct> listSaleOfferProduct( Long quotationid);

    /**
     * 根据报价单ID删除
     * @param quotationid 报价单id
     * @return
     */
    int deleteSaleOfferProduct( Long quotationid);


    /**
     * 根据产品编号和筛选时间查询报价单产品
     * @param productNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleOfferProduct> selectSaleOfferProductsByProductNumAndStartTimeAndEndTime(String productNum,
                                                                                     String startTime,
                                                                                     String endTime);
}
