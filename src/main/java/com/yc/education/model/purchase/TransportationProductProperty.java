package com.yc.education.model.purchase;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @ClassName TransportationProductProperty
 * @Description TODO 在途产品之TabelView数据绑定
 * @Author QuZhangJing
 * @Date 2018/10/17 16:48
 * @Version 1.0
 */
public class TransportationProductProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private final SimpleStringProperty serialNumber = new SimpleStringProperty();

    private final SimpleStringProperty purchaseorder = new SimpleStringProperty();

    private final SimpleStringProperty sort = new SimpleStringProperty();

    private final SimpleStringProperty seeorder = new SimpleStringProperty();

    private final SimpleStringProperty pronum = new SimpleStringProperty();

    private final SimpleStringProperty proName = new SimpleStringProperty();

    private final SimpleStringProperty stocknum = new SimpleStringProperty();

    private final SimpleStringProperty boxnum = new SimpleStringProperty();

    private final SimpleStringProperty thistimeontheway = new SimpleStringProperty();

    private final SimpleStringProperty totalnum = new SimpleStringProperty();

    private final SimpleStringProperty stockover = new SimpleStringProperty();

    private final SimpleStringProperty forcenum = new SimpleStringProperty();

    private final SimpleStringProperty ontheway = new SimpleStringProperty();

    private final SimpleStringProperty pro_depotnum = new SimpleStringProperty();

    private final SimpleStringProperty pro_depotname = new SimpleStringProperty();

    private final SimpleStringProperty pro_floor = new SimpleStringProperty();

    private final SimpleStringProperty remarks = new SimpleStringProperty();



    public TransportationProductProperty() {
    }

    public TransportationProductProperty(long id,int serialNumber,String purchaseorder,int sort,String seeorder,String pronum,String proName,String socknum,String boxnum,int thistimeontheway,int totalnum,int stockover,int forcenum,int ontheway,String pro_depotnum,String pro_depotname,String pro_floor,String remarks) {
        setId(id);
        setPurchaseorder(purchaseorder);
        setSerialNumber(String.valueOf(serialNumber));
        setSort(String.valueOf(sort));
        setSeeorder(seeorder);
        setPronum(pronum);
        setProName(proName);
        setStocknum(socknum);
        setBoxnum(boxnum);
        setThistimeontheway(String.valueOf(thistimeontheway));
        setTotalnum(String.valueOf(totalnum));
        setStockover(String.valueOf(stockover));
        setForcenum(String.valueOf(forcenum));
        setOntheway(String.valueOf(ontheway));
        setPro_depotnum(pro_depotnum);
        setPro_depotname(pro_depotname);
        setPro_floor(pro_floor);
        setRemarks(remarks);

    }

    public TransportationProductProperty(int serialNumber,String purchaseorder,int sort,String seeorder,String pronum,String proName,String socknum,String boxnum,int thistimeontheway,int totalnum,int stockover,int forcenum,int ontheway,String pro_depotnum,String pro_depotname,String pro_floor,String remarks) {
        setPurchaseorder(purchaseorder);
        setSort(String.valueOf(sort));
        setSerialNumber(String.valueOf(serialNumber));
        setSeeorder(seeorder);
        setPronum(pronum);
        setProName(proName);
        setStocknum(socknum);
        setBoxnum(boxnum);
        setThistimeontheway(String.valueOf(thistimeontheway));
        setTotalnum(String.valueOf(totalnum));
        setStockover(String.valueOf(stockover));
        setForcenum(String.valueOf(forcenum));
        setOntheway(String.valueOf(ontheway));
        setPro_depotnum(pro_depotnum);
        setPro_depotname(pro_depotname);
        setPro_floor(pro_floor);
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

    public String getPurchaseorder() {
        return purchaseorder.get();
    }

    public SimpleStringProperty purchaseorderProperty() {
        return purchaseorder;
    }

    public void setPurchaseorder(String purchaseorder) {
        this.purchaseorder.set(purchaseorder);
    }

    public int getSort() {

        if("".equals(sort.get())|| sort.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(sort.get());
        }
    }

    public SimpleStringProperty sortProperty() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort.set(sort);
    }

    public String getPronum() {
        return pronum.get();
    }

    public SimpleStringProperty pronumProperty() {
        return pronum;
    }

    public void setPronum(String pronum) {
        this.pronum.set(pronum);
    }

    public String getStocknum() {
        return stocknum.get();
    }

    public SimpleStringProperty stocknumProperty() {
        return stocknum;
    }

    public void setStocknum(String stocknum) {
        this.stocknum.set(stocknum);
    }

    public String getBoxnum() {
        return boxnum.get();
    }

    public SimpleStringProperty boxnumProperty() {
        return boxnum;
    }

    public void setBoxnum(String boxnum) {
        this.boxnum.set(boxnum);
    }

    public int getThistimeontheway() {

        if("".equals(thistimeontheway.get())|| thistimeontheway.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(thistimeontheway.get());
        }
    }

    public SimpleStringProperty thistimeonthewayProperty() {
        return thistimeontheway;
    }

    public void setThistimeontheway(String thistimeontheway) {
        this.thistimeontheway.set(thistimeontheway);
    }

    public int  getTotalnum() {

        if("".equals(totalnum.get())|| totalnum.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(totalnum.get());
        }
    }

    public SimpleStringProperty totalnumProperty() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum.set(totalnum);
    }

    public int getStockover() {

        if("".equals(stockover.get())|| stockover.get() == null ){
            return 0;
        }else{
            return Integer.valueOf(stockover.get());
        }
    }

    public SimpleStringProperty stockoverProperty() {
        return stockover;
    }

    public void setStockover(String stockover) {
        this.stockover.set(stockover);
    }

    public int getForcenum() {


        if("".equals(forcenum.get())|| forcenum.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(forcenum.get());
        }

    }

    public SimpleStringProperty forcenumProperty() {
        return forcenum;
    }

    public void setForcenum(String forcenum) {
        this.forcenum.set(forcenum);
    }

    public int getOntheway() {
        if("".equals(ontheway.get())|| ontheway.get() == null ){
            return 0;
        }else{
            return Integer.parseInt(ontheway.get());
        }
    }

    public SimpleStringProperty onthewayProperty() {
        return ontheway;
    }

    public void setOntheway(String ontheway) {
        this.ontheway.set(ontheway);
    }


    public String getPro_depotnum() {
        return pro_depotnum.get();
    }

    public SimpleStringProperty pro_depotnumProperty() {
        return pro_depotnum;
    }

    public void setPro_depotnum(String pro_depotnum) {
        this.pro_depotnum.set(pro_depotnum);
    }

    public String getPro_depotname() {
        return pro_depotname.get();
    }

    public SimpleStringProperty pro_depotnameProperty() {
        return pro_depotname;
    }

    public void setPro_depotname(String pro_depotname) {
        this.pro_depotname.set(pro_depotname);
    }

    public String getPro_floor() {
        return pro_floor.get();
    }

    public SimpleStringProperty pro_floorProperty() {
        return pro_floor;
    }

    public void setPro_floor(String pro_floor) {
        this.pro_floor.set(pro_floor);
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

    public String getProName() {
        return proName.get();
    }

    public SimpleStringProperty proNameProperty() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName.set(proName);
    }

    public String getSeeorder() {
        return seeorder.get();
    }

    public SimpleStringProperty seeorderProperty() {
        return seeorder;
    }

    public void setSeeorder(String seeorder) {
        this.seeorder.set(seeorder);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }
}
