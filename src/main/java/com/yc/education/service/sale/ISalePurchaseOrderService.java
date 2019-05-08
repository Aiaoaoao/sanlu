package com.yc.education.service.sale;

import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISalePurchaseOrderService extends IService<SalePurchaseOrder> {

    /**
     * @Description 查询所有订货单未销售完的订单
     * @Author BlueSky
     * @Date 17:29 2019/4/23
     **/
    List<SalePurchaseOrder> listSalePurchaseOrderByOrderNotPins(int page, int rows);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部订单
     * @return
     */
    List<SalePurchaseOrder> listSalePurchaseOrderAll();

    /**
     * 查询全部订单
     * @param audit 1：审核
     * @return
     */
    List<SalePurchaseOrder> listSalePurchaseOrderByPage(String text,String audit,int page, int rows);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SalePurchaseOrder getSalePurchaseOrder(String orderno);

    /**
     * 最后一条数据
     * @return
     */
    SalePurchaseOrder getLastSalePurchaseOrder();

    /**
     * 第一条数据
     * @return
     */
    SalePurchaseOrder getFirstSalePurchaseOrder();

    /**
     * 获取上下页数据
     * @return
     */
    SalePurchaseOrder getSalePurchaseOrderByPage(int page, int rows);

    /**
     * 统计订单数量
     * @return
     */
    int getSalePurchaseOrderCount();

    /**
     * 查询待审核的报价单
     * @return
     */
    List<SalePurchaseOrder> listSalePurchanseOrderNotSh();

}
