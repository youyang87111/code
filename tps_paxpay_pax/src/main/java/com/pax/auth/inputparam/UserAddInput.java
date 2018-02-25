package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserAddInput {
	
	@NotEmpty(message = "用户姓名不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "用户姓名格式不正确")
	private String	name;
	
	@NotEmpty(message = "登录名不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{4,20}$", message = "登录名格式不正确")
	private String	loginname;
	
	@NotEmpty(message = "密码不能为空")
	private String	password;
	
	@Pattern(regexp = "^$|^(([a-zA-Z0-9_\\-]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))|$", message = "邮箱格式不正确")
	private String	email;
	
	@Pattern(regexp = "^$|^0\\d{2,3}-?\\d{7,8}$|^1((((((3[4-9])|(47)|(5[0-27-9])|(78)|(8[2-478])))|(((33)|(53)|(8[019])|(77)))|(((3[0-2])|(45)|(5[56])|(76)|(8[56]))))\\d{8})|(70[057-9]\\d{7}))$", message = "电话格式不正确")
	private String	phoneno;
	
	@NotEmpty(message = "机构不能为空")
	private String	org_id;
	
	@NotEmpty(message = "站点不能为空")
	private String	site_id;
	
	private String	notes;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneno() {
		return phoneno;
	}
	
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String getOrg_id() {
		return org_id;
	}
	
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getSite_id() {
		return site_id;
	}
	
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	
}
