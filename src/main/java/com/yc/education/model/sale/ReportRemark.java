package com.yc.education.model.sale;

import java.util.Date;
import javax.persistence.*;

@Table(name = "report_remark")
public class ReportRemark {
    /**
     * 报表备注
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 外键id
     */
    private Long otherid;

    /**
     * 类型（1、报价单，2、订货单，3、销货单）
     */
    private Integer typeid;

    /**
     * 正文
     */
    private String content;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 获取报表备注
     *
     * @return id - 报表备注
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置报表备注
     *
     * @param id 报表备注
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getOtherid() {
        return otherid;
    }

    public void setOtherid(Long otherid) {
        this.otherid = otherid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取正文
     *
     * @return content - 正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置正文
     *
     * @param content 正文
     */
    public void setContent(String content) {
        this.content = content;
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

    public ReportRemark() {
    }

    public ReportRemark(String content) {
        this.content = content;
    }

    public ReportRemark(Long otherid, Integer typeid, String content, Date addtime) {
        this.otherid = otherid;
        this.typeid = typeid;
        this.content = content;
        this.addtime = addtime;
    }
}