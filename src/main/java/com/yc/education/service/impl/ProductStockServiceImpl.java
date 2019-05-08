package com.yc.education.service.impl;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.ProductStockMapper;
import com.yc.education.model.ProductStock;
import com.yc.education.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProducctStockServiceImpl
 * @Description TODO  产品库存
 * @Author QuZhangJing
 * @Date 2018/10/22 18:00
 * @Version 1.0
 */
@Service("ProductStockService")
public class ProductStockServiceImpl extends BaseService<ProductStock> implements ProductStockService {


    @Autowired
    private ProductStockMapper productStockMapper;


    @Override
    public List<ProductStock> findProductStock(String sisnum, String eisnum, String sproname, String eproname, String stype, String etype) {
        try {
            return productStockMapper.findProductStock(sisnum,eisnum,sproname,eproname,stype,etype);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductStock> findProductStock(String sisnum, String eisnum, String sproname, String eproname, String stype, String etype, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            return productStockMapper.findProductStock(sisnum,eisnum,sproname,eproname,stype,etype);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public  List<ProductStock> findProductStockByProIsnum(String isnum) {
        try {
            return productStockMapper.findProductStockByProIsnum(isnum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductStock findProductStockByDepotAndIsnum(String depot, String isnum) {
        try {
            return productStockMapper.findProductStockByDepotAndIsnum(depot, isnum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductStock> findProductStockByDepot(String depot) {
        try {
            return productStockMapper.findProductStockByDepot(depot);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Double findProductStockByWarehouseId(long warehouseId,String pronum) {
        try {
            return productStockMapper.findProductStockByWarehouseId(warehouseId,pronum);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductStock> findProductStockByDeoptParent(long parent) {
        try {
            return productStockMapper.findProductStockByDeoptParent(parent);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductStock> findProductStockByDeoptParent(long parent, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            return productStockMapper.findProductStockByDeoptParent(parent);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ProductStock findProductStockReturnNum(String isnum) {
        try {
            return productStockMapper.findProductStockReturnNum(isnum);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ProductStock> findProductStockByProductOrderAndProductNameAndMore(String productOrder, String productName, int productType, String warehouseName, String deportName) {
        try {
            return productStockMapper.findProductStockByProductOrderAndProductNameAndMore(productOrder, productName, productType, warehouseName, deportName);
        }catch (Exception e){
            return null;
        }
    }
}
