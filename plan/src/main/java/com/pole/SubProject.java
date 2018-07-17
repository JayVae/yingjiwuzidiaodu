package com.pole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class SubProject implements Serializable{
	private static final long serialVersionUID = 1L;
	//private Risker lastRisker;
	private String identifier; //只能为整数，与参数表的第一维对应
	//人、财、物的限制
	private int humanLimitation;
	private int moneyLimitation;
	private int materialLimitation;
	private int rtHuman;
	private int rtMoney;
	private int rtMaterial;
	//初始风险率
	private int initialRisk[];

	//private int initialTechRisk;
	//private int initialTimeRisk;
	//private int initialMoneyRisk;
	//实时风险率
	private int risk[];
	//private int rtTechRisk;
	//private int rtTimeRisk;
	//private int rtMoneyRisk;

	public SubProject(String id, int humanL, int moneyL, int materialL, int initialTechRisk, int initialTimeRisk, int initialMoneyRisk){
		this.identifier = id;
		this.humanLimitation = humanL;
		this.moneyLimitation = moneyL;
		this.materialLimitation = materialL;
		initialRisk = new int[3];
		initialRisk[0] = initialTechRisk;
		initialRisk[2] = initialTimeRisk;
		initialRisk[1] = initialMoneyRisk;
		
		risk = new int[3];
		risk[0] = initialTechRisk;
		risk[1] = initialTimeRisk;
		risk[2] = initialMoneyRisk;
		this.rtHuman = 0;
		this.rtMaterial = 0;
		this.rtMoney = 0;
	}
	public int[] getInitialRisk() {
		return initialRisk;
	}
	/**
	 * 检查使用的risker是否合适,会更新实时量，但不更新风险率，那个放过WBS中统一计算
	 * @param risker
	 * @return
	 * @throws OptionalDataException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Risker checkRisker(Risker risker) throws OptionalDataException, IOException, ClassNotFoundException {
		Risker tmp = (Risker)risker.myCopy();
		
			//TODO：不让超过限制
			if(this.rtHuman + tmp.getHumanLoaded() > this.humanLimitation) {
				tmp.setHumanLoaded(this.humanLimitation - this.rtHuman);
				//this.rtHuman = this.humanLimitation;
			} else {
				//this.rtHuman += tmp.getHumanLoaded();
			}
			if(this.rtMoney + tmp.getMoneyLoaded() > this.moneyLimitation) {
				tmp.setMoneyLoaded(this.moneyLimitation - this.rtMoney);
				//this.rtMoney = this.moneyLimitation;
			} else {
				//this.rtMoney += tmp.getMoneyLoaded();
			}
			if(this.rtMaterial + tmp.getMaterialLoaded() > this.materialLimitation) {
				tmp.setMaterialLoaded(this.materialLimitation - this.rtMaterial);
				//this.rtMaterial = this.materialLimitation;
			} else {
				//this.rtMaterial += tmp.getMaterialLoaded();
			}
			//lastRisker = (Risker)tmp.myCopy();//保存最近一次风险调度
				
		return tmp;
		
	}
	public void applyRisker(Risker risker) {
		this.rtHuman += risker.getHumanLoaded();
		this.rtMoney += risker.getMoneyLoaded();
		this.rtMaterial += risker.getMaterialLoaded();
	}
	public int getHumanLimitation() {
		return humanLimitation;
	}

	public void setHumanLimitation(int humanLimitation) {
		this.humanLimitation = humanLimitation;
	}

	public int getMoneyLimitation() {
		return moneyLimitation;
	}

	public void setMoneyLimitation(int moneyLimitation) {
		this.moneyLimitation = moneyLimitation;
	}

	public int getMaterialLimitation() {
		return materialLimitation;
	}

	public void setMaterialLimitation(int materialLimitation) {
		this.materialLimitation = materialLimitation;
	}
	public int getRtHuman() {
		return rtHuman;
	}
	public void setRtHuman(int rtHuman) {
		this.rtHuman = rtHuman;
	}
	public int getRtMoney() {
		return rtMoney;
	}
	public void setRtMoney(int rtMoney) {
		this.rtMoney = rtMoney;
	}
	public int getRtMaterial() {
		return rtMaterial;
	}
	public void setRtMaterial(int rtMaterial) {
		this.rtMaterial = rtMaterial;
	}
	

	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public void resetRatio() {
		risk[0] = initialRisk[0];
		risk[1] = initialRisk[1];
		risk[2] = initialRisk[2];
		//每次计算前复位一下
	}
	public void affectTheRatio(int subprojectIndex, int strategyid, int riskerid, int human, int money, int material, InitialParams initparam, Strategy strategyList[], WBS wbs) {
		//TODO:iniparams中的变量值要抽出来
		int humanTotal = 0, moneyTotal = 0, materialTotal = 0;
		for(int i = 0, len = strategyList.length; i < len; i++) {
			humanTotal += strategyList[i].getHumanLim();
			moneyTotal += strategyList[i].getMoneyLim();
			materialTotal += strategyList[i].getMaterialLim();
		}
		int delta = (int)(100*(initparam.getFactorA(riskerid, subprojectIndex, strategyid)*human/humanTotal + initparam.getFactorB(riskerid, subprojectIndex, strategyid)*money/moneyTotal + initparam.getFactorC(riskerid, subprojectIndex, strategyid)*material/materialTotal));
		wbs.setDelta(delta);
		this.risk[riskerid] -= delta;
	}
	//查看资源限制是否全部达到
	public int isDone() {
		if(this.humanLimitation == this.rtHuman && this.materialLimitation == this.rtMaterial && this.moneyLimitation == this.rtMoney) {
			return 1;
		}
		else return 0;
	}
    public int[] getRiskRatioArray() {
    	return risk;
    }
	  public int randBit(int min,int max){//����һ����Χ�ڵ������
		  Random rd=new Random();
		  return rd.nextInt(max-min+1)+min;
		  
	  }
	//�����¡
    public Object myCopy() throws IOException,OptionalDataException,ClassNotFoundException
    {
    //������д������
    ByteArrayOutputStream bo=new ByteArrayOutputStream();
    ObjectOutputStream oo=new ObjectOutputStream(bo);
    oo.writeObject(this);
    //�����������
    ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
    ObjectInputStream oi=new ObjectInputStream(bi);
    return(oi.readObject());
    }
}
