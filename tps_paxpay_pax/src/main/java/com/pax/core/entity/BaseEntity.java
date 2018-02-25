package com.pax.core.entity;

import com.pax.core.util.DateUtils;

public class BaseEntity {
	
	public static final String	STATUS_0	= "0";
	public static final String	STATUS_1	= "1";
	public static final String	STATUS_2	= "2";
	public static final String	STATUS_3	= "3";
	
	private String				status;				//状态  ’0’:未启用 ; ’1’:冻结’; 2’:启用 ; '3':删除
	private String				auditstatus;		//审核状态  ’0’:未审核’; 1’:审核不通过; ’2’:审核通过
	private String				buildoper;			//创建操作员
	private String				builddatetime;		//创建时间
	private String				modifyoper;			//修改操作员
	private String				modifydatetime;		//修改时间
	private String				auditoper;			// 审核操作员
	private String				auditdatetime;		// 审核时间
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAuditstatus() {
		return auditstatus;
	}
	
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	
	public String getBuildoper() {
		return buildoper;
	}
	
	public void setBuildoper(String buildoper) {
		this.buildoper = buildoper;
	}
	
	public String getBuilddatetime() {
		
		if (this.builddatetime != null) {
			return DateUtils.stringDateFormat(this.builddatetime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	
	public String getModifyoper() {
		return modifyoper;
	}
	
	public void setModifyoper(String modifyoper) {
		this.modifyoper = modifyoper;
	}
	
	public String getModifydatetime() {
		
		if (this.modifydatetime != null) {
			return DateUtils.stringDateFormat(this.modifydatetime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	
	public String getAuditoper() {
		return auditoper;
	}
	
	public void setAuditoper(String auditoper) {
		this.auditoper = auditoper;
	}
	
	public String getAuditdatetime() {
		return auditdatetime;
	}
	
	public boolean isStatusNormal() {
		return STATUS_2.equals(this.getStatus());
	}
	
	public void setPassStatus() {
		this.status = STATUS_2;
		this.auditstatus = STATUS_2;
	}
	
	public String getStatusName() {
		
		if (STATUS_0.equals(this.getStatus())) {
			return "未启用";
		} else if (STATUS_1.equals(this.getStatus())) {
			return "冻结";
		} else if (STATUS_2.equals(this.getStatus())) {
			return "启用";
		} else {
			return "已删除";
		}
	}
	
	public String getAstatusName() {
		
		if (STATUS_0.equals(this.getAuditstatus())) {
			return "审核中";
		} else if (STATUS_1.equals(this.getAuditstatus())) {
			return "审核不通过";
		} else {
			return "审核通过";
		}
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
	public void setModifydatetime(String modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	
	public void setAuditdatetime(String auditdatetime) {
		this.auditdatetime = auditdatetime;
	}
	
}
