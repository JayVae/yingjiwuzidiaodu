package com.pole;

import java.util.ArrayList;

public class WBS_Set {
	//可能需要串行化
	private ArrayList<WBS> wbsArray;
	public WBS_Set() {
		wbsArray = new ArrayList<WBS>();
	}
	public void addWBS(WBS wbs) {
		wbsArray.add(wbs);
	}
	public WBS getWBS(int index) {
		return wbsArray.get(index);
	}
	public int getWBS_Set_Size() {
		return wbsArray.size();
	}
}
