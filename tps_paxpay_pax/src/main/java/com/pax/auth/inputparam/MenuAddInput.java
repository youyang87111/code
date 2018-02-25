package com.pax.auth.inputparam;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class MenuAddInput {
	
	@NotEmpty(message = "菜单名称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{1,30}$", message = "菜单名称格式不正确")
	private String	name;
	
	@NotEmpty(message = "排序不能为空")
	@Pattern(regexp = "^[1-9]\\d?$", message = "排序格式不正确")
	private String	orderno;
	private String	url;
	private String	img;
	@NotEmpty(message = "上级菜单不能为空")
	private String	pid;
	private String	notes;
	
	private String	createuser;
	private String	createtime;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrderno() {
		return orderno;
	}
	
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
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
