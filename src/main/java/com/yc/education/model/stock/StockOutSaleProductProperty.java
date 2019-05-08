package com.yc.education.model.stock;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;

/**
 * @Description 销货出库单产品
 * @Author BlueSky
 * @Date 2018-11-06 16:58
 */
public class StockOutSaleProductProperty {

    private final SimpleLongProperty id = new SimpleLongProperty();

    private SimpleIntegerProperty no = new SimpleIntegerProperty();    // 序号

    private final SimpleStringProperty orderSource = new SimpleStringProperty();

    private final SimpleStringProperty stockOutSaleId = new SimpleStringProperty();

    private final SimpleStringProperty orderNo = new SimpleStringProperty();

    private final SimpleStringProperty productNo = new SimpleStringProperty();

    private final SimpleStringProperty productName = new SimpleStringProperty();

    private final SimpleStringProperty category = new SimpleStringProperty();

    private final SimpleStringProperty num = new SimpleStringProperty();

    private final SimpleStringProperty unit = new SimpleStringProperty();

    private final SimpleStringProperty price = new SimpleStringProperty();

    private final SimpleStringProperty warehouseName = new SimpleStringProperty();

    private final SimpleStringProperty floor = new SimpleStringProperty();

    private final SimpleStringProperty remark = new SimpleStringProperty();

    private SimpleBooleanProperty checked = new SimpleBooleanProperty();

    public StockOutSaleProductProperty(Long id,Integer no,  Long stockOutSaleId,String orderSource, String orderNo, String productNo, String productName, String category, Integer num, String unit, BigDecimal price, String warehouseName, String floor, String remarks) {
        setId(id);
        setNo(no);
        if(stockOutSaleId != null){
            setStockOutSaleId(stockOutSaleId.toString());
        }
        setOrderSource(orderSource);
        setOrderNo(orderNo);
        setProductNo(productNo);
        setProductName(productName);
        setCategory(category);
        setNum(num.toString());
        setUnit(unit);
        setPrice(price.toString());
        setWarehouseName(warehouseName);
        setFloor(floor);
        setRemark(remarks);
    }

    public StockOutSaleProductProperty(Long id,Integer no, Long stockOutSaleId,String orderSource, String orderNo, String productNo, String productName, String category, Integer num, String unit, BigDecimal price, String warehouseName, String floor, String remarks,Boolean checked) {
        setId(id);
        setNo(no);
        if(stockOutSaleId != null){
            setStockOutSaleId(stockOutSaleId.toString());
        }
        setOrderSource(orderSource);
        setOrderNo(orderNo);
        setProductNo(productNo);
        setProductName(productName);
        setCategory(category);
        setNum(num.toString());
        setUnit(unit);
        setPrice(price.toString());
        setWarehouseName(warehouseName);
        setFloor(floor);
        setRemark(remarks);
        setChecked(checked);
    }



    public StockOutSaleProductProperty() {
    }

    public int getNo() {
        return no.get();
    }

    public SimpleIntegerProperty noProperty() {
        return no;
    }

    public void setNo(int no) {
        this.no.set(no);
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

    public String getStockOutSaleId() {
        return stockOutSaleId.get();
    }

    public SimpleStringProperty stockOutSaleIdProperty() {
        return stockOutSaleId;
    }

    public void setStockOutSaleId(String stockOutSaleId) {
        this.stockOutSaleId.set(stockOutSaleId);
    }

    public Long getId() {
        if(id == null){
            return null;
        }
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getOrderSource() {
        return orderSource.get();
    }

    public SimpleStringProperty orderSourceProperty() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource.set(orderSource);
    }

    public String getOrderNo() {
        return orderNo.get();
    }

    public SimpleStringProperty orderNoProperty() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo.set(orderNo);
    }

    public String getProductNo() {
        return productNo.get();
    }

    public SimpleStringProperty productNoProperty() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo.set(productNo);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getNum() {
        return num.get();
    }

    public SimpleStringProperty numProperty() {
        return num;
    }

    public void setNum(String num) {
        this.num.set(num);
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

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getWarehouseName() {
        return warehouseName.get();
    }

    public SimpleStringProperty warehouseNameProperty() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName.set(warehouseName);
    }

    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getRemark() {
        return remark.get();
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }
}
