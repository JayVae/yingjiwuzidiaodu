package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.RiskList;
@MyBatisRepository
public interface RiskListMapper extends BaseMapper<RiskList>{
    int deleteByPrimaryKey(String riskid);

    int insert(RiskList record);

    int insertSelective(RiskList record);

    RiskList selectByPrimaryKey(String riskid);

    int updateByPrimaryKeySelective(RiskList record);

    int updateByPrimaryKey(RiskList record);
}