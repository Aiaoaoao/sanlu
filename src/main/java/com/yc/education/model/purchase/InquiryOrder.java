package com.yc.education.model.purchase;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 询价单
 *@Author QuZhangJing
 *@Date 17:10  2018/9/27
 *@Version 1.0
 */
@Table(name = "inquiry_order")
public class InquiryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 制单日期
     */
    private Date createdate;

    /**
     * String 日期
     */
    @Transient
    private String paremdate;


    /**
     * 询价单号
     */
    private String orders;

    /**
     * 订单编号
     */
    private String suppliernum;

    /**
     * 供应商名称
     */
    private String suppliername;

    /**
     * 税别
     */
    private Integer taxs;

    /**
     * 币别
     */
    private Integer currencys;

    /**
     * 回复日期
     */
    private Date replydate;

    /**
     * 有效日期
     */
    private Date validdate;

    /**
     * 采购负责人类型
     */
    private Integer purpeopletype;

    /**
     * 采购负责人
     */
    private String purpeople;

    /**
     * 制单人
     */
    private String createpeople;

    /**
     * 审核人
     */
    private String shpeople;

    /**
     * 审核日期
     */
    private String shdate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改日期
     */
    private String updatedate;

    /**
     * 供应商名称
     */
    private String supplierinfo;

    /**
     * 供应商联系人
     */
    private String supplierconcat;

    /**
     * 联系电话
     */
    private String supplierphone;

    /**
     * 供应商传真
     */
    private String supplierfax;

    /**
     * 供应商默认地址
     */
    private String supplieraddress;

    /**
     * 是否审核
     */
    private Integer shstatus;

    /**
     * 是否审核
     */
    @Transient
    private String strstatus;


    /**
     * 是否删除
     */
    private Integer isdel;

    public InquiryOrder() {

    }

    public InquiryOrder(Date createdate, String orders, String suppliernum, String suppliername, Integer taxs, Integer currencys, Date replydate, Date validdate, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus, Integer isdel) {
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.taxs = taxs;
        this.currencys = currencys;
        this.replydate = replydate;
        this.validdate = validdate;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shstatus = shstatus;
        this.isdel = isdel;
    }

    public InquiryOrder(long id,Date createdate, String orders, String suppliernum, String suppliername, Integer taxs, Integer currencys, Date replydate, Date validdate, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus, Integer isdel) {
        this.id=id;
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.taxs = taxs;
        this.currencys = currencys;
        this.replydate = replydate;
        this.validdate = validdate;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shstatus = shstatus;
        this.isdel = isdel;
    }

    public InquiryOrder(long id,Date createdate, String orders, String suppliernum, String suppliername, Integer taxs, Integer currencys, Date replydate, Date validdate, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String supplierinfo, String supplierconcat, String supplierphone, String supplierfax, String supplieraddress, Integer shstatus, Integer isdel) {
        this.id=id;
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.taxs = taxs;
        this.currencys = currencys;
        this.replydate = replydate;
        this.validdate = validdate;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.supplierinfo = supplierinfo;
        this.supplierconcat = supplierconcat;
        this.supplierphone = supplierphone;
        this.supplierfax = supplierfax;
        this.supplieraddress = supplieraddress;
        this.shstatus = shstatus;
        this.isdel = isdel;
    }

    public InquiryOrder(Date createdate, String orders, String suppliernum, String suppliername, Integer taxs, Integer currencys, Date replydate, Date validdate, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String supplierinfo, String supplierconcat, String supplierphone, String supplierfax, String supplieraddress, Integer shstatus, Integer isdel) {
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.taxs = taxs;
        this.currencys = currencys;
        this.replydate = replydate;
        this.validdate = validdate;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.supplierinfo = supplierinfo;
        this.supplierconcat = supplierconcat;
        this.supplierphone = supplierphone;
        this.supplierfax = supplierfax;
        this.supplieraddress = supplieraddress;
        this.shstatus = shstatus;
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
     * 获取制单日期
     *
     * @return createdate - 制单日期
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置制单日期
     *
     * @param createdate 制单日期
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取询价单号
     *
     * @return orders - 询价单号
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置询价单号
     *
     * @param orders 询价单号
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * 获取订单编号
     *
     * @return suppliernum - 订单编号
     */
    public String getSuppliernum() {
        return suppliernum;
    }

    /**
     * 设置订单编号
     *
     * @param suppliernum 订单编号
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
     * 获取税别
     *
     * @return taxs - 税别
     */
    public Integer getTaxs() {
        return taxs;
    }

    /**
     * 设置税别
     *
     * @param taxs 税别
     */
    public void setTaxs(Integer taxs) {
        this.taxs = taxs;
    }

    /**
     * 获取币别
     *
     * @return currencys - 币别
     */
    public Integer getCurrencys() {
        return currencys;
    }

    /**
     * 设置币别
     *
     * @param currencys 币别
     */
    public void setCurrencys(Integer currencys) {
        this.currencys = currencys;
    }

    /**
     * 获取回复日期
     *
     * @return replydate - 回复日期
     */
    public Date getReplydate() {
        return replydate;
    }

    /**
     * 设置回复日期
     *
     * @param replydate 回复日期
     */
    public void setReplydate(Date replydate) {
        this.replydate = replydate;
    }

    /**
     * 获取有效日期
     *
     * @return validdate - 有效日期
     */
    public Date getValiddate() {
        return validdate;
    }

    /**
     * 设置有效日期
     *
     * @param validdate 有效日期
     */
    public void setValiddate(Date validdate) {
        this.validdate = validdate;
    }

    /**
     * 获取采购负责人类型
     *
     * @return purpeopletype - 采购负责人类型
     */
    public Integer getPurpeopletype() {
        return purpeopletype;
    }

    /**
     * 设置采购负责人类型
     *
     * @param purpeopletype 采购负责人类型
     */
    public void setPurpeopletype(Integer purpeopletype) {
        this.purpeopletype = purpeopletype;
    }

    /**
     * 获取采购负责人
     *
     * @return purpeople - 采购负责人
     */
    public String getPurpeople() {
        return purpeople;
    }

    /**
     * 设置采购负责人
     *
     * @param purpeople 采购负责人
     */
    public void setPurpeople(String purpeople) {
        this.purpeople = purpeople;
    }

    /**
     * 获取制单人
     *
     * @return createpeople - 制单人
     */
    public String getCreatepeople() {
        return createpeople;
    }

    /**
     * 设置制单人
     *
     * @param createpeople 制单人
     */
    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople;
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
     * 获取审核日期
     *
     * @return shdate - 审核日期
     */
    public String getShdate() {
        return shdate;
    }

    /**
     * 设置审核日期
     *
     * @param shdate 审核日期
     */
    public void setShdate(String shdate) {
        this.shdate = shdate;
    }

    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }

    /**
     * 获取最后修改日期
     *
     * @return updatedate - 最后修改日期
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置最后修改日期
     *
     * @param updatedate 最后修改日期
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取是否审核
     *
     * @return shstatus - 是否审核
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置是否审核
     *
     * @param shstatus 是否审核
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
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

    public String getSupplierinfo() {
        return supplierinfo;
    }

    public void setSupplierinfo(String supplierinfo) {
        this.supplierinfo = supplierinfo;
    }

    public String getSupplierconcat() {
        return supplierconcat;
    }

    public void setSupplierconcat(String supplierconcat) {
        this.supplierconcat = supplierconcat;
    }

    public String getSupplierphone() {
        return supplierphone;
    }

    public void setSupplierphone(String supplierphone) {
        this.supplierphone = supplierphone;
    }

    public String getSupplierfax() {
        return supplierfax;
    }

    public void setSupplierfax(String supplierfax) {
        this.supplierfax = supplierfax;
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public String getParemdate() {
        return paremdate;
    }

    public void setParemdate(String paremdate) {
        this.paremdate = paremdate;
    }

    public String getStrstatus() {
        return strstatus;
    }

    public void setStrstatus(String strstatus) {
        this.strstatus = strstatus;
    }
}