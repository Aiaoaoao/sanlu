package com.yc.education.model.customer;

import com.yc.education.controller.customer.CustomerBasicInfoController;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ShippingAddressProperty {

    private long customerId;
    private SimpleLongProperty id = new SimpleLongProperty();
    private  SimpleStringProperty type = new SimpleStringProperty();
    private  SimpleStringProperty title = new SimpleStringProperty();
    private  SimpleStringProperty address = new SimpleStringProperty();
    private  SimpleStringProperty phone = new SimpleStringProperty();
    private  SimpleStringProperty contact = new SimpleStringProperty();
    private  SimpleObjectProperty<CustomerBasicInfoController.Participation> participation = new SimpleObjectProperty<CustomerBasicInfoController.Participation>();

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        if(id == null){
            return null;
        }else{
            return id.get();
        }
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getType() {
        if(type == null){
            return null;
        }else{
            return type.get();
        }
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAddress() {
        if(address == null){
            return null;
        }else{
            return address.get();
        }
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhone() {
        if(phone == null){
            return null;
        }else{
            return phone.get();
        }
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getContact() {
        if(contact == null){
            return null;
        }else{
            return contact.get();
        }
    }

    public String getTitle() {
        if(title == null){
            return null;
        }else{
            return title.get();
        }
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public CustomerBasicInfoController.Participation getParticipation() {
        return participation.get();
    }

    public SimpleObjectProperty<CustomerBasicInfoController.Participation> participationProperty() {
        return participation;
    }

    public void setParticipation(CustomerBasicInfoController.Participation participation) {
        this.participation.set(participation);
    }

    public ShippingAddressProperty() {
    }

    public ShippingAddressProperty(Long id, Long customerId, String type,String title, String address, String phone, String contact, CustomerBasicInfoController.Participation participation) {
        if(id != null){
            this.id = new SimpleLongProperty(id);
        }
        if(customerId != null){
            this.customerId = customerId;
        }
        if(type != null){
            this.type = new SimpleStringProperty(type);
        }
        if(title != null){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null){
            this.contact = new SimpleStringProperty(contact);
        }
        if(participation != null){
            this.participation.setValue(participation);
        }
    }

    public ShippingAddressProperty( Long customerId, String type,  String title, String address, String phone, String contact ) {
        if(customerId != null){
            this.customerId = customerId;
        }
        if(type != null){
            this.type = new SimpleStringProperty(type);
        }
        if(title != null){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null){
            this.contact = new SimpleStringProperty(contact);
        }
    }

    public ShippingAddressProperty(  String type,  String title, String address, String phone, String contact ) {
        if(type != null){
            this.type = new SimpleStringProperty(type);
        }
        if(title != null){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null){
            this.contact = new SimpleStringProperty(contact);
        }

    }
}
