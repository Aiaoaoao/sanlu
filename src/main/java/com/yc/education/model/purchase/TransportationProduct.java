package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO 在途产品
 *@Author QuZhangJing
 *@Date 11:07  2018/10/17
 *@Version 1.0
 */
@Table(name = "transportation_product")
public class TransportationProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 在途库存编号
     */
    private Long parentid;

    /**
     * 采购单号
     */
    private String purchaseorder;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 参考单号
     */
    private String seeorder;

    /**
     * 产品编号
     */
    private String pronum;

    /**
     * 产品名称
     */
    private String proname;

    /**
     * 仓库名称
     */
    private String stocknum;

    /**
     * 箱号
     */
    private String boxnum;

    /**
     * 本次在途数量
     */
    private Integer thistimeontheway;

    /**
     * 订货数量
     */
    private Integer totalnum;

    /**
     * 已入库数量
     */
    private Integer stockover;

    /**
     * 已强制结案数量
     */
    private Integer forcenum;

    /**
     * 已在途数量
     */
    private Integer ontheway;

    /**
     * 库位编号
     */
    private String depotnum;
    /**
     * 库位名称
     */
    private String depotname;
    /**
     * 楼层
     */
    private String depotfloor;

    /**
     * 备注
     */
    private String remarks;



    public TransportationProduct() {

    }


    public TransportationProduct(Long parentid, String purchaseorder, Integer sort,String seeorder,String pronum,String proname,String stocknum, String boxnum, Integer thistimeontheway, Integer totalnum, Integer stockover, Integer forcenum, Integer ontheway,String depotnum,String depotname,String depotfloor,String remarks) {
        this.parentid = parentid;
        this.purchaseorder = purchaseorder;
        this.sort = sort;
        this.seeorder = seeorder;
        this.pronum = pronum;
        this.proname = proname;
        this.stocknum = stocknum;
        this.boxnum = boxnum;
        this.thistimeontheway = thistimeontheway;
        this.totalnum = totalnum;
        this.stockover = stockover;
        this.forcenum = forcenum;
        this.ontheway = ontheway;
        this.depotnum = depotnum;
        this.depotname =  depotname;
        this.depotfloor = depotfloor;
        this.remarks =  remarks;
    }


    public TransportationProduct(long id,Long parentid, String purchaseorder, Integer sort,String seeorder, String pronum,String proname, String stocknum, String boxnum, Integer thistimeontheway, Integer totalnum, Integer stockover, Integer forcenum, Integer ontheway,String depotnum,String depotname,String depotfloor,String remarks) {
        this.id = id;
        this.parentid = parentid;
        this.purchaseorder = purchaseorder;
        this.sort = sort;
        this.seeorder = seeorder;
        this.pronum = pronum;
        this.proname = proname;
        this.stocknum = stocknum;
        this.boxnum = boxnum;
        this.thistimeontheway = thistimeontheway;
        this.totalnum = totalnum;
        this.stockover = stockover;
        this.forcenum = forcenum;
        this.ontheway = ontheway;
        this.depotnum = depotnum;
        this.depotname =  depotname;
        this.depotfloor = depotfloor;
        this.remarks = remarks;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取在途库存编号
     *
     * @return parentid - 在途库存编号
     */
    public Long getParentid() {
        return parentid;
    }

    /**
     * 设置在途库存编号
     *
     * @param parentid 在途库存编号
     */
    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取采购单号
     *
     * @return purchaseorder - 采购单号
     */
    public String getPurchaseorder() {
        return purchaseorder;
    }

    /**
     * 设置采购单号
     *
     * @param purchaseorder 采购单号
     */
    public void setPurchaseorder(String purchaseorder) {
        this.purchaseorder = purchaseorder;
    }

    /**
     * 获取序号
     *
     * @return sort - 序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置序号
     *
     * @param sort 序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取产品编号
     *
     * @return pronum - 产品编号
     */
    public String getPronum() {
        return pronum;
    }

    /**
     * 设置产品编号
     *
     * @param pronum 产品编号
     */
    public void setPronum(String pronum) {
        this.pronum = pronum;
    }

    /**
     * 获取库位
     *
     * @return stocknum - 库位
     */
    public String getStocknum() {
        return stocknum;
    }

    /**
     * 设置库位
     *
     * @param stocknum 库位
     */
    public void setStocknum(String stocknum) {
        this.stocknum = stocknum;
    }

    /**
     * 获取箱号
     *
     * @return boxnum - 箱号
     */
    public String getBoxnum() {
        return boxnum;
    }

    /**
     * 设置箱号
     *
     * @param boxnum 箱号
     */
    public void setBoxnum(String boxnum) {
        this.boxnum = boxnum;
    }

    /**
     * 获取本次在途数量
     *
     * @return thistimeontheway - 本次在途数量
     */
    public Integer getThistimeontheway() {
        return thistimeontheway;
    }

    /**
     * 设置本次在途数量
     *
     * @param thistimeontheway 本次在途数量
     */
    public void setThistimeontheway(Integer thistimeontheway) {
        this.thistimeontheway = thistimeontheway;
    }

    /**
     * 获取订货数量
     *
     * @return totalnum - 订货数量
     */
    public Integer getTotalnum() {
        return totalnum;
    }

    /**
     * 设置订货数量
     *
     * @param totalnum 订货数量
     */
    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    /**
     * 获取已入库数量
     *
     * @return stockover - 已入库数量
     */
    public Integer getStockover() {
        return stockover;
    }

    /**
     * 设置已入库数量
     *
     * @param stockover 已入库数量
     */
    public void setStockover(Integer stockover) {
        this.stockover = stockover;
    }

    /**
     * 获取已强制结案数量
     *
     * @return forcenum - 已强制结案数量
     */
    public Integer getForcenum() {
        return forcenum;
    }

    /**
     * 设置已强制结案数量
     *
     * @param forcenum 已强制结案数量
     */
    public void setForcenum(Integer forcenum) {
        this.forcenum = forcenum;
    }

    /**
     * 获取已在途数量
     *
     * @return ontheway - 已在途数量
     */
    public Integer getOntheway() {
        return ontheway;
    }

    /**
     * 设置已在途数量
     *
     * @param ontheway 已在途数量
     */
    public void setOntheway(Integer ontheway) {
        this.ontheway = ontheway;
    }

    public String getDepotnum() {
        return depotnum;
    }

    public void setDepotnum(String depotnum) {
        this.depotnum = depotnum;
    }

    public String getDepotname() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    public String getDepotfloor() {
        return depotfloor;
    }

    public void setDepotfloor(String depotfloor) {
        this.depotfloor = depotfloor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getSeeorder() {
        return seeorder;
    }

    public void setSeeorder(String seeorder) {
        this.seeorder = seeorder;
    }
}