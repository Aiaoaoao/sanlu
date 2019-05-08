package com.yc.education.model.basic;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName ProductSupplierProperty
 * @Description TODO 产品管理-供应商 TabelView数据绑定之类
 * @Author QuZhangJing
 * @Date 2018/9/19 16:39
 * @Version 1.0
 */
public class ProductSupplierProperty {


    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty supplierid = new SimpleStringProperty();

    private  final SimpleStringProperty supdes = new SimpleStringProperty();

    private  final SimpleStringProperty supply = new SimpleStringProperty();

    private  final SimpleStringProperty remarks = new SimpleStringProperty();


    public ProductSupplierProperty(){}

    public ProductSupplierProperty(long id,String supplierid,String supdes,String supply,String remarks){
        setId(id);
        setSupplierid(supplierid);
        setSupdes(supdes);
        setSupply(supply);
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

    public String getSupplierid() {
        return supplierid.get();
    }

    public SimpleStringProperty supplieridProperty() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid.set(supplierid);
    }

    public String getSupdes() {
        return supdes.get();
    }

    public SimpleStringProperty supdesProperty() {
        return supdes;
    }

    public void setSupdes(String supdes) {
        this.supdes.set(supdes);
    }

    public String getSupply() {
        return supply.get();
    }

    public SimpleStringProperty supplyProperty() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply.set(supply);
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
