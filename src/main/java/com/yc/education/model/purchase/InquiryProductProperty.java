package com.yc.education.model.purchase;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName InquiryProductProperty
 * @Description TODO 询价产品之TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/9/28 14:34
 * @Version 1.0
 */
public class InquiryProductProperty {

    private final SimpleLongProperty editId = new SimpleLongProperty();

    private final SimpleStringProperty proisnum = new SimpleStringProperty();

    private final SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleStringProperty protype = new SimpleStringProperty();

    private final SimpleStringProperty pronum = new SimpleStringProperty();

    private final SimpleStringProperty prounit = new SimpleStringProperty();

    private final SimpleStringProperty proprice = new SimpleStringProperty();

    private final SimpleStringProperty totalprice = new SimpleStringProperty();

    private final SimpleStringProperty proremark = new SimpleStringProperty();
    //期望交期
    private final SimpleStringProperty estimateDeliver = new SimpleStringProperty();


    public InquiryProductProperty() {
    }



    public InquiryProductProperty(long editId ,String proisnum,String proname,String protype,String pronum,String prounit,String proprice,String totalprice,String proremark,String estimateDeliver) {
    setEditId(editId);
    setProisnum(proisnum);
    setProname(proname);
    setProtype(protype);
    setPronum(pronum);
    setProunit(prounit);
    setProprice(proprice);
    setTotalprice(totalprice);
    setProremark(proremark);
    setEstimateDeliver(estimateDeliver);
    }


    public long getEditId() {
        return editId.get();
    }

    public SimpleLongProperty editIdProperty() {
        return editId;
    }

    public void setEditId(long editId) {
        this.editId.set(editId);
    }

    public String getProisnum() {
        return proisnum.get();
    }

    public SimpleStringProperty proisnumProperty() {
        return proisnum;
    }

    public void setProisnum(String proisnum) {
        this.proisnum.set(proisnum);
    }

    public String getProname() {
        return proname.get();
    }

    public SimpleStringProperty pronameProperty() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname.set(proname);
    }

    public String getProtype() {
        return protype.get();
    }

    public SimpleStringProperty protypeProperty() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype.set(protype);
    }

    public String getPronum() {
        return pronum.get();
    }

    public SimpleStringProperty pronumProperty() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum.set(pronum);
    }

    public String getProunit() {
        return prounit.get();
    }

    public SimpleStringProperty prounitProperty() {
        return prounit;
    }

    public void setProunit(String prounit) {
        this.prounit.set(prounit);
    }

    public String getProprice() {
        return proprice.get();
    }

    public SimpleStringProperty propriceProperty() {
        return proprice;
    }

    public void setProprice(String proprice) {
        this.proprice.set(proprice);
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

    public String getProremark() {
        return proremark.get();
    }

    public SimpleStringProperty proremarkProperty() {
        return proremark;
    }

    public void setProremark(String proremark) {
        this.proremark.set(proremark);
    }


    public String getEstimateDeliver() {
        return estimateDeliver.get();
    }

    public SimpleStringProperty estimateDeliverProperty() {
        return estimateDeliver;
    }

    public void setEstimateDeliver(String estimateDeliver) {
        this.estimateDeliver.set(estimateDeliver);
    }
}
