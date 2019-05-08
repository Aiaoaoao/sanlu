package com.yc.education.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_goods_product")
public class SaleGoodsProduct {
    /**
     * 销售-销货单-销货产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 序号
     */
    @Transient
    private Integer no;
    /**
     * 客户折扣
     */
    @Transient
    private String customerDiscount;
    /**
     * 税别Str
     */
    @Transient
    private String taxStr;

    /**
     * 含税小计 Str
     */
    @Transient
    private String taxContainStr;
    /**
     * 未税小计 Str
     */
    @Transient
    private String taxNotContainStr;

    /**
     * 客户编号
     */
    @Transient
    @Column(name = "customer_no")
    private String customerNo;
    /**
     * 客户名称
     */
    @Transient
    @Column(name = "customer_name")
    private String customerName;
    /**
     * 销货单号
     */
    @Transient
    @Column(name = "sale_no")
    private String saleNo;
    /**
     * 销货日期
     */
    @Transient
    @Column(name = "create_date")
    private Date createDate;
    /**
     * 销货日期Str
     */
    @Transient
    private String createDateStr;
    /**
     * 业务负责
     */
    @Transient
    @Column(name = "business_leader")
    private String businessLeader;

    /**
     * 可用数量
     */
    @Transient
    private String usableNum;

    /**
     * 库存数量
     */
    @Transient
    private String stockNum;

    /**
     * 销货单id
     */
    @Column(name = "sale_goods_id")
    private Long saleGoodsId;

    /**
     * 产品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 分类
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
     * 定价
     */
    private BigDecimal pricing;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 库位
     */
    @Column(name = "warehouse_position")
    private String warehousePosition;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 出库数量
     */
    @Column(name = "outbound_num")
    private Integer outboundNum;

    /**
     * 单据来源
     */
    @Column(name = "source_order")
    private String sourceOrder;

    /**
     * 来源单号
     */
    @Column(name = "source_no")
    private String sourceNo;


    @Transient
    private SaleGoods saleGoods;

    public String getSourceOrder() {
        return sourceOrder;
    }

    public void setSourceOrder(String sourceOrder) {
        this.sourceOrder = sourceOrder;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public SaleGoods getSaleGoods() {
        return saleGoods;
    }

    public void setSaleGoods(SaleGoods saleGoods) {
        this.saleGoods = saleGoods;
    }

    public String getStockNum() {
        return stockNum;
    }

    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getOutboundNum() {
        return outboundNum;
    }

    public void setOutboundNum(Integer outboundNum) {
        this.outboundNum = outboundNum;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取销售-销货单-销货产品
     *
     * @return id - 销售-销货单-销货产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-销货单-销货产品
     *
     * @param id 销售-销货单-销货产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取销货单id
     *
     * @return sale_goods_id - 销货单id
     */
    public Long getSaleGoodsId() {
        return saleGoodsId;
    }

    /**
     * 设置销货单id
     *
     * @param saleGoodsId 销货单id
     */
    public void setSaleGoodsId(Long saleGoodsId) {
        this.saleGoodsId = saleGoodsId;
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
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取分类
     *
     * @return category - 分类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置分类
     *
     * @param category 分类
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
     * 获取定价
     *
     * @return pricing - 定价
     */
    public BigDecimal getPricing() {
        return pricing;
    }

    /**
     * 设置定价
     *
     * @param pricing 定价
     */
    public void setPricing(BigDecimal pricing) {
        this.pricing = pricing;
    }

    /**
     * 获取折扣
     *
     * @return discount - 折扣
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * 设置折扣
     *
     * @param discount 折扣
     */
    public void setDiscount(Double discount) {
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
     * 获取库位
     *
     * @return warehouse_position - 库位
     */
    public String getWarehousePosition() {
        return warehousePosition;
    }

    /**
     * 设置库位
     *
     * @param warehousePosition 库位
     */
    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition = warehousePosition;
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

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getBusinessLeader() {
        return businessLeader;
    }

    public void setBusinessLeader(String businessLeader) {
        this.businessLeader = businessLeader;
    }

    public String getUsableNum() {
        return usableNum;
    }

    public void setUsableNum(String usableNum) {
        this.usableNum = usableNum;
    }



    public String getTaxStr() {
        return taxStr;
    }

    public void setTaxStr(String taxStr) {
        this.taxStr = taxStr;
    }

    public String getTaxContainStr() {
        return taxContainStr;
    }

    public void setTaxContainStr(String taxContainStr) {
        this.taxContainStr = taxContainStr;
    }

    public String getTaxNotContainStr() {
        return taxNotContainStr;
    }

    public void setTaxNotContainStr(String taxNotContainStr) {
        this.taxNotContainStr = taxNotContainStr;
    }

    public String getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(String customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}