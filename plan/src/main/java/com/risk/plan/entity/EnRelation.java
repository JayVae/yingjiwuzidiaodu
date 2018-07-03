package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class EnRelation implements Serializable {
    private String enrelationid;

    private String emerid;

    private String subid;

    private String riskid;

    private static final long serialVersionUID = 1L;
    private Emergency emergency;
    private Sub sub;
    private RiskList risklist;
    public Emergency getEmergency() {
		return emergency;
	}

	public void setEmergency(Emergency emergency) {
		this.emergency = emergency;
	}

	public Sub getsub() {
		return sub;
	}

	public void setsub(Sub sub) {
		this.sub = sub;
	}

	public RiskList getRisklist() {
		return risklist;
	}

	public void setRisklist(RiskList risklist) {
		this.risklist = risklist;
	}

	public String getEnrelationid() {
        return enrelationid;
    }

    public void setEnrelationid(String enrelationid) {
        this.enrelationid = enrelationid == null ? null : enrelationid.trim();
    }

    public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }

    public String getRiskid() {
        return riskid;
    }

    public void setRiskid(String riskid) {
        this.riskid = riskid == null ? null : riskid.trim();
    }
}