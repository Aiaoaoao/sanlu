package com.yc.education.model.stock;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName StockChangeProductProperty
 * @Description TODO 库存异动产品
 * @Author QuZhangJing
 * @Date 2018/11/6 10:20
 * @Version 1.0
 */
public class StockChangeProductProperty {


    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty soursebill = new SimpleStringProperty();

    private final SimpleStringProperty sourseorder = new SimpleStringProperty();

    private final SimpleStringProperty sort = new SimpleStringProperty();

    private final SimpleStringProperty productorder = new SimpleStringProperty();

    private final SimpleStringProperty productname = new SimpleStringProperty();

    private final SimpleStringProperty productnum = new SimpleStringProperty();

    private final SimpleStringProperty unit = new SimpleStringProperty();

    private final SimpleStringProperty depotorder = new SimpleStringProperty();

    private final SimpleStringProperty depotname = new SimpleStringProperty();

    private final SimpleStringProperty remarks = new SimpleStringProperty();


    public StockChangeProductProperty() {
    }

    public StockChangeProductProperty(long id,String soursebill,String sourseorder,String sort,String productorder,String productname,Integer productnum,String unit,String depotorder,String depotname,String remarks){
        setId(id);
        setSoursebill(soursebill);
        setSourseorder(sourseorder);
        setSort(sort);
        setProductorder(productorder);
        setProductname(productname);
        setProductnum(productnum);
        setUnit(unit);
        setDepotorder(depotorder);
        setDepotname(depotname);
        setRemarks(remarks);
    }


    public StockChangeProductProperty(String soursebill,String sourseorder,String sort,String productorder,String productname,Integer productnum,String unit,String depotorder,String depotname,String remarks){
        setSoursebill(soursebill);
        setSourseorder(sourseorder);
        setSort(sort);
        setProductorder(productorder);
        setProductname(productname);
        setProductnum(productnum);
        setUnit(unit);
        setDepotorder(depotorder);
        setDepotname(depotname);
        setRemarks(remarks);
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

    public String getSoursebill() {
        return soursebill.get();
    }

    public SimpleStringProperty soursebillProperty() {
        return soursebill;
    }

    public void setSoursebill(String soursebill) {
        this.soursebill.set(soursebill);
    }

    public String getSourseorder() {
        return sourseorder.get();
    }

    public SimpleStringProperty sourseorderProperty() {
        return sourseorder;
    }

    public void setSourseorder(String sourseorder) {
        this.sourseorder.set(sourseorder);
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

    public String getProductorder() {
        return productorder.get();
    }

    public SimpleStringProperty productorderProperty() {
        return productorder;
    }

    public void setProductorder(String productorder) {
        this.productorder.set(productorder);
    }

    public String getProductname() {
        return productname.get();
    }

    public SimpleStringProperty productnameProperty() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname.set(productname);
    }

    public int getProductnum() {
        if("".equals(productnum.get())|| productnum.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(productnum.get());
        }
    }

    public SimpleStringProperty productnumProperty() {
        return productnum;
    }

    public void setProductnum(Integer productnum) {
        this.productnum.set(productnum.toString());
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getDepotorder() {
        return depotorder.get();
    }

    public SimpleStringProperty depotorderProperty() {
        return depotorder;
    }

    public void setDepotorder(String depotorder) {
        this.depotorder.set(depotorder);
    }

    public String getDepotname() {
        return depotname.get();
    }

    public SimpleStringProperty depotnameProperty() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname.set(depotname);
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
}
