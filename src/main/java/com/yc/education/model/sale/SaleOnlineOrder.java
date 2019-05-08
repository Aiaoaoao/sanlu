package com.yc.education.model.sale;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_online_order")
public class SaleOnlineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description string - 单据审核状态
     * @Author BlueSky
     * @Date 15:50 2019/4/17
     **/
    @Transient
    private String AuditStatus;
    /**
     * 序号
     */
    @Transient
    private Integer no;

    /**
     * 订货日期
     */
    @Transient
    private String orderDateStr;

    /**
     * 订货日期
     */
    @Column(name = "order_date")
    private Date orderDate;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 订单备注
     */
    @Column(name = "order_remark")
    private String orderRemark;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 客户编号备注
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 订货人
     */
    @Column(name = "order_people")
    private String orderPeople;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 邮政编码
     */
    private String zip;

    /**
     * 发票抬头
     */
    @Column(name = "invoice_title")
    private String invoiceTitle;

    /**
     * 发票地址
     */
    @Column(name = "invoice_address")
    private String invoiceAddress;

    /**
     * 审核
     */
    private String audit;

    /**
     * 最后修改人
     */
    @Column(name = "update_last")
    private String updateLast;

    /**
     * 作废人
     */
    @Column(name = "invalid_people")
    private String invalidPeople;

    /**
     * 基本资料备注
     */
    @Column(name = "base_remark")
    private String baseRemark;

    /**
     * 联系电话
     */
    private String phone;

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
     * 审核备注
     */
    @Column(name = "audit_str")
    private String auditStr;

    /**
     * 最后修改人备注
     */
    @Column(name = "update_last_str")
    private String updateLastStr;

    /**
     * 作废人备注
     */
    @Column(name = "invalid_people_str")
    private String invalidPeopleStr;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 作废
     */
    private Boolean invalid;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        AuditStatus = auditStatus;
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
     * 获取订货日期
     *
     * @return order_date - 订货日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 设置订货日期
     *
     * @param orderDate 订货日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
     * 获取订单备注
     *
     * @return order_remark - 订单备注
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * 设置订单备注
     *
     * @param orderRemark 订单备注
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
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
     * 获取客户编号备注
     *
     * @return customer_no_str - 客户编号备注
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 设置客户编号备注
     *
     * @param customerNoStr 客户编号备注
     */
    public void setCustomerNoStr(String customerNoStr) {
        this.customerNoStr = customerNoStr;
    }

    /**
     * 获取订货人
     *
     * @return order_people - 订货人
     */
    public String getOrderPeople() {
        return orderPeople;
    }

    /**
     * 设置订货人
     *
     * @param orderPeople 订货人
     */
    public void setOrderPeople(String orderPeople) {
        this.orderPeople = orderPeople;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * 获取发票抬头
     *
     * @return invoice_title - 发票抬头
     */
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    /**
     * 设置发票抬头
     *
     * @param invoiceTitle 发票抬头
     */
    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
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
     * 获取审核
     *
     * @return audit - 审核
     */
    public String getAudit() {
        return audit;
    }

    /**
     * 设置审核
     *
     * @param audit 审核
     */
    public void setAudit(String audit) {
        this.audit = audit;
    }

    /**
     * 获取最后修改人
     *
     * @return update_last - 最后修改人
     */
    public String getUpdateLast() {
        return updateLast;
    }

    /**
     * 设置最后修改人
     *
     * @param updateLast 最后修改人
     */
    public void setUpdateLast(String updateLast) {
        this.updateLast = updateLast;
    }

    /**
     * 获取作废人
     *
     * @return invalid_people - 作废人
     */
    public String getInvalidPeople() {
        return invalidPeople;
    }

    /**
     * 设置作废人
     *
     * @param invalidPeople 作废人
     */
    public void setInvalidPeople(String invalidPeople) {
        this.invalidPeople = invalidPeople;
    }

    /**
     * 获取基本资料备注
     *
     * @return base_remark - 基本资料备注
     */
    public String getBaseRemark() {
        return baseRemark;
    }

    /**
     * 设置基本资料备注
     *
     * @param baseRemark 基本资料备注
     */
    public void setBaseRemark(String baseRemark) {
        this.baseRemark = baseRemark;
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
     * 获取审核备注
     *
     * @return audit_str - 审核备注
     */
    public String getAuditStr() {
        return auditStr;
    }

    /**
     * 设置审核备注
     *
     * @param auditStr 审核备注
     */
    public void setAuditStr(String auditStr) {
        this.auditStr = auditStr;
    }

    /**
     * 获取最后修改人备注
     *
     * @return update_last_str - 最后修改人备注
     */
    public String getUpdateLastStr() {
        return updateLastStr;
    }

    /**
     * 设置最后修改人备注
     *
     * @param updateLastStr 最后修改人备注
     */
    public void setUpdateLastStr(String updateLastStr) {
        this.updateLastStr = updateLastStr;
    }

    /**
     * 获取作废人备注
     *
     * @return invalid_people_str - 作废人备注
     */
    public String getInvalidPeopleStr() {
        return invalidPeopleStr;
    }

    /**
     * 设置作废人备注
     *
     * @param invalidPeopleStr 作废人备注
     */
    public void setInvalidPeopleStr(String invalidPeopleStr) {
        this.invalidPeopleStr = invalidPeopleStr;
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
     * 获取作废
     *
     * @return invalid - 作废
     */
    public Boolean getInvalid() {
        return invalid;
    }

    /**
     * 设置作废
     *
     * @param invalid 作废
     */
    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }
}