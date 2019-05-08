package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;

@Table(name = "stock_out_sale")
public class StockOutSale {
    /**
     * 销货出库单
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
    private String orderDateStr;
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
     * 出库单号
     */
    @Column(name = "outbound_no")
    private String outboundNo;

    /**
     * 销货单单号
     */
    @Column(name = "sale_no")
    private String saleNo;

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
     * 制单人
     */
    @Column(name = "made_people")
    private String madePeople;

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
     * 审核状态
     */
    @Column(name = "order_audit")
    private Boolean orderAudit;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取销货出库单
     *
     * @return id - 销货出库单
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销货出库单
     *
     * @param id 销货出库单
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
     * 获取出库单号
     *
     * @return outbound_no - 出库单号
     */
    public String getOutboundNo() {
        return outboundNo;
    }

    /**
     * 设置出库单号
     *
     * @param outboundNo 出库单号
     */
    public void setOutboundNo(String outboundNo) {
        this.outboundNo = outboundNo;
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
     * 获取入库仓库描述
     *
     * @return warehouse_in_str - 入库仓库描述
     */
    public String getWarehouseInStr() {
        return warehouseInStr;
    }

    /**
     * 设置入库仓库描述
     *
     * @param warehouseInStr 入库仓库描述
     */
    public void setWarehouseInStr(String warehouseInStr) {
        this.warehouseInStr = warehouseInStr;
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

    /**
     * 获取审核状态
     *
     * @return order_audit - 审核状态
     */
    public Boolean getOrderAudit() {
        return orderAudit;
    }

    /**
     * 设置审核状态
     *
     * @param orderAudit 审核状态
     */
    public void setOrderAudit(Boolean orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public void setOrderDateStr(String orderDateStr) {
        this.orderDateStr = orderDateStr;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }
}