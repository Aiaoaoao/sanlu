package com.yc.education.model.basic;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SupplierProductInfo
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/12/21 10:11
 * @Version 1.0
 */
public class SupplierProductInfo {

    private  final SimpleStringProperty productdate = new SimpleStringProperty();

    private  final SimpleStringProperty producttypes = new SimpleStringProperty();

    private  final SimpleStringProperty productorder = new SimpleStringProperty();

    private  final SimpleStringProperty producctid = new SimpleStringProperty();

    private  final SimpleStringProperty productnames = new SimpleStringProperty();

    private  final SimpleStringProperty prductnums = new SimpleStringProperty();

    private  final SimpleStringProperty productprices = new SimpleStringProperty();

    private  final SimpleStringProperty productRemarks = new SimpleStringProperty();


    public SupplierProductInfo() {
    }

    public SupplierProductInfo(Date productdate,String producttypes,String productorder ,String producctid,String productnames,String prductnums,String productprices,String productRemarks) {
    setProductdate(new SimpleDateFormat("yyyy-MM-dd").format(productdate));
    setProducttypes(producttypes);
    setProductorder(productorder);
    setProducctid(producctid);
    setProductnames(productnames);
    setPrductnums(prductnums);
    setProductprices(productprices);
    setProductRemarks(productRemarks);
    }


    public String getProductdate() {
        return productdate.get();
    }

    public SimpleStringProperty productdateProperty() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate.set(productdate);
    }

    public String getProducttypes() {
        return producttypes.get();
    }

    public SimpleStringProperty producttypesProperty() {
        return producttypes;
    }

    public void setProducttypes(String producttypes) {
        this.producttypes.set(producttypes);
    }

    public String getProductorder() {
        return productorder.get();
    }

    public SimpleStringProperty productorderProperty() {
        return productorder;
    }

    public void setProductorder(String productorder) {
        this.productorder.set(productorder);
    }

    public String getProducctid() {
        return producctid.get();
    }

    public SimpleStringProperty producctidProperty() {
        return producctid;
    }

    public void setProducctid(String producctid) {
        this.producctid.set(producctid);
    }

    public String getProductnames() {
        return productnames.get();
    }

    public SimpleStringProperty productnamesProperty() {
        return productnames;
    }

    public void setProductnames(String productnames) {
        this.productnames.set(productnames);
    }

    public String getPrductnums() {
        return prductnums.get();
    }

    public SimpleStringProperty prductnumsProperty() {
        return prductnums;
    }

    public void setPrductnums(String prductnums) {
        this.prductnums.set(prductnums);
    }

    public String getProductprices() {
        return productprices.get();
    }

    public SimpleStringProperty productpricesProperty() {
        return productprices;
    }

    public void setProductprices(String productprices) {
        this.productprices.set(productprices);
    }

    public String getProductRemarks() {
        return productRemarks.get();
    }

    public SimpleStringProperty productRemarksProperty() {
        return productRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        this.productRemarks.set(productRemarks);
    }
}
