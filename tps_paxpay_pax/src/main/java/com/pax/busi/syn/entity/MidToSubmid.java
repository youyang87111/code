package com.pax.busi.syn.entity;

public class MidToSubmid {
	
	private String	bid;
	private String	mid;
	private String	classId;
	
	private String	submid;
	
	private String	notes1;
	private String	notes2;
	private String	notes3;
	
	private String	builddatetime;	//创建时间
	
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
	
	public String getBuilddatetime() {
		return builddatetime;
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
	public String getSubmid() {
		return submid;
	}
	
	public void setSubmid(String submid) {
		this.submid = submid;
	}
	
	public String getClassId() {
		return classId;
	}
	
	public void setClassId(String classId) {
		this.classId = classId;
	}
	
}
