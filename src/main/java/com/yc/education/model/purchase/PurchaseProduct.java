package com.yc.education.model.purchase;

import javax.persistence.*;
/**
 *@Description TODO 采购产品
 *@Author QuZhangJing
 *@Date 16:29  2018/10/10
 *@Version 1.0
 */
@Table(name = "purchase_product")
public class PurchaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 来源单据
     */
    private String sourseorder;

    /**
     * 单据编号
     */
    private String orders;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 产品编号
     */
    private String proorders;

    /**
     * 产品名称
     */
    private String proname;

    /**
     * 供应商名称
     */
    private String supname;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private Double price;

    /**
     * 金额
     */
    private Double totalprice;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 采购订单id
     */
    private Long purchaseid;

    /**
     * 已结案数量
     */
    private Integer forcenum;
    /**
     * 已入库数量
     */
    private Integer stockover;
    /**
     * 在途数量
     */
    private Integer ontheway;
    /**
     * 强制结案日期
     */
    private String forcedate;

    /**
     * 采购订单
     */
    @Transient
    private PurchaseOrders purchaseOrders;


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
    private String floor;

    /**
     * 期望交期
     */
    private String expecteddate;

    public PurchaseProduct() {
    }

    public PurchaseProduct(long id,String sourseorder, String orders, Integer sort, String proorders, String proname, String supname, Integer num, String unit, Double price, Double totalprice, String remarks, Long purchaseid,String depotnum,String depotname,String floor,String expecteddate) {
        this.id = id;
        this.sourseorder = sourseorder;
        this.orders = orders;
        this.sort = sort;
        this.proorders = proorders;
        this.proname = proname;
        this.supname = supname;
        this.num = num;
        this.unit = unit;
        this.price = price;
        this.totalprice = totalprice;
        this.remarks = remarks;
        this.purchaseid = purchaseid;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.floor = floor;
        this.expecteddate = expecteddate;
    }

    public PurchaseProduct(String sourseorder, String orders, Integer sort, String proorders, String proname, String supname, Integer num, String unit, Double price, Double totalprice, String remarks, Long purchaseid,String depotnum,String depotname,String floor,String expecteddate) {
        this.sourseorder = sourseorder;
        this.orders = orders;
        this.sort = sort;
        this.proorders = proorders;
        this.proname = proname;
        this.supname = supname;
        this.num = num;
        this.unit = unit;
        this.price = price;
        this.totalprice = totalprice;
        this.remarks = remarks;
        this.purchaseid = purchaseid;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.floor = floor;
        this.expecteddate = expecteddate;
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
     * 获取来源单据
     *
     * @return sourseorder - 来源单据
     */
    public String getSourseorder() {
        return sourseorder;
    }

    /**
     * 设置来源单据
     *
     * @param sourseorder 来源单据
     */
    public void setSourseorder(String sourseorder) {
        this.sourseorder = sourseorder;
    }

    /**
     * 获取单据编号
     *
     * @return orders - 单据编号
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置单据编号
     *
     * @param orders 单据编号
     */
    public void setOrders(String orders) {
        this.orders = orders;
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
     * @return proorders - 产品编号
     */
    public String getProorders() {
        return proorders;
    }

    /**
     * 设置产品编号
     *
     * @param proorders 产品编号
     */
    public void setProorders(String proorders) {
        this.proorders = proorders;
    }

    /**
     * 获取产品名称
     *
     * @return proname - 产品名称
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置产品名称
     *
     * @param proname 产品名称
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取供应商名称
     *
     * @return supname - 供应商名称
     */
    public String getSupname() {
        return supname;
    }

    /**
     * 设置供应商名称
     *
     * @param supname 供应商名称
     */
    public void setSupname(String supname) {
        this.supname = supname;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取金额
     *
     * @return totalprice - 金额
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * 设置金额
     *
     * @param totalprice 金额
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
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
     * 获取采购订单id
     *
     * @return purchaseid - 采购订单id
     */
    public Long getPurchaseid() {
        return purchaseid;
    }

    /**
     * 设置采购订单id
     *
     * @param purchaseid 采购订单id
     */
    public void setPurchaseid(Long purchaseid) {
        this.purchaseid = purchaseid;
    }

    public Integer getForcenum() {
        return forcenum;
    }

    public void setForcenum(Integer forcenum) {
        this.forcenum = forcenum;
    }

    public Integer getStockover() {
        return stockover;
    }

    public void setStockover(Integer stockover) {
        this.stockover = stockover;
    }

    public Integer getOntheway() {
        return ontheway;
    }

    public void setOntheway(Integer ontheway) {
        this.ontheway = ontheway;
    }

    public String getForcedate() {
        return forcedate;
    }

    public void setForcedate(String forcedate) {
        this.forcedate = forcedate;
    }

    public PurchaseOrders getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrders purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getExpecteddate() {
        return expecteddate;
    }

    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate;
    }
}