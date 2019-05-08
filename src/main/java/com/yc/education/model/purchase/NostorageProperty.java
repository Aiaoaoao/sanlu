package com.yc.education.model.purchase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName NostorageProperty
 * @Description TODO 业务查询----采购未入库查询
 * @Author QuZhangJing
 * @Date 2018/10/19 14:45
 * @Version 1.0
 */
public class NostorageProperty {


    private final SimpleStringProperty orders = new SimpleStringProperty();

    private final SimpleStringProperty createdate = new SimpleStringProperty();

    private final SimpleStringProperty comedate = new SimpleStringProperty();

    private final SimpleStringProperty suppliernum = new SimpleStringProperty();

    private final SimpleStringProperty supplierdes = new SimpleStringProperty();

    private final SimpleStringProperty proid = new SimpleStringProperty();

    private final SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleIntegerProperty ordernum = new SimpleIntegerProperty();

    private final SimpleIntegerProperty stocknum = new SimpleIntegerProperty();

    private final SimpleStringProperty seeorder = new SimpleStringProperty();

    private final SimpleIntegerProperty overnum = new SimpleIntegerProperty();

    private final SimpleIntegerProperty onthewaynum = new SimpleIntegerProperty();

    private final SimpleIntegerProperty unpassnum = new SimpleIntegerProperty();

    private final SimpleStringProperty operation = new SimpleStringProperty();


    public NostorageProperty(String orders,String createdate,String comedate,String suppliernum,String supplierdes,String proid,String proname,int ordernum,int stocknum,String seeorder,
                             int overnum,int onthewaynum,int unpassnum,String operation) {
        setOrders(orders);
        setCreatedate(createdate);
        setComedate(comedate);
        setSuppliernum(suppliernum);
        setSupplierdes(supplierdes);
        setProid(proid);
        setProname(proname);
        setOrdernum(ordernum);
        setStocknum(stocknum);
        setSeeorder(seeorder);
        setOvernum(overnum);
        setOnthewaynum(onthewaynum);
        setUnpassnum(unpassnum);
        setOperation(operation);
    }

    public NostorageProperty() {
    }


    public String getComedate() {
        return comedate.get();
    }

    public SimpleStringProperty comedateProperty() {
        return comedate;
    }

    public void setComedate(String comedate) {
        this.comedate.set(comedate);
    }

    public String getOrders() {
        return orders.get();
    }

    public SimpleStringProperty ordersProperty() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders.set(orders);
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

    public String getSuppliernum() {
        return suppliernum.get();
    }

    public SimpleStringProperty suppliernumProperty() {
        return suppliernum;
    }

    public void setSuppliernum(String suppliernum) {
        this.suppliernum.set(suppliernum);
    }

    public String getSupplierdes() {
        return supplierdes.get();
    }

    public SimpleStringProperty supplierdesProperty() {
        return supplierdes;
    }

    public void setSupplierdes(String supplierdes) {
        this.supplierdes.set(supplierdes);
    }

    public String getProid() {
        return proid.get();
    }

    public SimpleStringProperty proidProperty() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid.set(proid);
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

    public int getOrdernum() {
        return ordernum.get();
    }

    public SimpleIntegerProperty ordernumProperty() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum.set(ordernum);
    }

    public int getStocknum() {
        return stocknum.get();
    }

    public SimpleIntegerProperty stocknumProperty() {
        return stocknum;
    }

    public void setStocknum(int stocknum) {
        this.stocknum.set(stocknum);
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

    public int getOvernum() {
        return overnum.get();
    }

    public SimpleIntegerProperty overnumProperty() {
        return overnum;
    }

    public void setOvernum(int overnum) {
        this.overnum.set(overnum);
    }

    public int getOnthewaynum() {
        return onthewaynum.get();
    }

    public SimpleIntegerProperty onthewaynumProperty() {
        return onthewaynum;
    }

    public void setOnthewaynum(int onthewaynum) {
        this.onthewaynum.set(onthewaynum);
    }

    public int getUnpassnum() {
        return unpassnum.get();
    }

    public SimpleIntegerProperty unpassnumProperty() {
        return unpassnum;
    }

    public void setUnpassnum(int unpassnum) {
        this.unpassnum.set(unpassnum);
    }

    public String getOperation() {
        return operation.get();
    }

    public SimpleStringProperty operationProperty() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation.set(operation);
    }
}
