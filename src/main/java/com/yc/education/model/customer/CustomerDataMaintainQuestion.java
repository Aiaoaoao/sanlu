package com.yc.education.model.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_data_maintain_question")
public class CustomerDataMaintainQuestion {
    /**
     * 客户资料维护-现有问题及意见
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客户资料维护id
     */
    @Column(name = "maintain_id")
    private Long maintainId;

    /**
     * 问题类型（1现有问题、2其他问题）
     */
    private Integer type;

    /**
     * 正文
     */
    private String content;

    /**
     * 系统添加时时间
     */
    private Date addtime;

    /**
     * 获取客户资料维护-现有问题及意见
     *
     * @return id - 客户资料维护-现有问题及意见
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置客户资料维护-现有问题及意见
     *
     * @param id 客户资料维护-现有问题及意见
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户资料维护id
     *
     * @return maintain_id - 客户资料维护id
     */
    public Long getMaintainId() {
        return maintainId;
    }

    /**
     * 设置客户资料维护id
     *
     * @param maintainId 客户资料维护id
     */
    public void setMaintainId(Long maintainId) {
        this.maintainId = maintainId;
    }

    /**
     * 获取问题类型（1现有问题、2其他问题）
     *
     * @return type - 问题类型（1现有问题、2其他问题）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置问题类型（1现有问题、2其他问题）
     *
     * @param type 问题类型（1现有问题、2其他问题）
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取系统添加时时间
     *
     * @return addtime - 系统添加时时间
     */
    public Date getAddtime() {
        return addtime;
    }

    /**
     * 设置系统添加时时间
     *
     * @param addtime 系统添加时时间
     */
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}