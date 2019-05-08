package com.yc.education.service;

import com.yc.education.model.Relation;

import java.util.List;

/**
 * @ClassName RelationService
 * @Description TODO 单据关联
 * @Author QuZhangJing
 * @Date 2019/4/25 22:08
 * @Version 1.0
 */
public interface RelationService extends IService<Relation> {


    /**
     * 根据被关联单据和单号查询该单据是否存在关联关系
     * @param beRelationName
     * @param beRelationId
     * @return
     */
    Boolean isRelation(String beRelationName,long beRelationId);

    /**
     *  根据关联单据和关联单号删除
     * @param relationName
     * @param relationId
     * @return
     */
    int deleteRelation(String relationName,long relationId);

    /**
     * 查询该单据关联的单据
     * @param beRelationName
     * @param beRelationId
     * @return
     */
    List<Relation> selectRelations(String beRelationName,long beRelationId);

}
