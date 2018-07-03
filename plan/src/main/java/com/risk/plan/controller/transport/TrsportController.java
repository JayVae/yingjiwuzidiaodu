package com.risk.plan.controller.transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.TranModel;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.transport.TranmodelService;

@Controller
public class TrsportController {
	//service
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	TranmodelService tranmodelService;
	//httpServlet
	@Autowired
	HttpServletRequest request;
	@RequestMapping("/gotoAddTrsport")
	public String gotoAddTrsport(ModelMap modelmap){
		List<String> statelist=new ArrayList<String>();
		statelist.add("正常");
		statelist.add("维修中");
		statelist.add("其它");
		modelmap.put("statelist", statelist);
		//加载运输工具类型及载重量
		List<String> transtypenames=new ArrayList<String>();
		List<TranModel> tranModels=tranmodelService.selectAll();
		modelmap.put("tranModellist",tranModels);
		for(TranModel tranModel:tranModels){
			transtypenames.add(tranModel.getTranstype());
		}
		modelmap.put("transtypename", transtypenames);
		//加载灾害类型和事件
        List<String>emertypenames=emerTypeService.selectAllEmerTypeNames();
//		Users user=(Users)request.getSession().getAttribute("user");
//		String userid=user.getUserid();
//		String usertype=user.getUsertype();
//		Map<String, Object> params1=new HashMap<String, Object>();
//		params1.put("userid", userid);
//		params1.put("emertypename", emertypenames.get(0));
//		params1.put("usertype", usertype);
//		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
//		if(emertypenames.size() >0)
//		{
//			modelmap.put("emergencylist", emergencylist);
//		}else {
//			String listStrng="No Option";
//			modelmap.put("emergencylist", listStrng);
//		}
//		modelmap.put("emertypename", emertypenames);
//		List<EmerType> list= emerTypeService.selectAll();
		/*List<String>emertypenames=new ArrayList<String>();
		List<EmerType> emerTypelist=new ArrayList<EmerType>();
		for (EmerType emerType : list) {
			if(!emertypenames.contains(emerType.getEmertypename()) ){
				emertypenames.add(emerType.getEmertypename());
				emerTypelist.add(emerType);
			}
		}*/
        modelmap.put("emertypename", emertypenames);
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename",emertypenames.get(0));
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listString="No Option";
			modelmap.put("emergencylist", listString);
		}
		return "WebRoot/Transportation/addTransportation";
	
	}
	
	@RequestMapping("/GotoSearchTrsport")
	public String GotoSearchTrsport(ModelMap modelmap){
		return "WebRoot/Transportation/listTransportation";
	}
}
