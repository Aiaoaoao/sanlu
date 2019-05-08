package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  盘库作业
 *@Author QuZhangJing
 *@Date 19:47  2018/11/7
 *@Version 1.0
 */
@Table(name = "check_stock")
public class CheckStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 盘点日期
     */
    private Date checkdate;

    @Transient
    private String flgTime;

    /**
     * 盘点单号
     */
    private String checkorder;

    /**
     * 盘点仓库编号
     */
    private String depotorder;

    /**
     * 盘点仓库名称
     */
    private String depotname;

    /**
     * 制单人
     */
    private String createpeople;

    /**
     * 审核人
     */
    private String shpeople;

    /**
     * 审核时间
     */
    private String shdate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 审核状态  0、未审核  1、已审核
     */
    private Integer shstatus;

    @Transient
    private String shstatusStr;


    public CheckStock() {
    }

    public CheckStock(Date checkdate, String checkorder, String depotorder, String depotname, String createpeople, String shpeople, String shdate, String remarks, Integer shstatus) {
        this.checkdate = checkdate;
        this.checkorder = checkorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.remarks = remarks;
        this.shstatus = shstatus;
    }

    public CheckStock(long id,Date checkdate, String checkorder, String depotorder, String depotname, String createpeople, String shpeople, String shdate, String remarks, Integer shstatus) {
        this.id = id;
        this.checkdate = checkdate;
        this.checkorder = checkorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.remarks = remarks;
        this.shstatus = shstatus;
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
     * 获取盘点日期
     *
     * @return checkdate - 盘点日期
     */
    public Date getCheckdate() {
        return checkdate;
    }

    /**
     * 设置盘点日期
     *
     * @param checkdate 盘点日期
     */
    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    /**
     * 获取盘点单号
     *
     * @return checkorder - 盘点单号
     */
    public String getCheckorder() {
        return checkorder;
    }

    /**
     * 设置盘点单号
     *
     * @param checkorder 盘点单号
     */
    public void setCheckorder(String checkorder) {
        this.checkorder = checkorder;
    }

    /**
     * 获取盘点仓库编号
     *
     * @return depotorder - 盘点仓库编号
     */
    public String getDepotorder() {
        return depotorder;
    }

    /**
     * 设置盘点仓库编号
     *
     * @param depotorder 盘点仓库编号
     */
    public void setDepotorder(String depotorder) {
        this.depotorder = depotorder;
    }

    /**
     * 获取盘点仓库名称
     *
     * @return depotname - 盘点仓库名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置盘点仓库名称
     *
     * @param depotname 盘点仓库名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
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
     * 获取审核时间
     *
     * @return shdate - 审核时间
     */
    public String getShdate() {
        return shdate;
    }

    /**
     * 设置审核时间
     *
     * @param shdate 审核时间
     */
    public void setShdate(String shdate) {
        this.shdate = shdate;
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
     * 获取审核状态  0、未审核  1、已审核
     *
     * @return shstatus - 审核状态  0、未审核  1、已审核
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置审核状态  0、未审核  1、已审核
     *
     * @param shstatus 审核状态  0、未审核  1、已审核
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
    }

    public String getFlgTime() {
        return flgTime;
    }

    public void setFlgTime(String flgTime) {
        this.flgTime = flgTime;
    }

    public String getShstatusStr() {
        return shstatusStr;
    }

    public void setShstatusStr(String shstatusStr) {
        this.shstatusStr = shstatusStr;
    }
}