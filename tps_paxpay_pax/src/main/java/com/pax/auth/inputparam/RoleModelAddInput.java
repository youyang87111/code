package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class RoleModelAddInput {
	
	@NotEmpty(message = "站点不能为空")
	private String	site_id;
	@NotEmpty(message = "角色名称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "角色名称格式不正确")
	private String	name;
	@NotEmpty(message = "标识不能为空")
	@Pattern(regexp = "^[A-Z]{1}$", message = "标识格式不正确")
	private String	tag;
	private String	notes;
	
	private String	createuser;
	private String	createtime;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSite_id() {
		return site_id;
	}
	
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getCreateuser() {
		return createuser;
	}
	
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
