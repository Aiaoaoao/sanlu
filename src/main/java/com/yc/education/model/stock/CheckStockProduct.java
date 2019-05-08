package com.yc.education.model.stock;

import javax.persistence.*;
/**
 *@Description TODO 盘库作业产品
 *@Author QuZhangJing
 *@Date 19:47  2018/11/7
 *@Version 1.0
 */
@Table(name = "check_stock_product")
public class CheckStockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品编号
     */
    private String productorder;

    /**
     * 产品名称
     */
    private String productname;

    /**
     * 库位编号
     */
    private String depotorder;

    /**
     * 库位名称
     */
    private String depotname;

    /**
     * 库存数量
     */
    private Integer depotnum;

    /**
     * 实盘数量
     */
    private Integer nownum;

    /**
     * 单位
     */
    private String productunit;

    /**
     * 盘盈/盘输
     */
    private String profitandloss;

    /**
     * 单价
     */
    private Double productprice;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 盘库作业编号
     */
    private Long checkid;

    @Transient
    private CheckStock checkStock;

    @Transient
    private String checkStockOrder;

    @Transient
    private String flagDate;


    public CheckStockProduct() {
    }


    public CheckStockProduct(String productorder, String productname, String depotorder, String depotname, Integer depotnum, Integer nownum, String productunit, String profitandloss, Double productprice, String remarks, Long checkid) {
        this.productorder = productorder;
        this.productname = productname;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.depotnum = depotnum;
        this.nownum = nownum;
        this.productunit = productunit;
        this.profitandloss = profitandloss;
        this.productprice = productprice;
        this.remarks = remarks;
        this.checkid = checkid;
    }


    public CheckStockProduct(long id,String productorder, String productname, String depotorder, String depotname, Integer depotnum, Integer nownum, String productunit, String profitandloss, Double productprice, String remarks, Long checkid) {
        this.id = id;
        this.productorder = productorder;
        this.productname = productname;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.depotnum = depotnum;
        this.nownum = nownum;
        this.productunit = productunit;
        this.profitandloss = profitandloss;
        this.productprice = productprice;
        this.remarks = remarks;
        this.checkid = checkid;
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
     * 获取产品编号
     *
     * @return productorder - 产品编号
     */
    public String getProductorder() {
        return productorder;
    }

    /**
     * 设置产品编号
     *
     * @param productorder 产品编号
     */
    public void setProductorder(String productorder) {
        this.productorder = productorder;
    }

    /**
     * 获取产品名称
     *
     * @return productname - 产品名称
     */
    public String getProductname() {
        return productname;
    }

    /**
     * 设置产品名称
     *
     * @param productname 产品名称
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * 获取库位编号
     *
     * @return depotorder - 库位编号
     */
    public String getDepotorder() {
        return depotorder;
    }

    /**
     * 设置库位编号
     *
     * @param depotorder 库位编号
     */
    public void setDepotorder(String depotorder) {
        this.depotorder = depotorder;
    }

    /**
     * 获取库位名称
     *
     * @return depotname - 库位名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置库位名称
     *
     * @param depotname 库位名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    /**
     * 获取库存数量
     *
     * @return depotnum - 库存数量
     */
    public Integer getDepotnum() {
        return depotnum;
    }

    /**
     * 设置库存数量
     *
     * @param depotnum 库存数量
     */
    public void setDepotnum(Integer depotnum) {
        this.depotnum = depotnum;
    }

    /**
     * 获取实盘数量
     *
     * @return nownum - 实盘数量
     */
    public Integer getNownum() {
        return nownum;
    }

    /**
     * 设置实盘数量
     *
     * @param nownum 实盘数量
     */
    public void setNownum(Integer nownum) {
        this.nownum = nownum;
    }

    /**
     * 获取单位
     *
     * @return productunit - 单位
     */
    public String getProductunit() {
        return productunit;
    }

    /**
     * 设置单位
     *
     * @param productunit 单位
     */
    public void setProductunit(String productunit) {
        this.productunit = productunit;
    }

    /**
     * 获取盘盈/盘输
     *
     * @return profitandloss - 盘盈/盘输
     */
    public String getProfitandloss() {
        return profitandloss;
    }

    /**
     * 设置盘盈/盘输
     *
     * @param profitandloss 盘盈/盘输
     */
    public void setProfitandloss(String profitandloss) {
        this.profitandloss = profitandloss;
    }

    /**
     * 获取单价
     *
     * @return productprice - 单价
     */
    public Double getProductprice() {
        return productprice;
    }

    /**
     * 设置单价
     *
     * @param productprice 单价
     */
    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取盘库作业编号
     *
     * @return checkid - 盘库作业编号
     */
    public Long getCheckid() {
        return checkid;
    }

    /**
     * 设置盘库作业编号
     *
     * @param checkid 盘库作业编号
     */
    public void setCheckid(Long checkid) {
        this.checkid = checkid;
    }

    public CheckStock getCheckStock() {
        return checkStock;
    }

    public void setCheckStock(CheckStock checkStock) {
        this.checkStock = checkStock;
    }

    public String getCheckStockOrder() {
        return checkStockOrder;
    }

    public void setCheckStockOrder(String checkStockOrder) {
        this.checkStockOrder = checkStockOrder;
    }

    public String getFlagDate() {
        return flagDate;
    }

    public void setFlagDate(String flagDate) {
        this.flagDate = flagDate;
    }
}