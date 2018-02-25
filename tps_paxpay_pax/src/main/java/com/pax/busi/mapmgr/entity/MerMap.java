package com.pax.busi.mapmgr.entity;

import org.apache.commons.lang.StringUtils;

import com.pax.core.entity.BaseEntity;

public class MerMap extends BaseEntity {
	
	public static final String	MMF_OTO				= "01";
	public static final String	MMF_CARDTYPE		= "02";
	public static final String	MMF_AMT				= "03";
	public static final String	MMF_CARDTYPEAMT		= "04";
	public static final String	MMF_ISSUER_CARDTYPE	= "05";
	
	private String				lbid;
	private String				lbid_name;
	
	private String				lmcr;
	private String				lmid;
	
	private String				cls;
	private String				cls_name;
	
	private String				rbid;
	private String				rbid_name;
	
	private String				rmcr;
	
	private String				mmf;
	private String				mmf_name;
	
	private String				rmid;
	
	private String				rmid1;
	private String				rmid2;
	private String				rmid3;
	private String				rmid4;
	private String				rmid5;
	
	private String				issuerid1;
	private String				issuerid1_name;
	private String				issuerid2;
	private String				issuerid2_name;
	private String				issuerid3;
	private String				issuerid3_name;
	private String				issuerid4;
	private String				issuerid4_name;
	private String				issuerid5;
	private String				issuerid5_name;
	
	private String				cardtype1;
	private String				cardtype1_name;
	private String				cardtype2;
	private String				cardtype2_name;
	private String				cardtype3;
	private String				cardtype3_name;
	private String				cardtype4;
	private String				cardtype4_name;
	private String				cardtype5;
	private String				cardtype5_name;
	
	private String				amt11;
	private String				amt12;
	private String				amt21;
	private String				amt22;
	private String				amt31;
	private String				amt32;
	private String				amt41;
	private String				amt42;
	private String				amt51;
	private String				amt52;
	
	private String				builddatetime_short;
	private String				modifydatetime_short;
	private String				auditdatetime_short;
	
	public String getLbid() {
		return lbid;
	}
	
