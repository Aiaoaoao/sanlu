package com.yc.education.model.customer;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 备注及说明
 */
public class RemarkProperty {
    /**
     *  备注及说明
     */
    private SimpleLongProperty id = new SimpleLongProperty();

    /**
     * 外键id
     */
    private SimpleLongProperty otherid = new SimpleLongProperty();

    /**
     * 类型（1、客户基本资料，2、报价单，3、订货单，4、退货单）
     */
    private SimpleLongProperty typeid = new SimpleLongProperty();

    /**
     * 备注及说明
     */
    private SimpleStringProperty remark = new SimpleStringProperty();

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

    public Long getOtherid() {
        if(otherid == null){
            return null;
        }else{
            return otherid.get();
        }
    }

    public SimpleLongProperty otheridProperty() {
        return otherid;
    }

    public void setOtherid(long otherid) {
        this.otherid.set(otherid);
    }

    public Long getTypeid() {
        if(typeid == null){
            return null;
        }else{
            return typeid.get();
        }
    }

    public SimpleLongProperty typeidProperty() {
        return typeid;
    }

    public void setTypeid(long typeid) {
        this.typeid.set(typeid);
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

    public RemarkProperty(Long id, String remark) {
        this.id = new SimpleLongProperty(id);
        this.remark =  new SimpleStringProperty(remark);
    }

    public RemarkProperty(SimpleLongProperty id, SimpleLongProperty otherid, SimpleLongProperty typeid, SimpleStringProperty remark) {
        this.id = id;
        this.otherid = otherid;
        this.typeid = typeid;
        this.remark = remark;
    }

    public RemarkProperty(String remark) {
        this.remark = new SimpleStringProperty(remark);
    }

    public RemarkProperty() {

    }
}
