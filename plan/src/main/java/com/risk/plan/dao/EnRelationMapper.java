package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.EnRelation;
@MyBatisRepository
public interface EnRelationMapper extends BaseMapper<EnRelation>{
    int deleteByPrimaryKey(String enrelationid);

    int insert(EnRelation record);

    int insertSelective(EnRelation record);

    EnRelation selectByPrimaryKey(String enrelationid);

    int updateByPrimaryKeySelective(EnRelation record);

    int updateByPrimaryKey(EnRelation record);
    
    int deleteByRiskid(String riskid);
}