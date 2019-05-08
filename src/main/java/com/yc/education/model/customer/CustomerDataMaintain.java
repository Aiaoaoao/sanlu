package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_data_maintain")
public class CustomerDataMaintain {

    /**
     * @Description 创建时间
     * @Author BlueSky
     * @Date 18:43 2019/4/29
     **/
    @Transient
    private String createDateStr;
    /**
     * 客户资料维护
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号后面的文本框
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 建档编号
     */
    @Column(name = "create_no")
    private String createNo;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 建档日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 系统添加时间
     */
    private Date addtime;

    /**
     * 负责人
     */
    @Column(name = "leader_people")
    private String leaderPeople;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 传真
     */
    private String fax;

    /**
     * 送货地址
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * 发票地址
     */
    @Column(name = "invoice_address")
    private String invoiceAddress;

    /**
     * 采购人
     */
    private String purchase;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建人
     */
    @Column(name = "create_people")
    private String createPeople;

    /**
     * 创建人描述
     */
    @Column(name = "create_people_str")
    private String createPeopleStr;

    /**
     * 最后修改人
     */
    @Column(name = "update_people")
    private String updatePeople;

    /**
     * 最后修改人描述
     */
    @Column(name = "update_people_str")
    private String updatePeopleStr;

    /**
     * 创业年度
     */
    @Column(name = "start_year")
    private String startYear;

    /**
     * 年营业额
     */
    @Column(name = "annual_turnover")
    private String annualTurnover;

    /**
     * 员工数量
     */
    @Column(name = "employee_num")
    private String employeeNum;

    /**
     * 今年预算
     */
    private String budget;

    /**
     * 工业形态
     */
    private String industry;
    /**
     * 主要生产类别
     */
    @Column(name = "production_type")
    private String productionType;
    /**
     * 上游厂商
     */
    private String vendor;
    /**
     * 卫星工厂
     */
    private String factory;

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    /**
     * 获取客户资料维护
     *
     * @return id - 客户资料维护
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户资料维护
     *
     * @param id 客户资料维护
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取客户编号后面的文本框
     *
     * @return customer_no_str - 客户编号后面的文本框
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 设置客户编号后面的文本框
     *
     * @param customerNoStr 客户编号后面的文本框
     */
    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
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
     * 获取客户名称
     *
     * @return customer_name - 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户名称
     *
     * @param customerName 客户名称
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
     * 获取系统添加时间
     *
     * @return addtime - 系统添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置系统添加时间
     *
     * @param addtime 系统添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取负责人
     *
     * @return leader_people - 负责人
     */
    public String getLeaderPeople() {
        return leaderPeople;
    }

    /**
     * 设置负责人
     *
     * @param leaderPeople 负责人
     */
    public void setLeaderPeople(String leaderPeople) {
        this.leaderPeople = leaderPeople;
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
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
     * 获取送货地址
     *
     * @return delivery_address - 送货地址
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 设置送货地址
     *
     * @param deliveryAddress 送货地址
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 获取发票地址
     *
     * @return invoice_address - 发票地址
     */
    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    /**
     * 设置发票地址
     *
     * @param invoiceAddress 发票地址
     */
    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    /**
     * 获取采购人
     *
     * @return purchase - 采购人
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 设置采购人
     *
     * @param purchase 采购人
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取创建人描述
     *
     * @return create_people_str - 创建人描述
     */
    public String getCreatePeopleStr() {
        return createPeopleStr;
    }

    /**
     * 设置创建人描述
     *
     * @param createPeopleStr 创建人描述
     */
    public void setCreatePeopleStr(String createPeopleStr) {
        this.createPeopleStr = createPeopleStr;
    }

    /**
     * 获取最后修改人
     *
     * @return update_people - 最后修改人
     */
    public String getUpdatePeople() {
        return updatePeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatePeople 最后修改人
     */
    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }

    /**
     * 获取最后修改人描述
     *
     * @return update_people_str - 最后修改人描述
     */
    public String getUpdatePeopleStr() {
        return updatePeopleStr;
    }

    /**
     * 设置最后修改人描述
     *
     * @param updatePeopleStr 最后修改人描述
     */
    public void setUpdatePeopleStr(String updatePeopleStr) {
        this.updatePeopleStr = updatePeopleStr;
    }

    /**
     * 获取创业年度
     *
     * @return start_year - 创业年度
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * 设置创业年度
     *
     * @param startYear 创业年度
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    /**
     * 获取年营业额
     *
     * @return annual_turnover - 年营业额
     */
    public String getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * 设置年营业额
     *
     * @param annualTurnover 年营业额
     */
    public void setAnnualTurnover(String annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    /**
     * 获取员工数量
     *
     * @return employee_num - 员工数量
     */
    public String getEmployeeNum() {
        return employeeNum;
    }

    /**
     * 设置员工数量
     *
     * @param employeeNum 员工数量
     */
    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    /**
     * 获取今年预算
     *
     * @return budget - 今年预算
     */
    public String getBudget() {
        return budget;
    }

    /**
     * 设置今年预算
     *
     * @param budget 今年预算
     */
    public void setBudget(String budget) {
        this.budget = budget;
    }

    /**
     * 获取工业形态
     *
     * @return industry - 工业形态
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置工业形态
     *
     * @param industry 工业形态
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }
}