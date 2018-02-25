package com.pax.busi.syn.entity;

/**
 * 
 *                       
 * @Filename Amcamsg.java
 *
 * @Description 支付宝自主收银配置
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

public class Amcamsg {
	
	private String	bid;
	private String	mid;
	
	private String	appid;
	private String	alp_pubkey;		//支付宝公钥
	private String	amca_prikey;	//商户私钥
	
	private String	notes1;			//门店号
	private String	notes2;
	private String	notes3;
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
	
	public String getAlp_pubkey() {
		return alp_pubkey;
	}
	
	public void setAlp_pubkey(String alp_pubkey) {
		this.alp_pubkey = alp_pubkey;
	}
	
	public String getAmca_prikey() {
		return amca_prikey;
	}
	
	public void setAmca_prikey(String amca_prikey) {
		this.amca_prikey = amca_prikey;
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
