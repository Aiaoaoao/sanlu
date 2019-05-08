package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_input_invoice_info")
public class AccountInputInvoiceInfo {
    /**
     * 账款--进项发票详情
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 进项发票id
     */
    private Long otherid;

    /**
     * 来源单据
     */
    @Column(name = "order_source")
    private String orderSource;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 序号
     */
    @Column(name = "order_num")
    private String orderNum;

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
    @Column(name = "invoice_name")
    private String invoiceName;

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
     * 单价
     */
    private BigDecimal money;

    /**
     * 税率
     */
    private String tax;

    /**
     * 税额
     */
    @Column(name = "tax_money")
    private BigDecimal taxMoney;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取账款--进项发票详情
     *
     * @return id - 账款--进项发票详情
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账款--进项发票详情
     *
     * @param id 账款--进项发票详情
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取进项发票id
     *
     * @return otherid - 进项发票id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置进项发票id
     *
     * @param otherid 进项发票id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取来源单据
     *
     * @return order_source - 来源单据
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置来源单据
     *
     * @param orderSource 来源单据
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
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
     * 获取序号
     *
     * @return order_num - 序号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置序号
     *
     * @param orderNum 序号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
     * @return invoice_name - 发票品名
     */
    public String getInvoiceName() {
        return invoiceName;
    }

    /**
     * 设置发票品名
     *
     * @param invoiceName 发票品名
     */
    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
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
     * 获取税率
     *
     * @return tax - 税率
     */
    public String getTax() {
        return tax;
    }

    /**
     * 设置税率
     *
     * @param tax 税率
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * 获取税额
     *
     * @return tax_money - 税额
     */
    public BigDecimal getTaxMoney() {
        return taxMoney;
    }

    /**
     * 设置税额
     *
     * @param taxMoney 税额
     */
    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}