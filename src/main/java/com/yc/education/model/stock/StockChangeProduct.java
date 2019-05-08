package com.yc.education.model.stock;

import javax.persistence.*;
/**
 *@Description TODO 库存异动产品
 *@Author QuZhangJing
 *@Date 20:33  2018/11/5
 *@Version 1.0
 */
@Table(name = "stock_change_product")
public class StockChangeProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 来源单据
     */
    private String soursebill;

    /**
     * 订单编号
     */
    private String sourseorder;

    /**
     * 序号
     */
    private String sort;

    /**
     * 产品编号
     */
    private String productorder;

    /**
     * 产品名称
     */
    private String productname;

    /**
     * 产品数量
     */
    private Integer productnum;

    /**
     * 单位
     */
    private String unit;

    /**
     * 库位编号
     */
    private String depotorder;

    /**
     * 库位名称
     */
    private String depotname;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 库存异动编号
     */
    private Long changestockid;

    @Transient
    private StockChange stockChange;

    @Transient
    private String changeType; //异动类型

    @Transient
    private String changeDate; //异动日期

    @Transient
    private String changeOrder;//异动单号

    @Transient
    private int repaidNum; //已还数量

    @Transient
    private int norepaidNum; //未还数量




    public StockChangeProduct() {

    }

    public StockChangeProduct(String soursebill, String sourseorder, String sort, String productorder, String productname, Integer productnum, String unit, String depotorder, String depotname, String remarks, Long changestockid) {
        this.soursebill = soursebill;
        this.sourseorder = sourseorder;
        this.sort = sort;
        this.productorder = productorder;
        this.productname = productname;
        this.productnum = productnum;
        this.unit = unit;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.remarks = remarks;
        this.changestockid = changestockid;
    }

    public StockChangeProduct(long id,String soursebill, String sourseorder, String sort, String productorder, String productname, Integer productnum, String unit, String depotorder, String depotname, String remarks, Long changestockid) {
        this.id = id;
        this.soursebill = soursebill;
        this.sourseorder = sourseorder;
        this.sort = sort;
        this.productorder = productorder;
        this.productname = productname;
        this.productnum = productnum;
        this.unit = unit;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.remarks = remarks;
        this.changestockid = changestockid;
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
     * 获取来源单据
     *
     * @return soursebill - 来源单据
     */
    public String getSoursebill() {
        return soursebill;
    }

    /**
     * 设置来源单据
     *
     * @param soursebill 来源单据
     */
    public void setSoursebill(String soursebill) {
        this.soursebill = soursebill;
    }

    /**
     * 获取订单编号
     *
     * @return sourseorder - 订单编号
     */
    public String getSourseorder() {
        return sourseorder;
    }

    /**
     * 设置订单编号
     *
     * @param sourseorder 订单编号
     */
    public void setSourseorder(String sourseorder) {
        this.sourseorder = sourseorder;
    }

    /**
     * 获取序号
     *
     * @return sort - 序号
     */
    public String getSort() {
        return sort;
    }

    /**
     * 设置序号
     *
     * @param sort 序号
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 获取产品编号
     *
     * @return productorder - 产品编号
     */
    public String getProductorder() {
        return productorder;
    }

    /**
     * 设置产品编号
     *
     * @param productorder 产品编号
     */
    public void setProductorder(String productorder) {
        this.productorder = productorder;
    }

    /**
     * 获取产品名称
     *
     * @return productname - 产品名称
     */
    public String getProductname() {
        return productname;
    }

    /**
     * 设置产品名称
     *
     * @param productname 产品名称
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * 获取产品数量
     *
     * @return productnum - 产品数量
     */
    public Integer getProductnum() {
        return productnum;
    }

    /**
     * 设置产品数量
     *
     * @param productnum 产品数量
     */
    public void setProductnum(Integer productnum) {
        this.productnum = productnum;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取库位编号
     *
     * @return depotorder - 库位编号
     */
    public String getDepotorder() {
        return depotorder;
    }

    /**
     * 设置库位编号
     *
     * @param depotorder 库位编号
     */
    public void setDepotorder(String depotorder) {
        this.depotorder = depotorder;
    }

    /**
     * 获取库位名称
     *
     * @return depotname - 库位名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置库位名称
     *
     * @param depotname 库位名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
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
     * 获取库存异动编号
     *
     * @return changestockid - 库存异动编号
     */
    public Long getChangestockid() {
        return changestockid;
    }

    /**
     * 设置库存异动编号
     *
     * @param changestockid 库存异动编号
     */
    public void setChangestockid(Long changestockid) {
        this.changestockid = changestockid;
    }

    public StockChange getStockChange() {
        return stockChange;
    }

    public void setStockChange(StockChange stockChange) {
        this.stockChange = stockChange;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeOrder() {
        return changeOrder;
    }

    public void setChangeOrder(String changeOrder) {
        this.changeOrder = changeOrder;
    }

    public int getRepaidNum() {
        return repaidNum;
    }

    public void setRepaidNum(int repaidNum) {
        this.repaidNum = repaidNum;
    }

    public int getNorepaidNum() {
        return norepaidNum;
    }

    public void setNorepaidNum(int norepaidNum) {
        this.norepaidNum = norepaidNum;
    }
}