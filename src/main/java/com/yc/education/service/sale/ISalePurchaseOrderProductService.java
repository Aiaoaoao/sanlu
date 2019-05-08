package com.yc.education.service.sale;


import com.yc.education.model.sale.SalePurchaseOrderProduct;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISalePurchaseOrderProductService extends IService<SalePurchaseOrderProduct> {

    /**
     * @Description 查询所有订货单未销售完的产品
     * @Author BlueSky
     * @Date 17:35 2019/4/23
     **/
    List<SalePurchaseOrderProduct> listSalePurchaseOrderProductByOrderNotPins(int page, int rows);

    /**
     * @Description 通过订货单订单号和产品编号获取订货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 16:55 2019/4/23
     **/
    SalePurchaseOrderProduct getSalePurchaseOrderProductBySaleNum( String orderNo, String productNo);

    /**
     * @Description 修改销货数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSalePurchaseOrderProductSaleNum( String orderNo, String productNo, String num);

    /**
     * 根据订货单id查询订货产品
     * @param orderid
     * @return
     */
    List<SalePurchaseOrderProduct> listPurchaseOrderProduct(String orderid );

    /**
     * 分页
     * 根据订货单id查询订货产品
     * @param orderid
     * @return
     */
    List<SalePurchaseOrderProduct> listPurchaseOrderProduct(String orderid ,int page,int rows);

    /**
     * 根据外键id删除
     * @param orderid
     * @return
     */
    int deleteSalePurchaseOrderProductByParentId( String orderid);


    /**
     * 根据产品编号和开始时间和结束时间查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<SalePurchaseOrderProduct> selectSalePurchaseOrderProductByProductNameAndStartTimeAndEndTime(String productName,String startTime,String endTime);

}
