package com.yc.education.model.check;

import javax.persistence.*;
/**
 *@Description TODO 考勤资料结转---员工考勤资料
 *@Author QuZhangJing
 *@Date 10:21  2019/2/22
 *@Version 1.0
 */
@Table(name = "check_data_employee")
public class CheckDataEmployee {
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
     * 工作日
     */
    private String workday;

    /**
     * 出勤天数
     */
    private String checkday;

    /**
     * 平时加班天数
     */
    private String normalovertime;

    /**
     * 周末加班天数
     */
    private String weekendovertime;

    /**
     * 节日加班天数
     */
    private String festivalovertime;

    /**
     * 出差天数
     */
    private String outworkday;

    /**
     * 事假天数
     */
    private String thingvacationday;

    /**
     * 病假天数
     */
    private String illnessvacationday;

    /**
     * 带薪假天数
     */
    private String paidvacationday;

    /**
     * 旷工天数
     */
    private String absenteeismday;

    /**
     * 迟到次数
     */
    private String latetime;

    /**
     * 迟到分钟
     */
    private String lateminute;

    /**
     * 早退次数
     */
    private String leaveearlytime;

    /**
     * 早退分钟
     */
    private String leaveearlyminute;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 考勤资料转结编号
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
     * 获取工作日
     *
     * @return workday - 工作日
     */
    public String getWorkday() {
        return workday;
    }

    /**
     * 设置工作日
     *
     * @param workday 工作日
     */
    public void setWorkday(String workday) {
        this.workday = workday;
    }

    /**
     * 获取出勤天数
     *
     * @return checkday - 出勤天数
     */
    public String getCheckday() {
        return checkday;
    }

    /**
     * 设置出勤天数
     *
     * @param checkday 出勤天数
     */
    public void setCheckday(String checkday) {
        this.checkday = checkday;
    }

    /**
     * 获取平时加班天数
     *
     * @return normalovertime - 平时加班天数
     */
    public String getNormalovertime() {
        return normalovertime;
    }

    /**
     * 设置平时加班天数
     *
     * @param normalovertime 平时加班天数
     */
    public void setNormalovertime(String normalovertime) {
        this.normalovertime = normalovertime;
    }

    /**
     * 获取周末加班天数
     *
     * @return weekendovertime - 周末加班天数
     */
    public String getWeekendovertime() {
        return weekendovertime;
    }

    /**
     * 设置周末加班天数
     *
     * @param weekendovertime 周末加班天数
     */
    public void setWeekendovertime(String weekendovertime) {
        this.weekendovertime = weekendovertime;
    }

    /**
     * 获取节日加班天数
     *
     * @return festivalovertime - 节日加班天数
     */
    public String getFestivalovertime() {
        return festivalovertime;
    }

    /**
     * 设置节日加班天数
     *
     * @param festivalovertime 节日加班天数
     */
    public void setFestivalovertime(String festivalovertime) {
        this.festivalovertime = festivalovertime;
    }

    /**
     * 获取出差天数
     *
     * @return outworkday - 出差天数
     */
    public String getOutworkday() {
        return outworkday;
    }

    /**
     * 设置出差天数
     *
     * @param outworkday 出差天数
     */
    public void setOutworkday(String outworkday) {
        this.outworkday = outworkday;
    }

    /**
     * 获取事假天数
     *
     * @return thingvacationday - 事假天数
     */
    public String getThingvacationday() {
        return thingvacationday;
    }

    /**
     * 设置事假天数
     *
     * @param thingvacationday 事假天数
     */
    public void setThingvacationday(String thingvacationday) {
        this.thingvacationday = thingvacationday;
    }

    /**
     * 获取病假天数
     *
     * @return illnessvacationday - 病假天数
     */
    public String getIllnessvacationday() {
        return illnessvacationday;
    }

    /**
     * 设置病假天数
     *
     * @param illnessvacationday 病假天数
     */
    public void setIllnessvacationday(String illnessvacationday) {
        this.illnessvacationday = illnessvacationday;
    }

    /**
     * 获取带薪假天数
     *
     * @return paidvacationday - 带薪假天数
     */
    public String getPaidvacationday() {
        return paidvacationday;
    }

    /**
     * 设置带薪假天数
     *
     * @param paidvacationday 带薪假天数
     */
    public void setPaidvacationday(String paidvacationday) {
        this.paidvacationday = paidvacationday;
    }

    /**
     * 获取旷工天数
     *
     * @return absenteeismday - 旷工天数
     */
    public String getAbsenteeismday() {
        return absenteeismday;
    }

    /**
     * 设置旷工天数
     *
     * @param absenteeismday 旷工天数
     */
    public void setAbsenteeismday(String absenteeismday) {
        this.absenteeismday = absenteeismday;
    }

    /**
     * 获取迟到次数
     *
     * @return latetime - 迟到次数
     */
    public String getLatetime() {
        return latetime;
    }

    /**
     * 设置迟到次数
     *
     * @param latetime 迟到次数
     */
    public void setLatetime(String latetime) {
        this.latetime = latetime;
    }

    /**
     * 获取迟到分钟
     *
     * @return lateminute - 迟到分钟
     */
    public String getLateminute() {
        return lateminute;
    }

    /**
     * 设置迟到分钟
     *
     * @param lateminute 迟到分钟
     */
    public void setLateminute(String lateminute) {
        this.lateminute = lateminute;
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
     * 获取考勤资料转结编号
     *
     * @return checkdataid - 考勤资料转结编号
     */
    public Long getCheckdataid() {
        return checkdataid;
    }

    /**
     * 设置考勤资料转结编号
     *
     * @param checkdataid 考勤资料转结编号
     */
    public void setCheckdataid(Long checkdataid) {
        this.checkdataid = checkdataid;
    }
}