package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Factor;
@MyBatisRepository
public interface FactorMapper extends BaseMapper<Factor>{
    int deleteByPrimaryKey(String factorid);

    int insert(Factor record);

    int insertSelective(Factor record);

    Factor selectByPrimaryKey(String factorid);

    int updateByPrimaryKeySelective(Factor record);

    int updateByPrimaryKey(Factor record);
}