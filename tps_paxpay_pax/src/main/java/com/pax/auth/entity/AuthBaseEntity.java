package com.pax.auth.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.pax.core.util.DateUtils;

/**
 * 权限相关的实体所共有的字段
 *                       
 * @Filename AuthBaseEntity.java
 *
 * @Description 
 *
 * @Version 1.0
 *
 * @Author pax
 *
 * @Email 
 *       
 * @History
 *<li>Author: pax</li>
 *<li>Date: 2017年4月27日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class AuthBaseEntity implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= -7125374170885519127L;
	
	private String				buildoper;									//创建操作员
	private String				builddatetime;								//创建时间
	private String				modifyoper;									//修改操作员
	private String				modifydatetime;								//修改时间
	private String				notes;
	
	public String getBuildoper() {
		return buildoper;
	}
	
	public void setBuildoper(String buildoper) {
		this.buildoper = buildoper;
	}
	
	public String getBuilddatetime() {
		if (StringUtils.isNotBlank(this.builddatetime)) {
			return DateUtils.stringDateFormat(this.builddatetime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
	public String getModifyoper() {
		return modifyoper;
	}
	
	public void setModifyoper(String modifyoper) {
		this.modifyoper = modifyoper;
	}
	
	public String getModifydatetime() {
		if (StringUtils.isNotBlank(this.modifydatetime)) {
			return DateUtils.stringDateFormat(this.modifydatetime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	
	public void setModifydatetime(String modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
