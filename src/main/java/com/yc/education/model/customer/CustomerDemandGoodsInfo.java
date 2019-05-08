package com.yc.education.model.customer;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_demand_goods_info")
public class CustomerDemandGoodsInfo {

    /********************** 临时变量 ***********************/

    /**
     * 建档日期
     */
    @Transient
    private Date createDate;
    /**
     * 建档日期-字符串
     */
    @Transient
    private String createDateStr;

    /**
     * 建档编号
     */
    @Transient
    private String createNo;
    /**
     * 备注
     */
    @Transient
    private String remark;

    /**
     * 客户简称
     */
    @Transient
    private String customerShortCall;

    /********************** 临时变量结束 ***********************/

    /**
     * 客户需求产品-商品需求详情
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 厂牌编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 厂牌
     */
    private String brand;

    /**
     * 需求量
     */
    @Column(name = "quantity_demand")
    private Integer quantityDemand;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 产品大类
     */
    @Column(name = "product_categories")
    private String productCategories;

    /**
     * 产品性质
     */
    @Column(name = "product_nature")
    private String productNature;

    /**
     * 制作方式
     */
    @Column(name = "manufacture_method")
    private String manufactureMethod;

    /**
     * 加工方式
     */
    @Column(name = "process_method")
    private String processMethod;

    /**
     * 加工材料
     */
    @Column(name = "process_material")
    private String processMaterial;

    /**
     * 材质
     */
    private String material;

    /**
     * 连续性
     */
    private String continuity;

    /**
     * 切削油
     */
    @Column(name = "cut_oil")
    private String cutOil;

    /**
     * 使用量
     */
    @Column(name = "use_amount")
    private Integer useAmount;

    /**
     * 切削速度
     */
    @Column(name = "cut_speed")
    private String cutSpeed;

    /**
     * 客户需求商品
     */
    @Column(name = "customer_demand_goods_id")
    private Long customerDemandGoodsId;

    /**
     * 获取客户需求产品-商品需求详情
     *
     * @return id - 客户需求产品-商品需求详情
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户需求产品-商品需求详情
     *
     * @param id 客户需求产品-商品需求详情
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取厂牌编号
     *
     * @return product_no - 厂牌编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置厂牌编号
     *
     * @param productNo 厂牌编号
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
     * 获取厂牌
     *
     * @return brand - 厂牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置厂牌
     *
     * @param brand 厂牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取需求量
     *
     * @return quantity_demand - 需求量
     */
    public Integer getQuantityDemand() {
        return quantityDemand;
    }

    /**
     * 设置需求量
     *
     * @param quantityDemand 需求量
     */
    public void setQuantityDemand(Integer quantityDemand) {
        this.quantityDemand = quantityDemand;
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
     * 获取产品大类
     *
     * @return product_categories - 产品大类
     */
    public String getProductCategories() {
        return productCategories;
    }

    /**
     * 设置产品大类
     *
     * @param productCategories 产品大类
     */
    public void setProductCategories(String productCategories) {
        this.productCategories = productCategories;
    }

    /**
     * 获取产品性质
     *
     * @return product_nature - 产品性质
     */
    public String getProductNature() {
        return productNature;
    }

    /**
     * 设置产品性质
     *
     * @param productNature 产品性质
     */
    public void setProductNature(String productNature) {
        this.productNature = productNature;
    }

    /**
     * 获取制作方式
     *
     * @return manufacture_method - 制作方式
     */
    public String getManufactureMethod() {
        return manufactureMethod;
    }

    /**
     * 设置制作方式
     *
     * @param manufactureMethod 制作方式
     */
    public void setManufactureMethod(String manufactureMethod) {
        this.manufactureMethod = manufactureMethod;
    }

    /**
     * 获取加工材料
     *
     * @return process_method - 加工材料
     */
    public String getProcessMethod() {
        return processMethod;
    }

    /**
     * 设置加工材料
     *
     * @param processMethod 加工材料
     */
    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod;
    }

    /**
     * 获取连续性
     *
     * @return continuity - 连续性
     */
    public String getContinuity() {
        return continuity;
    }

    /**
     * 设置连续性
     *
     * @param continuity 连续性
     */
    public void setContinuity(String continuity) {
        this.continuity = continuity;
    }

    /**
     * 获取切削油
     *
     * @return cut_oil - 切削油
     */
    public String getCutOil() {
        return cutOil;
    }

    /**
     * 设置切削油
     *
     * @param cutOil 切削油
     */
    public void setCutOil(String cutOil) {
        this.cutOil = cutOil;
    }

    /**
     * 获取使用量
     *
     * @return use_amount - 使用量
     */
    public Integer getUseAmount() {
        return useAmount;
    }

    /**
     * 设置使用量
     *
     * @param useAmount 使用量
     */
    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    /**
     * 获取切削速度
     *
     * @return cut_speed - 切削速度
     */
    public String getCutSpeed() {
        return cutSpeed;
    }

    /**
     * 设置切削速度
     *
     * @param cutSpeed 切削速度
     */
    public void setCutSpeed(String cutSpeed) {
        this.cutSpeed = cutSpeed;
    }

    /**
     * 获取客户需求商品
     *
     * @return customer_demand_goods_id - 客户需求商品
     */
    public Long getCustomerDemandGoodsId() {
        return customerDemandGoodsId;
    }

    /**
     * 设置客户需求商品
     *
     * @param customerDemandGoodsId 客户需求商品
     */
    public void setCustomerDemandGoodsId(Long customerDemandGoodsId) {
        this.customerDemandGoodsId = customerDemandGoodsId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateNo() {
        return createNo;
    }

    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerShortCall() {
        return customerShortCall;
    }

    public void setCustomerShortCall(String customerShortCall) {
        this.customerShortCall = customerShortCall;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProcessMaterial() {
        return processMaterial;
    }

    public void setProcessMaterial(String processMaterial) {
        this.processMaterial = processMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}