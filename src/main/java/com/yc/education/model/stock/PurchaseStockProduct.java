package com.yc.education.model.stock;

import javax.persistence.*;
/**
 *@Description TODO 采购入库单产品
 *@Author QuZhangJing
 *@Date 15:05  2018/10/25
 *@Version 1.0
 */
@Table(name = "purchase_stock_product")
public class PurchaseStockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 来源单据
     */
    private String sourseorder;

    /**
     * 单号
     */
    private String orders;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 参考单号
     */
    private String seeorder;

    /**
     * 产品名称
     */
    private String pronum;

    /**
     * 产品名称
     */
    private String proname;

    /**
     * 入库数量
     */
    private Integer stocknum;

    /**
     * 单价
     */
    private double price ;


    /**
     * 单位
     */
    private String units;

    /**
     * 产库编号
     */
    private String depotnum;

    /**
     * 仓库名称
     */
    private String depotname;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 箱号
     */
    @Column(name = "product_boxnum")
    private String productBoxnum;


    /**
     * 备注
     */
    private String remarks;

    /**
     * 入库单编号
     */
    private Long purchasestockid;


    /**
     * 出库数量
     */
    private Integer outnum;

    //采购入库
    @Transient
    private PurchaseStock purchaseStocks;



    public PurchaseStockProduct(long id,String sourseorder, String orders, Integer sort, String seeorder, String pronum, String proname, Integer stocknum,double price,String units, String depotnum, String depotname,String floor,String productBoxnum, String remarks, Long purchasestockid) {
        this.id = id;
        this.sourseorder = sourseorder;
        this.orders = orders;
        this.sort = sort;
        this.seeorder = seeorder;
        this.pronum = pronum;
        this.proname = proname;
        this.stocknum = stocknum;
        this.price =  price;
        this.units = units;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.productBoxnum = productBoxnum;
        this.remarks = remarks;
        this.purchasestockid = purchasestockid;
        this.floor = floor;
    }


    public PurchaseStockProduct(String sourseorder, String orders, Integer sort, String seeorder, String pronum, String proname, Integer stocknum, double price,String units, String depotnum, String depotname,String floor,String productBoxnum,String remarks,Long purchasestockid) {
        this.sourseorder = sourseorder;
        this.orders = orders;
        this.sort = sort;
        this.seeorder = seeorder;
        this.pronum = pronum;
        this.proname = proname;
        this.stocknum = stocknum;
        this.price = price;
        this.units = units;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.productBoxnum = productBoxnum;
        this.remarks = remarks;
        this.purchasestockid = purchasestockid;
        this.floor = floor;
    }

    public PurchaseStockProduct() {

    }

    public Integer getOutnum() {
        return outnum;
    }

    public void setOutnum(Integer outnum) {
        this.outnum = outnum;
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
     * 获取单号
     *
     * @return orders - 单号
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置单号
     *
     * @param orders 单号
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取参考单号
     *
     * @return seeorder - 参考单号
     */
    public String getSeeorder() {
        return seeorder;
    }

    /**
     * 设置参考单号
     *
     * @param seeorder 参考单号
     */
    public void setSeeorder(String seeorder) {
        this.seeorder = seeorder;
    }

    /**
     * 获取产品名称
     *
     * @return pronum - 产品名称
     */
    public String getPronum() {
        return pronum;
    }

    /**
     * 设置产品名称
     *
     * @param pronum 产品名称
     */
    public void setPronum(String pronum) {
        this.pronum = pronum;
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
     * 获取入库数量
     *
     * @return stocknum - 入库数量
     */
    public Integer getStocknum() {
        return stocknum;
    }

    /**
     * 设置入库数量
     *
     * @param stocknum 入库数量
     */
    public void setStocknum(Integer stocknum) {
        this.stocknum = stocknum;
    }

    /**
     * 获取单位
     *
     * @return units - 单位
     */
    public String getUnits() {
        return units;
    }

    /**
     * 设置单位
     *
     * @param units 单位
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * 获取产库编号
     *
     * @return depotnum - 产库编号
     */
    public String getDepotnum() {
        return depotnum;
    }

    /**
     * 设置产库编号
     *
     * @param depotnum 产库编号
     */
    public void setDepotnum(String depotnum) {
        this.depotnum = depotnum;
    }

    /**
     * 获取仓库名称
     *
     * @return depotname - 仓库名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置仓库名称
     *
     * @param depotname 仓库名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
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
     * 获取入库单编号
     *
     * @return purchasestockid - 入库单编号
     */
    public Long getPurchasestockid() {
        return purchasestockid;
    }

    /**
     * 设置入库单编号
     *
     * @param purchasestockid 入库单编号
     */
    public void setPurchasestockid(Long purchasestockid) {
        this.purchasestockid = purchasestockid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public PurchaseStock getPurchaseStocks() {
        return purchaseStocks;
    }

    public void setPurchaseStocks(PurchaseStock purchaseStocks) {
        this.purchaseStocks = purchaseStocks;
    }

    public String getProductBoxnum() {
        return productBoxnum;
    }

    public void setProductBoxnum(String productBoxnum) {
        this.productBoxnum = productBoxnum;
    }
}