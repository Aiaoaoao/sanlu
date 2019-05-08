package com.yc.education.service.sale;

import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleOnlineOrderService extends IService<SaleOnlineOrder> {

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部网上订单
     * @return
     */
    List<SaleOnlineOrder> listSaleOnlineOrderAll();

    /**
     * 分页
     * 查询全部网上订单
     * @param audit 1：审核
     * @return
     */
    List<SaleOnlineOrder> listSaleOnlineOrderByPage(String text,String audit,int page,int rows);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SaleOnlineOrder getSaleOnlineOrder(String orderno);

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
    SaleOnlineOrder getSaleOnlineOrderByPage(int page, int rows);

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
