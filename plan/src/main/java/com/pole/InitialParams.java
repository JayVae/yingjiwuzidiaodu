package com.pole;

public class InitialParams {
	private double factorsA[][][];
	private double factorsB[][][];
	private double factorsC[][][];
	private double human_strategy[];
	private double money_strategy[];
	private double material_strategy[];
	//E\F\G\H 表示子项目
	public static final int initialTechRisk_E = 40;
	public static final int initialMoneyRisk_E = 60;
	public static final int initialTimeRisk_E = 50;
	
	public static final int initialTechRisk_F = 40;
	public static final int initialMoneyRisk_F = 70;
	public static final int initialTimeRisk_F = 20;
	
	public static final int initialTechRisk_G = 50;
	public static final int initialMoneyRisk_G = 60;
	public static final int initialTimeRisk_G = 30;
	
	public static final int initialTechRisk_H = 40;
	public static final int initialMoneyRisk_H = 20;
	public static final int initialTimeRisk_H = 60;
	
	//
	public static final int evade_human_A = 250;
	public static final int transport_human_B = 200;
	public static final int ease_human_C = 130;
	public static final int accept_human_D = 20;
	
	public static final int evade_money_A = 500;
	public static final int transport_money_B = 350;
	public static final int ease_money_C = 300;
	public static final int accept_money_D = 50;
	
	public static final int evade_material_A = 450;
	public static final int transport_material_B = 300;
	public static final int ease_material_C = 200;
	public static final int accept_material_D = 50;
	
	//
	public static final int humanLim_E = 110;
	public static final int humanLim_F = 120;
	public static final int humanLim_G = 130;
	public static final int humanLim_H = 110;
	
	public static final int moneyLim_E = 285;
	public static final int moneyLim_F = 335;
	public static final int moneyLim_G = 280;
	public static final int moneyLim_H = 100;
	
	public static final int materialLim_E = 250;
	public static final int materialLim_F = 100;
	public static final int materialLim_G = 150;
	public static final int materialLim_H = 300;
	
	//小车参数
	public static final int tech_human_lim_single = 20;
	public static final int tech_money_lim_single = 10;
	public static final int tech_material_lim_single = 10;
	
	public static final int money_human_lim_single = 10;
	public static final int money_money_lim_single = 20;
	public static final int money_material_lim_single = 10;
	
	public static final int time_human_lim_single = 10;
	public static final int time_money_lim_single = 10;
	public static final int time_material_lim_single = 20;
	
	public InitialParams(int strategyNum) {
		human_strategy = new double[strategyNum];
		human_strategy[0] = 250;
		human_strategy[1] = 200;
		human_strategy[2] = 130;
		human_strategy[3] = 20;
		
		money_strategy = new double[strategyNum];
		money_strategy[0] = 500;
		money_strategy[1] = 350;
		money_strategy[2] = 300;
		money_strategy[3] = 50;
		
		material_strategy = new double[strategyNum];
		material_strategy[0] = 450;
		material_strategy[1] = 300;
		material_strategy[2] = 200;
		material_strategy[3] = 50;
		
		factorsA = new double[3][4][4];
		factorsB = new double[3][4][4];
		factorsC = new double[3][4][4];
	}
	
