package com.yc.education.model.sale;

import com.yc.education.model.stock.StockOutSale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_goods")
public class SaleGoods {
    /**
     * 销售-销货单
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 未收金额
     */
    @Transient
    private String receiveNotStr;
    /**
     * 冲账金额
     */
    @Transient
    private String receiveMoneyStr;
    /**
     * 货款金额
     */
    @Transient
    private String moneyStr;
    /**
     * 序号
     */
    @Transient
    private Integer no;
    /**
     * 客户资料是否回传
     */
    @Transient
    private Boolean sentBack;
    /**
     * 销货出库单
     */
    @Transient
    private StockOutSale stockOutSale;
    /**
     * 销货发货跟踪 - 销货出库单审核状态
     */
    @Transient
    private String outboundNoState;
    /**
     * 销货发货跟踪 - 出库单号
     */
    @Transient
    private String outboundNoStr;
    /**
     * 回传日期
     */
    @Transient
    private String passBackDateStr;
    /**
     * 是否需要回传
     */
    @Transient
    private String passBackStr;
    /**
     * 审核状态
     */
    @Transient
    private String auditStatus;
    /**
     * 制单日期
     */
    @Transient
    private String createDateStr;

    /**
     * 支付方式
     */
    @Transient
    private String paymentStr;
    /**
     * 运输方式
     */
    @Transient
    private String carryStr;

    /**
     * 发货状态
     */
    @Transient
    private String deliveryStatusStr;

    /**
     * 销货单详情--订单号
     */
    @Transient
    private String orderNo;

    /**
     * 销货单详情--大类
     */
    @Transient
    private String category;

    /**
     * 制单日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 销售单号
     */
    @Column(name = "sale_no")
    private String saleNo;

    /**
     * 出货仓库
     */
    @Column(name = "warehouse_out")
    private String warehouseOut;

    /**
     * 出货仓库描述
     */
    @Column(name = "warehouse_out_str")
    private String warehouseOutStr;


    /**
     * 客户编号
     */
    @Column(name = "customer_no")
    private String customerNo;

    /**
     * 客户编号描述
     */
    @Column(name = "customer_no_str")
    private String customerNoStr;

    /**
     * 客户订单号
     */
    @Column(name = "customer_order_no")
    private String customerOrderNo;

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
     * 客户类别
     */
    @Column(name = "customer_category")
    private String customerCategory;

    /**
     * 销售应收
     */
    @Column(name = "sale_receivable")
    private BigDecimal saleReceivable;

    /**
     * 业务负责
     */
    @Column(name = "business_leader")
    private String businessLeader;

    /**
     * 业务负责描述
     */
    @Column(name = "business_leader_str")
    private String businessLeaderStr;

    /**
     * 支付方式
     */
    private String payment;

    /**
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

    /**
     * 发货状态
     */
    @Column(name = "delivery_status")
    private String deliveryStatus;

    /**
     * 运输方式
     */
    @Column(name = "carry_method")
    private String carryMethod;

    /**
     * 快递或运输公司
     */
    @Column(name = "carry_method_str")
    private String carryMethodStr;

    /**
     * 重量（kg）
     */
    private Double weight;

    /**
     * 最后修改人
     */
    @Column(name = "last_update")
    private String lastUpdate;

    /**
     * 最后修改人描述
     */
    @Column(name = "last_update_str")
    private String lastUpdateStr;

    /**
     * 审核人
     */
    private String audit;

    /**
     * 审核人描述
     */
    @Column(name = "audit_str")
    private String auditStr;

    /**
     * 客户姓名
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
     * 作废
     */
    private Boolean invalid;

    /**
     * 特批单
     */
    @Column(name = "special_order")
    private Boolean specialOrder;

    /**
     * 特价单
     */
    @Column(name = "special_price_order")
    private Boolean specialPriceOrder;

    /**
     * 单据审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * 回传
     */
    @Column(name = "pass_back")
    private Boolean passBack;

    /**
     * 财务
     */
    @Column(name = "financial")
    private String financial;

    /**
     * 回传时间
     */
    @Column(name = "pass_back_date")
    private Date passBackDate;

    /**
     * 已核销项发票
     */
    @Column(name = "order_invoice")
    private Boolean orderInvoice;

    /**
     * 导出到销货出库单
     */
    @Column(name = "import_outbound_audit")
    private Boolean importOutboundAudit;

