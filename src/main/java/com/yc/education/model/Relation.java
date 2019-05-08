package com.yc.education.model;

import javax.persistence.*;

/**
 *@Description TODO  单据之间的关联关系建立
 ****************【注意:使用必看规则】***********************************************************************
 **************关联单据 : 导入单据***************************************************************************
 **************被关联单据 : 被导入单据***********************************************************************
 ******************例如:  询价单数据导入到采购订单中    采购订单为关联单据 ,询价单为被关联单据***************
 ************************************************************************************************************
 *@Author QuZhangJing
 *@Date 21:48  2019/4/25
 *@Version 1.0
 */
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Description 临时存放字段
     * @Author BlueSky
     * @Date 23:01 2019/4/28
     **/
    @Transient
    private String tempStr;

    /**
     * 关联单据名称
     */
    @Column(name = "relation_name")
    private String relationName;

    /**
     * 关联单据编号
     */
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 被关联单据名称
     */
    @Column(name = "be_relation_name")
    private String beRelationName;

    /**
     * 被关联单据编号
     */
    @Column(name = "be_relation_id")
    private Long beRelationId;

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
     * 获取关联单据名称
     *
     * @return relation_name - 关联单据名称
     */
    public String getRelationName() {
        return relationName;
    }

    /**
     * 设置关联单据名称
     *
     * @param relationName 关联单据名称
     */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    /**
     * 获取关联单据编号
     *
     * @return relation_id - 关联单据编号
     */
    public Long getRelationId() {
        return relationId;
    }

    /**
     * 设置关联单据编号
     *
     * @param relationId 关联单据编号
     */
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    /**
     * 获取被关联单据名称
     *
     * @return be_relation_name - 被关联单据名称
     */
    public String getBeRelationName() {
        return beRelationName;
    }

    /**
     * 设置被关联单据名称
     *
     * @param beRelationName 被关联单据名称
     */
    public void setBeRelationName(String beRelationName) {
        this.beRelationName = beRelationName;
    }

    /**
     * 获取被关联单据编号
     *
     * @return be_relation_id - 被关联单据编号
     */
    public Long getBeRelationId() {
        return beRelationId;
    }

    /**
     * 设置被关联单据编号
     *
     * @param beRelationId 被关联单据编号
     */
    public void setBeRelationId(Long beRelationId) {
        this.beRelationId = beRelationId;
    }

    public String getTempStr() {
        return tempStr;
    }

    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }
}