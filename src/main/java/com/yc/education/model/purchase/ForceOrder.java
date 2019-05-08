package com.yc.education.model.purchase;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  采购--强制结案
 *@Author QuZhangJing
 *@Date 14:52  2018/10/15
 *@Version 1.0
 */
@Table(name = "force_order")
public class ForceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 采购订单
     */
    private String purchaseorder;

    /**
     * 订单日期
     */
    private Date purchasedate;

    /**
     * 订货单号
     */
    private String planorder;

    /**
     * 预计到货日期
     */
    private Date comedate;

    /**
     * 供应商编号
     */
    private String suppliernum;

    /**
     * 供应商名称
     */
    private String suppliername;

    /**
     * 审核人
     */
    private String shpeople;

    /**
     * 审核时间
     */
    private String shdate;

    /**
     * 结案备注
     */
    private String remarks;

    /**
     * 是否删除
     */
    private Integer isdel;

    public ForceOrder() {
    }

    public ForceOrder(long id,String purchaseorder, Date purchasedate, String planorder, Date comedate, String suppliernum, String suppliername, String shpeople, String shdate, String remarks, Integer isdel) {
        this.id = id;
        this.purchaseorder = purchaseorder;
        this.purchasedate = purchasedate;
        this.planorder = planorder;
        this.comedate = comedate;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.remarks = remarks;
        this.isdel = isdel;
    }

    public ForceOrder(String purchaseorder, Date purchasedate, String planorder, Date comedate, String suppliernum, String suppliername, String shpeople, String shdate, String remarks, Integer isdel) {
        this.purchaseorder = purchaseorder;
        this.purchasedate = purchasedate;
        this.planorder = planorder;
        this.comedate = comedate;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.remarks = remarks;
        this.isdel = isdel;
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
     * 获取采购订单
     *
     * @return purchaseorder - 采购订单
     */
    public String getPurchaseorder() {
        return purchaseorder;
    }

    /**
     * 设置采购订单
     *
     * @param purchaseorder 采购订单
     */
    public void setPurchaseorder(String purchaseorder) {
        this.purchaseorder = purchaseorder;
    }

    /**
     * 获取订单日期
     *
     * @return purchasedate - 订单日期
     */
    public Date getPurchasedate() {
        return purchasedate;
    }

    /**
     * 设置订单日期
     *
     * @param purchasedate 订单日期
     */
    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    /**
     * 获取订货单号
     *
     * @return planorder - 订货单号
     */
    public String getPlanorder() {
        return planorder;
    }

    /**
     * 设置订货单号
     *
     * @param planorder 订货单号
     */
    public void setPlanorder(String planorder) {
        this.planorder = planorder;
    }

    /**
     * 获取预计到货日期
     *
     * @return comedate - 预计到货日期
     */
    public Date getComedate() {
        return comedate;
    }

    /**
     * 设置预计到货日期
     *
     * @param comedate 预计到货日期
     */
    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    /**
     * 获取供应商编号
     *
     * @return suppliernum - 供应商编号
     */
    public String getSuppliernum() {
        return suppliernum;
    }

    /**
     * 设置供应商编号
     *
     * @param suppliernum 供应商编号
     */
    public void setSuppliernum(String suppliernum) {
        this.suppliernum = suppliernum;
    }

    /**
     * 获取供应商名称
     *
     * @return suppliername - 供应商名称
     */
    public String getSuppliername() {
        return suppliername;
    }

    /**
     * 设置供应商名称
     *
     * @param suppliername 供应商名称
     */
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    /**
     * 获取审核人
     *
     * @return shpeople - 审核人
     */
    public String getShpeople() {
        return shpeople;
    }

    /**
     * 设置审核人
     *
     * @param shpeople 审核人
     */
    public void setShpeople(String shpeople) {
        this.shpeople = shpeople;
    }

    /**
     * 获取审核时间
     *
     * @return shdate - 审核时间
     */
    public String getShdate() {
        return shdate;
    }

    public void setShdate(String shdate) {
        this.shdate = shdate;
    }

    /**
     * 获取结案备注
     *
     * @return remarks - 结案备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置结案备注
     *
     * @param remarks 结案备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取是否删除
     *
     * @return isdel - 是否删除
     */
    public Integer getIsdel() {
        return isdel;
    }

    /**
     * 设置是否删除
     *
     * @param isdel 是否删除
     */
    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}