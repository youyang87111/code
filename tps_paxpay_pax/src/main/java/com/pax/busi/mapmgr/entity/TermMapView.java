package com.pax.busi.mapmgr.entity;

import com.pax.core.entity.BaseEntity;

/**
 * 
 *                       
 * @Filename TermMapView.java
 *
 * @Description 对T_B_TERM_MAP_MODE表和T_B_TERM_MAP表做的联合视图
 * 
create or replace view V_B_TERMMAP as 
select lbid,lmcr,lmid,rbid,rmcr,rmid,tmf tmf,' ' ltid,' ' rtid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from T_B_TERM_MAP_MODE where tmf='06' 
union all 
select lbid,lmcr,lmid,rbid,rmcr,rmid,'01' tmf,ltid ltid,rtid rtid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_TERM_MAP
 
 *
 * @Version 1.0
 *
 * @Author pax
 *
 * @Email 
 *       
 * @History
 *<li>Author: pax</li>
 *<li>Date: Mar 3, 2016</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */

public class TermMapView extends BaseEntity {
	
	public static final String	TMF_OTO		= "01";
	public static final String	TMF_SUIJI	= "06";
	
	private String				lbid;
	private String				lbid_name;
	
	private String				lmcr;
	private String				lmid;
	
	private String				rbid;
	private String				rbid_name;
	
	private String				rmcr;
	private String				rmid;
	
	private String				tmf;
	private String				tmf_name;
	
	private String				ltid;
	
	private String				rtid;
	
	private String				builddatetime_short;
	private String				modifydatetime_short;
	private String				auditdatetime_short;
	
	public String getLbid() {
		return lbid;
	}
	
	public void setLbid(String lbid) {
		this.lbid = lbid;
	}
	
	public String getLbid_name() {
		return lbid_name;
	}
	
	public void setLbid_name(String lbid_name) {
		this.lbid_name = lbid_name;
	}
	
	public String getLmcr() {
		return lmcr;
	}
	
	public void setLmcr(String lmcr) {
		this.lmcr = lmcr;
	}
	
	public String getLmid() {
		return lmid;
	}
	
	public void setLmid(String lmid) {
		this.lmid = lmid;
	}
	
	public String getRbid() {
		return rbid;
	}
	
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	
	public String getRbid_name() {
		return rbid_name;
	}
	
	public void setRbid_name(String rbid_name) {
		this.rbid_name = rbid_name;
	}
	
	public String getRmcr() {
		return rmcr;
	}
	
	public void setRmcr(String rmcr) {
		this.rmcr = rmcr;
	}
	
	public String getRmid() {
		return rmid;
	}
	
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	
	public String getTmf() {
		return tmf;
	}
	
	public void setTmf(String tmf) {
		this.tmf = tmf;
	}
	
	public String getTmf_name() {
		return tmf_name;
	}
	
	public void setTmf_name(String tmf_name) {
		this.tmf_name = tmf_name;
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
	
	public String getBuilddatetime_short() {
		return builddatetime_short;
	}
	
	public void setBuilddatetime_short(String builddatetime_short) {
		this.builddatetime_short = builddatetime_short;
	}
	
	public String getModifydatetime_short() {
		return modifydatetime_short;
	}
	
	public void setModifydatetime_short(String modifydatetime_short) {
		this.modifydatetime_short = modifydatetime_short;
	}
	
	public String getAuditdatetime_short() {
		return auditdatetime_short;
	}
	
	public void setAuditdatetime_short(String auditdatetime_short) {
		this.auditdatetime_short = auditdatetime_short;
	}
	
}
