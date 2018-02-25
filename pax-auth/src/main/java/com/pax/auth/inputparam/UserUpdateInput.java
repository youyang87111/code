package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateInput {
	
	@NotEmpty(message = "用户id不能为空")
	private String	id;
	@NotEmpty(message = "用户姓名不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "用户姓名格式不正确")
	private String	name;
	@Pattern(regexp = "^$|^(([a-zA-Z0-9_\\-]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))|$", message = "邮箱格式不正确")
	private String	email;
	@Pattern(regexp = "^$|^0\\d{2,3}-?\\d{7,8}$|^1((((((3[4-9])|(47)|(5[0-27-9])|(78)|(8[2-478])))|(((33)|(53)|(8[019])|(77)))|(((3[0-2])|(45)|(5[56])|(76)|(8[56]))))\\d{8})|(70[057-9]\\d{7}))$", message = "电话格式不正确")
	private String	phoneno;
	private String	notes;
	
	private String	modifyoper;		//修改操作员
	private String	modifydatetime;	//修改时间
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
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
	
}