    /**
     * 税率
     */
    @Column(name = "tax_rate")
    private Float taxRate;


    /**
     * 货款金额
     */
    private BigDecimal money;

    /**
     * 税款金额
     */
    @Column(name = "money_tax")
    private BigDecimal moneyTax;

    /**
     * 销货单开票情况
     */
    private Boolean ticket;


    /**
     * 导出到快递寄件
     */
    @Column(name = "import_express_audit")
    private Boolean importExpressAudit;

    /**
     * 冲款金额
     */
    @Column(name = "receive_money")
    private BigDecimal receiveMoney;

    public BigDecimal getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getMoneyStr() {
        return moneyStr;
    }

    public void setMoneyStr(String moneyStr) {
        this.moneyStr = moneyStr;
    }

    public Boolean getImportExpressAudit() {
        return importExpressAudit;
    }

    public void setImportExpressAudit(Boolean importExpressAudit) {
        this.importExpressAudit = importExpressAudit;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }

    public Boolean getTicket() {
        return ticket;
    }

    public void setTicket(Boolean ticket) {
        this.ticket = ticket;
    }

    public Float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    public Boolean getImportOutboundAudit() {
        return importOutboundAudit;
    }

    public void setImportOutboundAudit(Boolean importOutboundAudit) {
        this.importOutboundAudit = importOutboundAudit;
    }

