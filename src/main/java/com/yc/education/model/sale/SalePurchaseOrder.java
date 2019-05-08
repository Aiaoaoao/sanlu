package com.yc.education.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_purchase_order")
public class SalePurchaseOrder {
    /**
     * 销售-订货单
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 序号
     */
    @Transient
    private Integer no;
    /**
     * 制单日期
     */
    @Transient
    private String createDateStr;
    /**
     * 审核状态
     */
    @Transient
    private String auditStatus;

    /**
     * 制单日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 订货单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 出货仓库
     */
    @Column(name = "warehouse_out")
    private String warehouseOut;

    /**
     * 出货仓库备注
     */
    @Column(name = "warehouse_out_str")
    private String warehouseOutStr;

    /**
     * 客户编号备注
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 客户订单号
     */
    @Column(name = "customer_order_no")
    private String customerOrderNo;

    /**
     * 特价单
     */
    @Column(name = "special_order")
    private Boolean specialOrder;

    /**
     * 税别
     */
    private String tax;

    /**
     * 币别
     */
    private String currency;

    /**
     * 折扣
     */
    private String discount;

    /**
     * 作废
     */
    private Boolean invalid;

    /**
     * 客户类别
     */
    @Column(name = "customer_category")
    private String customerCategory;

    /**
     * 应收余额
     */
    @Column(name = "receivable_balance")
    private BigDecimal receivableBalance;

    /**
     * 业务负责
     */
    @Column(name = "business_leader")
    private String businessLeader;

    /**
     * 业务负责备注
     */
    @Column(name = "business_leader_str")
    private String businessLeaderStr;

    /**
     * 结算方式
     */
    @Column(name = "payment_method")
    private String paymentMethod;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

    /**
     * 审核人
     */
    @Column(name = "audit_people")
    private String auditPeople;

    /**
     * 审核人备注
     */
    @Column(name = "audit_people_str")
    private String auditPeopleStr;

    /**
     * 最后修改人
     */
    @Column(name = "last_update")
    private String lastUpdate;

    /**
     * 最后修改人备注
     */
    @Column(name = "last_update_str")
    private String lastUpdateStr;

    /**
     * 客户名称
     */
    @Column(name = "customer_name")
    private String customerName;

    /**
     * 邮政编码
     */
    private String zip;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 传真
     */
    private String fax;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 送货地址
     */
    @Column(name = "shipping_address")
    private String shippingAddress;

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
     * 添加时间
     */
    private Date addtime;

    /**
     * 订单审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getWarehouseOutStr() {
        return warehouseOutStr;
    }

    public void setWarehouseOutStr(String warehouseOutStr) {
        this.warehouseOutStr = warehouseOutStr;
    }

    /**
     * 获取销售-订货单
     *
     * @return id - 销售-订货单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-订货单
     *
     * @param id 销售-订货单
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取制单日期
     *
     * @return create_date - 制单日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置制单日期
     *
     * @param createDate 制单日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取订货单号
     *
     * @return order_no - 订货单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订货单号
     *
     * @param orderNo 订货单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
     * 获取出货仓库
     *
     * @return warehouse_out - 出货仓库
     */
    public String getWarehouseOut() {
        return warehouseOut;
    }

