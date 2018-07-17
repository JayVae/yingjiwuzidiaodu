package com.pole;
//FORCED WBS
public class WBS {
	private int identifier;
	private int strategy;
	private int destSubProject;
	private Risker risker;
	private int delta;
	

	public WBS(int strategy, Risker risker, int destSubProject) {
		this.strategy = strategy;
		this.risker = risker;
		this.destSubProject = destSubProject;
		delta = 0;
	}
	
	public String formatString(){
		String str = "(" + String.valueOf(destSubProject) + "-" +risker.formatString() + "-" + String.valueOf(strategy)+")+";
		return str;
	}
	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public int getStrategy() {
		return strategy;
	}
	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}
	public int getDestSubProject() {
		return destSubProject;
	}
	public void setDestSubProject(int destSubProject) {
		this.destSubProject = destSubProject;
	}
	public Risker getRisker() {
		return risker;
	}
	public void setRisker(Risker risker) {
		this.risker = risker;
	}
}
