package com.yc.education.model.stock;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName CheckStockProductProperty
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/9 16:06
 * @Version 1.0
 */
public class CheckStockProductProperty {


    private final SimpleLongProperty id = new SimpleLongProperty();


    /**
     * 产品编号
     */
    private final SimpleStringProperty productorder = new SimpleStringProperty();
    /**
     * 产品名称
     */
    private final SimpleStringProperty productname = new SimpleStringProperty();
    /**
     * 库位编号
     */
    private final SimpleStringProperty depotorder = new SimpleStringProperty();
    /**
     * 库位名称
     */
    private final SimpleStringProperty depotname = new SimpleStringProperty();
    /**
     * 库存数量
     */
    private final SimpleStringProperty depotnum = new SimpleStringProperty();

    /**
     * 实盘数量
     */
    private final SimpleStringProperty nownum = new SimpleStringProperty();
    /**
     * 单位
     */
    private final SimpleStringProperty productunit = new SimpleStringProperty();
    /**
     * 盘盈/盘亏
     */
    private final SimpleStringProperty profitandloss = new SimpleStringProperty();
    /**
     * 单价
     */
    private final SimpleStringProperty productprice = new SimpleStringProperty();
    /**
     * 备注
     */
    private final SimpleStringProperty remarks = new SimpleStringProperty();

    //无参构造
    public CheckStockProductProperty() {
    }

    //重构构造函数
    public CheckStockProductProperty(long id,String productorder,String productname,String depotorder,String depotname,int depotnum,int nownum,String productunit,String profitandloss,Double productprice,String remarks) {
    setId(id);
    setProductorder(productorder);
    setProductname(productname);
    setDepotorder(depotorder);
    setDepotname(depotname);
    setDepotnum(String.valueOf(depotnum));
    setNownum(String.valueOf(nownum));
    setProductunit(productunit);
    setProfitandloss(profitandloss);
    setProductprice(String.valueOf(productprice));
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

    public int getDepotnum() {
        if("".equals(depotnum.get())|| depotnum.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(depotnum.get());
        }
    }

    public SimpleStringProperty depotnumProperty() {
        return depotnum;
    }

    public void setDepotnum(String depotnum) {
        this.depotnum.set(depotnum);
    }

    public int getNownum() {
        if("".equals(nownum.get())|| nownum.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(nownum.get());
        }
    }

    public SimpleStringProperty nownumProperty() {
        return nownum;
    }

    public void setNownum(String nownum) {
        this.nownum.set(nownum);
    }

    public String getProductunit() {
        return productunit.get();
    }

    public SimpleStringProperty productunitProperty() {
        return productunit;
    }

    public void setProductunit(String productunit) {
        this.productunit.set(productunit);
    }

    public String getProfitandloss() {
        return profitandloss.get();
    }

    public SimpleStringProperty profitandlossProperty() {
        return profitandloss;
    }

    public void setProfitandloss(String profitandloss) {
        this.profitandloss.set(profitandloss);
    }

    public Double getProductprice() {
        if("".equals(productprice.get())|| productprice.get() == null ){
            return 0.0;
        }else{
            return Double.parseDouble(productprice.get());
        }

    }

    public SimpleStringProperty productpriceProperty() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice.set(productprice);
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
