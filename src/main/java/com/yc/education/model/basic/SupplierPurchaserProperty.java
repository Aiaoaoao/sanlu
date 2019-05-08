package com.yc.education.model.basic;

import com.yc.education.controller.basic.SupplierBasicController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName SupplierPurchaserProperty
 * @Description TODO 供应商-采购负责人 TabelView数据绑定之类
 * @Author QuZhangJing
 * @Date 2018/9/19 15:45
 * @Version 1.0
 */
public final class SupplierPurchaserProperty {


    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty staffcode = new SimpleStringProperty();

    private  final SimpleStringProperty staffname = new SimpleStringProperty();

    private  final SimpleStringProperty keycontent = new SimpleStringProperty();

    private  final SimpleStringProperty staffremarks = new SimpleStringProperty();

    private SimpleObjectProperty<SupplierBasicController.Participation> participation = new SimpleObjectProperty<>();

    private final SimpleBooleanProperty isbool = new SimpleBooleanProperty();


    public SupplierPurchaserProperty() {
    }

    public SupplierPurchaserProperty(long id,String staffcode,String staffname,String keycontent,String staffremarks,boolean isbool) {
        setId(id);
        setStaffcode(staffcode);
        setStaffname(staffname);
        setKeycontent(keycontent);
        setStaffremarks(staffremarks);
        setIsbool(isbool);
    }


    public SupplierPurchaserProperty(long id,String staffcode,String staffname,String keycontent,String staffremarks,SupplierBasicController.Participation participation ,boolean isbool) {
        setId(id);
        setStaffcode(staffcode);
        setStaffname(staffname);
        setKeycontent(keycontent);
        setStaffremarks(staffremarks);
        setParticipation(participation);
        setIsbool(isbool);
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

    public String getStaffcode() {
        return staffcode.get();
    }

    public SimpleStringProperty staffcodeProperty() {
        return staffcode;
    }

    public void setStaffcode(String staffcode) {
        this.staffcode.set(staffcode);
    }

    public String getStaffname() {
        return staffname.get();
    }

    public SimpleStringProperty staffnameProperty() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname.set(staffname);
    }

    public String getKeycontent() {
        return keycontent.get();
    }

    public SimpleStringProperty keycontentProperty() {
        return keycontent;
    }

    public void setKeycontent(String keycontent) {
        this.keycontent.set(keycontent);
    }

    public String getStaffremarks() {
        return staffremarks.get();
    }

    public SimpleStringProperty staffremarksProperty() {
        return staffremarks;
    }

    public void setStaffremarks(String staffremarks) {
        this.staffremarks.set(staffremarks);
    }

    public SupplierBasicController.Participation getParticipation() {
        return participation.get();
    }

    public SimpleObjectProperty<SupplierBasicController.Participation> participationProperty() {
        return participation;
    }

    public void setParticipation(SupplierBasicController.Participation participation) {
        this.participation.set(participation);
    }

    public boolean isIsbool() {
        return isbool.get();
    }

    public SimpleBooleanProperty isboolProperty() {
        return isbool;
    }

    public void setIsbool(boolean isbool) {
        this.isbool.set(isbool);
    }
}
