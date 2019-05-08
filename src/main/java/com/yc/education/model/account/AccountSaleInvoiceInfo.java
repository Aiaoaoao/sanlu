package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_sale_invoice_info")
public class AccountSaleInvoiceInfo {
    /**
     * 账款-销项发票-发票明细
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 销项发票id
     */
    private Long otherid;

    /**
     * 单据来源
     */
    @Column(name = "order_soruce")
    private String orderSoruce;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 序号
     */
    private String no;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户简称
     */
    @Column(name = "customer_str")
    private String customerStr;

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
     * 发票品名
     */
    @Column(name = "invoce_name")
    private String invoceName;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 单位
     */
    private String unit;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 税率
     */
    private String rate;
    /**
     * 税额
     */
    @Column(name = "rate_money")
    private BigDecimal rateMoney;
    /**
     * 税别
     */
    private String tax;
    /**
     * 未税小计
     */
    @Column(name = "rate_not")
    private BigDecimal rateNot;
    /**
     * 备注
     */
    private String remark;



    /**
     * 获取账款-销项发票-发票明细
     *
     * @return id - 账款-销项发票-发票明细
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款-销项发票-发票明细
     *
     * @param id 账款-销项发票-发票明细
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取销项发票id
     *
     * @return otherid - 销项发票id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置销项发票id
     *
     * @param otherid 销项发票id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取单据来源
     *
     * @return order_soruce - 单据来源
     */
    public String getOrderSoruce() {
        return orderSoruce;
    }

    /**
     * 设置单据来源
     *
     * @param orderSoruce 单据来源
     */
    public void setOrderSoruce(String orderSoruce) {
        this.orderSoruce = orderSoruce;
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
     * 获取序号
     *
     * @return no - 序号
     */
    public String getNo() {
        return no;
    }

    /**
     * 设置序号
     *
     * @param no 序号
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获取客户编号
     *
     * @return customer_no - 客户编号
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * 设置客户编号
     *
     * @param customerNo 客户编号
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    /**
     * 获取客户简称
     *
     * @return customer_str - 客户简称
     */
    public String getCustomerStr() {
        return customerStr;
    }

    /**
     * 设置客户简称
     *
     * @param customerStr 客户简称
     */
    public void setCustomerStr(String customerStr) {
        this.customerStr = customerStr;
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
     * 获取发票品名
     *
     * @return invoce_name - 发票品名
     */
    public String getInvoceName() {
        return invoceName;
    }

    /**
     * 设置发票品名
     *
     * @param invoceName 发票品名
     */
    public void setInvoceName(String invoceName) {
        this.invoceName = invoceName;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public BigDecimal getRateMoney() {
        return rateMoney;
    }

    public void setRateMoney(BigDecimal rateMoney) {
        this.rateMoney = rateMoney;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public BigDecimal getRateNot() {
        return rateNot;
    }

    public void setRateNot(BigDecimal rateNot) {
        this.rateNot = rateNot;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}