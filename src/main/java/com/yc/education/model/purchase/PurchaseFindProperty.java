package com.yc.education.model.purchase;


import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName PurchaseFindProperty
 * @Description TODO 最新采购订单之TableView数据绑定
 * @Author QuZhangJing
 * @Date 2018/10/23 19:37
 * @Version 1.0
 */
public class PurchaseFindProperty {


    private final SimpleStringProperty pronum = new SimpleStringProperty();

    private final SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleStringProperty protype = new SimpleStringProperty();

    private final SimpleStringProperty purchasenum = new SimpleStringProperty();

    private final SimpleStringProperty proremark = new SimpleStringProperty();

    private final SimpleStringProperty purchaseorder = new SimpleStringProperty();

    private final SimpleStringProperty seeorder = new SimpleStringProperty();

    private final SimpleStringProperty createdate = new SimpleStringProperty();



    public PurchaseFindProperty(String pronum,String proname,String protype,String purchasenum,String proremark,String purchaseorder,String seeorder,String createdate) {
        setPronum(pronum);
        setProname(proname);
        setProtype(protype);
        setPurchasenum(purchasenum);
        setProremark(proremark);
        setPurchaseorder(purchaseorder);
        setSeeorder(seeorder);
        setCreatedate(createdate);
    }

    public PurchaseFindProperty() {
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

    public String getPurchasenum() {
        return purchasenum.get();
    }

    public SimpleStringProperty purchasenumProperty() {
        return purchasenum;
    }

    public void setPurchasenum(String purchasenum) {
        this.purchasenum.set(purchasenum);
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

    public String getPurchaseorder() {
        return purchaseorder.get();
    }

    public SimpleStringProperty purchaseorderProperty() {
        return purchaseorder;
    }

    public void setPurchaseorder(String purchaseorder) {
        this.purchaseorder.set(purchaseorder);
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

    public String getCreatedate() {
        return createdate.get();
    }

    public SimpleStringProperty createdateProperty() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate.set(createdate);
    }
}
