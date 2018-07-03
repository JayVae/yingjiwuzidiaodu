package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Sub implements Serializable {
    private String subid;

    private String subname;

    private String subno;

    private String subriskp;

    private String personlimit;

    private String goodslimit;

    private String fundlimit;

    private String note;

    private String emerid;

    private static final long serialVersionUID = 1L;

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getSubno() {
        return subno;
    }

    public void setSubno(String subno) {
        this.subno = subno == null ? null : subno.trim();
    }

    public String getSubriskp() {
        return subriskp;
    }

    public void setSubriskp(String subriskp) {
        this.subriskp = subriskp == null ? null : subriskp.trim();
    }

    public String getPersonlimit() {
        return personlimit;
    }

    public void setPersonlimit(String personlimit) {
        this.personlimit = personlimit == null ? null : personlimit.trim();
    }

    public String getgoodslimit() {
        return goodslimit;
    }

    public void setgoodslimit(String goodslimit) {
        this.goodslimit = goodslimit == null ? null : goodslimit.trim();
    }

    public String getFundlimit() {
        return fundlimit;
    }

    public void setFundlimit(String fundlimit) {
        this.fundlimit = fundlimit == null ? null : fundlimit.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }
}