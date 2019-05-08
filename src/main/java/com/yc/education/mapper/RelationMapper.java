package com.yc.education.mapper;

import com.yc.education.model.Relation;
import com.yc.education.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *@Description TODO 单据关联
 *@Author QuZhangJing
 *@Date 21:54  2019/4/25
 *@Version 1.0
 */
public interface RelationMapper extends MyMapper<Relation> {


    /**
     * 根据被关联单据和单号查询该单据是否存在关联关系
     * @param beRelationName
     * @param beRelationId
     * @return
     */
    Boolean isRelation(@Param("beRelationName")String beRelationName,@Param("beRelationId")long beRelationId);

    /**
     *  根据关联单据和关联单号删除
     * @param relationName
     * @param relationId
     * @return
     */
    int deleteRelation(@Param("relationName")String relationName,@Param("relationId")long relationId);

    /**
     * 查询该单据关联的单据
     * @param beRelationName
     * @param beRelationId
     * @return
     */
    List<Relation> selectRelations(@Param("beRelationName")String beRelationName,@Param("beRelationId")long beRelationId);

}