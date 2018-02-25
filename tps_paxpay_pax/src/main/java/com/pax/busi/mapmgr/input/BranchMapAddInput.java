package com.pax.busi.mapmgr.input;

import org.hibernate.validator.constraints.NotEmpty;

public class BranchMapAddInput {
	
	@NotEmpty(message = "接入渠道不能为空")
	private String	lbid;
	private String	merPersonalized;
	private String	lmid;
	@NotEmpty(message = "交易类别不能为空")
	private String	cls;
	@NotEmpty(message = "映射模式不能为空")
	private String	bmf;
	
	private String	rbid_01;
	private String	rbid_02;
	private String	rbid_03;
	private String	rbid_04;
	private String	rbid_05;
	private String	rbid_06;
	
	private String	cardType_01;
	private String	cardType_02;
	private String	cardType_03;
	private String	cardType_04;
	private String	cardType_05;
	
	private String	amt1_01;
	private String	amt1_02;
	private String	amt1_03;
	private String	amt1_04;
	private String	amt1_05;
	
	private String	amt2_01;
	private String	amt2_02;
	private String	amt2_03;
	private String	amt2_04;
	private String	amt2_05;
	
	private String	issuerid_01;
	private String	issuerid_02;
	private String	issuerid_03;
	private String	issuerid_04;
	private String	issuerid_05;
	private String	issuerid_06;
	
	private String	buildoper;			//创建操作员
	private String	builddatetime;		//创建时间
										
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
	
	public String getCls() {
		return cls;
	}
	
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	public String getBmf() {
		return bmf;
	}
	
	public void setBmf(String bmf) {
		this.bmf = bmf;
	}
	
	public String getRbid_01() {
		return rbid_01;
	}
	
	public void setRbid_01(String rbid_01) {
		this.rbid_01 = rbid_01;
	}
	
	public String getRbid_02() {
		return rbid_02;
	}
	
	public void setRbid_02(String rbid_02) {
		this.rbid_02 = rbid_02;
	}
	
	public String getRbid_03() {
		return rbid_03;
	}
	
	public void setRbid_03(String rbid_03) {
		this.rbid_03 = rbid_03;
	}
	
	public String getRbid_04() {
		return rbid_04;
	}
	
	public void setRbid_04(String rbid_04) {
		this.rbid_04 = rbid_04;
	}
	
	public String getRbid_05() {
		return rbid_05;
	}
	
	public void setRbid_05(String rbid_05) {
		this.rbid_05 = rbid_05;
	}
	
	public String getCardType_01() {
		return cardType_01;
	}
	
	public void setCardType_01(String cardType_01) {
		this.cardType_01 = cardType_01;
	}
	
	public String getCardType_02() {
		return cardType_02;
	}
	
	public void setCardType_02(String cardType_02) {
		this.cardType_02 = cardType_02;
	}
	
	public String getCardType_03() {
		return cardType_03;
	}
	
	public void setCardType_03(String cardType_03) {
		this.cardType_03 = cardType_03;
	}
	
	public String getCardType_04() {
		return cardType_04;
	}
	
	public void setCardType_04(String cardType_04) {
		this.cardType_04 = cardType_04;
	}
	
	public String getCardType_05() {
		return cardType_05;
	}
	
	public void setCardType_05(String cardType_05) {
		this.cardType_05 = cardType_05;
	}
	
	public String getAmt1_01() {
		return amt1_01;
	}
	
	public void setAmt1_01(String amt1_01) {
		this.amt1_01 = amt1_01;
	}
	
	public String getAmt1_02() {
		return amt1_02;
	}
	
	public void setAmt1_02(String amt1_02) {
		this.amt1_02 = amt1_02;
	}
	
	public String getAmt1_03() {
		return amt1_03;
	}
	
	public void setAmt1_03(String amt1_03) {
		this.amt1_03 = amt1_03;
	}
	
	public String getAmt1_04() {
		return amt1_04;
	}
	
	public void setAmt1_04(String amt1_04) {
		this.amt1_04 = amt1_04;
	}
	
	public String getAmt1_05() {
		return amt1_05;
	}
	
	public void setAmt1_05(String amt1_05) {
		this.amt1_05 = amt1_05;
	}
	
	public String getAmt2_01() {
		return amt2_01;
	}
	
	public void setAmt2_01(String amt2_01) {
		this.amt2_01 = amt2_01;
	}
	
	public String getAmt2_02() {
		return amt2_02;
	}
	
	public void setAmt2_02(String amt2_02) {
		this.amt2_02 = amt2_02;
	}
	
	public String getAmt2_03() {
		return amt2_03;
	}
	
	public void setAmt2_03(String amt2_03) {
		this.amt2_03 = amt2_03;
	}
	
	public String getAmt2_04() {
		return amt2_04;
	}
	
	public void setAmt2_04(String amt2_04) {
		this.amt2_04 = amt2_04;
	}
	
	public String getAmt2_05() {
		return amt2_05;
	}
	
	public void setAmt2_05(String amt2_05) {
		this.amt2_05 = amt2_05;
	}
	
	public String getIssuerid_01() {
		return issuerid_01;
	}
	
	public void setIssuerid_01(String issuerid_01) {
		this.issuerid_01 = issuerid_01;
	}
	
	public String getIssuerid_02() {
		return issuerid_02;
	}
	
	public void setIssuerid_02(String issuerid_02) {
		this.issuerid_02 = issuerid_02;
	}
	
	public String getIssuerid_03() {
		return issuerid_03;
	}
	
	public void setIssuerid_03(String issuerid_03) {
		this.issuerid_03 = issuerid_03;
	}
	
	public String getIssuerid_04() {
		return issuerid_04;
	}
	
	public void setIssuerid_04(String issuerid_04) {
		this.issuerid_04 = issuerid_04;
	}
	
	public String getIssuerid_05() {
		return issuerid_05;
	}
	
	public void setIssuerid_05(String issuerid_05) {
		this.issuerid_05 = issuerid_05;
	}
	
	public String getRbid_06() {
		return rbid_06;
	}
	
	public void setRbid_06(String rbid_06) {
		this.rbid_06 = rbid_06;
	}
	
	public String getIssuerid_06() {
		return issuerid_06;
	}
	
	public void setIssuerid_06(String issuerid_06) {
		this.issuerid_06 = issuerid_06;
	}
	
	public String getMerPersonalized() {
		return merPersonalized;
	}
	
	public void setMerPersonalized(String merPersonalized) {
		this.merPersonalized = merPersonalized;
	}
	
}
