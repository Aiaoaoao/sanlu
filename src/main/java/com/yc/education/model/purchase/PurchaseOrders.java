package com.yc.education.model.purchase;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 采购订单
 *@Author QuZhangJing
 *@Date 17:36  2018/10/9
 *@Version 1.0
 */
@Table(name = "purchase_orders")
public class PurchaseOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 制单日期
     */
    private Date createdate;


    @Transient
    private String paremdate;


    /**
     * 采购单号
     */
    private String orders;

    /**
     * 供应商编号
     */
    private String suppliernum;

    /**
     * 供应商简称
     */
    private String supplierdes;

    /**
     * 产库编号
     */
    private String warehouseid;

    /**
     * 产库名称
     */
    private String warehousename;

    /**
     * 参考单号
     */
    private String seeorders;

    /**
     * 到货日期
     */
    private Date comedate;

    /**
     * 税别
     */
    private Integer ptax;

    /**
     * 币别
     */
    private Integer pcurrency;

    /**
     * 采购负责类型
     */
    private Integer purpeopletype;

    /**
     * 采购人
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
    private String suppliername;

    /**
     * 联系人
     */
    private String supplierconcat;

    /**
     * 电话
     */
    private String supplierphone;

    /**
     * 传真
     */
    private String supplierfax;

    /**
     * 地址
     */
    private String supplieraddress;

    /**
     * 是否审核
     */
    private int shstatus;

    @Transient
    private String strstatus;

    /**
     * 订单状态 0、进行中 1、已完结
     */
    private int orderstatus;

    /**
     * 是否删除
     */
    private int isdel;

    public PurchaseOrders() {
    }

    public PurchaseOrders(long id, Date createdate, String orders, String suppliernum, String supplierdes, String warehouseid, String warehousename, String seeorders, Date comedate, Integer ptax, Integer pcurrency, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String suppliername, String supplierconcat, String supplierphone, String supplierfax, String supplieraddress, int shstatus, int isdel) {
        this.id = id;
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.supplierdes = supplierdes;
        this.warehouseid = warehouseid;
        this.warehousename = warehousename;
        this.seeorders = seeorders;
        this.comedate = comedate;
        this.ptax = ptax;
        this.pcurrency = pcurrency;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.suppliername = suppliername;
        this.supplierconcat = supplierconcat;
        this.supplierphone = supplierphone;
        this.supplierfax = supplierfax;
        this.supplieraddress = supplieraddress;
        this.shstatus = shstatus;
        this.isdel = isdel;
    }

    public PurchaseOrders(Date createdate, String orders, String suppliernum, String supplierdes, String warehouseid, String warehousename, String seeorders, Date comedate, Integer ptax, Integer pcurrency, Integer purpeopletype, String purpeople, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String suppliername, String supplierconcat, String supplierphone, String supplierfax, String supplieraddress, int shstatus, int isdel) {
        this.createdate = createdate;
        this.orders = orders;
        this.suppliernum = suppliernum;
        this.supplierdes = supplierdes;
        this.warehouseid = warehouseid;
        this.warehousename = warehousename;
        this.seeorders = seeorders;
        this.comedate = comedate;
        this.ptax = ptax;
        this.pcurrency = pcurrency;
        this.purpeopletype = purpeopletype;
        this.purpeople = purpeople;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.suppliername = suppliername;
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
     * 获取采购单号
     *
     * @return orders - 采购单号
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置采购单号
     *
     * @param orders 采购单号
     */
    public void setOrders(String orders) {
        this.orders = orders;
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
     * 获取供应商简称
     *
     * @return supplierdes - 供应商简称
     */
    public String getSupplierdes() {
        return supplierdes;
    }

    /**
     * 设置供应商简称
     *
     * @param supplierdes 供应商简称
     */
    public void setSupplierdes(String supplierdes) {
        this.supplierdes = supplierdes;
    }

    /**
     * 获取产库编号
     *
     * @return warehouseid - 产库编号
     */
    public String getWarehouseid() {
        return warehouseid;
    }

    /**
     * 设置产库编号
     *
     * @param warehouseid 产库编号
     */
    public void setWarehouseid(String warehouseid) {
        this.warehouseid = warehouseid;
    }

    /**
     * 获取产库名称
     *
     * @return warehousename - 产库名称
     */
    public String getWarehousename() {
        return warehousename;
    }

    /**
     * 设置产库名称
     *
     * @param warehousename 产库名称
     */
    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    /**
     * 获取参考单号
     *
     * @return seeorders - 参考单号
     */
    public String getSeeorders() {
        return seeorders;
    }

    /**
     * 设置参考单号
     *
     * @param seeorders 参考单号
     */
    public void setSeeorders(String seeorders) {
        this.seeorders = seeorders;
    }

    /**
     * 获取到货日期
     *
     * @return comedate - 到货日期
     */
    public Date getComedate() {
        return comedate;
    }

    /**
     * 设置到货日期
     *
     * @param comedate 到货日期
     */
    public void setComedate(Date comedate) {
        this.comedate = comedate;
    }

    /**
     * 获取税别
     *
     * @return ptax - 税别
     */
    public Integer getPtax() {
        return ptax;
    }

    /**
     * 设置税别
     *
     * @param ptax 税别
     */
    public void setPtax(Integer ptax) {
        this.ptax = ptax;
    }

    /**
     * 获取币别
     *
     * @return pcurrency - 币别
     */
    public Integer getPcurrency() {
        return pcurrency;
    }

    /**
     * 设置币别
     *
     * @param pcurrency 币别
     */
    public void setPcurrency(Integer pcurrency) {
        this.pcurrency = pcurrency;
    }

    /**
     * 获取采购负责类型
     *
     * @return purpeopletype - 采购负责类型
     */
    public Integer getPurpeopletype() {
        return purpeopletype;
    }

    /**
     * 设置采购负责类型
     *
     * @param purpeopletype 采购负责类型
     */
    public void setPurpeopletype(Integer purpeopletype) {
        this.purpeopletype = purpeopletype;
    }

    /**
     * 获取采购人
     *
     * @return purpeople - 采购人
     */
    public String getPurpeople() {
        return purpeople;
    }

    /**
     * 设置采购人
     *
     * @param purpeople 采购人
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
     * 获取联系人
     *
     * @return supplierconcat - 联系人
     */
    public String getSupplierconcat() {
        return supplierconcat;
    }

    /**
     * 设置联系人
     *
     * @param supplierconcat 联系人
     */
    public void setSupplierconcat(String supplierconcat) {
        this.supplierconcat = supplierconcat;
    }

    /**
     * 获取电话
     *
     * @return supplierphone - 电话
     */
    public String getSupplierphone() {
        return supplierphone;
    }

    /**
     * 设置电话
     *
     * @param supplierphone 电话
     */
    public void setSupplierphone(String supplierphone) {
        this.supplierphone = supplierphone;
    }

    /**
     * 获取传真
     *
     * @return supplierfax - 传真
     */
    public String getSupplierfax() {
        return supplierfax;
    }

    /**
     * 设置传真
     *
     * @param supplierfax 传真
     */
    public void setSupplierfax(String supplierfax) {
        this.supplierfax = supplierfax;
    }

    /**
     * 获取地址
     *
     * @return supplieraddress - 地址
     */
    public String getSupplieraddress() {
        return supplieraddress;
    }

    /**
     * 设置地址
     *
     * @param supplieraddress 地址
     */
    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public int getShstatus() {
        return shstatus;
    }

    public void setShstatus(int shstatus) {
        this.shstatus = shstatus;
    }

    public int getIsdel() {
        return isdel;
    }

    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
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