package com.yc.education.model.customer;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javafx.scene.control.CheckBox;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


@Table(name = "customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description 序号
     * @Author BlueSky
     * @Date 20:06 2019/4/25
     **/
    @Transient
    private Integer no;

    //============================== 现有客户中临时字段 --> 开始 ==================================

    /**
     * 客户类型
     */
    @Transient
    @Column(name = "customer_category")
    private String customerType;

    /**
     * 客户等级
     */
    @Transient
    @Column(name = "customer_level")
    private String customerLevel;

    /**
     * 备注
     */
    @Transient
    private String remark;

    //============================== 现有客户中临时字段 --> 结束 ==================================

    /**
     * 编号
     */
    @Column(name = "customer_code")
    private String customerCode;

    /**
     * 简称
     */
    @Column(name = "short_name")
    private String shortName;

    private String name;

    /**
     * 注册地址
     */
    @Column(name = "register_address")
    private String registerAddress;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date addtime;

    /**
     * 一般客户
     */
    @Column(name = "general_customer")
    private Boolean generalCustomer;

    /**
     * 客户可用余额
     */
    @Column(name = "useable_money")
    private BigDecimal useableMoney;

    /**
     * 客户删除状态
     */
    @Column(name = "delete_is")
    private Boolean deleteIs;

    public Boolean getDeleteIs() {
        return deleteIs;
    }

    public void setDeleteIs(Boolean deleteIs) {
        this.deleteIs = deleteIs;
    }

    public BigDecimal getUseableMoney() {
        return useableMoney;
    }

    public void setUseableMoney(BigDecimal useableMoney) {
        this.useableMoney = useableMoney;
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
     * 获取编号
     *
     * @return customer_code - 编号
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * 设置编号
     *
     * @param customerCode 编号
     */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * 获取简称
     *
     * @return short_name - 简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置简称
     *
     * @param shortName 简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取注册地址
     *
     * @return register_address - 注册地址
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * 设置注册地址
     *
     * @param registerAddress 注册地址
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    /**
     * @return addtime
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * @param addtime
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }


    public Boolean getGeneralCustomer() {
        return generalCustomer;
    }

    public void setGeneralCustomer(Boolean generalCustomer) {
        this.generalCustomer = generalCustomer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Customer(Long id, String customerType, String customerLevel, String remark, String customerCode, String shortName, Boolean generalCustomer) {
        this.id = id;
        this.customerType = customerType;
        this.customerLevel = customerLevel;
        this.remark = remark;
        this.customerCode = customerCode;
        this.shortName = shortName;
        this.generalCustomer = generalCustomer;
    }

    public Customer() {
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}