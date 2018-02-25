package com.pax.busi.syn.entity;

/**
 * 
 *                       
 * @Filename Amcrmsg.java
 *
 * @Description 微信自主收银配置
 *
 * @Version 1.0
 *
 * @Author pax
 *
 * @Email 
 *       
 * @History
 *<li>Author: pax</li>
 *<li>Date: Dec 26, 2016</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */

public class Amcrmsg {
	
	private String	bid;
	private String	mid;
	
	private String	appid;			//
	private String	amcr_mid;		//微信为我们平台的商户号
	private String	amcr_key;		//微信为我们平台分配的秘钥
	private String	amcr_submid;	//
	
	private String	notes1;			//证书1 证书
	private String	notes2;			//证书2 证书key
	private String	notes3;			//证书3 根证书
	private String	notes4;
	private String	notes5;
	
	public String getBid() {
		return bid;
	}
	
	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getAppid() {
		return appid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getAmcr_mid() {
		return amcr_mid;
	}
	
	public void setAmcr_mid(String amcr_mid) {
		this.amcr_mid = amcr_mid;
	}
	
	public String getAmcr_key() {
		return amcr_key;
	}
	
	public void setAmcr_key(String amcr_key) {
		this.amcr_key = amcr_key;
	}
	
	public String getAmcr_submid() {
		return amcr_submid;
	}
	
	public void setAmcr_submid(String amcr_submid) {
		this.amcr_submid = amcr_submid;
	}
	
	public String getNotes1() {
		return notes1;
	}
	
	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}
	
	public String getNotes2() {
		return notes2;
	}
	
	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}
	
	public String getNotes3() {
		return notes3;
	}
	
	public void setNotes3(String notes3) {
		this.notes3 = notes3;
	}
	
	public String getNotes4() {
		return notes4;
	}
	
	public void setNotes4(String notes4) {
		this.notes4 = notes4;
	}
	
	public String getNotes5() {
		return notes5;
	}
	
	public void setNotes5(String notes5) {
		this.notes5 = notes5;
	}
	
}
