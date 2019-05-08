package com.yc.education.service.sale;


import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleReturnGoodsProductService extends IService<SaleReturnGoodsProduct> {


    /**
     * @Description 修改入库数量
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @param num 数量
     * @Date 16:39 2019/4/23
     **/
    int updateSaleReturnGoodsProductInboundNum( String orderNo, String productNo, String num);

    /**
     * @Description 通过销售退货单订单号和产品编号获取销售退货单产品详情
     * @Author BlueSky
     * @param orderNo 订单号
     * @param productNo 产品编号
     * @Date 19:42 2019/4/23
     **/
    SaleReturnGoodsProduct getSaleReturnGoodsProductBySaleNum( String orderNo, String productNo);

    /**
     * 时间条件查询
     * @param state 1:已开票、0：未开票
     * @param ben
     * @param end
     * @param customerNo 客户编号
     * @return
     */
    List<SaleReturnGoodsProduct> listTimeWhere(String state,String ben,String end,String customerNo);

    /**
     * 根据销售退货单id查询销退产品
     * @param orderid
     * @return
     */
    List<SaleReturnGoodsProduct> listReturnGoodsProduct(String orderid);

    /**
     * 分页
     * 根据销售退货单id查询销退产品
     * @param orderid
     * @return
     */
    List<SaleReturnGoodsProduct> listReturnGoodsProduct(String orderid, int page, int rows);


    /**
     * 根据外键id删除销退产品
     * @param orderid
     * @return
     */
    int deleteSaleReturnGoodsProductByParentId( String orderid);

    /**
     * 查询销退单
     * @param productNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<SaleReturnGoodsProduct> selectSaleReturnGoodsProductdByProductNameAndStartTimeAndEndTime(String productNum,
                                                                                                  String startTime,
                                                                                                  String endTime);


}
