package com.yc.education.service.impl;

import com.yc.education.mapper.RelationMapper;
import com.yc.education.model.Relation;
import com.yc.education.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RelationServiceImpl
 * @Description TODO 单据关联
 * @Author QuZhangJing
 * @Date 2019/4/25 22:08
 * @Version 1.0
 */
@Service("RelationService")
public class RelationServiceImpl extends BaseService<Relation> implements RelationService {

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public Boolean isRelation(String beRelationName, long beRelationId) {
        try {
            return relationMapper.isRelation(beRelationName, beRelationId);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int deleteRelation(String relationName, long relationId) {
        try {
            return relationMapper.deleteRelation(relationName, relationId);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Relation> selectRelations(String beRelationName, long beRelationId) {
        try {
            return relationMapper.selectRelations(beRelationName, beRelationId);
        } catch (Exception e) {
            return null;
        }
    }

}
