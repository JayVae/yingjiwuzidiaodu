package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class RiskList implements Serializable {
    private String riskid;

    private String riskname;

    private String riskper;

    private String riskno;

    private String note;

    private String subid;

    private static final long serialVersionUID = 1L;

    public String getRiskid() {
        return riskid;
    }

    public void setRiskid(String riskid) {
        this.riskid = riskid == null ? null : riskid.trim();
    }

    public String getRiskname() {
        return riskname;
    }

    public void setRiskname(String riskname) {
        this.riskname = riskname == null ? null : riskname.trim();
    }

    public String getRiskper() {
        return riskper;
    }

    public void setRiskper(String riskper) {
        this.riskper = riskper == null ? null : riskper.trim();
    }

    public String getRiskno() {
        return riskno;
    }

    public void setRiskno(String riskno) {
        this.riskno = riskno == null ? null : riskno.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }
}