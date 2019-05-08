package com.yc.education.model.basic;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName RegionProperty
 * @Description TODO 业务区域设定-TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/9/21 13:51
 * @Version 1.0
 */
public class RegionProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty empisnum = new SimpleStringProperty();

    private  final SimpleStringProperty empname = new SimpleStringProperty();

    private  final SimpleStringProperty remarks = new SimpleStringProperty();


    public RegionProperty() {

    }


    public RegionProperty(long id,String empisnum,String empname,String remarks) {
        setId(id);
        setEmpisnum(empisnum);
        setEmpname(empname);
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

    public String getEmpisnum() {
        return empisnum.get();
    }

    public SimpleStringProperty empisnumProperty() {
        return empisnum;
    }

    public void setEmpisnum(String empisnum) {
        this.empisnum.set(empisnum);
    }

    public String getEmpname() {
        return empname.get();
    }

    public SimpleStringProperty empnameProperty() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname.set(empname);
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
