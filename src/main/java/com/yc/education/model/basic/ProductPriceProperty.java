package com.yc.education.model.basic;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName ProductPriceProperty
 * @Description TODO 产品价格设定-TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/9/21 10:25
 * @Version 1.0
 */
public class ProductPriceProperty {



    private final SimpleLongProperty id = new SimpleLongProperty();

    private  final SimpleStringProperty isnum = new SimpleStringProperty();

    private  final SimpleStringProperty proname = new SimpleStringProperty();

    private  final SimpleStringProperty protype = new SimpleStringProperty();

    private  final SimpleStringProperty basicunit = new SimpleStringProperty();

    private  final SimpleDoubleProperty normprice = new SimpleDoubleProperty();

    private  final SimpleDoubleProperty lowprice = new SimpleDoubleProperty();

    private  final SimpleStringProperty remarks = new SimpleStringProperty();

    /**
     * 构造函数
     */
    public ProductPriceProperty() {

    }

    /**
     * 重载构造函数
     * @param id
     * @param isnum
     * @param proname
     * @param protype
     * @param basicunit
     * @param normprice
     * @param lowprice
     * @param remarks
     */
    public ProductPriceProperty(long id,String isnum,String proname,String protype,String basicunit,Double normprice,Double lowprice,String remarks ) {
        setId(id);
        setIsnum(isnum);
        setProname(proname);
        setProtype(protype);
        setBasicunit(basicunit);
        setNormprice(normprice);
        setLowprice(lowprice);
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

    public String getIsnum() {
        return isnum.get();
    }

    public SimpleStringProperty isnumProperty() {
        return isnum;
    }

    public void setIsnum(String isnum) {
        this.isnum.set(isnum);
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


    public String getProtype() {
        return protype.get();
    }

    public SimpleStringProperty protypeProperty() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype.set(protype);
    }

    public String getBasicunit() {
        return basicunit.get();
    }

    public SimpleStringProperty basicunitProperty() {
        return basicunit;
    }

    public void setBasicunit(String basicunit) {
        this.basicunit.set(basicunit);
    }

    public double getNormprice() {
        return normprice.get();
    }

    public SimpleDoubleProperty normpriceProperty() {
        return normprice;
    }

    public void setNormprice(double normprice) {
        this.normprice.set(normprice);
    }

    public double getLowprice() {
        return lowprice.get();
    }

    public SimpleDoubleProperty lowpriceProperty() {
        return lowprice;
    }

    public void setLowprice(double lowprice) {
        this.lowprice.set(lowprice);
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
