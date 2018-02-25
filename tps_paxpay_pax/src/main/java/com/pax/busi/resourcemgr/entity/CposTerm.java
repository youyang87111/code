package com.pax.busi.resourcemgr.entity;


import org.apache.commons.lang.StringUtils;

import com.pax.core.entity.BaseEntity;


/************************************************************************
 * @Package: com.paxsz.cpos.pojo
 * @FileName: Term.java
 * @Author: xiajinsong
 * @Version: V1.0
 * @Date: 2015-12-8
 * @Description: Cpos系统自己管理的终端资料
 *************************************************************************/
public class CposTerm extends BaseEntity{
	
	// 商户号归属的应用类型
	private CposMer 			mer;
	private String 				tid;
	
	private String mcrName;
	private String orgName;
	private String	builddatetime_short;
	private String	modifydatetime_short;
	private String	auditdatetime_short;
	
//	private Organization        org;
	
//	private String				appName;
	
	private String				serialno;
	
	private String				notes;
	
	private String 				depid;
	
//	private String				sn2;
	
	private String				tmk;
	
	private String				lmkTmk;
	
	private String				gentmk				= "1";
	
	private String				batchno				= "1";
	
	private String				resver;
	
	private String				ctrl;
	private String				menu;
	private String				tran;
	private String				dict;
	private String				oper;
	private String				rcpt;
	private String				scrt;
	private String				logo;
	private String				note;
	private String				list;
	private String				indx;
	
	private String				position;
	
	private String				tel;
	
	private String				traceno				= "1";

	private String pk;
	
	public CposTerm() {
	}
	
//	public CposTerm(String mcr, String appName, String mid, String tid, String mername,
//					String deptName) {
//		this.mcr = mcr;
//		this.appName = appName;
//		super.setMid(mid);
//		super.setTid(tid);
//		super.setMername(mername);
//		this.setDeptName(deptName);
//	}
	
//	public CposTerm(String mcr, String appName, String mid, String tid, String mername,
//					String status, String auditstatus, String deptName, String tmk, String depId) {
//		this.mcr = mcr;
//		this.appName = appName;
//		super.deptName = deptName;
//		this.tmk = tmk;
//		this.depId = depId;
//		super.setMid(mid);
//		super.setTid(tid);
//		super.setMername(mername);
//		super.setStatus(status);
//		super.setAuditstatus(auditstatus);
//	}
	
//	public CposTerm(String mcr, String appName, String mid, String tid, String mername,
//					String status, String auditstatus, String deptName, String tmk, String depId,
//					String batchno, String resver, String traceno) {
//		this.mcr = mcr;
//		this.appName = appName;
//		super.deptName = deptName;
//		this.tmk = tmk;
//		this.depId = depId;
//		this.traceno = StringUtils.leftPad(traceno, 6, "0");
//		this.batchno = StringUtils.leftPad(batchno, 6, "0");
//		setResver(resver);
//		super.setMid(mid);
//		super.setTid(tid);
//		super.setMername(mername);
//		super.setStatus(status);
//		super.setAuditstatus(auditstatus);
//	}
	
//	public CposTerm(String mcr, String appName, String mid, String tid, String mername,
//					String status, String auditstatus, String deptName, String tmk, String depId,
//					String batchno, String resver, String traceno, String gentmk) {
//		this(mcr, appName, mid, tid, mername, status, auditstatus, deptName, tmk, depId, batchno,
//			resver, traceno);
//		this.gentmk = gentmk;
//	}
	
//	public CposTerm(String mcr, String appName, String mid, String tid, String mername,
//					String status, String buildoper, String builddatetime, String modifyoper,
//					String modifydatetime, String serialno, String auditstatus, String depId,
//					String tmk, String batchno, String resver, String traceno) {
//		super(mid, tid, mername, status, buildoper, builddatetime, modifyoper, modifydatetime,
//			auditstatus);
//		this.mcr = mcr;
//		this.serialno = serialno;
//		this.appName = appName;
//		this.tmk = tmk;
//		super.setDepId(depId);
//		this.traceno = StringUtils.leftPad(traceno, 6, "0");
//		this.batchno = StringUtils.leftPad(batchno, 6, "0");
//		setResver(resver);
//	}
	
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

