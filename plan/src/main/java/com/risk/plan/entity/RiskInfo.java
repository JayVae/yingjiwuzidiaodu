package com.risk.plan.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public class RiskInfo implements Serializable {
    private String infoid;

    private String name;

    private String input;

    private String out;

    private Integer perlimit;

    private Integer fulimit;

    private Integer golimit;

    private static final long serialVersionUID = 1L;

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid == null ? null : infoid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input == null ? null : input.trim();
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out == null ? null : out.trim();
    }

    public Integer getPerlimit() {
        return perlimit;
    }

    public void setPerlimit(Integer perlimit) {
        this.perlimit = perlimit;
    }

    public Integer getFulimit() {
        return fulimit;
    }

    public void setFulimit(Integer fulimit) {
        this.fulimit = fulimit;
    }

    public Integer getGolimit() {
        return golimit;
    }

    public void setGolimit(Integer golimit) {
        this.golimit = golimit;
    }
}