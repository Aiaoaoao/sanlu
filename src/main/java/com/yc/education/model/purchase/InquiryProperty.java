package com.yc.education.model.purchase;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName InquiryProperty
 * @Description TODO 询价订单——之数据绑定  （占时没有用到-舍不得删除）
 * @Author QuZhangJing
 * @Date 2018/10/8 15:51
 * @Version 1.0
 */
public class InquiryProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty orders = new SimpleStringProperty();

    private final SimpleStringProperty createdate = new SimpleStringProperty();

    private final SimpleStringProperty suppliernum = new SimpleStringProperty();

    private final SimpleStringProperty supplierdes = new SimpleStringProperty();

    private final SimpleStringProperty responbility = new SimpleStringProperty();

    private final SimpleStringProperty contacts = new SimpleStringProperty();

    private final SimpleStringProperty phone = new SimpleStringProperty();

    private final SimpleStringProperty fax = new SimpleStringProperty();





    public InquiryProperty() {
    }

    public InquiryProperty(long id,String orders,String createdate,String suppliernum,String supplierdes,String responbility,String contacts,String phone,String fax) {
        setId(id);
        setOrders(orders);
        setCreatedate(createdate);
        setSuppliernum(suppliernum);
        setSupplierdes(supplierdes);
        setResponbility(responbility);
        setContacts(contacts);
        setPhone(phone);
        setFax(fax);
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

    public String getResponbility() {
        return responbility.get();
    }

    public SimpleStringProperty responbilityProperty() {
        return responbility;
    }

    public void setResponbility(String responbility) {
        this.responbility.set(responbility);
    }

    public String getContacts() {
        return contacts.get();
    }

    public SimpleStringProperty contactsProperty() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts.set(contacts);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getFax() {
        return fax.get();
    }

    public SimpleStringProperty faxProperty() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax.set(fax);
    }
}
