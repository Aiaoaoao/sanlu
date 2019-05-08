package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;

@Table(name = "purchase_stock")
public class PurchaseStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 制单日期
     */
    @Transient
    private String createDateStr;

    /**
     * 制单日期
     */
    private Date createdate;

    /**
     * 入库单号
     */
    private String stockorder;

    /**
     * 入库仓库编号
     */
    private String depotnum;

    /**
     * 入库仓库名称
     */
    private String depotname;

    /**
     * 入库仓库楼层
     */
    private String depotfloor;

    /**
     * 供应商编号
     */
    private String suppliernum;

    /**
     * 供应商名称
     */
    private String suppliername;

    /**
     * 装箱单号
     */
    private String boxnum;

    /**
     * 质检人编号
     */
    private String inspectnum;

    /**
     * 质检人名称
     */
    private String inspectname;

    /**
     * 制单人
     */
    private String createname;

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
     * 备注
     */
    private String remarks;

    /**
     * 审核状态  0 、 默认   1、已审核
     */
    private Integer shstatus;

    /**
     * 发票号码
     */
    private String invoiceno;

    /**
     * 开票日期
     */
    private String invoicedate;

    /**
     * @Description 成本核算
     * @Author BlueSky
     * @Date 16:32 2019/5/4
     **/
    private Boolean cost;

    /**
     * @Description 添加时间
     * @Author BlueSky
     * @Date 11:36 2019/5/5
     **/
    private Date addtime;

    @Transient
    private String strstatus;

    public PurchaseStock() {
    }

    public PurchaseStock(Date createdate, String stockorder, String depotnum, String depotname, String depotfloor, String suppliernum, String suppliername, String boxnum, String inspectnum, String inspectname, String createname, String shpeople, String shdate, String updatepeople, String updatedate, String remarks, Integer shstatus) {
        this.createdate = createdate;
        this.stockorder = stockorder;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.depotfloor = depotfloor;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.boxnum = boxnum;
        this.inspectnum = inspectnum;
        this.inspectname = inspectname;
        this.createname = createname;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.remarks = remarks;
        this.shstatus = shstatus;
    }


    public PurchaseStock(long id,Date createdate, String stockorder, String depotnum, String depotname, String depotfloor, String suppliernum, String suppliername, String boxnum, String inspectnum, String inspectname, String createname, String shpeople, String shdate, String updatepeople, String updatedate, String remarks, Integer shstatus) {
        this.id = id;
        this.createdate = createdate;
        this.stockorder = stockorder;
        this.depotnum = depotnum;
        this.depotname = depotname;
        this.depotfloor = depotfloor;
        this.suppliernum = suppliernum;
        this.suppliername = suppliername;
        this.boxnum = boxnum;
        this.inspectnum = inspectnum;
        this.inspectname = inspectname;
        this.createname = createname;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.remarks = remarks;
        this.shstatus = shstatus;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Boolean getCost() {
        return cost;
    }

    public void setCost(Boolean cost) {
        this.cost = cost;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
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
     * 获取入库单号
     *
     * @return stockorder - 入库单号
     */
    public String getStockorder() {
        return stockorder;
    }

    /**
     * 设置入库单号
     *
     * @param stockorder 入库单号
     */
    public void setStockorder(String stockorder) {
        this.stockorder = stockorder;
    }

    /**
     * 获取入库仓库编号
     *
     * @return depotnum - 入库仓库编号
     */
    public String getDepotnum() {
        return depotnum;
    }

    /**
     * 设置入库仓库编号
     *
     * @param depotnum 入库仓库编号
     */
    public void setDepotnum(String depotnum) {
        this.depotnum = depotnum;
    }

    /**
     * 获取入库仓库名称
     *
     * @return depotname - 入库仓库名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置入库仓库名称
     *
     * @param depotname 入库仓库名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    /**
     * 获取入库仓库楼层
     *
     * @return depotfloor - 入库仓库楼层
     */
    public String getDepotfloor() {
        return depotfloor;
    }

    /**
     * 设置入库仓库楼层
     *
     * @param depotfloor 入库仓库楼层
     */
    public void setDepotfloor(String depotfloor) {
        this.depotfloor = depotfloor;
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
     * 获取装箱单号
     *
     * @return boxnum - 装箱单号
     */
    public String getBoxnum() {
        return boxnum;
    }

    /**
     * 设置装箱单号
     *
     * @param boxnum 装箱单号
     */
    public void setBoxnum(String boxnum) {
        this.boxnum = boxnum;
    }

    /**
     * 获取质检人编号
     *
     * @return inspectnum - 质检人编号
     */
    public String getInspectnum() {
        return inspectnum;
    }

    /**
     * 设置质检人编号
     *
     * @param inspectnum 质检人编号
     */
    public void setInspectnum(String inspectnum) {
        this.inspectnum = inspectnum;
    }

    /**
     * 获取质检人名称
     *
     * @return inspectname - 质检人名称
     */
    public String getInspectname() {
        return inspectname;
    }

    /**
     * 设置质检人名称
     *
     * @param inspectname 质检人名称
     */
    public void setInspectname(String inspectname) {
        this.inspectname = inspectname;
    }

    /**
     * 获取制单人
     *
     * @return createname - 制单人
     */
    public String getCreatename() {
        return createname;
    }

    /**
     * 设置制单人
     *
     * @param createname 制单人
     */
    public void setCreatename(String createname) {
        this.createname = createname;
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
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 审核状态  0 、 默认   1、已审核
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 审核状态  0 、 默认   1、已审核
     *  @param shstatus 审核状态
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getStrstatus() {
        return strstatus;
    }

    public void setStrstatus(String strstatus) {
        this.strstatus = strstatus;
    }
}