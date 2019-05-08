package com.yc.education.model.purchase;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName ForceProductProperty
 * @Description TODO 强制结案产品 --- 之TableView数据绑定
 * @Author QuZhangJing
 * @Date 2018/10/16 16:39
 * @Version 1.0
 */
public class ForceProductProperty {


    private final SimpleLongProperty forceid = new SimpleLongProperty();

    private final SimpleStringProperty pronum = new SimpleStringProperty();

    private final SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleStringProperty ordernum = new SimpleStringProperty();

    private final SimpleStringProperty forcenum = new SimpleStringProperty();

    private final SimpleStringProperty forcedate = new SimpleStringProperty();

    private final SimpleStringProperty forceover = new SimpleStringProperty();

    private final SimpleStringProperty stockover = new SimpleStringProperty();

    private final SimpleStringProperty ontheway = new SimpleStringProperty();

    //无参构造
    public ForceProductProperty() {

    }

    //构造函数
    public ForceProductProperty(long forceid,String pronum,String proname,int ordernum,int forcenum,String forcedate,int forceover,int stockover,int ontheway) {
    setForceid(forceid);
    setPronum(pronum);
    setProname(proname);
    setOrdernum(String.valueOf(ordernum));
    setForcenum(String.valueOf(forcenum));
    setForcedate(forcedate);
    setForceover(String.valueOf(forceover));
    setStockover(String.valueOf(stockover));
    setOntheway(String.valueOf(ontheway));
    }

    public long getForceid() {
        return forceid.get();
    }

    public SimpleLongProperty forceidProperty() {
        return forceid;
    }

    public void setForceid(long forceid) {
        this.forceid.set(forceid);
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

    public int getOrdernum() {
        return Integer.parseInt(ordernum.get());
    }

    public SimpleStringProperty ordernumProperty() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum.set(ordernum);
    }

    public int getForcenum() {
        return Integer.parseInt(forcenum.get());
    }

    public SimpleStringProperty forcenumProperty() {
        return forcenum;
    }

    public void setForcenum(String forcenum) {
        this.forcenum.set(forcenum);
    }

    public String getForcedate() {
        return forcedate.get();
    }

    public SimpleStringProperty forcedateProperty() {
        return forcedate;
    }

    public void setForcedate(String forcedate) {
        this.forcedate.set(forcedate);
    }

    public int getForceover() {
        return Integer.parseInt(forceover.get());
    }

    public SimpleStringProperty forceoverProperty() {
        return forceover;
    }

    public void setForceover(String forceover) {
        this.forceover.set(forceover);
    }

    public int getStockover() {
        return Integer.valueOf(stockover.get());
    }

    public SimpleStringProperty stockoverProperty() {
        return stockover;
    }

    public void setStockover(String stockover) {
        this.stockover.set(stockover);
    }

    public int getOntheway() {
        return Integer.parseInt(ontheway.get());
    }

    public SimpleStringProperty onthewayProperty() {
        return ontheway;
    }

    public void setOntheway(String ontheway) {
        this.ontheway.set(ontheway);
    }
}
