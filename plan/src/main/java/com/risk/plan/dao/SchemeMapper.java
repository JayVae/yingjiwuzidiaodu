package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Scheme;
@MyBatisRepository
public interface SchemeMapper extends BaseMapper<Scheme>{
    int deleteByPrimaryKey(String sid);

    int insert(Scheme record);

    int insertSelective(Scheme record);

    Scheme selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(Scheme record);

    int updateByPrimaryKey(Scheme record);
}