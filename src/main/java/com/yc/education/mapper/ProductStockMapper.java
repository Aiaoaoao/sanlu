package com.yc.education.mapper;

import com.yc.education.model.ProductStock;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *@Description TODO 产品库存
 *@Author QuZhangJing
 *@Date 16:37  2018/10/22
 *@Version 1.0
 */
public interface ProductStockMapper extends MyMapper<ProductStock> {


    //产评库存查询
    List<ProductStock> findProductStock(@Param("sisnum")String sisnum,@Param("eisnum")String eisnum,
                                        @Param("sproname")String sproname,@Param("eproname")String eproname,
                                        @Param("stype")String stype,@Param("etype")String etype);


    //根据产品名称查询产品库存
    @Select("select * from product_stock where productorder = #{isnum}")
    List<ProductStock> findProductStockByProIsnum(@Param("isnum")String isnum);

    //根据库位编号和产品编号查询库存
    @Select("select * from product_stock where productorder = #{isnum} and depot = #{depot} ")
    ProductStock findProductStockByDepotAndIsnum(@Param("depot")String depot,@Param("isnum")String isnum);

    //根据库位编号查询库存
    @Select("select * from product_stock where  depot = #{depot} ")
    List<ProductStock> findProductStockByDepot(@Param("depot")String depot);


    Double findProductStockByWarehouseId(@Param("warehouseId")long warehouseId,@Param("pronum") String pronum);


    List<ProductStock> findProductStockByDeoptParent(@Param("parent")long parent);

    @Select("select SUM(usablenum) usablenum,SUM(stocknum) stocknum from product_stock ps where productorder = #{isnum}")
    ProductStock findProductStockReturnNum(@Param("isnum")String isnum);


    /**
     * 产品库存查询
     * @param productOrder
     * @param productName
     * @param productType
     * @param warehouseName
     * @param deportName
     * @return
     */
    List<ProductStock> findProductStockByProductOrderAndProductNameAndMore(
            @Param("productOrder")String productOrder,
            @Param("productName")String productName,
            @Param("productType")int productType,
            @Param("warehouseName")String warehouseName,
            @Param("deportName")String deportName
    );


}