package com.yc.education.model.check;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName CheckDataLeaveearlyProperty
 * @Description TODO 考勤资料结转 -- 员工早退资料
 * @Author QuZhangJing
 * @Date 2019/2/22 14:27
 * @Version 1.0
 */
public class CheckDataLeaveearlyProperty {

    private SimpleLongProperty id =  new SimpleLongProperty();

    private SimpleStringProperty jobnumber = new SimpleStringProperty();

    private SimpleStringProperty workname = new SimpleStringProperty();

    private SimpleStringProperty addtime = new SimpleStringProperty();

    private SimpleStringProperty leaveearlytime = new SimpleStringProperty();

    private SimpleStringProperty leaveearlyminute = new SimpleStringProperty();

    public CheckDataLeaveearlyProperty() {}

    public CheckDataLeaveearlyProperty(long id, String jobnumber, String workname, String addtime, String leaveearlytime, String leaveearlyminute) {
        setId(id);
        setJobnumber(jobnumber);
        setWorkname(workname);
        setAddtime(addtime);
        setLeaveearlytime(leaveearlytime);
        setLeaveearlyminute(leaveearlyminute);
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

    public String getAddtime() {
        return addtime.get();
    }

    public SimpleStringProperty addtimeProperty() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime.set(addtime);
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
}
