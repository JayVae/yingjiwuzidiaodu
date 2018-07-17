package com.pole;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.Random;



public class GenAlgo {

	public GenAlgo() throws OptionalDataException, IOException, ClassNotFoundException {

	}
	
	public Population InitAndRun(ProjectSet project_set, int prjs_Num, Risker risk_list[], int risk_Num, Strategy stra_list[], int stra_Num, int lit_Times, InitialParams initparams) throws OptionalDataException, IOException, ClassNotFoundException {

	    int projectsNum = prjs_Num;
		int riskNum = risk_Num; //3
		int strategyNum = stra_Num;
		int litTimes = lit_Times;
		int fullPercent = 100;
	//现在是纯模拟，还没有加入所谓的变异￥_$
	//变异算子：找到同风险的WBS，更换其对应的策略
	//	int popSize = 1;
	  //初始化策略
		Strategy strategyList[];
		strategyList = stra_list;
//	Strategy stg_evade = new Strategy(Constants.EVADE, InitialParams.evade_human_A, InitialParams.evade_money_A, InitialParams.evade_material_A);
//	Strategy stg_transport = new Strategy(Constants.TRANSPORT, InitialParams.transport_human_B, InitialParams.transport_money_B, InitialParams.transport_material_B);
//	Strategy stg_ease = new Strategy(Constants.EASE, InitialParams.ease_human_C, InitialParams.ease_money_C, InitialParams.ease_material_C);
		
	//	strategyList[0] = new Strategy(Constants.EVADE, InitialParams.evade_human_A, InitialParams.evade_money_A, InitialParams.evade_material_A);
	//	strategyList[1] = new Strategy(Constants.TRANSPORT, InitialParams.transport_human_B, InitialParams.transport_money_B, InitialParams.transport_material_B);
	//	strategyList[2] = new Strategy(Constants.EASE, InitialParams.ease_human_C, InitialParams.ease_money_C, InitialParams.ease_material_C);
	//	strategyList[3] = new Strategy(Constants.ACCEPT, InitialParams.accept_human_D, InitialParams.accept_money_D, InitialParams.accept_material_D);
		
	 //初始化项目
	//SubProject sub_e = new SubProject("0", InitialParams.humanLim_E, InitialParams.moneyLim_E, InitialParams.materialLim_E, InitialParams.initialTechRisk_E, InitialParams.initialTimeRisk_E, InitialParams.initialMoneyRisk_E);
	//SubProject sub_f = new SubProject("1", InitialParams.humanLim_F, InitialParams.moneyLim_F, InitialParams.materialLim_F, InitialParams.initialTechRisk_F, InitialParams.initialTimeRisk_F, InitialParams.initialMoneyRisk_F);
	//SubProject sub_g = new SubProject("2", InitialParams.humanLim_G, InitialParams.moneyLim_G, InitialParams.materialLim_G, InitialParams.initialTechRisk_G, InitialParams.initialTimeRisk_G, InitialParams.initialMoneyRisk_G);
	//SubProject sub_h = new SubProject("3", InitialParams.humanLim_H, InitialParams.moneyLim_H, InitialParams.materialLim_H, InitialParams.initialTechRisk_H, InitialParams.initialTimeRisk_H, InitialParams.initialMoneyRisk_H);
	
	 //初始化风险（也就是小车）
	Risker riskList[];
	riskList = risk_list;
//	Risker risker_tech = new Risker(Constants.TECHRISK, InitialParams.tech_human_lim_single, InitialParams.tech_money_lim_single, InitialParams.tech_material_lim_single);
//	Risker risker_time = new Risker(Constants.TIMERISK, InitialParams.time_human_lim_single, InitialParams.time_money_lim_single, InitialParams.time_material_lim_single);
//	Risker risker_money = new Risker(Constants.MONEYRISK, InitialParams.money_human_lim_single, InitialParams.money_money_lim_single, InitialParams.money_material_lim_single);
	//riskList = new Risker[riskNum];
	//riskList[0] = new Risker(Constants.TECHRISK, InitialParams.tech_human_lim_single, InitialParams.tech_money_lim_single, InitialParams.tech_material_lim_single);
	//riskList[1] = new Risker(Constants.TIMERISK, InitialParams.time_human_lim_single, InitialParams.time_money_lim_single, InitialParams.time_material_lim_single);
	//riskList[2] = new Risker(Constants.MONEYRISK, InitialParams.money_human_lim_single, InitialParams.money_money_lim_single, InitialParams.money_material_lim_single);
	 //初始化项目群中项目的个数，与子项目数对应
//	ProjectSet projectSet = new ProjectSet(projectsNum);
	ProjectSet projectSet = project_set;
//	projectSet.setSubProject(0, sub_e);
//	projectSet.setSubProject(1, sub_f);
//	projectSet.setSubProject(2, sub_g);
//	projectSet.setSubProject(3, sub_h);
	Population popChosen = null;
	int min = projectsNum*riskNum*fullPercent*100;
	for(int i = 0 ; i < litTimes; i++) {
		Population pop = new Population(riskNum, strategyNum);
		
		//给要计算的种群装配风险清单
		pop.setRiskerList(riskList);
		//给要计算的种群装配策略清单
		pop.setStrategyList(strategyList);
		//给要计算的种群装配上项目集
		pop.setProjectSet(projectSet);
		pop.initiation();
		//pop.printWBSSet();
		int retVal = pop.calcTheRatio(initparams);
		if( retVal != -1) {
			if (retVal < min){
				min = retVal;
				popChosen = pop;
			}			
		}
	}
	if(popChosen != null)
	popChosen.printRatio();
	
	System.out.println(">>>Final:" + min);
	return popChosen;
	}
	
