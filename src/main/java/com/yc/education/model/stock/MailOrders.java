package com.yc.education.model.stock;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName MailOrders
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2019/4/28 23:16
 * @Version 1.0
 */
public class MailOrders {


    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty  sort= new SimpleStringProperty();

    private final SimpleStringProperty  outOrders= new SimpleStringProperty();

    private final SimpleStringProperty mailOrder = new SimpleStringProperty();

    private final SimpleStringProperty remarks = new SimpleStringProperty();


    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getOutOrders() {
        return outOrders.get();
    }

    public SimpleStringProperty outOrdersProperty() {
        return outOrders;
    }

    public void setOutOrders(String outOrders) {
        this.outOrders.set(outOrders);
    }

    public String getMailOrder() {
        return mailOrder.get();
    }

    public SimpleStringProperty mailOrderProperty() {
        return mailOrder;
    }

    public void setMailOrder(String mailOrder) {
        this.mailOrder.set(mailOrder);
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

    public String getSort() {
        return sort.get();
    }

    public SimpleStringProperty sortProperty() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort.set(sort);
    }
}
