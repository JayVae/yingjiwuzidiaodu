package com.pole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
/**
 * 表示风险的对象
 * @author hu
 *
 */
public class Risker implements Serializable{
	private static final long serialVersionUID = 1L;
	private int riskType;
	private int humanLim;
	private int moneyLim;
	private int materialLim;
	private int humanLoaded;
	private int moneyLoaded;
	private int materialLoaded;

	
	public Risker(int riskType, int humanLim, int moneyLim, int materialLim) {
		this.riskType = riskType;
		this.humanLim = humanLim;
		this.moneyLim = moneyLim;
		this.materialLim = materialLim;
		this.humanLoaded = humanLim;
		this.moneyLoaded = moneyLim;
		this.materialLoaded = materialLim;
	}

	public String formatString() {
		String str = "[" + String.valueOf(riskType) + ":" + String.valueOf(humanLoaded) + ":" + String.valueOf(moneyLoaded) + ":" + String.valueOf(materialLoaded) + "]";
		return str;
	}
	public int validateEmpty() {
		int flag = 0;
		if(this.humanLoaded + this.moneyLoaded + this.materialLoaded == 0) {
			flag = 1;
		}
		return flag;
	}
	
	public int getRiskType() {
		return riskType;
	}
	public void setRiskType(int riskType) {
		this.riskType = riskType;
	}
	public int getHumanLim() {
		return humanLim;
	}
	public void setHumanLim(int humanLim) {
		this.humanLim = humanLim;
	}
	public int getMoneyLim() {
		return moneyLim;
	}
	public void setMoneyLim(int moneyLim) {
		this.moneyLim = moneyLim;
	}
	public int getMaterialLim() {
		return materialLim;
	}
	public void setMaterialLim(int materialLim) {
		this.materialLim = materialLim;
	}
	public int getTotalLim() {
		return this.moneyLim + this.humanLim + this.materialLim;
	}
	public int getQuantityLim(int riskType) {
		if(riskType == Constants.MONEYRISK) {
			return this.moneyLim;
		}
		if(riskType == Constants.TIMERISK) {
			return this.humanLim;
		}
		if(riskType == Constants.TECHRISK) {
			return this.materialLim;
		}
		return 0;
	}
	public int getHumanLoaded() {
		return humanLoaded;
	}


	public void setHumanLoaded(int humanLoaded) {
		this.humanLoaded = humanLoaded;
	}


	public int getMoneyLoaded() {
		return moneyLoaded;
	}


	public void setMoneyLoaded(int moneyLoaded) {
		this.moneyLoaded = moneyLoaded;
	}


	public int getMaterialLoaded() {
		return materialLoaded;
	}


	public void setMaterialLoaded(int materialLoaded) {
		this.materialLoaded = materialLoaded;
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
