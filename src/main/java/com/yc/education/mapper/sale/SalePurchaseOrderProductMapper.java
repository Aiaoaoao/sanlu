package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SalePurchaseOrderProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalePurchaseOrderProductMapper extends MyMapper<SalePurchaseOrderProduct> {

    /**
     * @Description 查询所有订货单未销售完的产品
     * @Author BlueSky
     * @Date 17:35 2019/4/23
     **/
    List<SalePurchaseOrderProduct> listSalePurchaseOrderProductByOrderNotPins();

    /**
     * @Description 通过订货单订单号和产品编号获取订货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 16:55 2019/4/23
     **/
    SalePurchaseOrderProduct getSalePurchaseOrderProductBySaleNum(@Param("orderNo") String orderNo,@Param("productNo") String productNo);

    /**
     * @Description 修改销货数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSalePurchaseOrderProductSaleNum(@Param("orderNo") String orderNo,@Param("productNo") String productNo,@Param("num") String num);

    /**
     * 根据订货单id查询订货产品
     * @param orderid
     * @return
     */
    List<SalePurchaseOrderProduct> listPurchaseOrderProduct(@Param("orderid") String orderid);

    /**
     * 根据外键id删除
     * @param orderid
     * @return
     */
    int deleteSalePurchaseOrderProductByParentId(@Param("orderid") String orderid);

    /**
     * 根据产品编号和开始时间和结束时间查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<SalePurchaseOrderProduct> selectSalePurchaseOrderProductByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);

}