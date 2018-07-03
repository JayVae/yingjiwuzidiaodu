package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.TranModel;
@MyBatisRepository
public interface TranModelMapper extends BaseMapper<TranModel>{
    int deleteByPrimaryKey(String tranmodelid);

    int insert(TranModel record);

    int insertSelective(TranModel record);

    TranModel selectByPrimaryKey(String tranmodelid);

    int updateByPrimaryKeySelective(TranModel record);

    int updateByPrimaryKey(TranModel record);
}