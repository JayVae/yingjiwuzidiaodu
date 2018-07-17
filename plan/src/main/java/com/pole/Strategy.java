package com.pole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

/**
 * 应对策略，要能监控实时数据
 * @author hu
 *
 */
public class Strategy implements Serializable{
	//需要串行化
	private static final long serialVersionUID = 1L;
	private int humanLim;
	private int moneyLim;
	private int materialLim;
	private int rtMaterial;
	private int rtMoney;
	private int rtHuman;
	private int type;
	private Risker lastRisker;
	
	public Strategy(int type, int humanLim, int moneyLim, int materialLim) {
		this.humanLim = humanLim;
		this.moneyLim = moneyLim;
		this.materialLim = materialLim;
		this.rtHuman = 0;
		this.rtMoney = 0;
		this.rtMaterial = 0;
		this.type = type;
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

	public int getRtMaterial() {
		return rtMaterial;
	}
	public void setRtMaterial(int rtMaterial) {
		this.rtMaterial = rtMaterial;
	}
	public int getRtMoney() {
		return rtMoney;
	}
	public void setRtMoney(int rtMoney) {
		this.rtMoney = rtMoney;
	}
	public int getRtHuman() {
		return rtHuman;
	}
	public void setRtHuman(int rtHuman) {
		this.rtHuman = rtHuman;
	}
	public Risker checkRisker(Risker risker) throws OptionalDataException, IOException, ClassNotFoundException {
		Risker tmp = (Risker)risker.myCopy();
		
		if(this.rtHuman + tmp.getHumanLoaded() <= this.humanLim) {
			
		} else {
			tmp.setHumanLoaded(this.humanLim - this.rtHuman);
			
		}
		
		if(this.rtMoney + tmp.getMoneyLoaded() <= this.moneyLim) {
			
		} else {
			tmp.setMoneyLoaded(this.moneyLim - this.rtMoney);
			
		}
		
		if(this.rtMaterial + tmp.getMaterialLoaded() <= this.materialLim) {
			
		} else {
			tmp.setMaterialLoaded(this.materialLim - this.rtMaterial);
			
		}
		
		return tmp;
	}
	public void applyRisker(Risker risker) {
		this.rtHuman += risker.getHumanLoaded();
		this.rtMaterial += risker.getMaterialLoaded();
		this.rtMoney += risker.getMoneyLoaded();
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
