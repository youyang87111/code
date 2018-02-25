package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class FunctionUpdateInput {
	
	@NotEmpty(message = "功能id不能为空")
	private String	id;
	@NotEmpty(message = "功能名称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "功能名称格式不正确")
	private String	name;
	private String	url;
	private String	img;
	private String	menu_id;
	private String	notes;
	
	private String	modifyoper;		//修改操作员
	private String	modifydatetime;	//修改时间
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMenu_id() {
		return menu_id;
	}
	
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
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
