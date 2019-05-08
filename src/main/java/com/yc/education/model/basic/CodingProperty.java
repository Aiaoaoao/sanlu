package com.yc.education.model.basic;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName CodingProperty
 * @Description TODO 编码设置-TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/9/21 11:15
 * @Version 1.0
 */
public class CodingProperty {


    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty idnum = new SimpleStringProperty();

    private  final SimpleStringProperty newcode = new SimpleStringProperty();

    private  final SimpleStringProperty supname = new SimpleStringProperty();

    private  final SimpleStringProperty adddate = new SimpleStringProperty();

    private  final SimpleStringProperty addpeople = new SimpleStringProperty();

    private  final SimpleStringProperty stopdes = new SimpleStringProperty();

    private  final SimpleStringProperty remarks = new SimpleStringProperty();


    public CodingProperty() {

    }

    public CodingProperty(long id, String idnum, String supname, String adddate, String addpeople, String stopdes, String remarks) {

        setId(id);
        setIdnum(idnum);
        setSupname(supname);
        setAddpeople(addpeople);
        setStopdes(stopdes);
        setRemarks(remarks);
        setAdddate(adddate);
    }

    public CodingProperty(long id, String idnum,String newcode, String supname, String adddate, String addpeople, String stopdes, String remarks) {
        setId(id);
        setIdnum(idnum);
        setNewcode(newcode);
        setSupname(supname);
        setAddpeople(addpeople);
        setStopdes(stopdes);
        setRemarks(remarks);
        setAdddate(adddate);
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

    public String getIdnum() {
        return idnum.get();
    }

    public SimpleStringProperty idnumProperty() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum.set(idnum);
    }

    public String getSupname() {
        return supname.get();
    }

    public SimpleStringProperty supnameProperty() {
        return supname;
    }

    public void setSupname(String supname) {
        this.supname.set(supname);
    }

    public String getAdddate() {
        return adddate.get();
    }

    public SimpleStringProperty adddateProperty() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate.set(adddate);
    }

    public String getAddpeople() {
        return addpeople.get();
    }

    public SimpleStringProperty addpeopleProperty() {
        return addpeople;
    }

    public void setAddpeople(String addpeople) {
        this.addpeople.set(addpeople);
    }

    public String getStopdes() {
        return stopdes.get();
    }

    public SimpleStringProperty stopdesProperty() {
        return stopdes;
    }

    public void setStopdes(String stopdes) {
        this.stopdes.set(stopdes);
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

    public String getNewcode() {
        return newcode.get();
    }

    public SimpleStringProperty newcodeProperty() {
        return newcode;
    }

    public void setNewcode(String newcode) {
        this.newcode.set(newcode);
    }
}
