package com.yc.education.model;

import javafx.fxml.FXML;

import javax.persistence.*;
/**
 *@Description TODO  产品库存
 *@Author QuZhangJing
 *@Date 16:31  2018/10/22
 *@Version 1.0
 */
@Table(name = "product_stock")
public class ProductStock {
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
     * 可用数量
     */
    private Integer usablenum;

    /**
     * 库存数量
     */
    private Integer stocknum;

    /**
     * 在途数量
     */
    private Integer onthewaynum;

    /**
     * 销售未出
     */
    private Integer salenum;

    /**
     * 销退未入
     */
    private Integer backnum;

    /**
     * 采购未入
     */
    private Integer purchasenum;

    /**
     * 标准售价
     */
    private Double parprice;

    /**
     * 进价
     */
    private Double purchaseprice;

    /**
     * 库位编号
     */
    private String depot;

    /**
     * 标准售价
     */
    @Transient
    private Double normprice;
    /**
     * 最低售价
     */
    @Transient
    private Double lowprice;
    /**
     * 基本单位
     */
    @Transient
    private Long  basicunit;
    /**
     * 仓库名称
     */
    @Transient
    private String title;
    /**
     * 库位名称
     */
    @Transient
    private String depname;


    @Transient
    private String basicunitStr;

    @Transient
    private int sort;



    public ProductStock() {
    }

    public ProductStock(String productorder, String productname, Integer usablenum, Integer stocknum, Integer onthewaynum, Integer salenum, Integer backnum, Integer purchasenum, Double parprice,Double purchaseprice,String depot) {
        this.productorder = productorder;
        this.productname = productname;
        this.usablenum = usablenum;
        this.stocknum = stocknum;
        this.onthewaynum = onthewaynum;
        this.salenum = salenum;
        this.backnum = backnum;
        this.purchasenum = purchasenum;
        this.parprice = parprice;
        this.purchaseprice =  purchaseprice;
        this.depot = depot;
    }

     public ProductStock(long id,String productorder, String productname, Integer usablenum, Integer stocknum, Integer onthewaynum, Integer salenum, Integer backnum, Integer purchasenum, Double parprice,Double purchaseprice,String depot) {
        this.id = id;
        this.productorder = productorder;
        this.productname = productname;
        this.usablenum = usablenum;
        this.stocknum = stocknum;
        this.onthewaynum = onthewaynum;
        this.salenum = salenum;
        this.backnum = backnum;
        this.purchasenum = purchasenum;
        this.parprice = parprice;
        this.purchaseprice =  purchaseprice;
        this.depot = depot;
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
     * 获取可用数量
     *
     * @return usablenum - 可用数量
     */
    public Integer getUsablenum() {
        return usablenum;
    }

    /**
     * 设置可用数量
     *
     * @param usablenum 可用数量
     */
    public void setUsablenum(Integer usablenum) {
        this.usablenum = usablenum;
    }

    /**
     * 获取库存数量
     *
     * @return stocknum - 库存数量
     */
    public Integer getStocknum() {
        return stocknum;
    }

    /**
     * 设置库存数量
     *
     * @param stocknum 库存数量
     */
    public void setStocknum(Integer stocknum) {
        this.stocknum = stocknum;
    }

    /**
     * 获取在途数量
     *
     * @return onthewaynum - 在途数量
     */
    public Integer getOnthewaynum() {
        return onthewaynum;
    }

    /**
     * 设置在途数量
     *
     * @param onthewaynum 在途数量
     */
    public void setOnthewaynum(Integer onthewaynum) {
        this.onthewaynum = onthewaynum;
    }

    /**
     * 获取销售未出
     *
     * @return salenum - 销售未出
     */
    public Integer getSalenum() {
        return salenum;
    }

    /**
     * 设置销售未出
     *
     * @param salenum 销售未出
     */
    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }

    /**
     * 获取销退未入
     *
     * @return backnum - 销退未入
     */
    public Integer getBacknum() {
        return backnum;
    }

    /**
     * 设置销退未入
     *
     * @param backnum 销退未入
     */
    public void setBacknum(Integer backnum) {
        this.backnum = backnum;
    }

    /**
     * 获取采购未入
     *
     * @return purchasenum - 采购未入
     */
    public Integer getPurchasenum() {
        return purchasenum;
    }

    /**
     * 设置采购未入
     *
     * @param purchasenum 采购未入
     */
    public void setPurchasenum(Integer purchasenum) {
        this.purchasenum = purchasenum;
    }

    /**
     * 获取标准售价
     *
     * @return parprice - 标准售价
     */
    public Double getParprice() {
        return parprice;
    }

    /**
     * 设置标准售价
     *
     * @param parprice 标准售价
     */
    public void setParprice(Double parprice) {
        this.parprice = parprice;
    }

    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public Double getNormprice() {
        return normprice;
    }

    public void setNormprice(Double normprice) {
        this.normprice = normprice;
    }

    public Double getLowprice() {
        return lowprice;
    }

    public void setLowprice(Double lowprice) {
        this.lowprice = lowprice;
    }

    public Long getBasicunit() {
        return basicunit;
    }

    public void setBasicunit(Long basicunit) {
        this.basicunit = basicunit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getBasicunitStr() {
        return basicunitStr;
    }

    public void setBasicunitStr(String basicunitStr) {
        this.basicunitStr = basicunitStr;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}