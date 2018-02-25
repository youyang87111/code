package com.pax.busi.mapmgr.input;

import org.hibernate.validator.constraints.NotEmpty;

public class TermMapAddInput {
	
	@NotEmpty(message = "接入渠道不能为空")
	private String	lbid;
	@NotEmpty(message = "接入商户号不能为空")
	private String	lmid;
	@NotEmpty(message = "映射模式不能为空")
	private String	tmf;
	@NotEmpty(message = "转出渠道不能为空")
	private String	rbid;
	@NotEmpty(message = "转出商户号不能为空")
	private String	rmid;
	
	private String	ltid;
	private String	rtid;
	
	private String	buildoper;		//创建操作员
	private String	builddatetime;	//创建时间
	
	public String getLbid() {
		return lbid;
	}
	
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	
	public String getLmid() {
		return lmid;
	}
	
	public void setLmid(String lmid) {
		this.lmid = lmid;
	}
	
	public String getTmf() {
		return tmf;
	}
	
	public void setTmf(String tmf) {
		this.tmf = tmf;
	}
	
	public String getRbid() {
		return rbid;
	}
	
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	
	public String getRmid() {
		return rmid;
	}
	
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	
	public String getLtid() {
		return ltid;
	}
	
	public void setLtid(String ltid) {
		this.ltid = ltid;
	}
	
	public String getRtid() {
		return rtid;
	}
	
	public void setRtid(String rtid) {
		this.rtid = rtid;
	}
	
	public String getBuildoper() {
		return buildoper;
	}
	
	public void setBuildoper(String buildoper) {
		this.buildoper = buildoper;
	}
	
	public String getBuilddatetime() {
		return builddatetime;
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
}
