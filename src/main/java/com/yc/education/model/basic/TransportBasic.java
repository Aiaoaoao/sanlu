package com.yc.education.model.basic;

import javax.persistence.*;
/**
 *@Description TODO 运输商基本信息
 *@Author QuZhangJing
 *@Date 11:55  2019/3/18
 *@Version 1.0
 */
@Table(name = "transport_basic")
public class TransportBasic {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String idnum;

    /**
     * 供应商简称
     */
    private String supdes;

    /**
     * 供应商名称
     */
    private String supname;

    /**
     * 注册地址
     */
    private String regadd;

    /**
     * 国家
     */
    private Integer country;

    /**
     * 地区
     */
    private String area;

    /**
     * 邮政编码
     */
    private String postalcode;

    /**
     * 电话类型
     */
    private Integer phonetype;

    /**
     * 电话
     */
    private String phone;

    /**
     * 传真类型
     */
    private Integer faxtype;

    /**
     * 传真
     */
    private String fax;

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
     * 是否暂停
     */
    private Integer isstop;

    /**
     * 暂停说明
     */
    private String stopdes;

    /**
     * 禁止来往
     */
    private Integer comeandgo;

    @Transient
    private int sort;


    public TransportBasic() {

    }

    public TransportBasic(long id,String idnum) {
        this.id=id;
        this.idnum = idnum;
    }

    public TransportBasic(Long id, String idnum, String supdes, String supname, String regadd, Integer country, String area, String postalcode, Integer phonetype, String phone, Integer faxtype, String fax, String remarks, String addpeople, String adddate, String updatepeople, String updatedate, Integer isstop, String stopdes, Integer comeandgo) {
        this.id = id;
        this.idnum = idnum;
        this.supdes = supdes;
        this.supname = supname;
        this.regadd = regadd;
        this.country = country;
        this.area = area;
        this.postalcode = postalcode;
        this.phonetype = phonetype;
        this.phone = phone;
        this.faxtype = faxtype;
        this.fax = fax;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.isstop = isstop;
        this.stopdes = stopdes;
        this.comeandgo = comeandgo;
    }

    public TransportBasic( String idnum, String supdes, String supname, String regadd, Integer country, String area, String postalcode, Integer phonetype, String phone, Integer faxtype, String fax, String remarks, String addpeople, String adddate, String updatepeople, String updatedate, Integer isstop, String stopdes, Integer comeandgo) {
        this.idnum = idnum;
        this.supdes = supdes;
        this.supname = supname;
        this.regadd = regadd;
        this.country = country;
        this.area = area;
        this.postalcode = postalcode;
        this.phonetype = phonetype;
        this.phone = phone;
        this.faxtype = faxtype;
        this.fax = fax;
        this.remarks = remarks;
        this.addpeople = addpeople;
        this.adddate = adddate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.isstop = isstop;
        this.stopdes = stopdes;
        this.comeandgo = comeandgo;
    }

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取编号
     *
     * @return idnum - 编号
     */
    public String getIdnum() {
        return idnum;
    }

    /**
     * 设置编号
     *
     * @param idnum 编号
     */
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    /**
     * 获取供应商简称
     *
     * @return supdes - 供应商简称
     */
    public String getSupdes() {
        return supdes;
    }

    /**
     * 设置供应商简称
     *
     * @param supdes 供应商简称
     */
    public void setSupdes(String supdes) {
        this.supdes = supdes;
    }

    /**
     * 获取供应商名称
     *
     * @return supname - 供应商名称
     */
    public String getSupname() {
        return supname;
    }

    /**
     * 设置供应商名称
     *
     * @param supname 供应商名称
     */
    public void setSupname(String supname) {
        this.supname = supname;
    }

    /**
     * 获取注册地址
     *
     * @return regadd - 注册地址
     */
    public String getRegadd() {
        return regadd;
    }

    /**
     * 设置注册地址
     *
     * @param regadd 注册地址
     */
    public void setRegadd(String regadd) {
        this.regadd = regadd;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public Integer getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(Integer country) {
        this.country = country;
    }

    /**
     * 获取地区
     *
     * @return area - 地区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置地区
     *
     * @param area 地区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取邮政编码
     *
     * @return postalcode - 邮政编码
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalcode 邮政编码
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * 获取电话类型
     *
     * @return phonetype - 电话类型
     */
    public Integer getPhonetype() {
        return phonetype;
    }

    /**
     * 设置电话类型
     *
     * @param phonetype 电话类型
     */
    public void setPhonetype(Integer phonetype) {
        this.phonetype = phonetype;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取传真类型
     *
     * @return faxtype - 传真类型
     */
    public Integer getFaxtype() {
        return faxtype;
    }

    /**
     * 设置传真类型
     *
     * @param faxtype 传真类型
     */
    public void setFaxtype(Integer faxtype) {
        this.faxtype = faxtype;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
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
     * 获取建档日期
     *
     * @return adddate - 建档日期
     */
    public String getAdddate() {
        return adddate;
    }

    /**
     * 设置建档日期
     *
     * @param adddate 建档日期
     */
    public void setAdddate(String adddate) {
        this.adddate = adddate;
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
     * 获取最后修改日期
     *
     * @return updatedate - 最后修改日期
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置最后修改日期
     *
     * @param updatedate 最后修改日期
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取是否暂停
     *
     * @return isstop - 是否暂停
     */
    public Integer getIsstop() {
        return isstop;
    }

    /**
     * 设置是否暂停
     *
     * @param isstop 是否暂停
     */
    public void setIsstop(Integer isstop) {
        this.isstop = isstop;
    }

    /**
     * 获取暂停说明
     *
     * @return stopdes - 暂停说明
     */
    public String getStopdes() {
        return stopdes;
    }

    /**
     * 设置暂停说明
     *
     * @param stopdes 暂停说明
     */
    public void setStopdes(String stopdes) {
        this.stopdes = stopdes;
    }

    /**
     * 获取禁止来往
     *
     * @return comeandgo - 禁止来往
     */
    public Integer getComeandgo() {
        return comeandgo;
    }

    /**
     * 设置禁止来往
     *
     * @param comeandgo 禁止来往
     */
    public void setComeandgo(Integer comeandgo) {
        this.comeandgo = comeandgo;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}