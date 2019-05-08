package com.yc.education.model.basic;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO  产品基本资料
 *@Author QuZhangJing
 *@Date 9:32  2018/9/5
 *@Version 1.0
 */
@Table(name = "product_basic")
public class ProductBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String protypeStr;

    /**
     * 虚拟字段 - 产品库存表 - 产品数量
     */
    @Transient
    private Integer stocknum;

    /**
     * 虚拟字段 - 产品库存表 - 产品进价
     */
    @Transient
    private Double purchaseprice;

    /**
     * 虚拟字段 - 计算结果 - 产品总成本
     */
    @Transient
    private Double totalCost;

    /**
     * 编号
     */
    private String isnum;

    /**
     * 产品名称
     */
    private String proname;

    /**
     * 产品条码
     */
    private String pronum;

    /**
     * 发票品名
     */
    private String invoicenum;

    /**
     * 基本单位
     */
    private Long basicunit;

    /**
     * 产品大类
     */
    private Long protype;

    /**
     * 标准售价类型
     */
    private Long normpricetype;

    /**
     * 标准售价
     */
    private Double normprice;

    /**
     * 最低售价类型
     */
    private Long lowpricetype;

    /**
     * 最低售价
     */
    private Double lowprice;

    /**
     * 产品性质
     */
    private Long pronature;

    /**
     * 产品来源
     */
    private Long prosource;

    /**
     * 包装数量
     */
    private Integer packnum;

    /**
     * 最小起订数
     */
    private Integer minnum;

    /**
     * 负责业务类型
     */
    private Long businesstype;

    /**
     * 负责业务
     */
    private String business;

    /**
     * 负责采购类型
     */
    private Long purchasetype;

    /**
     * 负责采购
     */
    private String purchase;

    /**
     * 最大库存量
     */
    private Integer maxstock;

    /**
     * 安全库存量
     */
    private Integer safetystock;

    /**
     * 重量
     */
    private Double weights;
    /**
     * 库位
     */
    private String inventoryplace;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String addpeople;

    /**
     * 建档日期
     */
    private String adddate;

    /**
     * 最后修改人
     */
    private String updatepeople;

    /**
     * 最后修改日期
     */
    private String updatedate;

    /**
     * 成本类型
     */
    private Long costtype;

    /**
     * 成本
     */
    private Double cost;


    /**
     * 美金
     */
    private Double usdcost;


    /**
     * 暂停使用
     */
    private Integer isstop;

    /**
     * 暂停描述
     */
    private String stopdes;

    @Transient
    private int sort;


    public ProductBasic() {

    }


    public ProductBasic(Long id ,  String isnum, String proname, String pronum, String invoicenum, Long basicunit, Long protype, Long normpricetype, Double normprice, Long lowpricetype, Double lowprice, Long pronature, Long prosource, Integer packnum, Integer minnum, Long businesstype, String business, Long purchasetype, String purchase, Integer maxstock, Integer safetystock,Double weights, String inventoryplace, String remarks, String addpeople, String adddate, String updatepeople, String updatedate, Long costtype, Double cost, Double usdcost, Integer isstop, String stopdes) {
        this.id = id;
        this.isnum = isnum;
        this.proname = proname;
        this.pronum = pronum;
        this.invoicenum = invoicenum;
        this.basicunit = basicunit;
        this.protype = protype;
        this.normpricetype = normpricetype;
        this.normprice = normprice;
        this.lowpricetype = lowpricetype;
        this.lowprice = lowprice;
        this.pronature = pronature;
        this.prosource = prosource;
        this.packnum = packnum;
        this.minnum = minnum;
        this.businesstype = businesstype;
        this.business = business;
        this.purchasetype = purchasetype;
        this.purchase = purchase;
        this.maxstock = maxstock;
        this.safetystock = safetystock;
        this.weights = weights;
        this.inventoryplace = inventoryplace;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.costtype = costtype;
        this.cost = cost;
        this.usdcost = usdcost;
        this.isstop = isstop;
        this.stopdes = stopdes;
    }

    public ProductBasic(  String isnum, String proname, String pronum, String invoicenum, Long basicunit, Long protype, Long normpricetype, Double normprice, Long lowpricetype, Double lowprice, Long pronature, Long prosource, Integer packnum, Integer minnum, Long businesstype, String business, Long purchasetype, String purchase, Integer maxstock, Integer safetystock,Double weights, String inventoryplace, String remarks, String addpeople, String adddate, String updatepeople, String updatedate, Long costtype, Double cost, Double usdcost, Integer isstop, String stopdes) {
        this.isnum = isnum;
        this.proname = proname;
        this.pronum = pronum;
        this.invoicenum = invoicenum;
        this.basicunit = basicunit;
        this.protype = protype;
        this.normpricetype = normpricetype;
        this.normprice = normprice;
        this.lowpricetype = lowpricetype;
        this.lowprice = lowprice;
        this.pronature = pronature;
        this.prosource = prosource;
        this.packnum = packnum;
        this.minnum = minnum;
        this.businesstype = businesstype;
        this.business = business;
        this.purchasetype = purchasetype;
        this.purchase = purchase;
        this.maxstock = maxstock;
        this.safetystock = safetystock;
        this.weights = weights;
        this.inventoryplace = inventoryplace;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.costtype = costtype;
        this.cost = cost;
        this.usdcost = usdcost;
        this.isstop = isstop;
        this.stopdes = stopdes;
    }

    public ProductBasic(long id, Double normprice, Double lowprice) {
        this.id = id;
        this.normprice = normprice;
        this.lowprice = lowprice;
    }

    public ProductBasic(long id,String isnum) {
        this.id = id;
        this.isnum = isnum;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return isnum - 编号
     */
    public String getIsnum() {
        return isnum;
    }

    /**
     * 设置编号
     *
     * @param isnum 编号
     */
    public void setIsnum(String isnum) {
        this.isnum = isnum;
    }

    /**
     * 获取产品名称
     *
     * @return proname - 产品名称
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置产品名称
     *
     * @param proname 产品名称
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取产品条码
     *
     * @return pronum - 产品条码
     */
    public String getPronum() {
        return pronum;
    }

    /**
     * 设置产品条码
     *
     * @param pronum 产品条码
     */
    public void setPronum(String pronum) {
        this.pronum = pronum;
    }

    /**
     * 获取发票品名
     *
     * @return invoicenum - 发票品名
     */
    public String getInvoicenum() {
        return invoicenum;
    }

    /**
     * 设置发票品名
     *
     * @param invoicenum 发票品名
     */
    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    /**
     * 获取基本单位
     *
     * @return basicunit - 基本单位
     */
    public Long getBasicunit() {
        return basicunit;
    }

    /**
     * 设置基本单位
     *
     * @param basicunit 基本单位
     */
    public void setBasicunit(Long basicunit) {
        this.basicunit = basicunit;
    }

    /**
     * 获取产品大类
     *
     * @return protype - 产品大类
     */
    public Long getProtype() {
        return protype;
    }

    /**
     * 设置产品大类
     *
     * @param protype 产品大类
     */
    public void setProtype(Long protype) {
        this.protype = protype;
    }

    /**
     * 获取标准售价类型
     *
     * @return normpricetype - 标准售价类型
     */
    public Long getNormpricetype() {
        return normpricetype;
    }

    /**
     * 设置标准售价类型
     *
     * @param normpricetype 标准售价类型
     */
    public void setNormpricetype(Long normpricetype) {
        this.normpricetype = normpricetype;
    }

    /**
     * 获取标准售价
     *
     * @return normprice - 标准售价
     */
    public Double getNormprice() {
        return normprice;
    }

    /**
     * 设置标准售价
     *
     * @param normprice 标准售价
     */
    public void setNormprice(Double normprice) {
        this.normprice = normprice;
    }

    /**
     * 获取最低售价类型
     *
     * @return lowpricetype - 最低售价类型
     */
    public Long getLowpricetype() {
        return lowpricetype;
    }

    /**
     * 设置最低售价类型
     *
     * @param lowpricetype 最低售价类型
     */
    public void setLowpricetype(Long lowpricetype) {
        this.lowpricetype = lowpricetype;
    }

    /**
     * 获取最低售价
     *
     * @return lowprice - 最低售价
     */
    public Double getLowprice() {
        return lowprice;
    }

    /**
     * 设置最低售价
     *
     * @param lowprice 最低售价
     */
    public void setLowprice(Double lowprice) {
        this.lowprice = lowprice;
    }

    /**
     * 获取产品性质
     *
     * @return pronature - 产品性质
     */
    public Long getPronature() {
        return pronature;
    }

    /**
     * 设置产品性质
     *
     * @param pronature 产品性质
     */
    public void setPronature(Long pronature) {
        this.pronature = pronature;
    }

    /**
     * 获取产品来源
     *
     * @return prosource - 产品来源
     */
    public Long getProsource() {
        return prosource;
    }

    /**
     * 设置产品来源
     *
     * @param prosource 产品来源
     */
    public void setProsource(Long prosource) {
        this.prosource = prosource;
    }

    /**
     * 获取包装数量
     *
     * @return packnum - 包装数量
     */
    public Integer getPacknum() {
        return packnum;
    }

    /**
     * 设置包装数量
     *
     * @param packnum 包装数量
     */
    public void setPacknum(Integer packnum) {
        this.packnum = packnum;
    }

    /**
     * 获取最小起订数
     *
     * @return minnum - 最小起订数
     */
    public Integer getMinnum() {
        return minnum;
    }

    /**
     * 设置最小起订数
     *
     * @param minnum 最小起订数
     */
    public void setMinnum(Integer minnum) {
        this.minnum = minnum;
    }

    /**
     * 获取负责业务类型
     *
     * @return businesstype - 负责业务类型
     */
    public Long getBusinesstype() {
        return businesstype;
    }

    /**
     * 设置负责业务类型
     *
     * @param businesstype 负责业务类型
     */
    public void setBusinesstype(Long businesstype) {
        this.businesstype = businesstype;
    }

    /**
     * 获取负责业务
     *
     * @return business - 负责业务
     */
    public String getBusiness() {
        return business;
    }

    /**
     * 设置负责业务
     *
     * @param business 负责业务
     */
    public void setBusiness(String business) {
        this.business = business;
    }

    /**
     * 获取负责采购类型
     *
     * @return purchasetype - 负责采购类型
     */
    public Long getPurchasetype() {
        return purchasetype;
    }

    /**
     * 设置负责采购类型
     *
     * @param purchasetype 负责采购类型
     */
    public void setPurchasetype(Long purchasetype) {
        this.purchasetype = purchasetype;
    }

    /**
     * 获取负责采购
     *
     * @return purchase - 负责采购
     */
    public String getPurchase() {
        return purchase;
    }

    /**
     * 设置负责采购
     *
     * @param purchase 负责采购
     */
    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    /**
     * 获取最大库存量
     *
     * @return maxstock - 最大库存量
     */
    public Integer getMaxstock() {
        return maxstock;
    }

    /**
     * 设置最大库存量
     *
     * @param maxstock 最大库存量
     */
    public void setMaxstock(Integer maxstock) {
        this.maxstock = maxstock;
    }

    /**
     * 获取安全库存量
     *
     * @return safetystock - 安全库存量
     */
    public Integer getSafetystock() {
        return safetystock;
    }

    /**
     * 设置安全库存量
     *
     * @param safetystock 安全库存量
     */
    public void setSafetystock(Integer safetystock) {
        this.safetystock = safetystock;
    }

    /**
     * 获取库位
     *
     * @return inventoryplace - 库位
     */
    public String getInventoryplace() {
        return inventoryplace;
    }

    /**
     * 设置库位
     *
     * @param inventoryplace 库位
     */
    public void setInventoryplace(String inventoryplace) {
        this.inventoryplace = inventoryplace;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取建档人
     *
     * @return addpeople - 建档人
     */
    public String getAddpeople() {
        return addpeople;
    }

    /**
     * 设置建档人
     *
     * @param addpeople 建档人
     */
    public void setAddpeople(String addpeople) {
        this.addpeople = addpeople;
    }



    /**
     * 获取最后修改人
     *
     * @return updatepeople - 最后修改人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后修改人
     *
     * @param updatepeople 最后修改人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }



    /**
     * 获取成本类型
     *
     * @return costtype - 成本类型
     */
    public Long getCosttype() {
        return costtype;
    }

    /**
     * 设置成本类型
     *
     * @param costtype 成本类型
     */
    public void setCosttype(Long costtype) {
        this.costtype = costtype;
    }

    /**
     * 获取成本
     *
     * @return cost - 成本
     */
    public Double getCost() {
        return cost;
    }

    /**
     * 设置成本
     *
     * @param cost 成本
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * 获取暂停使用
     *
     * @return isstop - 暂停使用
     */
    public Integer getIsstop() {
        return isstop;
    }

    /**
     * 设置暂停使用
     *
     * @param isstop 暂停使用
     */
    public void setIsstop(Integer isstop) {
        this.isstop = isstop;
    }

    /**
     * 获取暂停描述
     *
     * @return stopdes - 暂停描述
     */
    public String getStopdes() {
        return stopdes;
    }

    /**
     * 设置暂停描述
     *
     * @param stopdes 暂停描述
     */
    public void setStopdes(String stopdes) {
        this.stopdes = stopdes;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public Double getUsdcost() {
        return usdcost;
    }

    public void setUsdcost(Double usdcost) {
        this.usdcost = usdcost;
    }

    public String getProtypeStr() {
        return protypeStr;
    }

    public void setProtypeStr(String protypeStr) {
        this.protypeStr = protypeStr;
    }

    public Integer getStocknum() {
        return stocknum;
    }

    public void setStocknum(Integer stocknum) {
        this.stocknum = stocknum;
    }

    public Double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Double getWeights() {
        return weights;
    }

    public void setWeights(Double weights) {
        this.weights = weights;
    }
}