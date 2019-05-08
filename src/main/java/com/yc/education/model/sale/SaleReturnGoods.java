package com.yc.education.model.sale;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_return_goods")
public class SaleReturnGoods {
    /**
     * 销售-销售退货单
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
     * 创建时间 -字符串
     */
    @Transient
    private String createDateStr;
    /**
     * 退货原因 -字符串
     */
    @Transient
    private String reasonStr;
    /**
     * 税别 -字符串
     */
    @Transient
    private String taxStr;


    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 销退单号
     */
    @Column(name = "pinback_no")
    private String pinbackNo;

    /**
     * 前期单据
     */
    @Column(name = "early_document")
    private Boolean earlyDocument;

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
     * 业务负责人
     */
    @Column(name = "business_leader")
    private String businessLeader;

    /**
     * 业务负责人描述
     */
    @Column(name = "business_leader_str")
    private String businessLeaderStr;

    /**
     * 税别
     */
    private String tax;

    /**
     * 币别
     */
    private String currency;

    /**
     * 入库仓库
     */
    @Column(name = "warehouse_in")
    private String warehouseIn;

    /**
     * 入库仓库描述
     */
    @Column(name = "warehouse_in_str")
    private String warehouseInStr;

    /**
     * 退货原因
     */
    @Column(name = "return_reason")
    private String returnReason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 退货申请人
     */
    @Column(name = "return_reason_people")
    private String returnReasonPeople;

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
     * 审核人描述
     */
    @Column(name = "audit_people_str")
    private String auditPeopleStr;

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
     * 添加时间
     */
    private Date addtime;

    /**
     * 订单审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    /**
     * 单据作废
     */
    private Boolean invalid;

    /**
     * 税率
     */
    @Column(name = "tax_rate")
    private Float taxRate;

    /**
     * 销项发票开票
     */
    private Boolean ticket;

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

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
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

    public String getReasonStr() {
        return reasonStr;
    }

    public void setReasonStr(String reasonStr) {
        this.reasonStr = reasonStr;
    }

    public String getTaxStr() {
        return taxStr;
    }

    public void setTaxStr(String taxStr) {
        this.taxStr = taxStr;
    }

    /**
     * 获取销售-销售退货单
     *
     * @return id - 销售-销售退货单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-销售退货单
     *
     * @param id 销售-销售退货单
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取销退单号
     *
     * @return pinback_no - 销退单号
     */
    public String getPinbackNo() {
        return pinbackNo;
    }

    /**
     * 设置销退单号
     *
     * @param pinbackNo 销退单号
     */
    public void setPinbackNo(String pinbackNo) {
        this.pinbackNo = pinbackNo;
    }

    /**
     * 获取前期单据
     *
     * @return early_document - 前期单据
     */
    public Boolean getEarlyDocument() {
        return earlyDocument;
    }

    /**
     * 设置前期单据
     *
     * @param earlyDocument 前期单据
     */
    public void setEarlyDocument(Boolean earlyDocument) {
        this.earlyDocument = earlyDocument;
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
     * 获取业务负责人
     *
     * @return business_leader - 业务负责人
     */
    public String getBusinessLeader() {
        return businessLeader;
    }

    /**
     * 设置业务负责人
     *
     * @param businessLeader 业务负责人
     */
    public void setBusinessLeader(String businessLeader) {
        this.businessLeader = businessLeader;
    }

    /**
     * 获取业务负责人描述
     *
     * @return business_leader_str - 业务负责人描述
     */
    public String getBusinessLeaderStr() {
        return businessLeaderStr;
    }

    /**
     * 设置业务负责人描述
     *
     * @param businessLeaderStr 业务负责人描述
     */
    public void setBusinessLeaderStr(String businessLeaderStr) {
        this.businessLeaderStr = businessLeaderStr;
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
     * 获取入库仓库
     *
     * @return warehouse_in - 入库仓库
     */
    public String getWarehouseIn() {
        return warehouseIn;
    }

    /**
     * 设置入库仓库
     *
     * @param warehouseIn 入库仓库
     */
    public void setWarehouseIn(String warehouseIn) {
        this.warehouseIn = warehouseIn;
    }


    /**
     * 获取退货原因
     *
     * @return return_reason - 退货原因
     */
    public String getReturnReason() {
        return returnReason;
    }

    /**
     * 设置退货原因
     *
     * @param returnReason 退货原因
     */
    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
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
     * 获取退货申请人
     *
     * @return return_reason_people - 退货申请人
     */
    public String getReturnReasonPeople() {
        return returnReasonPeople;
    }

    /**
     * 设置退货申请人
     *
     * @param returnReasonPeople 退货申请人
     */
    public void setReturnReasonPeople(String returnReasonPeople) {
        this.returnReasonPeople = returnReasonPeople;
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
     * 获取审核人描述
     *
     * @return audit_people_str - 审核人描述
     */
    public String getAuditPeopleStr() {
        return auditPeopleStr;
    }

    /**
     * 设置审核人描述
     *
     * @param auditPeopleStr 审核人描述
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

    public String getWarehouseInStr() {
        return warehouseInStr;
    }

    public void setWarehouseInStr(String warehouseInStr) {
        this.warehouseInStr = warehouseInStr;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}