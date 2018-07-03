package com.risk.plan.entity;

import org.springframework.stereotype.Component;

@Component
public class FactorEx {
  private Factor factor;
  private RiskList riskList;
public Factor getFactor() {
	return factor;
}
public void setFactor(Factor factor) {
	this.factor = factor;
}
public RiskList getRiskList() {
	return riskList;
}
public void setRiskList(RiskList riskList) {
	this.riskList = riskList;
}
}
