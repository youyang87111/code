package com.pax.busi.transreport.entity;

import java.io.Serializable;

import com.pax.busi.common.entity.Branch;
import com.pax.busi.common.entity.Inntransdef;
import com.pax.busi.resourcemgr.entity.CposMer;



public class Cposhistrans implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	
	private String				sysid;
	
	private Inntransdef			innid;
	
	private String 				innidName;
	
	private Branch				lbid;
	
	private String				mcr;
	
	private String				mid;
	
	private CposMer				cposMer;
	
	private String				tid;
	
	private String				batchno;
	
	private String				traceno;
	
	private Branch				rbid;
	
	private String				rmid;
	
	private String				rtid;
	
	private String				authno;
	
	private String				refno;
	
	private String				transdate;
	
	private String				transtime;
	
	private String				cardno;
	
	private String				amount;
	
	private String				voidflag;
	
	private String				revflag;
	
	private String 				currency;
	
	private String				notes1;
	private String				notes2;
	
	public String getSysid() {
		return sysid;
	}
	
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	
	public Inntransdef getInnid() {
		return innid;
	}
	
	public void setInnid(Inntransdef innid) {
		this.innid = innid;
	}
	
	
	public String getMcr() {
		return mcr;
	}
	
	public void setMcr(String mcr) {
		this.mcr = mcr;
	}
	
	
	public String getInnidName() {
		return innidName;
	}

	public void setInnidName(String innidName) {
		this.innidName = innidName;
	}

	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getTid() {
		return tid;
	}
	
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getBatchno() {
		return batchno;
	}
	
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	
	public String getTraceno() {
		return traceno;
	}
	
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	
	

	public Branch getLbid() {
		return lbid;
	}

	public void setLbid(Branch lbid) {
		this.lbid = lbid;
	}

	public Branch getRbid() {
		return rbid;
	}

	public void setRbid(Branch rbid) {
		this.rbid = rbid;
	}

	public String getRmid() {
		return rmid;
	}
	
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	
	public String getRtid() {
		return rtid;
	}
	
	public void setRtid(String rtid) {
		this.rtid = rtid;
	}
	
	public String getAuthno() {
		return authno;
	}
	
	public void setAuthno(String authno) {
		this.authno = authno;
	}
	
	public String getRefno() {
		return refno;
	}
	
	public void setRefno(String refno) {
		this.refno = refno;
	}
	
	public String getTransdate() {
		return transdate;
	}
	
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	
	public String getTranstime() {
		return transtime;
	}
	
	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}
	
	public String getCardno() {
		return cardno;
	}
	
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getVoidflag() {
		return voidflag;
	}
	
	public void setVoidflag(String voidflag) {
		this.voidflag = voidflag;
	}
	
	public String getRevflag() {
		return revflag;
	}
	
	public void setRevflag(String revflag) {
		this.revflag = revflag;
	}
	
	public CposMer getCposMer() {
		return cposMer;
	}
	
	public void setCposMer(CposMer cposMer) {
		this.cposMer = cposMer;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getNotes1() {
		return notes1;
	}
	
	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}
	
	public String getNotes2() {
		return notes2;
	}
	
	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}
	
}
