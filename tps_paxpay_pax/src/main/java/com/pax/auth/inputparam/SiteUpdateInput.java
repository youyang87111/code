package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class SiteUpdateInput {
	
	@NotEmpty(message = "站点id不能为空")
	private String	id;
	@NotEmpty(message = "站点名称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "站点名称格式不正确")
	private String	name;
	@NotEmpty(message = "首页不能为空")
	private String	url;
	
	private String	modifyoper;		//修改操作员
	private String	modifydatetime;	//修改时间
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getModifyoper() {
		return modifyoper;
	}
	
	public void setModifyoper(String modifyoper) {
		this.modifyoper = modifyoper;
	}
	
	public String getModifydatetime() {
		return modifydatetime;
	}
	
	public void setModifydatetime(String modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
