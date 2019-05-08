package com.yc.education.service.stock;


import com.yc.education.model.stock.StockOutSale;
import com.yc.education.service.IService;

import java.util.List;


/**
 * @author BlueSky
 * @Description:
 * @Date 15:23 2018-09-26
 */
public interface IStockOutSaleService extends IService<StockOutSale> {

    /**
     * @Author BlueSky
     * @Description //TODO 通过销货单号查找销货出货单
     * @Date 15:02 2019/4/4
     **/
    StockOutSale getStockOutSaleBySaleNo(String saleNo);

    /**
     * 查询最大订单号
     * @return
     */
    String getMaxOrderNo();

    /**
     * 查询全部订单
     * @return
     */
    List<StockOutSale> listStockOutSaleAll();

    /**
     * 查询全部订单
     * @return
     */
    List<StockOutSale> listStockOutSaleAll(String text,int page, int rows);

    /**
     * 根据订货单号查询
     * @param orderno
     * @return
     */
    StockOutSale getStockOutSale( String orderno);

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
    StockOutSale getStockOutSaleByPage( int page, int rows);

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
    StockOutSale getStockOutSaleByOutboundNo(String outboundNo);

}
