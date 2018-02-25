package com.pax.busi.mapmgr.input;

import org.hibernate.validator.constraints.NotEmpty;

public class BranchMapUpdateInput {
	
	@NotEmpty(message = "id不能为空")
	private String	id;
	
	@NotEmpty(message = "转出渠道不能为空")
	private String	rbid;
	
	private String	modifyoper;		//修改操作员
	private String	modifydatetime;	//修改时间
	
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
	
	public String getRbid() {
		return rbid;
	}
	
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
}
