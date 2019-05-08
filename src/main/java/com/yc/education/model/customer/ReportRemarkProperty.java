package com.yc.education.model.customer;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportRemarkProperty {
    /**
     * 报表备注
     */
    private SimpleLongProperty id = new SimpleLongProperty();

    /**
     * 外键id
     */
    private SimpleLongProperty otherid = new SimpleLongProperty();

    /**
     * 类型（1、报价单，2、订货单，3、退货单）
     */
    private SimpleLongProperty typeid = new SimpleLongProperty();

    /**
     * 正文
     */
    private SimpleStringProperty content = new SimpleStringProperty();

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

    public String getContent() {
        if(content == null){
            return null;
        }else{
            return content.get();
        }
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public ReportRemarkProperty(String content) {
        this.content = new SimpleStringProperty(content);
    }

    public ReportRemarkProperty(Long id, String content) {
        this.id = new SimpleLongProperty(id);
        this.content = new SimpleStringProperty(content);
    }

    public ReportRemarkProperty() {
    }
}
