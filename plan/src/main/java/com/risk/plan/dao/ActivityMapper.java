package com.risk.plan.dao;

import java.io.Serializable;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Activity;
@MyBatisRepository
public interface ActivityMapper extends BaseMapper<Activity>{
    int deleteByPrimaryKey(String aid);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String aid);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

	int deleteByEmerid(Serializable emerid);
}