	public String getPk() {
		if (pk == null || StringUtils.isEmpty(pk))
			pk = this.getMer().getMcr() + getMer().getMid() + getTid();
		return pk;
	}
	
	public void setPk(String pk) {
		this.pk = pk;
		this.getMer().setMcr(pk.substring(0, 4));
		this.getMer().setMid(pk.substring(4, 19));
		this.tid = pk.substring(19);
	}
	
//	public String getSn2() {
//		return sn2;
//	}
//	
//	public void setSn2(String sn2) {
//		this.sn2 = sn2;
//	}
	
	public String getSerialno() {
		return serialno;
	}
	
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
//	public Mcr getMcr() {
//		return mcr;
//	}
//	
//	public void setMcr(Mcr mcr) {
//		this.mcr = mcr;
//	}
	
//	public String getAppName() {
//		return appName;
//	}
//	
//	public void setAppName(String appName) {
//		this.appName = appName;
//	}
	
	public String getTmk() {
		return tmk;
	}
	
	public void setTmk(String tmk) {
		this.tmk = tmk;
	}
	
	
	public String getLmkTmk() {
		return lmkTmk;
	}
	
	public void setLmkTmk(String lmkTmk) {
		this.lmkTmk = lmkTmk;
	}
	
	public String getGentmk() {
		return gentmk;
	}
	
	public String getGentmkName() {
		
//		Locale locale = ActionContext.getContext().getLocale();
//		
//		ResourceBundleUtils resUtils = new ResourceBundleUtils(locale);
//		
//		if ("1".equals(this.getGentmk())) {
//			return resUtils.getStringMessage("CposTerm_key1");
//		} else {
//			return resUtils.getStringMessage("CposTerm_key2");
//		}
		// TODO
		return null;
	}
	
	public void setGentmk(String gentmk) {
		this.gentmk = gentmk;
	}
	
	public String getBatchno() {
		return batchno;
	}
	
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
	public String getResver() {
		return resver;
	}
	
	public void setResver(String resver) {
		this.resver = resver;
		try {
			this.ctrl = resver.substring(3, 7);
			this.menu = resver.substring(10, 14);
			this.tran = resver.substring(17, 21);
			this.dict = resver.substring(24, 28);
			this.oper = resver.substring(31, 35);
			this.rcpt = resver.substring(38, 42);
			this.scrt = resver.substring(45, 49);
			this.logo = resver.substring(52, 56);
			this.note = resver.substring(59, 63);
			this.list = resver.substring(66, 70);
			this.indx = resver.substring(73, 77);
		} catch (Exception e) {
			
		}
		
	}
	
	public String getCtrl() {
		return ctrl;
	}
	
	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}
	
	public String getMenu() {
		return menu;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public String getTran() {
		return tran;
	}
	
	public void setTran(String tran) {
		this.tran = tran;
	}
	
	public String getDict() {
		return dict;
	}
	
	public void setDict(String dict) {
		this.dict = dict;
	}
	
	public String getOper() {
		return oper;
	}
	
	public void setOper(String oper) {
		this.oper = oper;
	}
	
	public String getRcpt() {
		return rcpt;
	}
	
	public void setRcpt(String rcpt) {
		this.rcpt = rcpt;
	}
	
	public String getScrt() {
		return scrt;
	}
	
	public void setScrt(String scrt) {
		this.scrt = scrt;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getList() {
		return list;
	}
	
	public void setList(String list) {
		this.list = list;
	}
	
	public String getIndx() {
		return indx;
	}
	
	public void setIndx(String indx) {
		this.indx = indx;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getTraceno() {
		return traceno;
	}
	
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public CposMer getMer() {
		return mer;
	}

	public void setMer(CposMer mer) {
		this.mer = mer;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
		this.mer.setDepid(depid);
	}

	public String getMcrName() {
		return mcrName;
	}

	public void setMcrName(String mcrName) {
		this.mcrName = mcrName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mer == null) ? 0 : mer.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CposTerm other = (CposTerm) obj;
		if (mer == null) {
			if (other.mer != null)
				return false;
		} else if (!mer.equals(other.mer))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}

//	public Organization getOrg() {
//		return org;
//	}
//
//	public void setOrg(Organization org) {
//		this.org = org;
//	}
	
	
	
}
