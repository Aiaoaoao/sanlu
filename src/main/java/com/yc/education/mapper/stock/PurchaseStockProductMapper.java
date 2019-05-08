package com.yc.education.mapper.stock;

import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PurchaseStockProductMapper extends MyMapper<PurchaseStockProduct> {

    /**
     * @Description 根据产品编号查询未出库完的采购入库单
     * @Author BlueSky
     * @Date 11:14 2019/5/5
     **/
    List<PurchaseStockProduct> listNotOutboundPurchaseStockProduct(@Param("productNo")String productNo);

    /**
     * @Author BlueSky
     * @Description //TODO 通过采购入库单查询下面所有的产品详情并携带入库单信息
     * @Date 15:44 2019/3/28
     * @Param [productNo]
     * @return java.util.List<com.yc.education.model.stock.PurchaseStockProduct>
     **/
    List<PurchaseStockProduct> listPurchaseStockProductAndPurchaseStockByProdutNo(@Param("stockOrder")String stockOrder);

    /**
     * @Author BlueSky
     * @Description //TODO 根据产品编号查找产品库位和楼层
     * @Date 15:40 2019/3/27
     * @Param [productNo]
     * @return com.yc.education.model.stock.PurchaseStockProduct
     **/
    @Select("select * from purchase_stock_product where pronum = #{productNo} limit 1")
    PurchaseStockProduct getProductAddressByProductNo(@Param("productNo")String productNo);

    /**
     * @Author BlueSky
     * @Description //TODO 查询产品是否存在多个库位
     * @Date 13:39 2019/3/27
     * @Param [productNo] 产品编号
     * @return java.util.List<java.lang.String>
     **/
    List<String> listPurchaseStockProductMoreStockByproductNo(@Param("productNo")String productNo);

    /**
     * 根据采购入库单编号查询采购入库产品
     * @param id
     * @return
     */
    List<PurchaseStockProduct> findStockProductBypurchaseStockId(@Param("id")long id);


    /*
     *根据采购订单
     */
     List<PurchaseStockProduct> findPurchaseStockProductByPurchaseOrder(@Param("purchaseOrder") String purchaseOrder);

     Double findPurchaseStockProductPriceSUM(@Param("id")long id);


    /**
     * 根据产品名称和时间筛选查询采购入库产品
     * @param productName
     * @param startTime
     * @param endTime
     * @return
     */
     List<PurchaseStockProduct> findPurchaseStockProductByProductNameAndStartTimeAndEndTime(@Param("productName")String productName,@Param("startTime")String startTime,@Param("endTime")String endTime);


}