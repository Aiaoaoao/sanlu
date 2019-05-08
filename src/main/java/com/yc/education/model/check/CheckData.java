package com.yc.education.model.check;

import javax.persistence.*;
import java.util.Date;

/**
 *@Description TODO 考勤资料结转
 *@Author QuZhangJing
 *@Date 16:31  2019/2/19
 *@Version 1.0
 */
@Table(name = "check_data")
public class CheckData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 考勤编号
     */
    private String orders;

    /**
     * 考勤部门
     */
    private String depot;

    /**
     * 考勤开始时间
     */
    private Date starttime;

    /**
     * 考勤结束时间
     */
    private Date endtime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String createpeople;

    /**
     * 建档日期
     */
    private String createdate;

    /**
     * 审核人
     */
    private String shpeople;

    /**
     * 审核日期
     */
    private String shdate;

    /**
     * 最后更新人
     */
    private String updatepeople;

    /**
     * 最后更新日期
     */
    private String updatedate;

    /**
     * 审核状态 默认0 1、已审核
     */
    private Integer shstatus;


    public CheckData(){}

    public CheckData(long id, String orders, String depot, Date starttime, Date endtime, String remarks, String createpeople, String createdate, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus) {
        this.id = id;
        this.orders = orders;
        this.depot = depot;
        this.starttime = starttime;
        this.endtime = endtime;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shstatus = shstatus;
    }

    public CheckData(String orders, String depot, Date starttime, Date endtime, String remarks, String createpeople, String createdate, String shpeople, String shdate, String updatepeople, String updatedate, Integer shstatus) {
        this.orders = orders;
        this.depot = depot;
        this.starttime = starttime;
        this.endtime = endtime;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
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

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * 获取考勤部门
     *
     * @return depot - 考勤部门
     */
    public String getDepot() {
        return depot;
    }

    /**
     * 设置考勤部门
     *
     * @param depot 考勤部门
     */
    public void setDepot(String depot) {
        this.depot = depot;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
     * 获取建档人
     *
     * @return createpeople - 建档人
     */
    public String getCreatepeople() {
        return createpeople;
    }

    /**
     * 设置建档人
     *
     * @param createpeople 建档人
     */
    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople;
    }

    /**
     * 获取建档日期
     *
     * @return createdate - 建档日期
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 设置建档日期
     *
     * @param createdate 建档日期
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
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
     * 获取最后更新人
     *
     * @return updatepeople - 最后更新人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后更新人
     *
     * @param updatepeople 最后更新人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }

    /**
     * 获取最后更新日期
     *
     * @return updatedate - 最后更新日期
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置最后更新日期
     *
     * @param updatedate 最后更新日期
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取审核状态 默认0 1、已审核
     *
     * @return shstatus - 审核状态 默认0 1、已审核
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置审核状态 默认0 1、已审核
     *
     * @param shstatus 审核状态 默认0 1、已审核
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
    }


}