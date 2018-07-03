package com.risk.plan.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class Emergency implements Serializable {
    private String emerid;

    private String emername;

    private String emerno;

    private Date timelimit;

    private String emerdescribe;

//    private String inaddress;

    private String note;

    private String subnum;
    
//    private EmerType emertype;
    
    private String timelimitS;

    private static final long serialVersionUID = 1L;
    
    
    public String getTimelimitS() {
		return timelimitS;
	}

    /* public EmerType getEmertype() {
		return emertype;
	}

	public void setEmertype(EmerType emertype) {
		this.emertype = emertype;
	}*/

	public String getEmerid() {
        return emerid;
    }

    public void setEmerid(String emerid) {
        this.emerid = emerid == null ? null : emerid.trim();
    }

    public String getEmername() {
        return emername;
    }

    public void setEmername(String emername) {
        this.emername = emername == null ? null : emername.trim();
    }

    public String getEmerno() {
        return emerno;
    }

    public void setEmerno(String emerno) {
        this.emerno = emerno == null ? null : emerno.trim();
    }

    public Date getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Date timelimit) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	String dateString=format.format(timelimit);
        this.timelimitS=dateString;
        this.timelimit = timelimit;
    }

    public String getEmerdescribe() {
        return emerdescribe;
    }

    public void setEmerdescribe(String emerdescribe) {
        this.emerdescribe = emerdescribe == null ? null : emerdescribe.trim();
    }

 /*   public String getInaddress() {
        return inaddress;
    }

    public void setInaddress(String inaddress) {
        this.inaddress = inaddress == null ? null : inaddress.trim();
    }*/

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getSubnum() {
        return subnum;
    }

    public void setSubnum(String subnum) {
        this.subnum = subnum == null ? null : subnum.trim();
    }
}