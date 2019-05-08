package com.yc.education.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_purchase_order_product")
public class SalePurchaseOrderProduct {

    /**
     * 序号
     */
    @Transient
    private Integer no;
    /**
     * 可用数量
     */
    @Transient
    private String usableNum;

    /**
     * 库存数量
     */
    @Transient
    private String ifstock;

    /**
     * 销售-订货单-订货产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订货单id
     */
    @Column(name = "purchase_order_id")
    private Long purchaseOrderId;

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
     * 订价
     */
    private BigDecimal pricing;

    /**
     * 折扣
     */
    private String discount;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 订货单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 产品来源
     */
    @Column(name = "product_source")
    private String productSource;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 销货数量
     */
    @Column(name = "sale_num")
    private Integer saleNum;

    /**
     * 库位
     */
    @Column(name = "warehouse_position")
    private String warehousePosition;

    /**
     * 楼层
     */
    private String floor;

    //订货单
    @Transient
    private SalePurchaseOrder salePurchaseOrders;

    public String getWarehousePosition() {
        return warehousePosition;
    }

    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition = warehousePosition;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取销售-订货单-订货产品
     *
     * @return id - 销售-订货单-订货产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-订货单-订货产品
     *
     * @param id 销售-订货单-订货产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订货单id
     *
     * @return purchase_order_id - 订货单id
     */
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * 设置订货单id
     *
     * @param purchaseOrderId 订货单id
     */
    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
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
     * 获取订价
     *
     * @return pricing - 订价
     */
    public BigDecimal getPricing() {
        return pricing;
    }

    /**
     * 设置订价
     *
     * @param pricing 订价
     */
    public void setPricing(BigDecimal pricing) {
        this.pricing = pricing;
    }

    /**
     * 获取折扣
     *
     * @return discount - 折扣
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * 设置折扣
     *
     * @param discount 折扣
     */
    public void setDiscount(String discount) {
        this.discount = discount;
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
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取订货单号
     *
     * @return order_no - 订货单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订货单号
     *
     * @param orderNo 订货单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取产品来源
     *
     * @return product_source - 产品来源
     */
    public String getProductSource() {
        return productSource;
    }

    /**
     * 设置产品来源
     *
     * @param productSource 产品来源
     */
    public void setProductSource(String productSource) {
        this.productSource = productSource;
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

    public String getIfstock() {
        return ifstock;
    }

    public void setIfstock(String ifstock) {
        this.ifstock = ifstock;
    }

    public String getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(String usableNum) {
        this.usableNum = usableNum;
    }

    public SalePurchaseOrder getSalePurchaseOrders() {
        return salePurchaseOrders;
    }

    public void setSalePurchaseOrders(SalePurchaseOrder salePurchaseOrders) {
        this.salePurchaseOrders = salePurchaseOrders;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}