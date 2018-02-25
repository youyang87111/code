package com.pax.busi.mapmgr.entity;

import org.apache.commons.lang.StringUtils;

import com.pax.core.entity.BaseEntity;

/**
 * 
 *                       
 * @Filename BranchMap.java
 *
 * @Description 针对所有渠道映射而创建的一个视图V_B_BRANCHMAP
create or replace view V_B_BRANCHMAP as 
select rid,lbid,lmcr,lmid,class,rbid,'01' bmf,null cardtype,null amt1,null amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from T_B_BRANCHMAP 
union all 
select rid,lbid,lmcr,lmid,class,rbid,'02' bmf,cardtype cardtype,null amt1,null amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_CARDTYPE
union all 
select rid,lbid,lmcr,lmid,class,rbid,'03' bmf,null cardtype,amt1 amt1,amt2 amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_AMT
union all 
select rid,lbid,lmcr,lmid,class,rbid,'04' bmf,cardtype cardtype,amt1 amt1,amt2 amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_CARDTYPE_AMT
union all 
select rid,lbid,lmcr,lmid,class,rbid,'06' bmf,null cardtype,null amt1,null amt2,issuerid issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_ISSUER
union all 
select rid,lbid,lmcr,lmid,class,rbid,'05' bmf,cardtype cardtype,null amt1,null amt2,issuerid issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_ISSUER_CARDTYPE; 
 *
 * @Version 1.0
 *
 * @Author pax
 *
 * @Email 
 *       
 * @History
 *<li>Author: pax</li>
 *<li>Date: Feb 19, 2016</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */

public class BranchMapView extends BaseEntity {
	
	public static final String	BMF_OTO				= "01";
	public static final String	BMF_CARDTYPE		= "02";
	public static final String	BMF_AMT				= "03";
	public static final String	BMF_CARDTYPEAMT		= "04";
	public static final String	BMF_ISSUER_CARDTYPE	= "05";
	public static final String	BMF_ISSUER			= "06";
	
	private String				rid;
	
	private String				lbid;
	private String				lbid_name;
	
	private String				lmcr;
	private String				lmid;
	
	private String				cls;
	private String				cls_name;
	
	private String				rbid;
	private String				rbid_name;
	
	private String				bmf;
	private String				bmf_name;
	
	private String				cardtype;
	private String				cardtype_name;
	
	private String				amt1;
	private String				amt2;
	
	private String				issuerid;
	private String				issuerid_name;
	
	private String				builddatetime_short;
	private String				modifydatetime_short;
	private String				auditdatetime_short;
	
	public String getRid() {
		return rid;
	}
	
	public void setRid(String rid) {
		this.rid = rid;
	}
	
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
	
	public String getCls() {
		return cls;
	}
	
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	public String getCls_name() {
		return cls_name;
	}
	
	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
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
	
	public String getBmf() {
		return bmf;
	}
	
	public void setBmf(String bmf) {
		this.bmf = bmf;
	}
	
	public String getBmf_name() {
		return bmf_name;
	}
	
	public void setBmf_name(String bmf_name) {
		this.bmf_name = bmf_name;
	}
	
	public String getCardtype() {
		return cardtype;
	}
	
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	public String getCardtype_name() {
		return cardtype_name;
	}
	
	public void setCardtype_name(String cardtype_name) {
		this.cardtype_name = cardtype_name;
	}
	
	public String getIssuerid() {
		return issuerid;
	}
	
	public void setIssuerid(String issuerid) {
		this.issuerid = issuerid;
	}
	
	public String getIssuerid_name() {
		return issuerid_name;
	}
	
	public void setIssuerid_name(String issuerid_name) {
		this.issuerid_name = issuerid_name;
	}
	
	public String getAmt1() {
		if (StringUtils.isNotBlank(amt1)) {
			return String.valueOf(Double.valueOf(amt1).intValue());
		} else {
			return amt1;
		}
		
	}
	
	public void setAmt1(String amt1) {
		if (StringUtils.isNotBlank(amt1)) {
			this.amt1 = String.valueOf(Double.valueOf(amt1).intValue());
		} else {
			this.amt1 = amt1;
		}
		
	}
	
	public String getAmt2() {
		if (StringUtils.isNotBlank(amt2)) {
			return String.valueOf(Double.valueOf(amt2).intValue());
		} else {
			return amt2;
		}
		
	}
	
	public void setAmt2(String amt2) {
		if (StringUtils.isNotBlank(amt2)) {
			this.amt2 = String.valueOf(Double.valueOf(amt2).intValue());
		} else {
			this.amt2 = amt2;
		}
		
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
