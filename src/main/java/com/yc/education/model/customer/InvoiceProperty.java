package com.yc.education.model.customer;

import com.yc.education.controller.customer.CustomerBasicInfoController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * 客户基本资料-发票明细
 */
public class InvoiceProperty {

    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty usual = new SimpleStringProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty contact = new SimpleStringProperty();
    private SimpleStringProperty remark = new SimpleStringProperty();
    private SimpleStringProperty disabled = new SimpleStringProperty();
    private SimpleStringProperty disdate = new SimpleStringProperty();
    private SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual> participation = new SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual>();
    private SimpleBooleanProperty participationStop = new SimpleBooleanProperty();



    public InvoiceProperty(Long id, Boolean usual, String title, String address, String phone, String contact, String remark,Boolean disabled,Date disdate, CustomerBasicInfoController.ParticipationUsual participation, Boolean participationStop) {
        if(id != null ){
            this.id = new SimpleLongProperty(id);
        }
        if(usual != null ){
            this.usual = new SimpleStringProperty(usual.toString());
        }
        if(title != null ){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null ){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null ){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null ){
            this.contact = new SimpleStringProperty(contact);
        }
        if(remark != null ){
            this.remark = new SimpleStringProperty(remark);
        }
        if(disabled != null ){
            this.disabled = new SimpleStringProperty(disabled.toString());
        }
        if(disdate != null ){
            this.disdate = new SimpleStringProperty(disdate.toString());
        }
        if(participation != null){
            this.participation.setValue(participation);
        }
        if(participationStop != null){
            this.participationStop.setValue(participationStop);
        }
    }

    public InvoiceProperty(Long id, Boolean usual, String title, String address, String phone, String contact, String remark,Boolean disabled,Date disdate) {
        if(id != null ){
            this.id = new SimpleLongProperty(id);
        }
        if(usual != null ){
            this.usual = new SimpleStringProperty(usual.toString());
        }
        if(title != null ){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null ){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null ){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null ){
            this.contact = new SimpleStringProperty(contact);
        }
        if(remark != null ){
            this.remark = new SimpleStringProperty(remark);
        }
        if(disabled != null ){
            this.disabled = new SimpleStringProperty(disabled.toString());
        }
        if(disdate != null ){
            this.disdate = new SimpleStringProperty(disdate.toString());
        }
    }

    public InvoiceProperty( String usual, String title, String address, String phone, String contact, String remark,String disabled,String disdate) {
        if(usual != null ){
            this.usual = new SimpleStringProperty(usual);
        }
        if(title != null ){
            this.title = new SimpleStringProperty(title);
        }
        if(address != null ){
            this.address = new SimpleStringProperty(address);
        }
        if(phone != null ){
            this.phone = new SimpleStringProperty(phone);
        }
        if(contact != null ){
            this.contact = new SimpleStringProperty(contact);
        }
        if(remark != null ){
            this.remark = new SimpleStringProperty(remark);
        }
        if(disabled != null ){
            this.disabled = new SimpleStringProperty(disabled);
        }
        if(disdate != null ){
            this.disdate = new SimpleStringProperty(disdate);
        }
    }

    public InvoiceProperty() {

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

    public void setId(long id) {
        this.id.set(id);
    }

    public String getDisabled() {
        if(disabled == null){
            return null;
        }else{
            return disabled.get();
        }
    }

    public SimpleStringProperty disabledProperty() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled.set(disabled);
    }

    public String getDisdate() {
        if(disdate == null){
            return null;
        }else{
            return disdate.get();
        }
    }

    public CustomerBasicInfoController.ParticipationUsual getParticipation() {
        if(participation == null){
            return null;
        }else{
            return participation.get();
        }
    }

    public SimpleObjectProperty<CustomerBasicInfoController.ParticipationUsual> participationProperty() {
        return participation;
    }

    public void setParticipation(CustomerBasicInfoController.ParticipationUsual participation) {
        this.participation.set(participation);
    }

    public SimpleStringProperty disdateProperty() {
        return disdate;
    }

    public void setDisdate(String disdate) {
        this.disdate.set(disdate);
    }

    public String getUsual() {
        if(usual == null){
            return null;
        }else{
            return usual.get();
        }
    }

    public SimpleStringProperty usualProperty() {
        return usual;
    }

    public void setUsual(String usual) {
        this.usual.set(usual);
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

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public String getRemark() {
        if(remark == null){
            return null;
        }else{
            return remark.get();
        }
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public Boolean isParticipationStop() {
        if(participationStop == null){
            return null;
        }else{
            return participationStop.get();
        }
    }

    public SimpleBooleanProperty participationStopProperty() {
        return participationStop;
    }

    public void setParticipationStop(boolean participationStop) {
        this.participationStop.set(participationStop);
    }
}
