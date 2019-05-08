package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_data_maintenance")
public class CustomerDataMaintenance {
    /**
     * 客户基本资料-资料维护
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
     * 建档编号
     */
    @Column(name = "document_no")
    private String documentNo;

    /**
     * 建档时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 负责人
     */
    @Column(name = "leader_people")
    private String leaderPeople;

    /**
     * 采购人
     */
    @Column(name = "purchase_people")
    private String purchasePeople;

    /**
     * 联络人
     */
    private String contact;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 创业年度
     */
    @Column(name = "startup_year")
    private Date startupYear;

    /**
     * 员工人数
     */
    @Column(name = "employee_number")
    private Integer employeeNumber;

    /**
     * 去年营业
     */
    @Column(name = "last_year_business")
    private String lastYearBusiness;

    /**
     * 今年计划
     */
    @Column(name = "year_plan")
    private String yearPlan;

    /**
     * 工业形态
     */
    private String industry;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取客户基本资料-资料维护
     *
     * @return id - 客户基本资料-资料维护
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户基本资料-资料维护
     *
     * @param id 客户基本资料-资料维护
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
     * 获取建档编号
     *
     * @return document_no - 建档编号
     */
    public String getDocumentNo() {
        return documentNo;
    }

    /**
     * 设置建档编号
     *
     * @param documentNo 建档编号
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * 获取建档时间
     *
     * @return create_date - 建档时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置建档时间
     *
     * @param createDate 建档时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
     * 获取采购人
     *
     * @return purchase_people - 采购人
     */
    public String getPurchasePeople() {
        return purchasePeople;
    }

    /**
     * 设置采购人
     *
     * @param purchasePeople 采购人
     */
    public void setPurchasePeople(String purchasePeople) {
        this.purchasePeople = purchasePeople;
    }

    /**
     * 获取联络人
     *
     * @return contact - 联络人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联络人
     *
     * @param contact 联络人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取电话
     *
     * @return telephone - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * 获取创业年度
     *
     * @return startup_year - 创业年度
     */
    public Date getStartupYear() {
        return startupYear;
    }

    /**
     * 设置创业年度
     *
     * @param startupYear 创业年度
     */
    public void setStartupYear(Date startupYear) {
        this.startupYear = startupYear;
    }

    /**
     * 获取员工人数
     *
     * @return employee_number - 员工人数
     */
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * 设置员工人数
     *
     * @param employeeNumber 员工人数
     */
    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /**
     * 获取去年营业
     *
     * @return last_year_business - 去年营业
     */
    public String getLastYearBusiness() {
        return lastYearBusiness;
    }

    /**
     * 设置去年营业
     *
     * @param lastYearBusiness 去年营业
     */
    public void setLastYearBusiness(String lastYearBusiness) {
        this.lastYearBusiness = lastYearBusiness;
    }

    /**
     * 获取今年计划
     *
     * @return year_plan - 今年计划
     */
    public String getYearPlan() {
        return yearPlan;
    }

    /**
     * 设置今年计划
     *
     * @param yearPlan 今年计划
     */
    public void setYearPlan(String yearPlan) {
        this.yearPlan = yearPlan;
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

    public CustomerDataMaintenance() {
    }

    public CustomerDataMaintenance(Long customerId, String documentNo, Date createDate, String leaderPeople, String purchasePeople, String contact, String telephone, String fax, Date startupYear, Integer employeeNumber, String lastYearBusiness, String yearPlan, String industry, Date addtime) {
        this.customerId = customerId;
        this.documentNo = documentNo;
        this.createDate = createDate;
        this.leaderPeople = leaderPeople;
        this.purchasePeople = purchasePeople;
        this.contact = contact;
        this.telephone = telephone;
        this.fax = fax;
        this.startupYear = startupYear;
        this.employeeNumber = employeeNumber;
        this.lastYearBusiness = lastYearBusiness;
        this.yearPlan = yearPlan;
        this.industry = industry;
        this.addtime = addtime;
    }

    public CustomerDataMaintenance( String documentNo, Date createDate, String leaderPeople, String purchasePeople, String contact, String telephone, String fax, Date startupYear, Integer employeeNumber, String lastYearBusiness, String yearPlan, String industry, Date addtime) {
        this.documentNo = documentNo;
        this.createDate = createDate;
        this.leaderPeople = leaderPeople;
        this.purchasePeople = purchasePeople;
        this.contact = contact;
        this.telephone = telephone;
        this.fax = fax;
        this.startupYear = startupYear;
        this.employeeNumber = employeeNumber;
        this.lastYearBusiness = lastYearBusiness;
        this.yearPlan = yearPlan;
        this.industry = industry;
        this.addtime = addtime;
    }
}