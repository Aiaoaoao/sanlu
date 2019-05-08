package com.yc.education.model.check;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName CheckDataEmployeeProperty
 * @Description TODO 考勤资料转结 -- 员工考勤记录
 * @Author QuZhangJing
 * @Date 2019/2/22 14:18
 * @Version 1.0
 */
public class CheckDataEmployeeProperty {

    private SimpleLongProperty id =  new SimpleLongProperty();

    private SimpleStringProperty jobnumber =  new SimpleStringProperty();

    private SimpleStringProperty workname =  new SimpleStringProperty();

    private SimpleStringProperty workday =  new SimpleStringProperty();

    private SimpleStringProperty checkday =  new SimpleStringProperty();

    private SimpleStringProperty normalovertime =  new SimpleStringProperty();

    private SimpleStringProperty weekendovertime =  new SimpleStringProperty();

    private SimpleStringProperty festivalovertime =  new SimpleStringProperty();

    private SimpleStringProperty outworkday =  new SimpleStringProperty();

    private SimpleStringProperty thingvacationday =  new SimpleStringProperty();

    private SimpleStringProperty illnessvacationday =  new SimpleStringProperty();

    private SimpleStringProperty paidvacationday =  new SimpleStringProperty();

    private SimpleStringProperty absenteeismday =  new SimpleStringProperty();

    private SimpleStringProperty latetime =  new SimpleStringProperty();

    private SimpleStringProperty lateminute =  new SimpleStringProperty();

    private SimpleStringProperty leaveearlytime =  new SimpleStringProperty();

    private SimpleStringProperty leaveearlyminute =  new SimpleStringProperty();

    private SimpleStringProperty remarks =  new SimpleStringProperty();


    public CheckDataEmployeeProperty() {

    }

    public CheckDataEmployeeProperty(long id, String jobnumber, String workname, String workday, String checkday, String normalovertime, String weekendovertime, String festivalovertime, String outworkday, String thingvacationday, String illnessvacationday, String paidvacationday, String absenteeismday, String latetime, String lateminute, String leaveearlytime, String leaveearlyminute, String remarks) {
        setId(id);
        setJobnumber(jobnumber);
        setWorkname(workname);
        setWorkday(workday);
        setCheckday(checkday);
        setNormalovertime(normalovertime);
        setWeekendovertime(weekendovertime);
        setFestivalovertime(festivalovertime);
        setOutworkday(outworkday);
        setThingvacationday(thingvacationday);
        setIllnessvacationday(illnessvacationday);
        setPaidvacationday(paidvacationday);
        setAbsenteeismday(absenteeismday);
        setLatetime(latetime);
        setLateminute(lateminute);
        setLeaveearlytime(leaveearlytime);
        setLeaveearlyminute(leaveearlyminute);
        setRemarks(remarks);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getJobnumber() {
        return jobnumber.get();
    }

    public SimpleStringProperty jobnumberProperty() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber.set(jobnumber);
    }

    public String getWorkname() {
        return workname.get();
    }

    public SimpleStringProperty worknameProperty() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname.set(workname);
    }

    public String getWorkday() {
        return workday.get();
    }

    public SimpleStringProperty workdayProperty() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday.set(workday);
    }

    public String getCheckday() {
        return checkday.get();
    }

    public SimpleStringProperty checkdayProperty() {
        return checkday;
    }

    public void setCheckday(String checkday) {
        this.checkday.set(checkday);
    }

    public String getNormalovertime() {
        return normalovertime.get();
    }

    public SimpleStringProperty normalovertimeProperty() {
        return normalovertime;
    }

    public void setNormalovertime(String normalovertime) {
        this.normalovertime.set(normalovertime);
    }

    public String getWeekendovertime() {
        return weekendovertime.get();
    }

    public SimpleStringProperty weekendovertimeProperty() {
        return weekendovertime;
    }

    public void setWeekendovertime(String weekendovertime) {
        this.weekendovertime.set(weekendovertime);
    }

    public String getFestivalovertime() {
        return festivalovertime.get();
    }

    public SimpleStringProperty festivalovertimeProperty() {
        return festivalovertime;
    }

    public void setFestivalovertime(String festivalovertime) {
        this.festivalovertime.set(festivalovertime);
    }

    public String getOutworkday() {
        return outworkday.get();
    }

    public SimpleStringProperty outworkdayProperty() {
        return outworkday;
    }

    public void setOutworkday(String outworkday) {
        this.outworkday.set(outworkday);
    }

    public String getThingvacationday() {
        return thingvacationday.get();
    }

    public SimpleStringProperty thingvacationdayProperty() {
        return thingvacationday;
    }

    public void setThingvacationday(String thingvacationday) {
        this.thingvacationday.set(thingvacationday);
    }

    public String getIllnessvacationday() {
        return illnessvacationday.get();
    }

    public SimpleStringProperty illnessvacationdayProperty() {
        return illnessvacationday;
    }

    public void setIllnessvacationday(String illnessvacationday) {
        this.illnessvacationday.set(illnessvacationday);
    }

    public String getPaidvacationday() {
        return paidvacationday.get();
    }

    public SimpleStringProperty paidvacationdayProperty() {
        return paidvacationday;
    }

    public void setPaidvacationday(String paidvacationday) {
        this.paidvacationday.set(paidvacationday);
    }

    public String getAbsenteeismday() {
        return absenteeismday.get();
    }

    public SimpleStringProperty absenteeismdayProperty() {
        return absenteeismday;
    }

    public void setAbsenteeismday(String absenteeismday) {
        this.absenteeismday.set(absenteeismday);
    }

    public String getLatetime() {
        return latetime.get();
    }

    public SimpleStringProperty latetimeProperty() {
        return latetime;
    }

    public void setLatetime(String latetime) {
        this.latetime.set(latetime);
    }

    public String getLateminute() {
        return lateminute.get();
    }

    public SimpleStringProperty lateminuteProperty() {
        return lateminute;
    }

    public void setLateminute(String lateminute) {
        this.lateminute.set(lateminute);
    }

    public String getLeaveearlytime() {
        return leaveearlytime.get();
    }

    public SimpleStringProperty leaveearlytimeProperty() {
        return leaveearlytime;
    }

    public void setLeaveearlytime(String leaveearlytime) {
        this.leaveearlytime.set(leaveearlytime);
    }

    public String getLeaveearlyminute() {
        return leaveearlyminute.get();
    }

    public SimpleStringProperty leaveearlyminuteProperty() {
        return leaveearlyminute;
    }

    public void setLeaveearlyminute(String leaveearlyminute) {
        this.leaveearlyminute.set(leaveearlyminute);
    }

    public String getRemarks() {
        return remarks.get();
    }

    public SimpleStringProperty remarksProperty() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }
}
