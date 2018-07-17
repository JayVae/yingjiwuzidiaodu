package com.risk.plan.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class GoodsType implements Serializable {
    private String goodstypeid;

	private String goodsname;

	private String goodslimit;

	private String note;

	private String subid;

	private static final long serialVersionUID = 1L;

	public String getGoodstypeid() {
		return goodstypeid;
	}

	public void setGoodstypeid(String goodstypeid) {
		this.goodstypeid = goodstypeid == null ? null : goodstypeid.trim();
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname == null ? null : goodsname.trim();
	}

	public String getGoodslimit() {
		return goodslimit;
	}

	public void setGoodslimit(String goodslimit) {
		this.goodslimit = goodslimit == null ? null : goodslimit.trim();
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