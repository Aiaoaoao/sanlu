package com.yc.education.mapper.stock;


import com.yc.education.model.stock.StockOutSale;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StockOutSaleMapper extends MyMapper<StockOutSale> {

    /**
     * @Author BlueSky
     * @Description //TODO 通过销货单号查找销货出货单
     * @Date 15:02 2019/4/4
     **/
    StockOutSale getStockOutSaleBySaleNo(@Param("saleNo") String saleNo);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部订单
     * @return
     */
    List<StockOutSale> listStockOutSaleAll(@Param("text") String text);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    StockOutSale getStockOutSale(@Param("orderno") String orderno);

    /**
     * 最后一条数据
     * @return
     */
    StockOutSale getLastStockOutSale();

    /**
     * 第一条数据
     * @return
     */
    StockOutSale getFirstStockOutSale();

    /**
     * 获取上下页数据
     * @return
     */
    StockOutSale getStockOutSaleByPage(@Param("page") int page, @Param("rows")int rows);

    /**
     * 统计订单数量
     * @return
     */
    int getStockOutSaleCount();

    /**
     * 查询未审核数据
     * @return
     */
    List<StockOutSale> listStockOutSaleNotSh();


    /**
     * 根据销货单号查询
     * @param outboundNo
     * @return
     */
    StockOutSale getStockOutSaleByOutboundNo(@Param("outboundNo") String outboundNo);

}