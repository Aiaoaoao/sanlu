package com.yc.education.service.sale;


import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleGoodsProductService extends IService<SaleGoodsProduct> {

    /**
     * @Description 修改出库数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSaleGoodsProductOutboundNum( String orderNo, String productNo, String num);

    /**
     * @Description 通过销货单订单号和产品编号获取销货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 19:42 2019/4/23
     **/
    SaleGoodsProduct getSaleGoodsProductBySaleNum( String orderNo, String productNo);

    /**
     * 时间条件查询未开票的销货单
     * @param state 1:已开票、0：未开票
     * @param ben
     * @param end
     * @param customerNo 客户编号
     * @return
     */
    List<SaleGoodsProduct> listTimeWhereNoTicket(String state,String ben,String end,String customerNo);

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleGoodsProduct> listTimeWhere(String ben,String end);

    /**
     * 根据销货单id查询销货产品
     * @param orderid
     * @return
     */
    List<SaleGoodsProduct> listSaleGoodsProduct(String orderid);

    /**
     * 分页
     * 根据销货单id查询销货产品
     * @param orderid
     * @return
     */
    List<SaleGoodsProduct> listSaleGoodsProduct(String orderid, int page, int rows);

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
    List<SaleGoodsProduct> listSaleGoodsProductDiscount( int page, int rows,String customerNo, String customerNoEnd, String productNo, String productNoEnd, String productName, String productNameEnd, String saleDateStr, String saleDateEndStr, String customerName, String customerNameEnd, String discountStr);

    /**
     * 根据外键id删除
     * @param orderid
     * @return
     */
    int deleteSaleGoodsProductByParentId( String orderid);


    /**
     * 销货单查询
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleGoodsProduct> selectSaleGoodsProdutByProductNameAndStartTimeAndEndTime(String productName,String startTime,String endTime);


}
