package com.yc.education.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_online_order_product")
public class SaleOnlineOrderProduct {
    /**
     * 销售-网上订单-订货产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 网上订单id
     */
    @Column(name = "online_order_id")
    private Long onlineOrderId;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

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
     * 金额
     */
    private BigDecimal money;

    /**
     * 可用数量
     */
    @Column(name = "usable_num")
    private Integer usableNum;

    /**
     * 是否有货
     */
    private Boolean ifstock;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取销售-网上订单-订货产品
     *
     * @return id - 销售-网上订单-订货产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-网上订单-订货产品
     *
     * @param id 销售-网上订单-订货产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取网上订单id
     *
     * @return online_order_id - 网上订单id
     */
    public Long getOnlineOrderId() {
        return onlineOrderId;
    }

    /**
     * 设置网上订单id
     *
     * @param onlineOrderId 网上订单id
     */
    public void setOnlineOrderId(Long onlineOrderId) {
        this.onlineOrderId = onlineOrderId;
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
     * 获取可用数量
     *
     * @return usable_num - 可用数量
     */
    public Integer getUsableNum() {
        return usableNum;
    }

    /**
     * 设置可用数量
     *
     * @param usableNum 可用数量
     */
    public void setUsableNum(Integer usableNum) {
        this.usableNum = usableNum;
    }

    /**
     * 获取库存
     *
     * @return ifstock - 库存
     */
    public Boolean getIfstock() {
        return ifstock;
    }

    /**
     * 设置库存
     *
     * @param ifstock 库存
     */
    public void setIfstock(Boolean ifstock) {
        this.ifstock = ifstock;
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
}