package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.RiskInfo;
@MyBatisRepository
public interface RiskInfoMapper extends BaseMapper<RiskInfo>{
    int deleteByPrimaryKey(String infoid);

    int insert(RiskInfo record);

    int insertSelective(RiskInfo record);

    RiskInfo selectByPrimaryKey(String infoid);

    int updateByPrimaryKeySelective(RiskInfo record);

    int updateByPrimaryKey(RiskInfo record);
}