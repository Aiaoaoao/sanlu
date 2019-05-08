package com.yc.education.model.stock;

import java.util.Date;
import javax.persistence.*;
/**
 *@Description TODO 快递寄件
 *@Author QuZhangJing
 *@Date 15:44  2018/11/12
 *@Version 1.0
 */
@Table(name = "express_mail")
public class ExpressMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 寄件日期
     */
    private Date maildate;

    /**
     * 寄件日期
     */
    @Transient
    private String maildatetime;


    /**
     * 快递单号
     */
    private String mailorder;

    /**
     * 快递公司
     */
    private String company;

    /**
     * 数量(件数)
     */
    private Integer mailnum;

    /**
     * 重量(KG)
     */
    private Double mailweight;

    /**
     * 寄托内容
     */
    private Integer contenttype;

    /**
     * 内容
     */
    private String content;

    /**
     * 支付方式
     */
    private Integer paymethodtype;

    /**
     * 支付方法
     */
    private String paymethod;

    /**
     * 本次运费类型
     */
    private Integer freighttype;

    /**
     * 本次运费
     */
    private Double freightprice;

    /**
     * 是否制单时已付
     */
    private Integer ispay;

    /**
     * 是否月付
     */
    private Integer ismonth;

    /**
     * 账号
     */
    private String account;

    /**
     * 保洁费
     */
    private Double ensure;

    /**
     * 保洁费
     */
    private Double ensurepoint;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 建档人
     */
    private String createpeople;

    /**
     * 建档日期
     */
    private String createdate;

    /**
     * 最后更新人
     */
    private String updatepeople;

    /**
     * 最后更新日期
     */
    private String updatedate;

    /**
     * 审核人
     */
    private String shpeople;

    /**
     * 审核日期
     */
    private String shdate;

    /**
     * 审核状态
     */
    private Integer shstatus;

    /**
     * 寄件公司编号
     */
    private String mailid;

    /**
     * 寄件公司
     */
    private String mailcompany;

    /**
     * 寄件公司省
     */
    private String mailprovince;

    /**
     * 寄件公司市
     */
    private String mailcity;

    /**
     * 寄件公司区
     */
    private String mailarea;

    /**
     * 寄件公司地址
     */
    private String mailaddress;

    /**
     * 寄件人
     */
    private String mailpeople;

    /**
     * 寄件人联系方式
     */
    private String mailphone;

    /**
     * 客户编号
     */
    private String collectid;

    /**
     * 客户名称
     */
    private String collectdes;

    /**
     * 收件公司
     */
    private String collectcompany;

    /**
     * 收件省
     */
    private String collectprovince;

    /**
     * 收件市
     */
    private String collectcity;

    /**
     * 收件区
     */
    private String collectarea;

    /**
     * 收件地址
     */
    private String collectaddress;

    /**
     * 收件人
     */
    private String collectpeople;

    /**
     * 联络方式
     */
    private String collectphone;


    public ExpressMail() {
    }

    public ExpressMail(Date maildate, String mailorder, String company, Integer mailnum, Double mailweight, Integer contenttype, String content, Integer paymethodtype, String paymethod, Integer freighttype, Double freightprice, Integer ispay, Integer ismonth, String account, Double ensure, Double ensurepoint, String remarks, String createpeople, String createdate, String updatepeople, String updatedate, String shpeople, String shdate, Integer shstatus, String mailid, String mailcompany, String mailprovince, String mailcity, String mailarea, String mailaddress, String mailpeople, String mailphone, String collectid, String collectdes, String collectcompany, String collectprovince, String collectcity, String collectarea, String collectaddress, String collectpeople, String collectphone) {
        this.maildate = maildate;
        this.mailorder = mailorder;
        this.company = company;
        this.mailnum = mailnum;
        this.mailweight = mailweight;
        this.contenttype = contenttype;
        this.content = content;
        this.paymethodtype = paymethodtype;
        this.paymethod = paymethod;
        this.freighttype = freighttype;
        this.freightprice = freightprice;
        this.ispay = ispay;
        this.ismonth = ismonth;
        this.account = account;
        this.ensure = ensure;
        this.ensurepoint = ensurepoint;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.shstatus = shstatus;
        this.mailid = mailid;
        this.mailcompany = mailcompany;
        this.mailprovince = mailprovince;
        this.mailcity = mailcity;
        this.mailarea = mailarea;
        this.mailaddress = mailaddress;
        this.mailpeople = mailpeople;
        this.mailphone = mailphone;
        this.collectid = collectid;
        this.collectdes = collectdes;
        this.collectcompany = collectcompany;
        this.collectprovince = collectprovince;
        this.collectcity = collectcity;
        this.collectarea = collectarea;
        this.collectaddress = collectaddress;
        this.collectpeople = collectpeople;
        this.collectphone = collectphone;
    }



    public ExpressMail(long id,Date maildate, String mailorder, String company, Integer mailnum, Double mailweight, Integer contenttype, String content, Integer paymethodtype, String paymethod, Integer freighttype, Double freightprice, Integer ispay, Integer ismonth, String account, Double ensure, Double ensurepoint, String remarks, String createpeople, String createdate, String updatepeople, String updatedate, String shpeople, String shdate, Integer shstatus, String mailid, String mailcompany, String mailprovince, String mailcity, String mailarea, String mailaddress, String mailpeople, String mailphone, String collectid, String collectdes, String collectcompany, String collectprovince, String collectcity, String collectarea, String collectaddress, String collectpeople, String collectphone) {
        this.id = id;
        this.maildate = maildate;
        this.mailorder = mailorder;
        this.company = company;
        this.mailnum = mailnum;
        this.mailweight = mailweight;
        this.contenttype = contenttype;
        this.content = content;
        this.paymethodtype = paymethodtype;
        this.paymethod = paymethod;
        this.freighttype = freighttype;
        this.freightprice = freightprice;
        this.ispay = ispay;
        this.ismonth = ismonth;
        this.account = account;
        this.ensure = ensure;
        this.ensurepoint = ensurepoint;
        this.remarks = remarks;
        this.createpeople = createpeople;
        this.createdate = createdate;
        this.updatepeople = updatepeople;
        this.updatedate = updatedate;
        this.shpeople = shpeople;
        this.shdate = shdate;
        this.shstatus = shstatus;
        this.mailid = mailid;
        this.mailcompany = mailcompany;
        this.mailprovince = mailprovince;
        this.mailcity = mailcity;
        this.mailarea = mailarea;
        this.mailaddress = mailaddress;
        this.mailpeople = mailpeople;
        this.mailphone = mailphone;
        this.collectid = collectid;
        this.collectdes = collectdes;
        this.collectcompany = collectcompany;
        this.collectprovince = collectprovince;
        this.collectcity = collectcity;
        this.collectarea = collectarea;
        this.collectaddress = collectaddress;
        this.collectpeople = collectpeople;
        this.collectphone = collectphone;
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
     * 获取寄件日期
     *
     * @return maildate - 寄件日期
     */
    public Date getMaildate() {
        return maildate;
    }

    /**
     * 设置寄件日期
     *
     * @param maildate 寄件日期
     */
    public void setMaildate(Date maildate) {
        this.maildate = maildate;
    }

    /**
     * 获取快递单号
     *
     * @return mailorder - 快递单号
     */
    public String getMailorder() {
        return mailorder;
    }

    /**
     * 设置快递单号
     *
     * @param mailorder 快递单号
     */
    public void setMailorder(String mailorder) {
        this.mailorder = mailorder;
    }

    /**
     * 获取快递公司
     *
     * @return company - 快递公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置快递公司
     *
     * @param company 快递公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取数量(件数)
     *
     * @return mailnum - 数量(件数)
     */
    public Integer getMailnum() {
        return mailnum;
    }

    /**
     * 设置数量(件数)
     *
     * @param mailnum 数量(件数)
     */
    public void setMailnum(Integer mailnum) {
        this.mailnum = mailnum;
    }

    /**
     * 获取重量(KG)
     *
     * @return mailweight - 重量(KG)
     */
    public Double getMailweight() {
        return mailweight;
    }

    /**
     * 设置重量(KG)
     *
     * @param mailweight 重量(KG)
     */
    public void setMailweight(Double mailweight) {
        this.mailweight = mailweight;
    }

    /**
     * 获取寄托内容
     *
     * @return contenttype - 寄托内容
     */
    public Integer getContenttype() {
        return contenttype;
    }

    /**
     * 设置寄托内容
     *
     * @param contenttype 寄托内容
     */
    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取支付方式
     *
     * @return paymethodtype - 支付方式
     */
    public Integer getPaymethodtype() {
        return paymethodtype;
    }

    /**
     * 设置支付方式
     *
     * @param paymethodtype 支付方式
     */
    public void setPaymethodtype(Integer paymethodtype) {
        this.paymethodtype = paymethodtype;
    }

    /**
     * 获取支付方法
     *
     * @return paymethod - 支付方法
     */
    public String getPaymethod() {
        return paymethod;
    }

    /**
     * 设置支付方法
     *
     * @param paymethod 支付方法
     */
    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    /**
     * 获取本次运费类型
     *
     * @return freighttype - 本次运费类型
     */
    public Integer getFreighttype() {
        return freighttype;
    }

    /**
     * 设置本次运费类型
     *
     * @param freighttype 本次运费类型
     */
    public void setFreighttype(Integer freighttype) {
        this.freighttype = freighttype;
    }

    /**
     * 获取本次运费
     *
     * @return freightprice - 本次运费
     */
    public Double getFreightprice() {
        return freightprice;
    }

    /**
     * 设置本次运费
     *
     * @param freightprice 本次运费
     */
    public void setFreightprice(Double freightprice) {
        this.freightprice = freightprice;
    }

    /**
     * 获取是否制单时已付
     *
     * @return ispay - 是否制单时已付
     */
    public Integer getIspay() {
        return ispay;
    }

    /**
     * 设置是否制单时已付
     *
     * @param ispay 是否制单时已付
     */
    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    /**
     * 获取是否月付
     *
     * @return ismonth - 是否月付
     */
    public Integer getIsmonth() {
        return ismonth;
    }

    /**
     * 设置是否月付
     *
     * @param ismonth 是否月付
     */
    public void setIsmonth(Integer ismonth) {
        this.ismonth = ismonth;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取保洁费
     *
     * @return ensure - 保洁费
     */
    public Double getEnsure() {
        return ensure;
    }

    /**
     * 设置保洁费
     *
     * @param ensure 保洁费
     */
    public void setEnsure(Double ensure) {
        this.ensure = ensure;
    }

    /**
     * 获取保洁费
     *
     * @return ensurepoint - 保洁费
     */
    public Double getEnsurepoint() {
        return ensurepoint;
    }

    /**
     * 设置保洁费
     *
     * @param ensurepoint 保洁费
     */
    public void setEnsurepoint(Double ensurepoint) {
        this.ensurepoint = ensurepoint;
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
     * @return createpeople - 建档人
     */
    public String getCreatepeople() {
        return createpeople;
    }

    /**
     * 设置建档人
     *
     * @param createpeople 建档人
     */
    public void setCreatepeople(String createpeople) {
        this.createpeople = createpeople;
    }

    /**
     * 获取建档日期
     *
     * @return createdate - 建档日期
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 设置建档日期
     *
     * @param createdate 建档日期
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取最后更新人
     *
     * @return updatepeople - 最后更新人
     */
    public String getUpdatepeople() {
        return updatepeople;
    }

    /**
     * 设置最后更新人
     *
     * @param updatepeople 最后更新人
     */
    public void setUpdatepeople(String updatepeople) {
        this.updatepeople = updatepeople;
    }

    /**
     * 获取最后更新日期
     *
     * @return updatedate - 最后更新日期
     */
    public String getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置最后更新日期
     *
     * @param updatedate 最后更新日期
     */
    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    /**
     * 获取审核人
     *
     * @return shpeople - 审核人
     */
    public String getShpeople() {
        return shpeople;
    }

    /**
     * 设置审核人
     *
     * @param shpeople 审核人
     */
    public void setShpeople(String shpeople) {
        this.shpeople = shpeople;
    }

    /**
     * 获取审核日期
     *
     * @return shdate - 审核日期
     */
    public String getShdate() {
        return shdate;
    }

    /**
     * 设置审核日期
     *
     * @param shdate 审核日期
     */
    public void setShdate(String shdate) {
        this.shdate = shdate;
    }

    /**
     * 获取审核状态
     *
     * @return shstatus - 审核状态
     */
    public Integer getShstatus() {
        return shstatus;
    }

    /**
     * 设置审核状态
     *
     * @param shstatus 审核状态
     */
    public void setShstatus(Integer shstatus) {
        this.shstatus = shstatus;
    }

    /**
     * 获取寄件公司编号
     *
     * @return mailid - 寄件公司编号
     */
    public String getMailid() {
        return mailid;
    }

    /**
     * 设置寄件公司编号
     *
     * @param mailid 寄件公司编号
     */
    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    /**
     * 获取寄件公司
     *
     * @return mailcompany - 寄件公司
     */
    public String getMailcompany() {
        return mailcompany;
    }

    /**
     * 设置寄件公司
     *
     * @param mailcompany 寄件公司
     */
    public void setMailcompany(String mailcompany) {
        this.mailcompany = mailcompany;
    }

    /**
     * 获取寄件公司省
     *
     * @return mailprovince - 寄件公司省
     */
    public String getMailprovince() {
        return mailprovince;
    }

    /**
     * 设置寄件公司省
     *
     * @param mailprovince 寄件公司省
     */
    public void setMailprovince(String mailprovince) {
        this.mailprovince = mailprovince;
    }

    /**
     * 获取寄件公司市
     *
     * @return mailcity - 寄件公司市
     */
    public String getMailcity() {
        return mailcity;
    }

    /**
     * 设置寄件公司市
     *
     * @param mailcity 寄件公司市
     */
    public void setMailcity(String mailcity) {
        this.mailcity = mailcity;
    }

    /**
     * 获取寄件公司区
     *
     * @return mailarea - 寄件公司区
     */
    public String getMailarea() {
        return mailarea;
    }

    /**
     * 设置寄件公司区
     *
     * @param mailarea 寄件公司区
     */
    public void setMailarea(String mailarea) {
        this.mailarea = mailarea;
    }

    /**
     * 获取寄件公司地址
     *
     * @return mailaddress - 寄件公司地址
     */
    public String getMailaddress() {
        return mailaddress;
    }

    /**
     * 设置寄件公司地址
     *
     * @param mailaddress 寄件公司地址
     */
    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    /**
     * 获取寄件人
     *
     * @return mailpeople - 寄件人
     */
    public String getMailpeople() {
        return mailpeople;
    }

    /**
     * 设置寄件人
     *
     * @param mailpeople 寄件人
     */
    public void setMailpeople(String mailpeople) {
        this.mailpeople = mailpeople;
    }

    /**
     * 获取寄件人联系方式
     *
     * @return mailphone - 寄件人联系方式
     */
    public String getMailphone() {
        return mailphone;
    }

    /**
     * 设置寄件人联系方式
     *
     * @param mailphone 寄件人联系方式
     */
    public void setMailphone(String mailphone) {
        this.mailphone = mailphone;
    }

    /**
     * 获取客户编号
     *
     * @return collectid - 客户编号
     */
    public String getCollectid() {
        return collectid;
    }

    /**
     * 设置客户编号
     *
     * @param collectid 客户编号
     */
    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }

    /**
     * 获取客户名称
     *
     * @return collectdes - 客户名称
     */
    public String getCollectdes() {
        return collectdes;
    }

    /**
     * 设置客户名称
     *
     * @param collectdes 客户名称
     */
    public void setCollectdes(String collectdes) {
        this.collectdes = collectdes;
    }

    /**
     * 获取收件公司
     *
     * @return collectcompany - 收件公司
     */
    public String getCollectcompany() {
        return collectcompany;
    }

    /**
     * 设置收件公司
     *
     * @param collectcompany 收件公司
     */
    public void setCollectcompany(String collectcompany) {
        this.collectcompany = collectcompany;
    }

    /**
     * 获取收件省
     *
     * @return collectprovince - 收件省
     */
    public String getCollectprovince() {
        return collectprovince;
    }

    /**
     * 设置收件省
     *
     * @param collectprovince 收件省
     */
    public void setCollectprovince(String collectprovince) {
        this.collectprovince = collectprovince;
    }

    /**
     * 获取收件市
     *
     * @return collectcity - 收件市
     */
    public String getCollectcity() {
        return collectcity;
    }

    /**
     * 设置收件市
     *
     * @param collectcity 收件市
     */
    public void setCollectcity(String collectcity) {
        this.collectcity = collectcity;
    }

    /**
     * 获取收件区
     *
     * @return collectarea - 收件区
     */
    public String getCollectarea() {
        return collectarea;
    }

    /**
     * 设置收件区
     *
     * @param collectarea 收件区
     */
    public void setCollectarea(String collectarea) {
        this.collectarea = collectarea;
    }

    /**
     * 获取收件地址
     *
     * @return collectaddress - 收件地址
     */
    public String getCollectaddress() {
        return collectaddress;
    }

    /**
     * 设置收件地址
     *
     * @param collectaddress 收件地址
     */
    public void setCollectaddress(String collectaddress) {
        this.collectaddress = collectaddress;
    }

    /**
     * 获取收件人
     *
     * @return collectpeople - 收件人
     */
    public String getCollectpeople() {
        return collectpeople;
    }

    /**
     * 设置收件人
     *
     * @param collectpeople 收件人
     */
    public void setCollectpeople(String collectpeople) {
        this.collectpeople = collectpeople;
    }

    /**
     * 获取联络方式
     *
     * @return collectphone - 联络方式
     */
    public String getCollectphone() {
        return collectphone;
    }

    /**
     * 设置联络方式
     *
     * @param collectphone 联络方式
     */
    public void setCollectphone(String collectphone) {
        this.collectphone = collectphone;
    }

    public String getMaildatetime() {
        return maildatetime;
    }

    public void setMaildatetime(String maildatetime) {
        this.maildatetime = maildatetime;
    }
}