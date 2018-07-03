package com.risk.plan.controller.subpro;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.EnRelation;
import com.risk.plan.entity.Factor;
import com.risk.plan.entity.FactorEx;
import com.risk.plan.entity.GoodsType;
import com.risk.plan.entity.RiskInfo;
import com.risk.plan.entity.RiskList;
import com.risk.plan.entity.Strategy;
import com.risk.plan.entity.Sub;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.disaster.EnrelationService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.goods.GoodsTypeService;
import com.risk.plan.service.sub.FactorService;
import com.risk.plan.service.sub.RiskInfoService;
import com.risk.plan.service.sub.RiskListService;
import com.risk.plan.service.sub.StrategyService;
import com.risk.plan.service.sub.SubService;
import com.risk.plan.util.Identities;


@Controller

public class SubController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired 
     SubService subService;
    @Autowired
     EmergencyService emergencyService;
    @Autowired
     Emergency emergency;
    @Autowired
     Sub sub;
    @Autowired
     GoodsTypeService goodsTypeService;
    @Autowired
     GoodsType goodsType;
    @Autowired
    RiskListService risklistservice;
    @Autowired
    RiskList risklist;
    @Autowired
    EnRelation enRelation;
    @Autowired
    EnrelationService enrelationService;
    @Autowired
    RiskInfo riskInfo;
    @Autowired
    RiskInfoService riskInfoService;
    @Autowired
    StrategyService strategyService;
    @Autowired
    Strategy strategy;
    @Autowired
    Factor factor;
    @Autowired
    FactorService factorService;
    String id;
    @RequestMapping("/budget")
        public String budget(ModelMap modelMap,String page){
        StringBuffer url=new StringBuffer();
 		//把action跳转的地址存在url中
 		url.append(request.getContextPath()+"/budget?method=budget");
 		int current = 1;
 		if (page != null && !page.trim().equals("")) {
 			current = Integer.parseInt(page);
 		}
 		try {
 			int first = 0;
 			int pageSize=Integer.parseInt(String.valueOf(PageBean.pagecount));
 			if(current != 0){
 				first = (current-1)*pageSize;
 			}
 			int startNum = first;
 			Map<String, Object> params = new HashMap<String, Object>();
 	        params.put("pageSize", pageSize);
 		    params.put("startNum", startNum);
 		    
	 		int num = subService.findByParamsCount(params);
	 		params.put("sortName", "subno");
	     	List<Sub> enRelationlist = subService.findByParams(params);
	 		PageBean PageBean = new PageBean(current,pageSize,num,enRelationlist); 
	 		PageBean.setUrl(url.toString());
	 		modelMap.put("pageBean", PageBean);
	 		for(int i = 0;i<PageBean.getList().size();i++){
	 		  System.out.println(PageBean.getList().get(i));
	 		  }
	 		modelMap.put("Pagelist", PageBean.getList());
	 		Users user=(Users) request.getSession().getAttribute("user");
	 		modelMap.put("user", user);
	 		return "WebRoot/DisasterNode/listbudget";
     	}catch(Exception e){
     		e.printStackTrace();
 			return "error/404";
     	}
	    	
	    }
    @RequestMapping("/gotoAddSub")
    	public String gotoAddsub(ModelMap modelMap){
          List<Emergency> emergemcylist=emergencyService.selectAll();
          modelMap.put("emergencylist", emergemcylist);
    	return "WebRoot/DisasterNode/addDisasterNode";
        }
    @RequestMapping("/saveSub")
        public String savesub(Sub sub, RiskList riskList,
        	  String[] riskname, String[] riskper, String emerid, ModelMap modelMap){
    	
       /* if(goods.length>0&&goods!=null){
        for(int i=0;i<goods.length;i++){
        	goodsType.setGoodsname(goods[i]);
        	goodsType.setGoodslimit(num[i]);
        	goodsType.setSubid(sub.getSubid());
        	goodsType.setGoodstypeid(Identities.uuid2());
            goodsTypeService.insert(goodsType);
        }
      }*/
	    	sub.setSubid(Identities.uuid2());
		    sub.setEmerid(emerid);
		    subService.insert(sub);
        if(riskname!=null && riskname.length>0 ){
		    
	        int indexTmp = 0;
	        for(int i=0; i<riskper.length; i++){
	        	//Spring 做了多余的事
	        	
	        	if(!"".equals(riskper[i])){
	        		riskList.setRiskname(riskname[indexTmp]);
	        		riskList.setRiskper(riskper[i]+"00");
	        		riskList.setSubid(sub.getSubid());
	            	riskList.setRiskid(Identities.uuid2());
	                risklistservice.insert(riskList);
	                indexTmp++;
	                enRelation.setEmerid(emerid);
	                enRelation.setSubid(sub.getSubid());
	                enRelation.setRiskid(riskList.getRiskid());
	                enRelation.setEnrelationid(Identities.uuid2());
	                enrelationService.insert(enRelation);
	               
	        	}
	        	
	        }
      }
        else {
			modelMap.put("msg", "请输入风险类型!");
			return "forward:gotoAddSub";
		}
        return "WebRoot/DisasterNode/addSuccess";
    }
    @RequestMapping("/gotoSearchSub")
    public String gotoSearchsub(ModelMap modelMap,String page,String emerid,String subid,String riskid){
        List<RiskList> risks=new ArrayList<RiskList>();
        List<String> risknames=new ArrayList<String>();
        StringBuffer url=new StringBuffer();
       /* StringBuffer url=new StringBuffer();*/
		//把action跳转的地址存在url中
		url.append(request.getContextPath()+"/gotoSearchSub?method=gotoSearchSub");
        /*for(Sub Sub:list2){
            subnames.add(Sub.getSubname());
          }*/
		int current=1;
		if (page != null && !page.trim().equals("")) {
			current=Integer.parseInt(page);
		}
		try {
			int first=0;
			int pageSize=Integer.parseInt(String.valueOf(PageBean.pagecount));
			if(current!=0){
				first=(current-1)*pageSize;
			}
			int startNum = first;
			Map<String, Object> params=new HashMap<String, Object>();
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
		List<Emergency> emergemcylist=emergencyService.selectAll();
		if(!"".equals(emerid)&& emerid!=null){
			params.put("emerid","%"+ emerid+"%");
			List<Sub> subp=subService.findByParams(params);
			modelMap.put("sublist", subp);
			}
		else {
			List<Sub> subs=subService.selectAll();
			modelMap.put("sublist", subs);
		}
		
		if(!"".equals(subid)&&subid !=null){
			params.put("subid", "%"+subid+"%");
		    List<RiskList> risks1=risklistservice.findByParams(params);
		    modelMap.put("risk", risks1);
		}
		else {
			List<RiskList> list3=risklistservice.selectAll();
			for(RiskList riskList:list3){
	            if(!risknames.contains(riskList.getRiskname())){
	            	risknames.add(riskList.getRiskname());
	            	risks.add(riskList);
	            	modelMap.put("risk", risks);
	            }
		    }
        }
		if(!"".equals(riskid)&& riskid !=null){
			 params.put("riskid", "%"+riskid+"%");
		}
       
        modelMap.put("emergency", emergemcylist);
        int num=enrelationService.findByParamsCount(params);
        params.put("sortName", "subno");
    	List<EnRelation> enRelationlist=enrelationService.findByParams(params);
    	for (EnRelation enRelation : enRelationlist) {
    		int aaa = Integer.valueOf(enRelation.getRisklist().getRiskper());
    		int bbb = aaa/100;
			enRelation.getRisklist().setRiskper(String.valueOf(bbb));
		}
		PageBean PageBean=new PageBean(current,pageSize,num,enRelationlist); 
		PageBean.setUrl(url.toString());
		modelMap.put("pageBean", PageBean);
		for(int i=0;i<PageBean.getList().size();i++){
		  System.out.println(PageBean.getList().get(i));
		  }
		modelMap.put("Pagelist", PageBean.getList());
		Users user=(Users) request.getSession().getAttribute("user");
		modelMap.put("user", user);
    	return "WebRoot/DisasterNode/listDisasterNode";
    	}catch(Exception e){
    		e.printStackTrace();
			return "error/404";
    	}
    }
    @RequestMapping("/SearchRisklist")
    public String searchRisklist(String emerid,String subid,String riskid,ModelMap modelMap,String page){
    	Map<String, Object> params=new HashMap<String, Object>();
    	StringBuffer url=new StringBuffer();
    	/*List<Emergency> emer=new ArrayList<Emergency>();
    	List<Sub> Sub=new ArrayList<Sub>();*/
    	List<RiskList> risks=new ArrayList<RiskList>();
    	List<String> risknames=new ArrayList<String>();
    	List<Emergency> emer=emergencyService.selectAll();
    	List<Sub> sub=subService.selectAll();
    	List<RiskList> list3=risklistservice.selectAll();
		for(RiskList riskList:list3){
            if(!risknames.contains(riskList.getRiskname())){
            	risknames.add(riskList.getRiskname());
            	risks.add(riskList);
            	modelMap.put("risk", risks);
            }
		}
		modelMap.put("emergency", emer);
		modelMap.put("sublist", sub);
    	url.append(request.getContextPath()+"/SearchRisklist?method=SearchRisklist");
    	int current=1;
		if (page != null && !page.trim().equals("")) {
			current=Integer.parseInt(page);
		}
		try{
			int first=0;
			int pageSize=Integer.parseInt(String.valueOf(PageBean.pagecount));
			if(current!=0){
				first=(current-1)*pageSize;
			}
			int startNum = first;
			/*params.put("sortName", "emerid");*/
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
    	if(riskid!= null&&!"".equals(riskid)){
    		params.put("riskid", riskid);
    		url.append("&riskid="+riskid);
    		/*RiskList risklist=risklistservice.selectByPrimaryKey(riskid);
    		risk.add(risklist);
    		modelMap.put("risk", risk);*/
    	} if(subid!= null&&!"".equals(subid)){
    		params.put("subid", subid);
    		url.append("&subid="+subid);
    		System.out.println("subid="+subid);
    		/*Sub Sub=SubService.selectByPrimaryKey(subid);
    		Sub.add(Sub);
    		modelMap.put("sublist", Sub);*/
    	} if(emerid != null && !"".equals(emerid)){        
			  params.put("emerid", emerid);
		      url.append("&emerid="+emerid);  //翻页的时候用的，拼接url！
		      /*Emergency emergency=emergencyService.selectByPrimaryKey(emerid);
		      emer.add(emergency);
		      modelMap.put("emergency", emer);*/
    	}

    	int num=enrelationService.findByParamsCount(params);
    	List<EnRelation> enRelationlist=enrelationService.findByParams(params);
		PageBean PageBean=new PageBean(current,pageSize,num,enRelationlist); 
		PageBean.setUrl(url.toString());
		modelMap.put("pageBean", PageBean);
		for(int i=0;i<PageBean.getList().size();i++){
		  System.out.println(PageBean.getList().get(i));
		  }
		modelMap.put("Pagelist", PageBean.getList());
    	return "WebRoot/DisasterNode/listDisasterNode";
    	}catch(Exception e){
    		e.printStackTrace();
			return "error/404";
    	}
    }
    @RequestMapping("/gotoInfo")
    public String searchInfo(ModelMap modelMap){
    	List<RiskInfo> infoList = riskInfoService.selectAll();
    	modelMap.put("pageList", infoList);
    	return "WebRoot/DisasterNode/RInformation"; 
    }
    @RequestMapping("/editRiskInfo")
    public String editRiskInfo(String infoid,ModelMap modelMap){
    	RiskInfo info = riskInfoService.selectByPrimaryKey(infoid);
    	modelMap.put("riskInfo", info);
    	return "WebRoot/DisasterNode/editInfo";
    }
    @RequestMapping("updateRiskInfo")
    public String updateRiskInfo(RiskInfo riskInfo){
    	riskInfoService.updateByPrimaryKeySelective(riskInfo);
    	return "forward:gotoInfo";
    }
    @RequestMapping("/SaveInfo")
    public String saveInfo(RiskInfo riskInfo){
    	riskInfo.setInfoid(Identities.uuid2());
    	riskInfoService.insertSelective(riskInfo);
    	return "forward:gotoInfo";
    }
    @RequestMapping("/strategy")
    public String strategy(ModelMap modelMap){
    	List<Strategy> strategies = strategyService.selectAll();
    	modelMap.put("strategies", strategies);
    	return "WebRoot/DisasterNode/strategy";
    }
    @RequestMapping("/saveStrategy")
    public String saveStrategy(Strategy strategy){
    	strategy.setId(Identities.uuid2());
    	strategyService.insertSelective(strategy);
    	return "forward:strategy";
    }
    @RequestMapping("/resource")
    public String resource(ModelMap modelMap){
    	List<Strategy> strategies = strategyService.selectAll();
    	modelMap.put("strategies", strategies);
    	int sum1 = 0,sum2 = 0,sum3 = 0;
    	for (Strategy strategy : strategies) {
			sum1 += Integer.valueOf(strategy.getPerson());
			sum2 += Integer.valueOf(strategy.getFund());
			sum3 += Integer.valueOf(strategy.getGoods());
		}
    	modelMap.put("person", sum1);
    	modelMap.put("fund", sum2);
    	modelMap.put("goods", sum3);
    	return "WebRoot/DisasterNode/resource";
    }
    @RequestMapping("/editResource")
    public String editResource(String id,ModelMap modelMap){
    	Strategy strategy = strategyService.selectByPrimaryKey(id);
    	modelMap.put("strategy", strategy);
    	return "WebRoot/DisasterNode/editResource";
    }
    @RequestMapping("updateResource")
    public String updateResource(Strategy strategy){
    	strategyService.updateByPrimaryKeySelective(strategy);
    	return "forward:resource";
    }
    @RequestMapping("deleteResource")
    public String deleteResource(String id){
    	strategyService.deleteByPrimaryKey(id);
    	return "forward:resource";
    }
    @RequestMapping("/gotoFactor")
    public String gotoFactor(ModelMap modelMap){
    	List<Strategy> strategies = strategyService.selectAll();
    	List<Emergency> emergencies = emergencyService.selectAll();
    	modelMap.put("strategy", strategies);
    	modelMap.put("emergency", emergencies);
    	return "WebRoot/DisasterNode/factorSet";
    }
    @RequestMapping("/factorSet")
    public String factorSet(ModelMap modelMap,String subid,String id){
    	this.id=id;
    	List<Strategy> strategies = strategyService.selectAll();
    	List<Emergency> emergencies = emergencyService.selectAll();
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("subid", subid);
    	List<RiskList> riskLists = risklistservice.findByParams(params);
    	modelMap.put("strategy", strategies);
    	modelMap.put("emergency", emergencies);
    	modelMap.put("risk", riskLists);
    	return "WebRoot/DisasterNode/factorSet";
    }
    @RequestMapping("/saveFactor")
    public String saveFactor(String[] riskid,String[] factorA,String[] factorB,String[] factorC){
    	for (int i = 0; i < riskid.length; i++) {
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("riskid", riskid[i]);
			List<EnRelation> enRelation = enrelationService.findByParams(params);
			Factor factor = new Factor();
			factor.setFactorid(Identities.uuid2());
			factor.setId(id);
			factor.setFactora(factorA[i]);
			factor.setFactorb(factorB[i]);
			factor.setFactorc(factorC[i]);
			factor.setEnrelationid(enRelation.get(0).getEnrelationid());
			factorService.insertSelective(factor);
			
		}
    	return "forward:gotoFactor";
    }
    @RequestMapping("listFactor")
    public String listFactor(ModelMap modelMap){
    	List<Factor> factors = factorService.selectAll();
    	List<FactorEx> list = new ArrayList<FactorEx>();
    	for (Factor factor : factors) {
    		Factor fac = new Factor();
    		FactorEx factorEx = new FactorEx();
			EnRelation enRelation = enrelationService.selectByPrimaryKey(factor.getEnrelationid());
			Strategy strategy = strategyService.selectByPrimaryKey(factor.getId());
			Sub sub = subService.selectByPrimaryKey(enRelation.getSubid());
			RiskList riskList = risklistservice.selectByPrimaryKey(enRelation.getRiskid());
			fac.setFactorid(factor.getFactorid());
			fac.setFactora(factor.getFactora());
			fac.setFactorb(factor.getFactorb());
			fac.setFactorc(factor.getFactorc());
			fac.setId(strategy.getName());
			fac.setEnrelationid(sub.getSubname());
			factorEx.setRiskList(riskList);
			factorEx.setFactor(fac);
			list.add(factorEx);
		}
    	modelMap.put("Pagelist", list);
    	return "WebRoot/DisasterNode/listFactor";
    }
    @RequestMapping("/editFactor")
    public String editFactor(String factorid,ModelMap modelMap){
    	Factor factor = factorService.selectByPrimaryKey(factorid);
    	Factor fac = new Factor();
    	EnRelation enRelation = enrelationService.selectByPrimaryKey(factor.getEnrelationid());
		Strategy strategy = strategyService.selectByPrimaryKey(factor.getId());
		Sub sub = subService.selectByPrimaryKey(enRelation.getSubid());
		RiskList riskList = risklistservice.selectByPrimaryKey(enRelation.getRiskid());
    	fac.setFactora(factor.getFactora());
    	fac.setFactorb(factor.getFactorb());
    	fac.setFactorc(factor.getFactorc());
    	fac.setFactorid(factor.getFactorid());
    	fac.setEnrelationid(sub.getSubname()+"、"+riskList.getRiskname()+"、"+strategy.getName());
    	modelMap.put("factor", fac);
    	return "WebRoot/DisasterNode/editFactor";
    }
    @RequestMapping("/updateFactor")
    public String updateFactor(Factor factor){
    	factorService.updateByPrimaryKeySelective(factor);
    	return "forward:listFactor";
    }
    @RequestMapping("/editRisklist")
    public String editRisklist(ModelMap modelMap,String emerid,String subid,String riskid){
    	Emergency emer=emergencyService.selectByPrimaryKey(emerid);
    	Sub sub=subService.selectByPrimaryKey(subid);
    	RiskList risk=risklistservice.selectByPrimaryKey(riskid);
    	modelMap.put("emer", emer);
    	modelMap.put("sub", sub);
    	modelMap.put("risk", risk);
        return "WebRoot/DisasterNode/editDisasterNode";
    }
    @RequestMapping("/updateRisklist")
    public String updateRisklist(Sub sub,RiskList riskList,String emerid,String emername){
    	int result;
    	emergency.setEmerid(emerid);
    	emergency.setEmername(emername);
    	emergencyService.updateByPrimaryKeySelective(emergency);
    	subService.updateByPrimaryKeySelective(sub);
    	result=risklistservice.updateByPrimaryKeySelective(riskList);
    	if(result >0)
			return "redirect:gotoSearchSub";
		else
			return "WebRoot/DisasterNode/addfailed";
    }
    @RequestMapping("/deleteRisklist")
    public String deleteRisklist(String riskid){
    	int result;
    	enrelationService.deleteByRiskid(riskid);
    	result=risklistservice.deleteByPrimaryKey(riskid);
    	if(result >0)
			return "redirect:gotoSearchSub";
		else
			return "WebRoot/DisasterNode/deletefailed";
    }
    @ResponseBody
	@RequestMapping("/findEmergency")
	public void findEmergency(String emerid) throws IOException{
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("emerid", emerid);
		List<Sub> sublist=subService.findByParams(params);
		if (sublist != null && sublist.size() > 0) {
			for (int i = 0; i < sublist.size(); i++) {
				Sub sub = sublist.get(i);
				cSelect += "<option value=" + sub.getSubid()
						+ ">" + sub.getSubname() + "</option>";
			}
		} else {
			cSelect += "<option value=\" \">无符合实例，请选择其它</option>";
		}			
	response.getWriter().write(cSelect);
	}
    @ResponseBody
	@RequestMapping("/findSub")
	public void findsub(String subid) throws IOException{
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("subid", subid);
		List<RiskList> risklist1=risklistservice.findByParams(params);
		if (risklist1!= null && risklist1.size() > 0) {
			for (int i = 0; i < risklist1.size(); i++) {
				RiskList riskList = risklist1.get(i);
				cSelect += "<option value=" + riskList.getRiskid()
						+ ">" + riskList.getRiskname() + "</option>";
			}
		} else {
			cSelect += "<option value=\" \">无符合实例，请选择其它</option>";
		}			
	response.getWriter().write(cSelect);
	}
    @ResponseBody
    @RequestMapping("/findsubnum")
    public String findsubnum(String emerid, ModelMap modelMap){
    	
    	Emergency emergency =emergencyService.selectByPrimaryKey(emerid);
    	String num=emergency.getSubnum();
//    	modelMap.put("subnum", num);
    	return num;
    }
}