    /**
     * 设置出货仓库
     *
     * @param warehouseOut 出货仓库
     */
    public void setWarehouseOut(String warehouseOut) {
        this.warehouseOut = warehouseOut;
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
     * 获取客户订单号
     *
     * @return customer_order_no - 客户订单号
     */
    public String getCustomerOrderNo() {
        return customerOrderNo;
    }

    /**
     * 设置客户订单号
     *
     * @param customerOrderNo 客户订单号
     */
    public void setCustomerOrderNo(String customerOrderNo) {
        this.customerOrderNo = customerOrderNo;
    }

    /**
     * 获取特价单
     *
     * @return special_order - 特价单
     */
    public Boolean getSpecialOrder() {
        return specialOrder;
    }

    /**
     * 设置特价单
     *
     * @param specialOrder 特价单
     */
    public void setSpecialOrder(Boolean specialOrder) {
        this.specialOrder = specialOrder;
    }

    /**
     * 获取税别
     *
     * @return tax - 税别
     */
    public String getTax() {
        return tax;
    }

    /**
     * 设置税别
     *
     * @param tax 税别
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * 获取币别
     *
     * @return currency - 币别
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币别
     *
     * @param currency 币别
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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

    /**
     * 获取客户类别
     *
     * @return customer_category - 客户类别
     */
    public String getCustomerCategory() {
        return customerCategory;
    }

    /**
     * 设置客户类别
     *
     * @param customerCategory 客户类别
     */
    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    /**
     * 获取应收余额
     *
     * @return receivable_balance - 应收余额
     */
    public BigDecimal getReceivableBalance() {
        return receivableBalance;
    }

    /**
     * 设置应收余额
     *
     * @param receivableBalance 应收余额
     */
    public void setReceivableBalance(BigDecimal receivableBalance) {
        this.receivableBalance = receivableBalance;
    }

    /**
     * 获取业务负责
     *
     * @return business_leader - 业务负责
     */
    public String getBusinessLeader() {
        return businessLeader;
    }

    /**
     * 设置业务负责
     *
     * @param businessLeader 业务负责
     */
    public void setBusinessLeader(String businessLeader) {
        this.businessLeader = businessLeader;
    }

    /**
     * 获取业务负责备注
     *
     * @return business_leader_str - 业务负责备注
     */
    public String getBusinessLeaderStr() {
        return businessLeaderStr;
    }

    /**
     * 设置业务负责备注
     *
     * @param businessLeaderStr 业务负责备注
     */
    public void setBusinessLeaderStr(String businessLeaderStr) {
        this.businessLeaderStr = businessLeaderStr;
    }

    /**
     * 获取结算方式
     *
     * @return payment_method - 结算方式
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 设置结算方式
     *
     * @param paymentMethod 结算方式
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 获取制单人
     *
     * @return made_people - 制单人
     */
    public String getMadePeople() {
        return madePeople;
    }

    /**
     * 设置制单人
     *
     * @param madePeople 制单人
     */
    public void setMadePeople(String madePeople) {
        this.madePeople = madePeople;
    }

    /**
     * 获取审核人
     *
     * @return audit_people - 审核人
     */
    public String getAuditPeople() {
        return auditPeople;
    }

    /**
     * 设置审核人
     *
     * @param auditPeople 审核人
     */
    public void setAuditPeople(String auditPeople) {
        this.auditPeople = auditPeople;
    }

    /**
     * 获取审核人备注
     *
     * @return audit_people_str - 审核人备注
     */
    public String getAuditPeopleStr() {
        return auditPeopleStr;
    }

    /**
     * 设置审核人备注
     *
     * @param auditPeopleStr 审核人备注
     */
    public void setAuditPeopleStr(String auditPeopleStr) {
        this.auditPeopleStr = auditPeopleStr;
    }

    /**
     * 获取最后修改人
     *
     * @return last_update - 最后修改人
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 设置最后修改人
     *
     * @param lastUpdate 最后修改人
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * 获取最后修改人备注
     *
     * @return last_update_str - 最后修改人备注
     */
    public String getLastUpdateStr() {
        return lastUpdateStr;
    }

    /**
     * 设置最后修改人备注
     *
     * @param lastUpdateStr 最后修改人备注
     */
    public void setLastUpdateStr(String lastUpdateStr) {
        this.lastUpdateStr = lastUpdateStr;
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
     * 获取送货地址
     *
     * @return shipping_address - 送货地址
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * 设置送货地址
     *
     * @param shippingAddress 送货地址
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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
     * 获取订单审核状态
     *
     * @return order_audit - 订单审核状态
     */
    public Boolean getOrderAudit() {
        return orderAudit;
    }

    /**
     * 设置订单审核状态
     *
     * @param orderAudit 订单审核状态
     */
    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}