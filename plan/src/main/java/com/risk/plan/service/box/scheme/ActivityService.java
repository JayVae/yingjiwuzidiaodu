package com.risk.plan.service.box.scheme;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.ActivityMapper;
import com.risk.plan.entity.Activity;

@Service
public class ActivityService extends BaseService<Activity>{
	@Autowired
	ActivityMapper activityMapper;
	public int deleteByEmerid(Serializable emerid) {
		return activityMapper.deleteByEmerid(emerid);
	}
}
