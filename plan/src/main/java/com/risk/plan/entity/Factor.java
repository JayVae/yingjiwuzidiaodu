package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class Factor implements Serializable {
    private String factorid;

    private String factora;

    private String factorb;

    private String factorc;

    private String id;

    private String enrelationid;

    private static final long serialVersionUID = 1L;

    public String getFactorid() {
        return factorid;
    }

    public void setFactorid(String factorid) {
        this.factorid = factorid == null ? null : factorid.trim();
    }

    public String getFactora() {
        return factora;
    }

    public void setFactora(String factora) {
        this.factora = factora == null ? null : factora.trim();
    }

    public String getFactorb() {
        return factorb;
    }

    public void setFactorb(String factorb) {
        this.factorb = factorb == null ? null : factorb.trim();
    }

    public String getFactorc() {
        return factorc;
    }

    public void setFactorc(String factorc) {
        this.factorc = factorc == null ? null : factorc.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnrelationid() {
        return enrelationid;
    }

    public void setEnrelationid(String enrelationid) {
        this.enrelationid = enrelationid == null ? null : enrelationid.trim();
    }
}