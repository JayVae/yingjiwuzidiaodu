package com.risk.plan.controller.Scheme;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risk.plan.entity.Activity;
import com.risk.plan.service.box.scheme.ActivityService;
import com.risk.plan.service.sub.RiskListService;
import com.risk.plan.service.sub.StrategyService;
import com.risk.plan.service.sub.SubService;
@Controller
public class ActivityControler {
	@Autowired
	Activity activity;
	@Autowired
	ActivityService activityService;
	@Autowired
	SubService subService;
	@Autowired
	StrategyService strategyService;
	@Autowired
	RiskListService riskListService;
		@RequestMapping("/gotoListActivity") 
		public String gotoListActivity(ModelMap modelmap){
			List<Activity> ac1 = new ArrayList<Activity>();
			List<Activity> activitylist=activityService.selectAll();
			
			for (Activity activity : activitylist) {
				String subname = subService.selectByPrimaryKey(activity.getSubname()).getSubname();
				String strategyname= strategyService.selectByPrimaryKey(activity.getStrategy()).getName();
				String risktype = riskListService.selectByPrimaryKey(activity.getRisktype()).getRiskname();
				activity.setRisktype(risktype);
				activity.setStrategy(strategyname);
				activity.setSubname(subname);
				String aaa = activity.getDegree();
				double bbb = (Integer.valueOf(aaa))/100.0;
				activity.setDegree(String.valueOf(bbb));
				ac1.add(activity);
			}
			modelmap.put("activitylist", ac1);
			System.out.println("test======");
			return "WebRoot/Scheme/ListActivity";
		}
	
}
