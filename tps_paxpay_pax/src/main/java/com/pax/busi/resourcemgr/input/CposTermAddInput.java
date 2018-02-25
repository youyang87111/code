package com.pax.busi.resourcemgr.input;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class CposTermAddInput {
	
	@NotEmpty(message = "商户来源不能为空")
	private String	mcr;
	
	@NotEmpty(message = "商户号不能为空")
	private String	mid;
	
	@NotEmpty(message = "终端号不能为空")
	@Pattern(regexp = "^[A-Za-z0-9]{8}$", message = "终端号格式不正确")
	private String	tid;
	
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
	
	public String getTid() {
		return tid;
	}
	
	public void setTid(String tid) {
		this.tid = tid;
	}
	
}
