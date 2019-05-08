package com.yc.education.model.check;

import javax.persistence.*;
/**
 *@Description TODO 考勤资料结转 ---  早退员工记录
 *@Author QuZhangJing
 *@Date 10:22  2019/2/22
 *@Version 1.0
 */
@Table(name = "check_data_leaveearly")
public class CheckDataLeaveearly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 工号
     */
    private String jobnumber;

    /**
     * 姓名
     */
    private String workname;

    /**
     * 添加时间
     */
    private String addtime;

    /**
     * 早退次数
     */
    private String leaveearlytime;

    /**
     * 早退分钟
     */
    private String leaveearlyminute;

    /**
     * 考勤资料编号
     */
    private Long checkdataid;

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
     * 获取工号
     *
     * @return jobnumber - 工号
     */
    public String getJobnumber() {
        return jobnumber;
    }

    /**
     * 设置工号
     *
     * @param jobnumber 工号
     */
    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    /**
     * 获取姓名
     *
     * @return workname - 姓名
     */
    public String getWorkname() {
        return workname;
    }

    /**
     * 设置姓名
     *
     * @param workname 姓名
     */
    public void setWorkname(String workname) {
        this.workname = workname;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public String getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取早退次数
     *
     * @return leaveearlytime - 早退次数
     */
    public String getLeaveearlytime() {
        return leaveearlytime;
    }

    /**
     * 设置早退次数
     *
     * @param leaveearlytime 早退次数
     */
    public void setLeaveearlytime(String leaveearlytime) {
        this.leaveearlytime = leaveearlytime;
    }

    /**
     * 获取早退分钟
     *
     * @return leaveearlyminute - 早退分钟
     */
    public String getLeaveearlyminute() {
        return leaveearlyminute;
    }

    /**
     * 设置早退分钟
     *
     * @param leaveearlyminute 早退分钟
     */
    public void setLeaveearlyminute(String leaveearlyminute) {
        this.leaveearlyminute = leaveearlyminute;
    }

    /**
     * 获取考勤资料编号
     *
     * @return checkdataid - 考勤资料编号
     */
    public Long getCheckdataid() {
        return checkdataid;
    }

    /**
     * 设置考勤资料编号
     *
     * @param checkdataid 考勤资料编号
     */
    public void setCheckdataid(Long checkdataid) {
        this.checkdataid = checkdataid;
    }
}