	public double getHumanStrategy(int strategyid) {
		return human_strategy[strategyid];
	}
	public double getMoneyStrategy(int strategyid) {
		return money_strategy[strategyid];
	}
	public double getMaterialStrategy(int strategyid) {
		return material_strategy[strategyid];
	}
	public void setFactorA(int j, int i, int k, int value) {
		factorsA[j][i][k] = value;
	}
	public double getFactorA(int j, int i, int k) {
		return factorsA[j][i][k];
	}
	public void setFactorB(int j, int i, int k, int value) {
		factorsB[j][i][k] = value;
	}
	public double getFactorB(int j, int i, int k) {
		return factorsB[j][i][k];
	}
	public void setFactorC(int j, int i, int k, int value) {
		factorsC[j][i][k] = value;
	}
	public double getFactorC(int j, int i, int k) {
		return factorsC[j][i][k];
	}
	public void setDefault() {
		this.setDefaultFactorsA();
		this.setDefaultFactorsB();
		this.setDefaultFactorsC();
	}
	public void setDefaultFactorsA() {
		factorsA[0][0][0] = 42;
		factorsA[0][0][1] = 49;
		factorsA[0][0][2] = 56;
		factorsA[0][0][3] = 54;
		
		factorsA[0][1][0] = 55;
		factorsA[0][1][1] = 48;
		factorsA[0][1][2] = 51;
		factorsA[0][1][3] = 46;
		
		factorsA[0][2][0] = 47;
		factorsA[0][2][1] = 53;
		factorsA[0][2][2] = 44;
		factorsA[0][2][3] = 46;
		
		factorsA[0][3][0] = 48;
		factorsA[0][3][1] = 52;
		factorsA[0][3][2] = 53;
		factorsA[0][3][3] = 59;
		
		//还有11组数据要录
		factorsA[1][0][0] = 14;
		factorsA[1][0][1] = 9;
		factorsA[1][0][2] = 16;
		factorsA[1][0][3] = 18;
		
		factorsA[1][1][0] = 14;
		factorsA[1][1][1] = 7;
		factorsA[1][1][2] = 10;
		factorsA[1][1][3] = 9;
		
		factorsA[1][2][0] = 7;
		factorsA[1][2][1] = 15;
		factorsA[1][2][2] = 9;
		factorsA[1][2][3] = 12;
		
		factorsA[1][3][0] = 16;
		factorsA[1][3][1] = 4;
		factorsA[1][3][2] = 3;
		factorsA[1][3][3] = 17;
		
		//table 6
		factorsA[2][0][0] = 29;
		factorsA[2][0][1] = 31;
		factorsA[2][0][2] = 36;
		factorsA[2][0][3] = 28;
		
		factorsA[2][1][0] = 27;
		factorsA[2][1][1] = 38;
		factorsA[2][1][2] = 34;
		factorsA[2][1][3] = 29;
		
		factorsA[2][2][0] = 35;
		factorsA[2][2][1] = 32;
		factorsA[2][2][2] = 24;
		factorsA[2][2][3] = 36;
		
		factorsA[2][3][0] = 34;
		factorsA[2][3][1] = 29;
		factorsA[2][3][2] = 33;
		factorsA[2][3][3] = 30;

	}
	public void setDefaultFactorsB() {
		factorsB[0][0][0] = 11;
		factorsB[0][0][1] = 10;
		factorsB[0][0][2] = 19;
		factorsB[0][0][3] = 14;
		
		factorsB[0][1][0] = 17;
		factorsB[0][1][1] = 17;
		factorsB[0][1][2] = 12;
		factorsB[0][1][3] = 4;
		
		factorsB[0][2][0] = 9;
		factorsB[0][2][1] = 10;
		factorsB[0][2][2] = 12;
		factorsB[0][2][3] = 7;
		
		factorsB[0][3][0] = 11;
		factorsB[0][3][1] = 14;
		factorsB[0][3][2] = 9;
		factorsB[0][3][3] = 15;
		
		//table 8
		factorsB[1][0][0] = 45;
		factorsB[1][0][1] = 43;
		factorsB[1][0][2] = 57;
		factorsB[1][0][3] = 55;
		
		factorsB[1][1][0] = 52;
		factorsB[1][1][1] = 47;
		factorsB[1][1][2] = 56;
		factorsB[1][1][3] = 43;
		
		factorsB[1][2][0] = 48;
		factorsB[1][2][1] = 54;
		factorsB[1][2][2] = 42;
		factorsB[1][2][3] = 45;
		
		factorsB[1][3][0] = 47;
		factorsB[1][3][1] = 54;
		factorsB[1][3][2] = 53;
		factorsB[1][3][3] = 47;
		
		//table 9
		factorsB[2][0][0] = 27;
		factorsB[2][0][1] = 35;
		factorsB[2][0][2] = 33;
		factorsB[2][0][3] = 24;
		
		factorsB[2][1][0] = 26;
		factorsB[2][1][1] = 30;
		factorsB[2][1][2] = 38;
		factorsB[2][1][3] = 25;
		
		factorsB[2][2][0] = 34;
		factorsB[2][2][1] = 37;
		factorsB[2][2][2] = 27;
		factorsB[2][2][3] = 34;
		
		factorsB[2][3][0] = 38;
		factorsB[2][3][1] = 34;
		factorsB[2][3][2] = 37;
		factorsB[2][3][3] = 36;
		
	}
	
	public void setDefaultFactorsC() {
		//Table 10
		factorsC[0][0][0] = 25;
		factorsC[0][0][1] = 36;
		factorsC[0][0][2] = 37;
		factorsC[0][0][3] = 28;
		
		factorsC[0][1][0] = 23;
		factorsC[0][1][1] = 36;
		factorsC[0][1][2] = 34;
		factorsC[0][1][3] = 33;
		
		factorsC[0][2][0] = 34;
		factorsC[0][2][1] = 27;
		factorsC[0][2][2] = 22;
		factorsC[0][2][3] = 29;
		
		factorsC[0][3][0] = 35;
		factorsC[0][3][1] = 28;
		factorsC[0][3][2] = 31;
		factorsC[0][3][3] = 29;
		
		//Table 11
		factorsC[1][0][0] = 15;
		factorsC[1][0][1] = 12;
		factorsC[1][0][2] = 14;
		factorsC[1][0][3] = 17;
		
		factorsC[1][1][0] = 10;
		factorsC[1][1][1] = 7;
		factorsC[1][1][2] = 11;
		factorsC[1][1][3] = 14;
		
		factorsC[1][2][0] = 5;
		factorsC[1][2][1] = 13;
		factorsC[1][2][2] = 10;
		factorsC[1][2][3] = 15;
		
		factorsC[1][3][0] = 12;
		factorsC[1][3][1] = 15;
		factorsC[1][3][2] = 9;
		factorsC[1][3][3] = 18;
		
		//Table 12
		factorsC[2][0][0] = 55;
		factorsC[2][0][1] = 53;
		factorsC[2][0][2] = 47;
		factorsC[2][0][3] = 53;
		
		factorsC[2][1][0] = 52;
		factorsC[2][1][1] = 57;
		factorsC[2][1][2] = 46;
		factorsC[2][1][3] = 53;
		
		factorsC[2][2][0] = 58;
		factorsC[2][2][1] = 44;
		factorsC[2][2][2] = 52;
		factorsC[2][2][3] = 49;
		
		factorsC[2][3][0] = 57;
		factorsC[2][3][1] = 44;
		factorsC[2][3][2] = 57;
		factorsC[2][3][3] = 43;
		
	}
}
