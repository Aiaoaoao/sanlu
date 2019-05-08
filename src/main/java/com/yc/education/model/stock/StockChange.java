package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 库存异动
 *@Author QuZhangJing
 *@Date 10:34  2018/11/5
 *@Version 1.0
 */
@Table(name = "stock_change")
public class StockChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 异动日期
     */
    private Date changedate;

    @Transient
    private String flgTime;


    /**
     * 异动单号
     */
    private String changeorder;

    /**
     * 异动仓库编号
     */
    private String depotorder;

    /**
     * 异动仓库名称
     */
    private String depotname;

    /**
     * 异动类型
     */
    private Integer changetype;

    /**
     * 异动申请人编号
     */
    private String peopleorder;

    /**
     * 异动申请人名称
     */
    private String peoplename;

    /**
     * 币率
     */
    private Integer changecurrency;

    /**
     * 异动来源
     */
    private String changesourse;

    /**
     * 制单人员
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
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改时间
     */
    private String updatedate;

    /**
     * 备注
     */
    private String remarks;


    public StockChange() {
    }

    public StockChange(Date changedate, String changeorder, String depotorder, String depotname, Integer changetype, String peopleorder, String peoplename, Integer changecurrency, String changesourse, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String remarks, Integer shstatus) {
        this.changedate = changedate;
        this.changeorder = changeorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.changetype = changetype;
        this.peopleorder = peopleorder;
        this.peoplename = peoplename;
        this.changecurrency = changecurrency;
        this.changesourse = changesourse;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.remarks = remarks;
        this.shstatus = shstatus;
    }

    public StockChange(long id,Date changedate, String changeorder, String depotorder, String depotname, Integer changetype, String peopleorder, String peoplename, Integer changecurrency, String changesourse, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, String remarks, Integer shstatus) {
        this.id = id;
        this.changedate = changedate;
        this.changeorder = changeorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.changetype = changetype;
        this.peopleorder = peopleorder;
        this.peoplename = peoplename;
        this.changecurrency = changecurrency;
        this.changesourse = changesourse;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.remarks = remarks;
        this.shstatus = shstatus;
    }



    /**
     * 是否审核 默认、0      已审核、1
     */
    private Integer shstatus;

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
     * 获取异动日期
     *
     * @return changedate - 异动日期
     */
    public Date getChangedate() {
        return changedate;
    }

    /**
     * 设置异动日期
     *
     * @param changedate 异动日期
     */
    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    /**
     * 获取异动单号
     *
     * @return changeorder - 异动单号
     */
    public String getChangeorder() {
        return changeorder;
    }

    /**
     * 设置异动单号
     *
     * @param changeorder 异动单号
     */
    public void setChangeorder(String changeorder) {
        this.changeorder = changeorder;
    }

    /**
     * 获取异动仓库编号
     *
     * @return depotorder - 异动仓库编号
     */
    public String getDepotorder() {
        return depotorder;
    }

    /**
     * 设置异动仓库编号
     *
     * @param depotorder 异动仓库编号
     */
    public void setDepotorder(String depotorder) {
        this.depotorder = depotorder;
    }

    /**
     * 获取异动仓库名称
     *
     * @return depotname - 异动仓库名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置异动仓库名称
     *
     * @param depotname 异动仓库名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    /**
     * 获取异动类型
     *
     * @return changetype - 异动类型
     */
    public Integer getChangetype() {
        return changetype;
    }



    /**
     * 设置异动类型
     *
     * @param changetype 异动类型
     */
    public void setChangetype(Integer changetype) {
        this.changetype = changetype;
    }

    /**
     * 获取异动申请人编号
     *
     * @return peopleorder - 异动申请人编号
     */
    public String getPeopleorder() {
        return peopleorder;
    }

    /**
     * 设置异动申请人编号
     *
     * @param peopleorder 异动申请人编号
     */
    public void setPeopleorder(String peopleorder) {
        this.peopleorder = peopleorder;
    }

    /**
     * 获取异动申请人名称
     *
     * @return peoplename - 异动申请人名称
     */
    public String getPeoplename() {
        return peoplename;
    }

    /**
     * 设置异动申请人名称
     *
     * @param peoplename 异动申请人名称
     */
    public void setPeoplename(String peoplename) {
        this.peoplename = peoplename;
    }

    /**
     * 获取币率
     *
     * @return changecurrency - 币率
     */
    public Integer getChangecurrency() {
        return changecurrency;
    }


    /**
     * 设置币率
     *
     * @param changecurrency 币率
     */
    public void setChangecurrency(Integer changecurrency) {
        this.changecurrency = changecurrency;
    }
    /**
     * 获取异动来源
     *
     * @return changesourse - 异动来源
     */
    public String getChangesourse() {
        return changesourse;
    }

    /**
     * 设置异动来源
     *
     * @param changesourse 异动来源
     */
    public void setChangesourse(String changesourse) {
        this.changesourse = changesourse;
    }

    /**
     * 获取制单人员
     *
     * @return createpeople - 制单人员
     */
    public String getCreatepeople() {
        return createpeople;
    }

    /**
     * 设置制单人员
     *
     * @param createpeople 制单人员
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
     * 获取最后修改时间
     *
     * @return updatedate - 最后修改时间
     */
    public String getUpdatedate() {
        return updatedate;
    }

/**
     * 设置最后修改时间
     *
     * @param updatedate 最后修改时间
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
     * 获取是否审核 默认、0      已审核、1
     *
     * @return shstatus - 是否审核 默认、0      已审核、1
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置是否审核 默认、0      已审核、1
     *
     * @param shstatus 是否审核 默认、0      已审核、1
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
}