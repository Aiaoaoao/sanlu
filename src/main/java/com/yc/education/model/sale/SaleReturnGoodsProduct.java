package com.yc.education.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sale_return_goods_product")
public class SaleReturnGoodsProduct {
    /**
     * 销售-销售退货单-销退产品
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description 序号
     * @Author BlueSky
     * @Date 11:05 2019/4/25
     **/
    @Transient
    private Integer no;

    /**
     * 销售退货单id
     */
    @Column(name = "return_goods_id")
    private Long returnGoodsId;

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
     * 品类
     */
    private String category;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 单位
     */
    private String unit;

    /**
     * 定价
     */
    private BigDecimal pricing;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 来源
     */
    private String source;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 库位
     */
    @Column(name = "warehouse_position")
    private String warehousePosition;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 入库数量
     */
    @Column(name = "inbound_num")
    private Integer inboundNum;

    //销退单
    @Transient
    private SaleReturnGoods saleReturnGoods;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInboundNum() {
        return inboundNum;
    }

    public void setInboundNum(Integer inboundNum) {
        this.inboundNum = inboundNum;
    }

    /**
     * 获取销售-销售退货单-销退产品
     *
     * @return id - 销售-销售退货单-销退产品
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置销售-销售退货单-销退产品
     *
     * @param id 销售-销售退货单-销退产品
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取销售退货单id
     *
     * @return return_goods_id - 销售退货单id
     */
    public Long getReturnGoodsId() {
        return returnGoodsId;
    }

    /**
     * 设置销售退货单id
     *
     * @param returnGoodsId 销售退货单id
     */
    public void setReturnGoodsId(Long returnGoodsId) {
        this.returnGoodsId = returnGoodsId;
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
     * 获取品类
     *
     * @return category - 品类
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置品类
     *
     * @param category 品类
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 获取定价
     *
     * @return pricing - 定价
     */
    public BigDecimal getPricing() {
        return pricing;
    }

    /**
     * 设置定价
     *
     * @param pricing 定价
     */
    public void setPricing(BigDecimal pricing) {
        this.pricing = pricing;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取来源
     *
     * @return source - 来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取库位
     *
     * @return warehouse_position - 库位
     */
    public String getWarehousePosition() {
        return warehousePosition;
    }

    /**
     * 设置库位
     *
     * @param warehousePosition 库位
     */
    public void setWarehousePosition(String warehousePosition) {
        this.warehousePosition = warehousePosition;
    }

    /**
     * 获取楼层
     *
     * @return floor - 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置楼层
     *
     * @param floor 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取添加时间
     *
     * @return addtime - 添加时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public SaleReturnGoods getSaleReturnGoods() {
        return saleReturnGoods;
    }

    public void setSaleReturnGoods(SaleReturnGoods saleReturnGoods) {
        this.saleReturnGoods = saleReturnGoods;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }
}