package com.yc.education.model.sale;


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_offer_product")
public class SaleOfferProduct {
    /**
     * 销售-报价产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quotation_id")
    private Long quotationId;

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
     * 定价
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
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 添加用户id
     */
    private Long userid;

    //报价单
    @Transient
    private SaleQuotation saleQuotations;

    public SaleOfferProduct(Long quotationId, String productNo, String productName, String category, Integer num, String unit, BigDecimal pricing, String discount, BigDecimal price, BigDecimal money, String remark, Date addtime, Long userid) {
        this.quotationId = quotationId;
        this.productNo = productNo;
        this.productName = productName;
        this.category = category;
        this.num = num;
        this.unit = unit;
        this.pricing = pricing;
        this.discount = discount;
        this.price = price;
        this.money = money;
        this.remark = remark;
        this.addtime = addtime;
        this.userid = userid;
    }

    public SaleOfferProduct() {
    }

    /**
     * 获取销售-报价产品
     *
     * @return id - 销售-报价产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-报价产品
     *
     * @param id 销售-报价产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return quotation_id
     */
    public Long getQuotationId() {
        return quotationId;
    }

    /**
     * @param quotationId
     */
    public void setQuotationId(Long quotationId) {
        this.quotationId = quotationId;
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
     * @return money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取添加用户id
     *
     * @return userid - 添加用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置添加用户id
     *
     * @param userid 添加用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public SaleQuotation getSaleQuotations() {
        return saleQuotations;
    }

    public void setSaleQuotations(SaleQuotation saleQuotations) {
        this.saleQuotations = saleQuotations;
    }
}