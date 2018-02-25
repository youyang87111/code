package com.pax.busi.resourcemgr.input;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class RMerAddInput {
	
	@NotEmpty(message = "商户来源不能为空")
	private String	mcr;
	
	@NotEmpty(message = "商户号不能为空")
	@Pattern(regexp = "^[A-Za-z0-9]{15}+$", message = "商户号格式不正确")
	private String	mid;
	
	@NotEmpty(message = "商户名称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{2,30}$", message = "商户名称格式不正确")
	private String	name;
	
	private String	buildoper;		//创建操作员
	private String	builddatetime;	//创建时间
									
	public String getBuildoper() {
		return buildoper;
	}
	
	public void setBuildoper(String buildoper) {
		this.buildoper = buildoper;
	}
	
	public String getBuilddatetime() {
		return builddatetime;
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getMcr() {
		return mcr;
	}
	
	public void setMcr(String mcr) {
		this.mcr = mcr;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
