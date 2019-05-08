package com.yc.education.service;

import com.yc.education.model.ProductStock;

import java.util.List;

/**
 * @ClassName ProductStockService
 * @Description TODO 产品库存
 * @Author QuZhangJing
 * @Date 2018/10/22 17:58
 * @Version 1.0
 */
public interface ProductStockService extends IService<ProductStock> {

    //产评库存查询
    List<ProductStock> findProductStock(String sisnum, String eisnum,
                                        String sproname,String eproname,
                                        String stype,String etype);

    List<ProductStock> findProductStock(String sisnum, String eisnum,
                                        String sproname,String eproname,
                                        String stype,String etype,int pageNum,int pageSize);


    //根据产品名称查询产品库存
    List<ProductStock> findProductStockByProIsnum(String isnum);

    //根据库位编号和产品编号查询库存
    ProductStock findProductStockByDepotAndIsnum(String depot,String isnum);


    //根据库位编号查询库存
    List<ProductStock> findProductStockByDepot(String depot);


    Double findProductStockByWarehouseId(long warehouseId,String pronum);


    List<ProductStock> findProductStockByDeoptParent(long parent);

    List<ProductStock> findProductStockByDeoptParent(long parent,int pageNum,int pageSize);

    ProductStock findProductStockReturnNum(String isnum);


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
            String productOrder,
            String productName,
            int productType,
            String warehouseName,
            String deportName
    );

}
