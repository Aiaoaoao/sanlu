package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleOnlineOrderMapper extends MyMapper<SaleOnlineOrder> {

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部网上订单
     * @return
     */
    List<SaleOnlineOrder> listSaleOnlineOrderAll(@Param("text") String text,@Param("audit")String audit);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SaleOnlineOrder getSaleOnlineOrder(@Param("orderno")String orderno);

    /**
     * 最后一条数据
     * @return
     */
    SaleOnlineOrder getLastSaleOnlineOrder();

    /**
     * 第一条数据
     * @return
     */
    SaleOnlineOrder getFirstSaleOnlineOrder();

    /**
     * 获取上下页数据
     * @return
     */
    SaleOnlineOrder getSaleOnlineOrderByPage(@Param("page")int page, @Param("rows")int rows);

    /**
     * 统计网上订单数量
     * @return
     */
    int getSaleOnlineOrderCount();

    /**
     * 查询待审核网上订单
     * @return
     */
    List<SaleOnlineOrder> listSaleOnlineOrderNotSh();

}