package com.risk.plan.service.box.disaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EnRelationMapper;
import com.risk.plan.entity.EnRelation;
@Service	
public class EnrelationService extends BaseService<EnRelation> {
	@Autowired
	EnRelationMapper enRelationMapper;
	public int deleteByRiskid(String riskid) {
		return enRelationMapper.deleteByRiskid(riskid);
	}
}