	public void setLbid(String lbid) {
		this.lbid = lbid;
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
	
	public String getRbid() {
		return rbid;
	}
	
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	
	public String getRmcr() {
		return rmcr;
	}
	
	public void setRmcr(String rmcr) {
		this.rmcr = rmcr;
	}
	
	public String getMmf() {
		return mmf;
	}
	
	public void setMmf(String mmf) {
		this.mmf = mmf;
	}
	
	public String getLbid_name() {
		return lbid_name;
	}
	
	public void setLbid_name(String lbid_name) {
		this.lbid_name = lbid_name;
	}
	
	public String getCls_name() {
		return cls_name;
	}
	
	public void setCls_name(String cls_name) {
		this.cls_name = cls_name;
	}
	
	public String getRbid_name() {
		return rbid_name;
	}
	
	public void setRbid_name(String rbid_name) {
		this.rbid_name = rbid_name;
	}
	
	public String getMmf_name() {
		return mmf_name;
	}
	
	public void setMmf_name(String mmf_name) {
		this.mmf_name = mmf_name;
	}
	
	public String getRmid() {
		return rmid;
	}
	
	public void setRmid(String rmid) {
		this.rmid = rmid;
	}
	
	public String getRmid1() {
		return rmid1;
	}
	
	public void setRmid1(String rmid1) {
		this.rmid1 = rmid1;
	}
	
	public String getRmid2() {
		return rmid2;
	}
	
	public void setRmid2(String rmid2) {
		this.rmid2 = rmid2;
	}
	
	public String getRmid3() {
		return rmid3;
	}
	
	public void setRmid3(String rmid3) {
		this.rmid3 = rmid3;
	}
	
	public String getRmid4() {
		return rmid4;
	}
	
	public void setRmid4(String rmid4) {
		this.rmid4 = rmid4;
	}
	
	public String getRmid5() {
		return rmid5;
	}
	
	public void setRmid5(String rmid5) {
		this.rmid5 = rmid5;
	}
	
	public String getIssuerid1() {
		return issuerid1;
	}
	
	public void setIssuerid1(String issuerid1) {
		this.issuerid1 = issuerid1;
	}
	
	public String getIssuerid1_name() {
		return issuerid1_name;
	}
	
	public void setIssuerid1_name(String issuerid1_name) {
		this.issuerid1_name = issuerid1_name;
	}
	
	public String getIssuerid2() {
		return issuerid2;
	}
	
	public void setIssuerid2(String issuerid2) {
		this.issuerid2 = issuerid2;
	}
	
	public String getIssuerid2_name() {
		return issuerid2_name;
	}
	
	public void setIssuerid2_name(String issuerid2_name) {
		this.issuerid2_name = issuerid2_name;
	}
	
	public String getIssuerid3() {
		return issuerid3;
	}
	
	public void setIssuerid3(String issuerid3) {
		this.issuerid3 = issuerid3;
	}
	
	public String getIssuerid3_name() {
		return issuerid3_name;
	}
	
	public void setIssuerid3_name(String issuerid3_name) {
		this.issuerid3_name = issuerid3_name;
	}
	
	public String getIssuerid4() {
		return issuerid4;
	}
	
	public void setIssuerid4(String issuerid4) {
		this.issuerid4 = issuerid4;
	}
	
	public String getIssuerid4_name() {
		return issuerid4_name;
	}
	
	public void setIssuerid4_name(String issuerid4_name) {
		this.issuerid4_name = issuerid4_name;
	}
	
	public String getIssuerid5() {
		return issuerid5;
	}
	
	public void setIssuerid5(String issuerid5) {
		this.issuerid5 = issuerid5;
	}
	
	public String getIssuerid5_name() {
		return issuerid5_name;
	}
	
	public void setIssuerid5_name(String issuerid5_name) {
		this.issuerid5_name = issuerid5_name;
	}
	
	public String getCardtype1() {
		return cardtype1;
	}
	
	public void setCardtype1(String cardtype1) {
		this.cardtype1 = cardtype1;
	}
	
	public String getCardtype1_name() {
		return cardtype1_name;
	}
	
	public void setCardtype1_name(String cardtype1_name) {
		this.cardtype1_name = cardtype1_name;
	}
	
	public String getCardtype2() {
		return cardtype2;
	}
	
	public void setCardtype2(String cardtype2) {
		this.cardtype2 = cardtype2;
	}
	
	public String getCardtype2_name() {
		return cardtype2_name;
	}
	
	public void setCardtype2_name(String cardtype2_name) {
		this.cardtype2_name = cardtype2_name;
	}
	
	public String getCardtype3() {
		return cardtype3;
	}
	
	public void setCardtype3(String cardtype3) {
		this.cardtype3 = cardtype3;
	}
	
	public String getCardtype3_name() {
		return cardtype3_name;
	}
	
	public void setCardtype3_name(String cardtype3_name) {
		this.cardtype3_name = cardtype3_name;
	}
	
	public String getCardtype4() {
		return cardtype4;
	}
	
	public void setCardtype4(String cardtype4) {
		this.cardtype4 = cardtype4;
	}
	
	public String getCardtype4_name() {
		return cardtype4_name;
	}
	
	public void setCardtype4_name(String cardtype4_name) {
		this.cardtype4_name = cardtype4_name;
	}
	
	public String getCardtype5() {
		return cardtype5;
	}
	
	public void setCardtype5(String cardtype5) {
		this.cardtype5 = cardtype5;
	}
	
	public String getCardtype5_name() {
		return cardtype5_name;
	}
	
	public void setCardtype5_name(String cardtype5_name) {
		this.cardtype5_name = cardtype5_name;
	}
	
	public String getAmt11() {
		if (StringUtils.isNotBlank(amt11)) {
			return String.valueOf(Double.valueOf(amt11).intValue());
		} else {
			return amt11;
		}
	}
	
	public void setAmt11(String amt11) {
		this.amt11 = amt11;
	}
	
	public String getAmt12() {
		if (StringUtils.isNotBlank(amt12)) {
			return String.valueOf(Double.valueOf(amt12).intValue());
		} else {
			return amt12;
		}
	}
	
	public void setAmt12(String amt12) {
		this.amt12 = amt12;
	}
	
	public String getAmt21() {
		if (StringUtils.isNotBlank(amt21)) {
			return String.valueOf(Double.valueOf(amt21).intValue());
		} else {
			return amt21;
		}
	}
	
	public void setAmt21(String amt21) {
		this.amt21 = amt21;
	}
	
	public String getAmt22() {
		if (StringUtils.isNotBlank(amt22)) {
			return String.valueOf(Double.valueOf(amt22).intValue());
		} else {
			return amt22;
		}
	}
	
	public void setAmt22(String amt22) {
		this.amt22 = amt22;
	}
	
	public String getAmt31() {
		if (StringUtils.isNotBlank(amt31)) {
			return String.valueOf(Double.valueOf(amt31).intValue());
		} else {
			return amt31;
		}
	}
	
	public void setAmt31(String amt31) {
		this.amt31 = amt31;
	}
	
	public String getAmt32() {
		if (StringUtils.isNotBlank(amt32)) {
			return String.valueOf(Double.valueOf(amt32).intValue());
		} else {
			return amt32;
		}
	}
	
	public void setAmt32(String amt32) {
		this.amt32 = amt32;
	}
	
	public String getAmt41() {
		if (StringUtils.isNotBlank(amt41)) {
			return String.valueOf(Double.valueOf(amt41).intValue());
		} else {
			return amt41;
		}
	}
	
	public void setAmt41(String amt41) {
		this.amt41 = amt41;
	}
	
	public String getAmt42() {
		if (StringUtils.isNotBlank(amt42)) {
			return String.valueOf(Double.valueOf(amt42).intValue());
		} else {
			return amt42;
		}
	}
	
	public void setAmt42(String amt42) {
		this.amt42 = amt42;
	}
	
	public String getAmt51() {
		if (StringUtils.isNotBlank(amt51)) {
			return String.valueOf(Double.valueOf(amt51).intValue());
		} else {
			return amt51;
		}
	}
	
	public void setAmt51(String amt51) {
		this.amt51 = amt51;
	}
	
	public String getAmt52() {
		if (StringUtils.isNotBlank(amt52)) {
			return String.valueOf(Double.valueOf(amt52).intValue());
		} else {
			return amt52;
		}
	}
	
	public void setAmt52(String amt52) {
		this.amt52 = amt52;
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
