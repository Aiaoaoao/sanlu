package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleOnlineOrderProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOnlineOrderProductMapper extends MyMapper<SaleOnlineOrderProduct> {

    /**
     * 根据网上订单查询ta订购的产品
     * @param orderid
     * @return
     */
    List<SaleOnlineOrderProduct> listByOnlineOrderAll(@Param("orderid") String orderid);


    /**
     * 根据外键网上订单id删除
     * @param otherid 网上订单id
     * @return
     */
    int deleteSaleOnlineOrderProductByParentId(@Param("otherid") Long otherid);

}