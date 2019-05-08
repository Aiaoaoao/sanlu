package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_demand_goods")
public class CustomerDemandGoods {

    /********************** 临时变量 ***********************/
    /**
     * 客户类型 -转换后的String类型
     */
    @Transient
    private String customerTypeStr;


    /********************** 临时变量 ***********************/

    /**
     * 客户需求商品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号备注
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 客户姓名
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 建档日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 建档编号
     */
    @Column(name = "create_no")
    private String createNo;

    /**
     * 客户类别
     */
    @Column(name = "customer_type")
    private String customerType;

    /**
     * 邮政编码
     */
    private String zip;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 创建人
     */
    @Column(name = "create_people")
    private String createPeople;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 客户类型 -转换后的String类型
     */
    public String getCustomerTypeStr() {
        return customerTypeStr;
    }

    /**
     * 客户类型 -转换后的String类型
     */
    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    /**
     * 获取客户需求商品
     *
     * @return id - 客户需求商品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户需求商品
     *
     * @param id 客户需求商品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户id
     *
     * @return customer_id - 客户id
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取客户编号
     *
     * @return customer_name - 客户编号
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户编号
     *
     * @param customerName 客户编号
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 获取建档日期
     *
     * @return create_date - 建档日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置建档日期
     *
     * @param createDate 建档日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取建档编号
     *
     * @return create_no - 建档编号
     */
    public String getCreateNo() {
        return createNo;
    }

    /**
     * 设置建档编号
     *
     * @param createNo 建档编号
     */
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }

    /**
     * 获取客户类别
     *
     * @return customer_type - 客户类别
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * 设置客户类别
     *
     * @param customerType 客户类别
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     * 获取邮政编码
     *
     * @return zip - 邮政编码
     */
    public String getZip() {
        return zip;
    }

    /**
     * 设置邮政编码
     *
     * @param zip 邮政编码
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * 获取联系人（客户基本资料中的联系人id）
     *
     * @return contacts - 联系人（客户基本资料中的联系人id）
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人（客户基本资料中的联系人id）
     *
     * @param contacts 联系人（客户基本资料中的联系人id）
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取创建人
     *
     * @return create_people - 创建人
     */
    public String getCreatePeople() {
        return createPeople;
    }

    /**
     * 设置创建人
     *
     * @param createPeople 创建人
     */
    public void setCreatePeople(String createPeople) {
        this.createPeople = createPeople;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 客户编号备注
     * @return
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 客户编号备注
     * @return
     */
    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
    }
}