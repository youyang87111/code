package com.pax.busi.resourcemgr.input;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CposMerUpdateInput {
	
	@NotEmpty(message = "id不能为空")
	private String	id;
	
	@NotEmpty(message = "商户名称不能为空")
	@Length(max = 30, message = "商户名称最长为30位")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z0-9_（）\\(\\)\\-\\s]{2,30}$", message = "商户名称格式不正确")
	private String	name;
	
	private String	modifyoper;	//修改操作员
	private String	modifydatetime; //修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
