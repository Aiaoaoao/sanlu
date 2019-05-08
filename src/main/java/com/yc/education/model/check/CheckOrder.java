package com.yc.education.model.check;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 请假/加班/出差申请单
 *@Author QuZhangJing
 *@Date 16:25  2018/11/22
 *@Version 1.0
 */
@Table(name = "check_order")
public class CheckOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 制单日期
     */
    private Date checkdate;

    /**
     * 申请单号
     */
    private String checkorder;

    /**
     * 部门编号
     */
    private String depotorder;

    /**
     * 部门名称
     */
    private String depotname;

    /**
     * 申请人编号
     */
    private String applicantorder;

    /**
     * 申请人名称
     */
    private String applicantname;

    /**
     * 申请人类型
     */
    private String applytype;

    /**
     * 申请人明细
     */
    private String applydes;

    /**
     * 开始时间
     */
    private Date startdate;

    /**
     * 结束时间
     */
    private Date enddate;

    /**
     * 备注
     */
    private String remarks;

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
     * 审核状态0、默认未审核 1 、已审核
     */
    private Integer shstatus;

    public CheckOrder() {

    }

    public CheckOrder(Date checkdate, String checkorder, String depotorder, String depotname, String applicantorder, String applicantname, String applytype, String applydes, Date startdate, Date enddate, String remarks, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus) {
        this.checkdate = checkdate;
        this.checkorder = checkorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.applicantorder = applicantorder;
        this.applicantname = applicantname;
        this.applytype = applytype;
        this.applydes = applydes;
        this.startdate = startdate;
        this.enddate = enddate;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shstatus = shstatus;
    }

    public CheckOrder(long id,Date checkdate, String checkorder, String depotorder, String depotname, String applicantorder, String applicantname, String applytype, String applydes, Date startdate, Date enddate, String remarks, String createpeople, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus) {
        this.id = id;
        this.checkdate = checkdate;
        this.checkorder = checkorder;
        this.depotorder = depotorder;
        this.depotname = depotname;
        this.applicantorder = applicantorder;
        this.applicantname = applicantname;
        this.applytype = applytype;
        this.applydes = applydes;
        this.startdate = startdate;
        this.enddate = enddate;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
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
     * 获取制单日期
     *
     * @return checkdate - 制单日期
     */
    public Date getCheckdate() {
        return checkdate;
    }

    /**
     * 设置制单日期
     *
     * @param checkdate 制单日期
     */
    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    /**
     * 获取申请单号
     *
     * @return checkorder - 申请单号
     */
    public String getCheckorder() {
        return checkorder;
    }

    /**
     * 设置申请单号
     *
     * @param checkorder 申请单号
     */
    public void setCheckorder(String checkorder) {
        this.checkorder = checkorder;
    }

    /**
     * 获取部门编号
     *
     * @return depotorder - 部门编号
     */
    public String getDepotorder() {
        return depotorder;
    }

    /**
     * 设置部门编号
     *
     * @param depotorder 部门编号
     */
    public void setDepotorder(String depotorder) {
        this.depotorder = depotorder;
    }

    /**
     * 获取部门名称
     *
     * @return depotname - 部门名称
     */
    public String getDepotname() {
        return depotname;
    }

    /**
     * 设置部门名称
     *
     * @param depotname 部门名称
     */
    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    /**
     * 获取申请人编号
     *
     * @return applicantorder - 申请人编号
     */
    public String getApplicantorder() {
        return applicantorder;
    }

    /**
     * 设置申请人编号
     *
     * @param applicantorder 申请人编号
     */
    public void setApplicantorder(String applicantorder) {
        this.applicantorder = applicantorder;
    }

    /**
     * 获取申请人名称
     *
     * @return applicantname - 申请人名称
     */
    public String getApplicantname() {
        return applicantname;
    }

    /**
     * 设置申请人名称
     *
     * @param applicantname 申请人名称
     */
    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    /**
     * 获取申请人类型
     *
     * @return applytype - 申请人类型
     */
    public String getApplytype() {
        return applytype;
    }

    /**
     * 设置申请人类型
     *
     * @param applytype 申请人类型
     */
    public void setApplytype(String applytype) {
        this.applytype = applytype;
    }

    /**
     * 获取申请人明细
     *
     * @return applydes - 申请人明细
     */
    public String getApplydes() {
        return applydes;
    }

    /**
     * 设置申请人明细
     *
     * @param applydes 申请人明细
     */
    public void setApplydes(String applydes) {
        this.applydes = applydes;
    }

    /**
     * 获取开始时间
     *
     * @return startdate - 开始时间
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置开始时间
     *
     * @param startdate 开始时间
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取结束时间
     *
     * @return enddate - 结束时间
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 设置结束时间
     *
     * @param enddate 结束时间
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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
     * 获取审核状态0、默认未审核 1 、已审核
     *
     * @return shstatus - 审核状态0、默认未审核 1 、已审核
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置审核状态0、默认未审核 1 、已审核
     *
     * @param shstatus 审核状态0、默认未审核 1 、已审核
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
    }
}