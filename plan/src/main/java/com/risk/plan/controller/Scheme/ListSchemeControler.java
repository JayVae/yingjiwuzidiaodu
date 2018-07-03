package com.risk.plan.controller.Scheme;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pole.Constants;
import com.pole.GenAlgo;
import com.pole.InitialParams;
import com.pole.Population;
import com.pole.ProjectSet;
import com.pole.Risker;
import com.pole.SubProject;
import com.risk.plan.entity.Activity;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.EnRelation;
import com.risk.plan.entity.Factor;
import com.risk.plan.entity.RiskInfo;
import com.risk.plan.entity.RiskList;
import com.risk.plan.entity.Scheme;
import com.pole.*;
import com.risk.plan.entity.Strategy;
import com.risk.plan.entity.Sub;
import com.risk.plan.service.box.disaster.EnrelationService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.scheme.ActivityService;
import com.risk.plan.service.box.scheme.SchemeService;
import com.risk.plan.service.sub.FactorService;
import com.risk.plan.service.sub.RiskInfoService;
import com.risk.plan.service.sub.RiskListService;
import com.risk.plan.service.sub.StrategyService;
import com.risk.plan.service.sub.SubService;
import com.risk.plan.util.Identities;
@Controller
public class ListSchemeControler {
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	Sub sub;
	@Autowired
	SubService subService;
	/*@Autowired
	SchemeService schemeServive;
	@Autowired
	 SchemeMapper schemeMapper;*/
	@Autowired
	HttpServletResponse response;
	//httpServlet
	@Autowired
	HttpServletRequest request;
	@Autowired 
	EnRelation enRelation;
	@Autowired
	EnrelationService enrelationService;
	@Autowired
	RiskList riskList;
	@Autowired
	RiskListService riskListService;
	@Autowired
	Scheme scheme;
	@Autowired
	SchemeService schemeService;
	@Autowired
	Strategy strategy;
	@Autowired 
	StrategyService strategyService;
	@Autowired
	RiskInfo riskInfo;
	@Autowired
	RiskInfoService riskInfoService;
	@Autowired
	Factor factor;
	@Autowired
	FactorService factorService;
	@Autowired
	Activity activity;
	@Autowired
	ActivityService activityService;
	@RequestMapping("/goSearchScheme") 
	public String goSearchScheme(ModelMap modelmap){
		List<Emergency> emergencylist=emergencyService.selectAll();
		modelmap.put("emergencylist", emergencylist);
		List<Scheme> schemes = schemeService.selectAll();
		modelmap.put("Pagelist", schemes);
		return "WebRoot/Scheme/ListScheme";
	}
	
