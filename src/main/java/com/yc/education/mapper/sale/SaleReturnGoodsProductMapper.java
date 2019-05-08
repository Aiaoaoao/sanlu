package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SaleReturnGoodsProductMapper extends MyMapper<SaleReturnGoodsProduct> {


    /**
     * @Description 修改入库数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSaleReturnGoodsProductInboundNum( @Param("orderNo") String orderNo,@Param("productNo") String productNo,@Param("num") String num);

    /**
     * @Description 通过销售退货单订单号和产品编号获取销售退货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 19:42 2019/4/23
     **/
    SaleReturnGoodsProduct getSaleReturnGoodsProductBySaleNum( @Param("orderNo") String orderNo, @Param("productNo")String productNo);

    /**
     * 时间条件查询
     * @param state 1:已开票、0：未开票
     * @param ben
     * @param end
     * @param customerNo 客户编号
     * @return
     */
    List<SaleReturnGoodsProduct> listTimeWhere(@Param("state")String state,@Param("ben")String ben,@Param("end")String end,@Param("customerNo")String customerNo);

    /**
     * 根据销售退货单id查询销退产品
     * @param orderid
     * @return
     */
    List<SaleReturnGoodsProduct> listSaleReturnGoodsProduct(@Param("orderid") String orderid);

    /**
     * 根据外键id删除销退产品
     * @param orderid
     * @return
     */
    int deleteSaleReturnGoodsProductByParentId(@Param("orderid") String orderid);

    /**
     * 查询销退单
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleReturnGoodsProduct> selectSaleReturnGoodsProductdByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,
                                                                                                  @Param("startTime")String startTime,
                                                                                                  @Param("endTime")String endTime);
}