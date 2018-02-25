package com.pax.busi.common.entity;

public class Branch {
	
	private String	id;
	private String	name;
	private String	type;
	
	//一个渠道使用什么商户编码规则
	private String	mcr;
	
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMcr() {
		return mcr;
	}
	
	public void setMcr(String mcr) {
		this.mcr = mcr;
	}
	
}