	/**
	   * 随机整数产生函数
	   * @param min
	   * @param max
	   * @return
	   */
	  public int randBit(int min,int max){//����һ����Χ�ڵ������
		  Random rd=new Random();
		  return rd.nextInt(max-min+1)+min;
		  
	  }
	  
//	  
//	  public static void main(String[] args) throws OptionalDataException, IOException, ClassNotFoundException {
//		    int projectsNum = 4;
//			int riskNum = 3;
//			int strategyNum = 4;
//			int litTimes = 1000;
//			int fullPercent = 100;
//		//现在是纯模拟，还没有加入所谓的变异￥_$
//		//变异算子：找到同风险的WBS，更换其对应的策略
//		//	int popSize = 1;
//		  //初始化策略
//			Strategy strategyList[];
//			strategyList = new Strategy[strategyNum];
////		Strategy stg_evade = new Strategy(Constants.EVADE, InitialParams.evade_human_A, InitialParams.evade_money_A, InitialParams.evade_material_A);
////		Strategy stg_transport = new Strategy(Constants.TRANSPORT, InitialParams.transport_human_B, InitialParams.transport_money_B, InitialParams.transport_material_B);
////		Strategy stg_ease = new Strategy(Constants.EASE, InitialParams.ease_human_C, InitialParams.ease_money_C, InitialParams.ease_material_C);
//			strategyList[0] = new Strategy(Constants.EVADE, InitialParams.evade_human_A, InitialParams.evade_money_A, InitialParams.evade_material_A);
//			strategyList[1] = new Strategy(Constants.TRANSPORT, InitialParams.transport_human_B, InitialParams.transport_money_B, InitialParams.transport_material_B);
//			strategyList[2] = new Strategy(Constants.EASE, InitialParams.ease_human_C, InitialParams.ease_money_C, InitialParams.ease_material_C);
//			strategyList[3] = new Strategy(Constants.ACCEPT, InitialParams.accept_human_D, InitialParams.accept_money_D, InitialParams.accept_material_D);
//		 //初始化项目
//		SubProject sub_e = new SubProject("0", InitialParams.humanLim_E, InitialParams.moneyLim_E, InitialParams.materialLim_E, InitialParams.initialTechRisk_E, InitialParams.initialTimeRisk_E, InitialParams.initialMoneyRisk_E);
//		SubProject sub_f = new SubProject("1", InitialParams.humanLim_F, InitialParams.moneyLim_F, InitialParams.materialLim_F, InitialParams.initialTechRisk_F, InitialParams.initialTimeRisk_F, InitialParams.initialMoneyRisk_F);
//		SubProject sub_g = new SubProject("2", InitialParams.humanLim_G, InitialParams.moneyLim_G, InitialParams.materialLim_G, InitialParams.initialTechRisk_G, InitialParams.initialTimeRisk_G, InitialParams.initialMoneyRisk_G);
//		SubProject sub_h = new SubProject("3", InitialParams.humanLim_H, InitialParams.moneyLim_H, InitialParams.materialLim_H, InitialParams.initialTechRisk_H, InitialParams.initialTimeRisk_H, InitialParams.initialMoneyRisk_H);
//		
//		 //初始化风险（也就是小车）
//		Risker riskList[];
////		Risker risker_tech = new Risker(Constants.TECHRISK, InitialParams.tech_human_lim_single, InitialParams.tech_money_lim_single, InitialParams.tech_material_lim_single);
////		Risker risker_time = new Risker(Constants.TIMERISK, InitialParams.time_human_lim_single, InitialParams.time_money_lim_single, InitialParams.time_material_lim_single);
////		Risker risker_money = new Risker(Constants.MONEYRISK, InitialParams.money_human_lim_single, InitialParams.money_money_lim_single, InitialParams.money_material_lim_single);
//		riskList = new Risker[riskNum];
//		riskList[0] = new Risker(Constants.TECHRISK, InitialParams.tech_human_lim_single, InitialParams.tech_money_lim_single, InitialParams.tech_material_lim_single);
//		riskList[1] = new Risker(Constants.TIMERISK, InitialParams.time_human_lim_single, InitialParams.time_money_lim_single, InitialParams.time_material_lim_single);
//		riskList[2] = new Risker(Constants.MONEYRISK, InitialParams.money_human_lim_single, InitialParams.money_money_lim_single, InitialParams.money_material_lim_single);
//		 //初始化项目群中项目的个数，与子项目数对应
//		ProjectSet projectSet = new ProjectSet(projectsNum);
//		projectSet.setSubProject(0, sub_e);
//		projectSet.setSubProject(1, sub_f);
//		projectSet.setSubProject(2, sub_g);
//		projectSet.setSubProject(3, sub_h);
//		Population popChosen = null;
//		int min = projectsNum*riskNum*fullPercent;
//		for(int i = 0 ; i < litTimes; i++) {
//			Population pop = new Population(riskNum, strategyNum);
//			
//			//给要计算的种群装配风险清单
//			pop.setRiskerList(riskList);
//			//给要计算的种群装配策略清单
//			pop.setStrategyList(strategyList);
//			//给要计算的种群装配上项目集
//			pop.setProjectSet(projectSet);
//			pop.initiation();
//			//pop.printWBSSet();
//			int retVal = pop.calcTheRatio();
//			if( retVal != -1) {
//				if (retVal < min){
//					min = retVal;
//					popChosen = pop;
//				}			
//			}
//		}
//		
//		popChosen.printRatio();
//		System.out.println(">>>Final:" + min);
//		
//	  }

}