    public Boolean getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(Boolean orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public Boolean getPassBack() {
        return passBack;
    }

    public void setPassBack(Boolean passBack) {
        this.passBack = passBack;
    }

    public String getFinancial() {
        return financial;
    }

    public void setFinancial(String financial) {
        this.financial = financial;
    }

    public Date getPassBackDate() {
        return passBackDate;
    }

    public void setPassBackDate(Date passBackDate) {
        this.passBackDate = passBackDate;
    }

    @Transient
    private String status;

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Boolean getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public Boolean getSpecialOrder() {
        return specialOrder;
    }

    public void setSpecialOrder(Boolean specialOrder) {
        this.specialOrder = specialOrder;
    }

    public Boolean getSpecialPriceOrder() {
        return specialPriceOrder;
    }

    public void setSpecialPriceOrder(Boolean specialPriceOrder) {
        this.specialPriceOrder = specialPriceOrder;
    }

    /**
     * 获取销售-销货单
     *
     * @return id - 销售-销货单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-销货单
     *
     * @param id 销售-销货单
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
     * 获取销售单号
     *
     * @return sale_no - 销售单号
     */
    public String getSaleNo() {
        return saleNo;
    }

    /**
     * 设置销售单号
     *
     * @param saleNo 销售单号
     */
    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
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
     * 获取出货仓库描述
     *
     * @return warehouse_out_str - 出货仓库描述
     */
    public String getWarehouseOutStr() {
        return warehouseOutStr;
    }

    /**
     * 设置出货仓库描述
     *
     * @param warehouseOutStr 出货仓库描述
     */
    public void setWarehouseOutStr(String warehouseOutStr) {
        this.warehouseOutStr = warehouseOutStr;
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
     * 获取客户编号描述
     *
     * @return customer_no_str - 客户编号描述
     */
    public String getCustomerNoStr() {
        return customerNoStr;
    }

    /**
     * 设置客户编号描述
     *
     * @param customerNoStr 客户编号描述
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
     * 获取销售应收
     *
     * @return sale_receivable - 销售应收
     */
    public BigDecimal getSaleReceivable() {
        return saleReceivable;
    }

    /**
     * 设置销售应收
     *
     * @param saleReceivable 销售应收
     */
    public void setSaleReceivable(BigDecimal saleReceivable) {
        this.saleReceivable = saleReceivable;
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
     * 获取业务负责描述
     *
     * @return business_leader_str - 业务负责描述
     */
    public String getBusinessLeaderStr() {
        return businessLeaderStr;
    }

    /**
     * 设置业务负责描述
     *
     * @param businessLeaderStr 业务负责描述
     */
    public void setBusinessLeaderStr(String businessLeaderStr) {
        this.businessLeaderStr = businessLeaderStr;
    }

    /**
     * 获取支付方式
     *
     * @return payment - 支付方式
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 设置支付方式
     *
     * @param payment 支付方式
     */
    public void setPayment(String payment) {
        this.payment = payment;
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
     * 获取发货状态
     *
     * @return delivery_status - 发货状态
     */
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * 设置发货状态
     *
     * @param deliveryStatus 发货状态
     */
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * 获取运输方式
     *
     * @return carry_method - 运输方式
     */
    public String getCarryMethod() {
        return carryMethod;
    }

    /**
     * 设置运输方式
     *
     * @param carryMethod 运输方式
     */
    public void setCarryMethod(String carryMethod) {
        this.carryMethod = carryMethod;
    }

    /**
     * 获取快递或运输公司
     *
     * @return carry_method_str - 快递或运输公司
     */
    public String getCarryMethodStr() {
        return carryMethodStr;
    }

    /**
     * 设置快递或运输公司
     *
     * @param carryMethodStr 快递或运输公司
     */
    public void setCarryMethodStr(String carryMethodStr) {
        this.carryMethodStr = carryMethodStr;
    }

    /**
     * 获取重量（kg）
     *
     * @return weight - 重量（kg）
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置重量（kg）
     *
     * @param weight 重量（kg）
     */
    public void setWeight(Double weight) {
        this.weight = weight;
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
     * 获取最后修改人描述
     *
     * @return last_update_str - 最后修改人描述
     */
    public String getLastUpdateStr() {
        return lastUpdateStr;
    }

    /**
     * 设置最后修改人描述
     *
     * @param lastUpdateStr 最后修改人描述
     */
    public void setLastUpdateStr(String lastUpdateStr) {
        this.lastUpdateStr = lastUpdateStr;
    }

    /**
     * 获取审核人
     *
     * @return audit - 审核人
     */
    public String getAudit() {
        return audit;
    }

    /**
     * 设置审核人
     *
     * @param audit 审核人
     */
    public void setAudit(String audit) {
        this.audit = audit;
    }

    /**
     * 获取审核人描述
     *
     * @return audit_str - 审核人描述
     */
    public String getAuditStr() {
        return auditStr;
    }

    /**
     * 设置审核人描述
     *
     * @param auditStr 审核人描述
     */
    public void setAuditStr(String auditStr) {
        this.auditStr = auditStr;
    }

    /**
     * 获取客户姓名
     *
     * @return customer_name - 客户姓名
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设置客户姓名
     *
     * @param customerName 客户姓名
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStr() {
        return paymentStr;
    }

    public void setPaymentStr(String paymentStr) {
        this.paymentStr = paymentStr;
    }

    public String getCarryStr() {
        return carryStr;
    }

    public void setCarryStr(String carryStr) {
        this.carryStr = carryStr;
    }

    public String getDeliveryStatusStr() {
        return deliveryStatusStr;
    }

    public void setDeliveryStatusStr(String deliveryStatusStr) {
        this.deliveryStatusStr = deliveryStatusStr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPassBackDateStr() {
        return passBackDateStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public void setPassBackDateStr(String passBackDateStr) {
        this.passBackDateStr = passBackDateStr;
    }

    public String getPassBackStr() {
        return passBackStr;
    }

    public void setPassBackStr(String passBackStr) {
        this.passBackStr = passBackStr;
    }

    public String getOutboundNoStr() {
        return outboundNoStr;
    }

    public void setOutboundNoStr(String outboundNoStr) {
        this.outboundNoStr = outboundNoStr;
    }

    public String getOutboundNoState() {
        return outboundNoState;
    }

    public StockOutSale getStockOutSale() {
        return stockOutSale;
    }

    public void setStockOutSale(StockOutSale stockOutSale) {
        this.stockOutSale = stockOutSale;
    }

    public void setOutboundNoState(String outboundNoState) {
        this.outboundNoState = outboundNoState;
    }

    public Boolean getSentBack() {
        return sentBack;
    }

    public void setSentBack(Boolean sentBack) {
        this.sentBack = sentBack;
    }

    public String getReceiveNotStr() {
        return receiveNotStr;
    }

    public void setReceiveNotStr(String receiveNotStr) {
        this.receiveNotStr = receiveNotStr;
    }

    public String getReceiveMoneyStr() {
        return receiveMoneyStr;
    }

    public void setReceiveMoneyStr(String receiveMoneyStr) {
        this.receiveMoneyStr = receiveMoneyStr;
    }
}