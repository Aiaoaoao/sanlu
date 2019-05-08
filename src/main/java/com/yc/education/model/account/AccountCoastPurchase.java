package com.yc.education.model.account;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "account_coast_purchase")
public class AccountCoastPurchase {
    /**
     * 成本核算-采购成本
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 成本核算id
     */
    private Long otherid;

    /**
     * 产品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 仓库库位
     */
    @Column(name = "warehouse_position")
    private String warehousePosition;

    /**
     * 入库数量
     */
    @Column(name = "warehouse_num")
    private Integer warehouseNum;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 美金
     */
    private BigDecimal dollar;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;


    /**
     * 人民币总金额
     */
    @Column(name = "rmb_money")
    private BigDecimal rmbMoney;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 美金总金额
     */
    @Column(name = "usd_money")
    private BigDecimal usdMoney;

    public BigDecimal getUsdMoney() {
        return usdMoney;
    }

    public void setUsdMoney(BigDecimal usdMoney) {
        this.usdMoney = usdMoney;
    }

    public BigDecimal getRmbMoney() {
        return rmbMoney;
    }

    public void setRmbMoney(BigDecimal rmbMoney) {
        this.rmbMoney = rmbMoney;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取成本核算-采购成本
     *
     * @return id - 成本核算-采购成本
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置成本核算-采购成本
     *
     * @param id 成本核算-采购成本
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取成本核算id
     *
     * @return otherid - 成本核算id
     */
    public Long getOtherid() {
        return otherid;
    }

    /**
     * 设置成本核算id
     *
     * @param otherid 成本核算id
     */
    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    /**
     * 获取产品编号
     *
     * @return product_no - 产品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置产品编号
     *
     * @param productNo 产品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取仓库库位
     *
     * @return warehouse_position - 仓库库位
     */
    public String getWarehousePosition() {
        return warehousePosition;
    }

    /**
     * 设置仓库库位
     *
     * @param warehousePosition 仓库库位
     */
    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition = warehousePosition;
    }

    /**
     * 获取入库数量
     *
     * @return warehouse_num - 入库数量
     */
    public Integer getWarehouseNum() {
        return warehouseNum;
    }

    /**
     * 设置入库数量
     *
     * @param warehouseNum 入库数量
     */
    public void setWarehouseNum(Integer warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取美金
     *
     * @return dollar - 美金
     */
    public BigDecimal getDollar() {
        return dollar;
    }

    /**
     * 设置美金
     *
     * @param dollar 美金
     */
    public void setDollar(BigDecimal dollar) {
        this.dollar = dollar;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}