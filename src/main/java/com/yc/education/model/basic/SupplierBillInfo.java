package com.yc.education.model.basic;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SupplierBillInfo
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/12/19 15:52
 * @Version 1.0
 */
public class SupplierBillInfo {

    private  final SimpleStringProperty createdate = new SimpleStringProperty();

    private  final SimpleStringProperty types = new SimpleStringProperty();

    private  final SimpleStringProperty order = new SimpleStringProperty();

    private  final SimpleStringProperty seeorder = new SimpleStringProperty();

    private  final SimpleStringProperty billemp = new SimpleStringProperty();

    private  final SimpleStringProperty totalprice = new SimpleStringProperty();


    public SupplierBillInfo() {

    }

    public SupplierBillInfo(Date createdate, String types, String order,String seeorder,String billemp,Double totalprice) {

        setCreatedate(new SimpleDateFormat("yyyy-MM-dd").format(createdate));
        setTypes(types);
        setOrder(order);
        setSeeorder(seeorder);
        setBillemp(billemp);
        setTotalprice(String.valueOf(totalprice));
    }

    public String getCreatedate() {
        return createdate.get();
    }

    public SimpleStringProperty createdateProperty() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate.set(createdate);
    }

    public String getTypes() {
        return types.get();
    }

    public SimpleStringProperty typesProperty() {
        return types;
    }

    public void setTypes(String types) {
        this.types.set(types);
    }

    public String getOrder() {
        return order.get();
    }

    public SimpleStringProperty orderProperty() {
        return order;
    }

    public void setOrder(String order) {
        this.order.set(order);
    }

    public String getSeeorder() {
        return seeorder.get();
    }

    public SimpleStringProperty seeorderProperty() {
        return seeorder;
    }

    public void setSeeorder(String seeorder) {
        this.seeorder.set(seeorder);
    }

    public String getBillemp() {
        return billemp.get();
    }

    public SimpleStringProperty billempProperty() {
        return billemp;
    }

    public void setBillemp(String billemp) {
        this.billemp.set(billemp);
    }

    public String getTotalprice() {
        return totalprice.get();
    }

    public SimpleStringProperty totalpriceProperty() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice.set(totalprice);
    }
}
