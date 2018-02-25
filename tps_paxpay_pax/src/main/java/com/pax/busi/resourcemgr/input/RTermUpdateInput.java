package com.pax.busi.resourcemgr.input;

import org.hibernate.validator.constraints.NotEmpty;

public class RTermUpdateInput {
	
	@NotEmpty(message = "id不能为空")
	private String	id;
	
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
	
}
