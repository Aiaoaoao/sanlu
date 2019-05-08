package com.yc.education.model.stock;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "stock_out_sale_product")
public class StockOutSaleProduct {
    /**
     * 销货出库单-产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 销货出库单id
     */
    @Column(name = "stock_out_sale_id")
    private Long stockOutSaleId;
    /**
     * 单据来源
     */
    @Column(name = "order_source")
    private String orderSource;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 产品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 品类
     */
    private String category;

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
    private BigDecimal price;

    /**
     * 仓库名称
     */
    @Column(name = "warehouse_name")
    private String warehouseName;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注
     */
    private Date addtime;

    //销货出库
    @Transient
    private StockOutSale stockOutSales;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取销货出库单-产品
     *
     * @return id - 销货出库单-产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销货出库单-产品
     *
     * @param id 销货出库单-产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取单据来源
     *
     * @return order_source - 单据来源
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置单据来源
     *
     * @param orderSource 单据来源
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取产品编号
     *
     * @return product_no - 产品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置产品编号
     *
     * @param productNo 产品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取品类
     *
     * @return category - 品类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置品类
     *
     * @param category 品类
     */
    public void setCategory(String category) {
        this.category = category;
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
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取仓库名称
     *
     * @return warehouse_name - 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 设置仓库名称
     *
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * 获取楼层
     *
     * @return floor - 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置楼层
     *
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getStockOutSaleId() {
        return stockOutSaleId;
    }

    public void setStockOutSaleId(Long stockOutSaleId) {
        this.stockOutSaleId = stockOutSaleId;
    }

    public StockOutSale getStockOutSales() {
        return stockOutSales;
    }

    public void setStockOutSales(StockOutSale stockOutSales) {
        this.stockOutSales = stockOutSales;
    }
}