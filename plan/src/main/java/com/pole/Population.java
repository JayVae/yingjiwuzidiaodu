package com.pole;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.Random;
/**
 * Population中只有一个解
 * @author hu
 *
 */
public class Population implements Serializable{
	private static final long serialVersionUID = 1L;
    //
	private WBS_Set wbsSet;//每一个wbsset记录一种可行解
	private ProjectSet projectSet;
	private int popuSize; //表示要产生多少组解
	private Risker riskerList[];
	private Strategy strategyList[];
	//还要预留一个参数传递【公式】中的因子
	//针对每一个wbsset要重复制一份riskerList[]和strategyList[],否则容易出现混乱
	
	public Population( int riskerNum, int strategyNum) {
		wbsSet = new WBS_Set();
		riskerList = new Risker[riskerNum];
		strategyList = new Strategy[strategyNum];
		for(int i = 0; i < riskerNum; i++) {
			riskerList[i] = new Risker(0, 0, 0, 0);
		}
		for(int i = 0; i < strategyNum; i ++) {
			strategyList[i] = new Strategy(0, 0, 0, 0);
		}
	}
	public WBS_Set getWbsSet() {
		return wbsSet;
	}
	public void setWbsSet(WBS_Set wbsSet) {
		this.wbsSet = wbsSet;
	}
	public Risker[] getRiskerList() {
		return riskerList;
	}
	public void setRiskerList(Risker[] riskerList) throws OptionalDataException, IOException, ClassNotFoundException {
		for(int i = 0; i < riskerList.length; i++) {
			this.riskerList[i] = (Risker)riskerList[i].myCopy();
		}
		
	}
	public Strategy[] getStrategyList() {
		return strategyList;
	}
	public void setStrategyList(Strategy[] strategyList) throws OptionalDataException, IOException, ClassNotFoundException {
		for(int i = 0; i< strategyList.length; i++) {
			this.strategyList[i] = (Strategy)strategyList[i].myCopy();
		}

	}
	public ProjectSet getProjectSet() {
		return projectSet;
	}
	public void setProjectSet(ProjectSet prjs) throws OptionalDataException, ClassNotFoundException, IOException {
		projectSet = (ProjectSet)prjs.myCopy();
	}
	
	/**
	 * 在这里初始化wbsset
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws OptionalDataException 
	 */
	public void initiation() throws OptionalDataException, IOException, ClassNotFoundException {
		//最外层先对项目进行循环
		//然后对其中的风险清单进行循环
		//先尝试全负荷分配（如20，10，10），检查当前项目资源限制是否已经达到，若达到则对对应的资源进行消减
		//再检查对应的策略是否达到资源限制，如果达到，先尝试在不变更载荷的情况下进行策略更换，如果不能更换则消减对应资源。
		ProjectSet prjset = (ProjectSet)projectSet.myCopy();//复制一份进行操作

		while(true) {
			for(int i =0, len1 = prjset.getSize(); i < len1; i++) {
				SubProject subp = prjset.getSubProject(i);
				
				for(int j = 0, len2 = riskerList.length; j < len2; j++) {
					if(prjset.getSubProject(i).isDone() == 1) {
						continue;
					}
					Risker risker_tmp, final_risk_tmp;
					//注意：riskerList没有被修改
					risker_tmp = subp.checkRisker(riskerList[j]);
					int strategyid;
					//这里可能还要有一层遍历,先试下随机到的策略资源够不够，够的话就可以进入下一个风险清单；否则，查找其他策略能不能满足当前载荷，如果找不到，
					//如果找不到，就在当前策略下削减载荷，调用strategy的checkRisker，再调用subproject的checkRisker，但其第二个参数要设为1表示重做最近一次操作
					while(true) {
						 strategyid = randBit(0, strategyList.length - 1);
						 final_risk_tmp = strategyList[strategyid].checkRisker(risker_tmp);
						if(final_risk_tmp.validateEmpty() != 1) { //验证调整完成之后是不是为空
							break;
						}
					}
					//注意strategyList已经被修改
					strategyList[strategyid].applyRisker(final_risk_tmp);//应用调整后的risker
					subp.applyRisker(final_risk_tmp);//回过头来对项目重新调整
					WBS wbs = new WBS(strategyid, final_risk_tmp, i);
					wbsSet.addWBS(wbs);
					
				}
		
			}
			//结束条件
			int zeros = 0;
			for(int k =0, len3 = prjset.getSize(); k <len3; k++) {
				if(prjset.getSubProject(k).isDone() == 1)
					zeros += 1;
			}
			if(zeros == prjset.getSize())
				break;
		}		
	}
	/**
	 * 打印生成的WBS集
	 */
	public void printWBSSet() {
		for(int i = 0, len = wbsSet.getWBS_Set_Size(); i < len; i++) {
			if(i%8 == 0)
				System.out.println();
			System.out.print(wbsSet.getWBS(i).formatString());
		}
	}
	//计算风险率变化
	public int calcTheRatio(InitialParams initparam) {
		int total = 0;
		//TODO:TO BE FIXED

		for(int i = 0; i < projectSet.getSize(); i++) {
			projectSet.getSubProject(i).resetRatio();
		}
		for(int i = 0, len = wbsSet.getWBS_Set_Size(); i < len; i++) {
			int subpId = wbsSet.getWBS(i).getDestSubProject();
			int strategyid = wbsSet.getWBS(i).getStrategy();
			Risker rsk = wbsSet.getWBS(i).getRisker();
			SubProject subp = projectSet.getSubProject(subpId);
			subp.affectTheRatio(subpId, strategyid, rsk.getRiskType(), rsk.getHumanLoaded(), rsk.getMoneyLoaded(), rsk.getMaterialLoaded(), initparam, strategyList, wbsSet.getWBS(i));			
		}
		//print out
		for(int j = 0, len1 = projectSet.getSize(); j < len1; j++) {
			int tmp[] = projectSet.getSubProject(j).getRiskRatioArray();
			//System.out.print("[[");
			for(int k = 0, len2 = tmp.length; k < len2; k++) {
				if(tmp[k] < 0) {
					return -1;
				}
				total += tmp[k];
				//System.out.print("?"+ String.valueOf(tmp[k]) + "?");
			}
			//System.out.println("]]");
		}
		return total;
	}
	
	public void printRatio() {
		//print out
		for(int j = 0, len1 = projectSet.getSize(); j < len1; j++) {
			int tmp[] = projectSet.getSubProject(j).getRiskRatioArray();
			System.out.print("[[");
			for(int k = 0, len2 = tmp.length; k < len2; k++) {
				
				System.out.print("?"+ String.valueOf(tmp[k]) + "?");
			}
			System.out.println("]]");
		}
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
	//深度克隆
    public Object myCopy() throws IOException,OptionalDataException,ClassNotFoundException
    {
    ByteArrayOutputStream bo=new ByteArrayOutputStream();
    ObjectOutputStream oo=new ObjectOutputStream(bo);
    oo.writeObject(this);
    ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
    ObjectInputStream oi=new ObjectInputStream(bi);
    return(oi.readObject());
    }
}
