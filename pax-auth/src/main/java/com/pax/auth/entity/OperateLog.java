package com.pax.auth.entity;

import java.util.ArrayList;
import java.util.List;

public class OperateLog {
	
	private int						id;
	//操作时间
	private String					operatetime;
	//操作人员
	private String					operator;
	//操作名称
	private String					name;
	//操作结果描述
	private String					description;
	
	//操作结果 0：成功；1：失败
	private String					flag;
	
	private List<OperateLogParam>	params	= new ArrayList<OperateLogParam>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOperatetime() {
		return operatetime;
	}
	
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public List<OperateLogParam> getParams() {
		return params;
	}
	
	public void setParams(List<OperateLogParam> params) {
		this.params = params;
	}
	
}
