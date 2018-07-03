package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Scheme implements Serializable {
    private String sid;

    private String sname;

    private String rname;

    private Integer persum;

    private Integer fusum;

    private Integer gosum;

    private String percent;

    private static final long serialVersionUID = 1L;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public Integer getPersum() {
        return persum;
    }

    public void setPersum(Integer persum) {
        this.persum = persum;
    }

    public Integer getFusum() {
        return fusum;
    }

    public void setFusum(Integer fusum) {
        this.fusum = fusum;
    }

    public Integer getGosum() {
        return gosum;
    }

    public void setGosum(Integer gosum) {
        this.gosum = gosum;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent == null ? null : percent.trim();
    }
}