	@RequestMapping("/Calculate")
	public String calculate(ModelMap modelMap, String emerid) throws OptionalDataException, ClassNotFoundException, IOException{
		
		List<Scheme> list = schemeService.selectAll();
		for (Scheme schemes : list) {
			schemeService.deleteByPrimaryKey(schemes.getSid());
		}
		activityService.deleteByEmerid(emerid);
		Map<String, Object> params=new HashMap<String, Object>();
		//1.获取子项目数，prjs_Num;
		params.put("emerid", emerid);
		int prjs_Num = subService.findByParamsCount(params);
		System.out.println(prjs_Num);
		//2.获取风险类型数量，risk_Num;
		List<EnRelation> enRelations = enrelationService.findByParams(params);
		int risk_Num = 0;
		List<String> risknames = new ArrayList<String>();
		for (EnRelation enRelation : enRelations) {
			if (!risknames.contains(enRelation.getRisklist().getRiskname())) {
				risknames.add(enRelation.getRisklist().getRiskname());
				risk_Num ++;
			}
		}
		System.out.println(risk_Num);
		//3.获取策略类型数量，stra_Num;
		List<Strategy> strategies = strategyService.selectAll();
		int stra_Num = strategies.size();
		//4.设置迭代次数，lit_Times;
		int lit_Times = 100;
		//5.实例化ProjectSet类，ProjectSet prjset = new ProjectSet(prjs_Num);
		ProjectSet prjset = new ProjectSet(prjs_Num);
		//6.将SubProject组成一个数组（构建的参数从数据库中读出），利用for循环将此数组中的每一个SubProject装配到prjset中
		List<Sub> subs = subService.findByParams(params);
		for (int i = 0;i < subs.size();i ++) {
			Sub sub =  subs.get(i);
			String id = sub.getSubno();
			int humanL = Integer.valueOf(sub.getPersonlimit());
			int moneyL = Integer.valueOf(sub.getFundlimit());
			int materialL = Integer.valueOf(sub.getgoodslimit());
			int initialTimeRisk = 0,initialTechRisk = 0,initialMoneyRisk = 0;
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("subid", sub.getSubid());
			List<RiskList> lists = riskListService.findByParams(params1);
			for (RiskList riskList : lists) {
				if (riskList.getRiskname().equals("进度风险")) {
					initialTimeRisk =Integer.valueOf(riskList.getRiskper());
				}
				if (riskList.getRiskname().equals("技术风险")) {
					initialTechRisk =Integer.valueOf(riskList.getRiskper());
				}
				if (riskList.getRiskname().equals("费用风险")) {
					initialMoneyRisk =Integer.valueOf(riskList.getRiskper());
				}
			}
			SubProject subprj =new SubProject(id, humanL, moneyL, materialL, initialTechRisk, initialTimeRisk, initialMoneyRisk);
			prjset.setSubProject(i, subprj);
		}
		//---调用prjset.setSubProject()方法
		//7.实例化一个Strategy数组，Strategy stra_list = new Strategy[stra_Num];
		com.pole.Strategy[] stra_list = new com.pole.Strategy[stra_Num];
		for (int i = 0; i < stra_Num; i++) {
			Strategy strategy = strategies.get(i);
			int type = 0,humanLim,moneyLim,materialLim;
			humanLim = Integer.valueOf(strategy.getPerson());
			moneyLim = Integer.valueOf(strategy.getFund());
			materialLim = Integer.valueOf(strategy.getGoods());
			if (strategy.getName().equals("风险规避")) {
				type = Constants.EVADE;
			}else if (strategy.getName().equals("风险转移")) {
				type = Constants.TRANSPORT;
			}else if (strategy.getName().equals("风险减轻")) {
				type = Constants.EASE;
			}else if (strategy.getName().equals("风险接受")) {
				type = Constants.ACCEPT;
			}
			stra_list[type] = new com.pole.Strategy(type, humanLim, moneyLim, materialLim);
		}
		//8.同6将每个Strategy对应指定到stra_list中。
		//9.实例化一个Risker数组，Risker risk_list = new Risker[risk_Num];
		Map<String, Object> params2 = new HashMap<String, Object>();
		Risker[] risk_list = new Risker[risk_Num];
		int riskType = 0, humanLim, moneyLim, materialLim;
		for (int i = 0; i < risk_Num; i++) {
			if (risknames.get(i).equals("技术风险")) {
				riskType = Constants.TECHRISK;
				params2.put("name", "技术风险");
			}else if (risknames.get(i).equals("费用风险")) {
				riskType = Constants.MONEYRISK;
				params2.put("name", "费用风险");
			}else if (risknames.get(i).equals("进度风险")) {
				riskType = Constants.TIMERISK;
				params2.put("name", "进度风险");
			}
			List<RiskInfo> infos = riskInfoService.findByParams(params2);
			humanLim = infos.get(0).getPerlimit();
			moneyLim = infos.get(0).getFulimit();
			materialLim = infos.get(0).getGolimit();
			risk_list[i] = new Risker(riskType, humanLim, moneyLim, materialLim);
		}
		
		//10.同6将每个Risker对应指定到risk_list中。
		//11.实例化InitParams类
		InitialParams initparams = new InitialParams(stra_Num);
		Map<String, Object> pa = new HashMap<String, Object>();
		Map<String, Object> pa1 = new HashMap<String, Object>();
		Map<String, Object> pa2 = new HashMap<String, Object>();
		for (int m = 0; m < stra_Num; m++) {
			//根据策略筛选影响因子
			String stra =strategies.get(m).getName();
			int k =0;
			if (stra.equals("风险规避")) {
				pa.put("name", "风险规避");
				k = 0;
			}else if (stra.equals("风险转移")) {
				pa.put("name", "风险转移");
				k = 1;
			}else if (stra.equals("风险减轻")) {
				pa.put("name", "风险减轻");
				k = 2;
			}else if (stra.equals("风险接受")) {
				pa.put("name", "风险接受");
				k = 3;
			}
			List<Strategy> stras = strategyService.findByParams(pa);
			pa2.put("id", stras.get(0).getId());
			List<Sub> subs2 = subService.selectAll();
			for (int i = 0; i < subs2.size(); i++) {
				String subname = subs2.get(i).getSubname();
				if (subname.equals("项目11")) {
					pa1.put("subname", "项目11");
				}else if (subname.equals("项目12")) {
					pa1.put("subname", "项目12");
				}else if (subname.equals("项目13")) {
					pa1.put("subname", "项目13");
				}else if (subname.equals("项目14")) {
					pa1.put("subname", "项目14");
				}
				List<Sub> subp = subService.findByParams(pa1);
				String subid = subp.get(0).getSubid();
				pa1.put("subid", subid);
				List<EnRelation> enRes = enrelationService.findByParams(pa1);
				for (int j = 0; j < risk_Num; j++) {
					System.out.println(enRes.size());
					String enreid = enRes.get(j).getEnrelationid();
					pa2.put("enrelationid", enreid);
					List<Factor> fa = factorService.findByParams(pa2);
					Factor fac = fa.get(0);
					
					initparams.setFactorA(j, i, k,(int) (Double.valueOf(fac.getFactora())*100));
					initparams.setFactorB(j, i, k,(int) (Double.valueOf(fac.getFactorb())*100));
					initparams.setFactorC(j, i, k,(int) (Double.valueOf(fac.getFactorc())*100));
				}
			}
		}
		//12.调用initparams.setFactorX(i, j, k, value)
		//---i：子项目编号，j:风险编号，k:风险应对策略编号,value:对应的值；X:可取A\B\C
		//开始调用算法
		//Use default values;
		/*initparams.setDefault();*/
		GenAlgo myAlgo = new GenAlgo();//step 2
		
		Population rePopu = myAlgo.InitAndRun(prjset, prjs_Num, risk_list, risk_Num, stra_list, stra_Num, lit_Times, initparams);//step 3
		WBS_Set wbsSet =  rePopu.getWbsSet();
		int sum = wbsSet.getWBS_Set_Size();
		
		for (int i = 0; i < sum; i++) {
			WBS wbs = wbsSet.getWBS(i);
			Activity activity = new Activity();
			int strat = wbs.getStrategy();
			Map<String, Object> param = new HashMap<String, Object>();
			if (strat == 0) {
				param.put("name", "风险规避");
			}else if (strat == 1) {
				param.put("name", "风险转移");
			}else if (strat == 2) {
				param.put("name", "风险减轻");
			}else if (strat == 3) {
				param.put("name", "风险接受");
			}
			List<Strategy> str = strategyService.findByParams(param);
			activity.setStrategy(str.get(0).getId());
			activity.setAid(Identities.uuid2());
			int sub = wbs.getDestSubProject();
			if (sub == 0) {
				param.put("subname", subs.get(0).getSubname());
			}else if (sub == 1) {
				param.put("subname", subs.get(1).getSubname());
			}else if (sub == 2) {
				param.put("subname", subs.get(2).getSubname());
			}else if (sub == 3) {
				param.put("subname", subs.get(3).getSubname());
			}
			List<Sub> sub1 = subService.findByParams(param);
			activity.setSubname(sub1.get(0).getSubid());
			activity.setEmerid(emerid);
			Risker risker = wbs.getRisker();
			param.put("subid",sub1.get(0).getSubid());
			int risk = risker.getRiskType();
			if (risk == 0) {
				param.put("riskname", "技术风险");
			}else if (risk == 1) {
				param.put("riskname", "费用风险");
			}else if (risk == 2) {
				param.put("riskname", "进度风险");
			}
			List<RiskList> riskLists = riskListService.findByParams(param);
			activity.setRisktype(riskLists.get(0).getRiskid());
			activity.setPerson(String.valueOf(risker.getHumanLoaded()));
			activity.setMoney(String.valueOf(risker.getMoneyLoaded()));
			activity.setGoods(String.valueOf(risker.getMaterialLoaded()));
			activity.setDegree(String.valueOf(wbs.getDelta()));
			activityService.insertSelective(activity);
			
			}
		/*int[][] s = {{0, 0, 0},{0, 0, 0}, {0, 0, 0},{0, 0, 0}};
		for (int i = 0; i < prjs_Num; i++) {
			for (int j = 0; j < risk_Num; j++) {
				s[i][j] = prjset.getSubProject(i).getInitialRisk()[j];
			}
		}*/
		List<Scheme> schemeList = new ArrayList<Scheme>();
		for (int i = 0;i < prjs_Num;i ++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> map1 = new HashMap<String, Object>();
			if (i == 0) {
				map.put("subname", "项目11");
			}else if (i == 1) {
				map.put("subname", "项目12");
			}else if (i == 2) {
				map.put("subname", "项目13");
			}else if (i == 3) {
				map.put("subname", "项目14");
			}
			List<Sub> sub = subService.findByParams(map);
			map.put("subid", sub.get(0).getSubid());
			for (int j = 0; j < risk_Num; j++) {
				if (j ==0) {
					map.put("riskname","技术风险");
				}else if (j ==1) {
					map.put("riskname","费用风险");
				}else if (j ==2) {
					map.put("riskname","进度风险");
				}
				List<RiskList> lists = riskListService.findByParams(map);
				map1.put("subname", sub.get(0).getSubid());
				map1.put("risktype", lists.get(0).getRiskid());
				List<Activity> act = activityService.findByParams(map1);
				Scheme scheme = new Scheme();
				scheme.setSname(sub.get(0).getSubname());
				scheme.setRname(lists.get(0).getRiskname());
				double rlimit = Double.valueOf(lists.get(0).getRiskper());
				int person = 0,money = 0,goods = 0;
				for (Activity acti : act) {
					person += Integer.valueOf(acti.getPerson());
					money += Integer.valueOf(acti.getMoney());
					goods += Integer.valueOf(acti.getGoods());
					rlimit -= Double.valueOf(acti.getDegree());
				}
				scheme.setPercent(String.valueOf(rlimit/100.0));
				scheme.setPersum(person);
				scheme.setFusum(money);
				scheme.setGosum(goods);
				scheme.setSid(Identities.uuid2());
				schemeList.add(scheme);
				schemeService.insertSelective(scheme);
			}
			
		}
		List<Emergency> emergencylist=emergencyService.selectAll();
		modelMap.put("emergencylist", emergencylist);	
		modelMap.put("Pagelist", schemeList);
		return "WebRoot/Scheme/ListScheme";
		}	


	
}
