package com.yc.education.mapper.sale;

import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SaleReturnGoodsMapper  extends MyMapper<SaleReturnGoods> {

    /**
     * @Description 查询所有销售退货单未入库完的订单
     * @Author BlueSky
     * @Date 17:29 2019/4/23
     **/
    List<SaleReturnGoods> listSaleGoodsByOrderNotInbound();

    /**
     * 时间条件查询
     * @param ben
     * @param end
     * @return
     */
    List<SaleReturnGoods> listTimeWhere(@Param("ben")String ben,@Param("end")String end);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * @Description 查询全部订单 + 模糊 订单号、客户名称查询
     * @Author BlueSky
     * @Date 14:17 2019/4/11
     **/
    List<SaleReturnGoods> listSaleReturnGoodsAll(@Param("text")String text,@Param("audit")String audit);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    SaleReturnGoods getSaleReturnGoods(@Param("orderno") String orderno);

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
    SaleReturnGoods getSaleReturnGoodsByPage(@Param("page")int page, @Param("rows")int rows);


    List<SaleReturnGoods> getSaleReturnGoodsByPages();


    /**
     * 统计订单数量
     * @return
     */
    int getSaleReturnGoodsCount();

    /**
     * 查询待审核的退货单
     * @return
     */
    List<SaleReturnGoods> listSaleReturnGoodsNotSh();
}