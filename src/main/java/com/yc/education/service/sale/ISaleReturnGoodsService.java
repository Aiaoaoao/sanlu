package com.yc.education.service.sale;

import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface ISaleReturnGoodsService extends IService<SaleReturnGoods> {

    /**
     * @Description 查询所有销售退货单未入库完的订单
     * @Author BlueSky
     * @Date 17:29 2019/4/23
     **/
    List<SaleReturnGoods> listSaleGoodsByOrderNotInbound(int page, int rows);

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleReturnGoods> listTimeWhere(String ben,String end);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部订单
     * @return
     */
    List<SaleReturnGoods> listSaleReturnGoodsAll();

    /**
     * 查询全部订单 + 模糊 订单号、客户名称查询
     * @param audit 1:已审核
     * @return
     */
    List<SaleReturnGoods> listSaleReturnGoodsByPage(String text,String audit,int page, int rows);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SaleReturnGoods getSaleReturnGoods(String orderno);

    /**
     * 最后一条数据
     * @return
     */
    SaleReturnGoods getLastSaleReturnGoods();

    /**
     * 第一条数据
     * @return
     */
    SaleReturnGoods getFirstSaleReturnGoods();

    /**
     * 获取上下页数据
     * @return
     */
    SaleReturnGoods getSaleReturnGoodsByPage(int page, int rows);

    /**
     * 统计订单数量
     * @return
     */
    int getSaleReturnGoodsCount();


    List<SaleReturnGoods> getSaleReturnGoodsByPages();


    List<SaleReturnGoods> getSaleReturnGoodsByPages(int pageNum,int pageSize);

    /**
     * 查询待审核的退货单
     * @return
     */
    List<SaleReturnGoods> listSaleReturnGoodsNotSh();

}
