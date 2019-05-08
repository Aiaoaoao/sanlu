package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleGoodsProductMapper extends MyMapper<SaleGoodsProduct> {

    /**
     * @Description 修改出库数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSaleGoodsProductOutboundNum(@Param("orderNo") String orderNo,@Param("productNo") String productNo,@Param("num") String num);

    /**
     * @Description 通过销货单订单号和产品编号获取销货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 19:42 2019/4/23
     **/
    SaleGoodsProduct getSaleGoodsProductBySaleNum(@Param("orderNo") String orderNo, @Param("productNo")String productNo);

    /**
     * 时间条件查询未开票的销货单
     * @param state 1:已开票、0：未开票
     * @param ben
     * @param end
     * @param customerNo
     * @return
     */
    List<SaleGoodsProduct> listTimeWhereNoTicket(@Param("state")String state,@Param("ben")String ben,@Param("end")String end,@Param("customerNo")String customerNo);

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleGoodsProduct> listTimeWhere(@Param("ben")String ben,@Param("end")String end);

    /**
     * 根据销售退货单id查询销退产品
     * @param orderid
     * @return
     */
    List<SaleGoodsProduct> listSaleGoodsProduct(@Param("orderid") String orderid);

    /**
     *  销售产品折扣查询
     * @param customerNo 客户编号 区域
     * @param customerNoEnd 客户编号结束
     * @param productNo 产品编号 区域
     * @param productNoEnd 产品编号结束
     * @param productName 产品名称区域
     * @param productNameEnd 产品名称结束
     * @param saleDateStr 销售日期 区域
     * @param saleDateEndStr 销售日期结束
     * @param customerName 客户名称 区域
     * @param customerNameEnd 客户名称 结束
     * @param discountStr 折扣
     * @return
     */
    List<SaleGoodsProduct> listSaleGoodsProductDiscount(@Param("customerNo") String customerNo,@Param("customerNoEnd") String customerNoEnd,@Param("productNo") String productNo,@Param("productNoEnd") String productNoEnd,@Param("productName") String productName,@Param("productNameEnd") String productNameEnd,@Param("saleDateStr") String saleDateStr,@Param("saleDateEndStr") String saleDateEndStr,@Param("customerName") String customerName,@Param("customerNameEnd") String customerNameEnd,@Param("discountStr") String discountStr);

    /**
     * 根据外键id删除
     * @param orderid
     * @return
     */
    int deleteSaleGoodsProductByParentId(@Param("orderid") String orderid);

    /**
     * 销货单查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleGoodsProduct> selectSaleGoodsProdutByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);


}