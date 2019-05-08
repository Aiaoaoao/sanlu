package com.yc.education.model.purchase;

import javafx.beans.property.*;

/**
 * @ClassName InquiryImportProperty
 * @Description TODO 导入--询价单数据之TableView数据
 * @Author QuZhangJing
 * @Date 2018/10/18 14:38
 * @Version 1.0
 */
public class InquiryImportProperty {


    private final SimpleBooleanProperty checked = new SimpleBooleanProperty();


    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty proisnum = new SimpleStringProperty();

    private final SimpleIntegerProperty sort = new SimpleIntegerProperty();

    private final  SimpleStringProperty proname = new SimpleStringProperty();

    private final SimpleIntegerProperty pronum = new SimpleIntegerProperty();

    private final SimpleDoubleProperty proprice = new SimpleDoubleProperty();



    public InquiryImportProperty() {
    }

    public InquiryImportProperty(boolean checked,long id,String proisnum,int sort,String proname,int pronum,double proprice) {
        setChecked(checked);
        setId(id);
        setProisnum(proisnum);
        setSort(sort);
        setProname(proname);
        setPronum(pronum);
        setProprice(proprice);
    }


    public boolean isChecked() {
        return checked.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
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

    public String getProisnum() {
        return proisnum.get();
    }

    public SimpleStringProperty proisnumProperty() {
        return proisnum;
    }

    public void setProisnum(String proisnum) {
        this.proisnum.set(proisnum);
    }

    public int getSort() {
        return sort.get();
    }

    public SimpleIntegerProperty sortProperty() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort.set(sort);
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

    public int getPronum() {
        return pronum.get();
    }

    public SimpleIntegerProperty pronumProperty() {
        return pronum;
    }

    public void setPronum(int pronum) {
        this.pronum.set(pronum);
    }

    public double getProprice() {
        return proprice.get();
    }

    public SimpleDoubleProperty propriceProperty() {
        return proprice;
    }

    public void setProprice(double proprice) {
        this.proprice.set(proprice);
    }
}
