package com.risk.plan.service.box.edge;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EdgeMapper;
import com.risk.plan.entity.Edge;

@Service
public class EdgeService extends BaseService<Edge>{
	@Autowired
	EdgeMapper edgeMapper;
	
	public List<String> findFirstNodes(String secondnode){
		
		List<String> firstnodes=edgeMapper.findByNodeid(secondnode);
		if(firstnodes!=null){
			if(firstnodes.size()>0){
				for (String node : firstnodes) {
					List<String> firstnodesO=findFirstNodes(node);
					if(firstnodesO!=null){
						firstnodes.addAll(firstnodesO);
					}
				}
			}
		}
		return firstnodes;
		
	}
	
	public void DeleteByfirstAndsecond(Map<String, Object> params) {
		edgeMapper.DeleteByfirstAndsecond(params);
	}
	public List<Edge> findbyfirstandsecond(String sub) {
		return edgeMapper.findbyfirstandsecond(sub);
	}
	public int updateBySecondid(Edge edge){
		return edgeMapper.updateBySecondid(edge);
	}
}
