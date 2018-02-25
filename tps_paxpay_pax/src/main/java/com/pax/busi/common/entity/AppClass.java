package com.pax.busi.common.entity;

public class AppClass {
	
	private String	id;
	private String	name;
	
	//一个交易类别属于什么分组
	private String	classgroup;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassgroup() {
		return classgroup;
	}
	
	public void setClassgroup(String classgroup) {
		this.classgroup = classgroup;
	}
	
}
