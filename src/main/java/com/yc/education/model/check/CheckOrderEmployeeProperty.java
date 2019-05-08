package com.yc.education.model.check;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName CheckOrderEmployeeProperty
 * @Description TODO  申请人
 * @Author QuZhangJing
 * @Date 2019/2/15 17:50
 * @Version 1.0
 */
public class CheckOrderEmployeeProperty {

    private SimpleLongProperty id =  new SimpleLongProperty();

    private SimpleStringProperty emporder =  new SimpleStringProperty();

    private SimpleStringProperty empname =  new SimpleStringProperty();

    public CheckOrderEmployeeProperty() {

    }

    public CheckOrderEmployeeProperty(long id, String emporder, String empname) {
       setId(id);
       setEmporder(emporder);
       setEmpname(empname);
    }

    public CheckOrderEmployeeProperty( String emporder, String empname) {
        setEmporder(emporder);
        setEmpname(empname);
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

    public String getEmporder() {
        return emporder.get();
    }

    public SimpleStringProperty emporderProperty() {
        return emporder;
    }

    public void setEmporder(String emporder) {
        this.emporder.set(emporder);
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
}
