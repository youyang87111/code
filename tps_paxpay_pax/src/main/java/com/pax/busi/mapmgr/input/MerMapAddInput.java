package com.pax.busi.mapmgr.input;

import org.hibernate.validator.constraints.NotEmpty;

public class MerMapAddInput {
	
	@NotEmpty(message = "接入渠道不能为空")
	private String	lbid;
	@NotEmpty(message = "接入商户号不能为空")
	private String	lmid;
	@NotEmpty(message = "交易类别不能为空")
	private String	cls;
	@NotEmpty(message = "映射模式不能为空")
	private String	mmf;
	@NotEmpty(message = "转出渠道不能为空")
	private String	rbid;
	
	private String	rmid;
	
	private String	c_cardType1;
	private String	c_cardType2;
	private String	c_cardType3;
	private String	c_cardType4;
	private String	c_cardType5;
	private String	c_rmid1;
	private String	c_rmid2;
	private String	c_rmid3;
	private String	c_rmid4;
	private String	c_rmid5;
	
	private String	a_amt11;
	private String	a_amt12;
	private String	a_amt21;
	private String	a_amt22;
	private String	a_amt31;
	private String	a_amt32;
	private String	a_amt41;
	private String	a_amt42;
	private String	a_amt51;
	private String	a_amt52;
	private String	a_rmid1;
	private String	a_rmid2;
	private String	a_rmid3;
	private String	a_rmid4;
	private String	a_rmid5;
	
	private String	ca_cardType1;
	private String	ca_cardType2;
	private String	ca_cardType3;
	private String	ca_cardType4;
	private String	ca_cardType5;
	private String	ca_amt11;
	private String	ca_amt12;
	private String	ca_amt21;
	private String	ca_amt22;
	private String	ca_amt31;
	private String	ca_amt32;
	private String	ca_amt41;
	private String	ca_amt42;
	private String	ca_amt51;
	private String	ca_amt52;
	private String	ca_rmid1;
	private String	ca_rmid2;
	private String	ca_rmid3;
	private String	ca_rmid4;
	private String	ca_rmid5;
	
	private String	ic_issuerid1;
	private String	ic_issuerid2;
	private String	ic_issuerid3;
	private String	ic_issuerid4;
	private String	ic_issuerid5;
	private String	ic_cardType1;
	private String	ic_cardType2;
	private String	ic_cardType3;
	private String	ic_cardType4;
	private String	ic_cardType5;
	private String	ic_rmid1;
	private String	ic_rmid2;
	private String	ic_rmid3;
	private String	ic_rmid4;
	private String	ic_rmid5;
	
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
	
	public String getCls() {
		return cls;
	}
	
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	public String getMmf() {
		return mmf;
	}
	
	public void setMmf(String mmf) {
		this.mmf = mmf;
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
	
	public String getC_cardType1() {
		return c_cardType1;
	}
	
	public void setC_cardType1(String type1) {
		c_cardType1 = type1;
	}
	
	public String getC_cardType2() {
		return c_cardType2;
	}
	
	public void setC_cardType2(String type2) {
		c_cardType2 = type2;
	}
	
	public String getC_cardType3() {
		return c_cardType3;
	}
	
	public void setC_cardType3(String type3) {
		c_cardType3 = type3;
	}
	
	public String getC_cardType4() {
		return c_cardType4;
	}
	
	public void setC_cardType4(String type4) {
		c_cardType4 = type4;
	}
	
	public String getC_cardType5() {
		return c_cardType5;
	}
	
	public void setC_cardType5(String type5) {
		c_cardType5 = type5;
	}
	
	public String getC_rmid1() {
		return c_rmid1;
	}
	
	public void setC_rmid1(String c_rmid1) {
		this.c_rmid1 = c_rmid1;
	}
	
	public String getC_rmid2() {
		return c_rmid2;
	}
	
	public void setC_rmid2(String c_rmid2) {
		this.c_rmid2 = c_rmid2;
	}
	
	public String getC_rmid3() {
		return c_rmid3;
	}
	
	public void setC_rmid3(String c_rmid3) {
		this.c_rmid3 = c_rmid3;
	}
	
	public String getC_rmid4() {
		return c_rmid4;
	}
	
	public void setC_rmid4(String c_rmid4) {
		this.c_rmid4 = c_rmid4;
	}
	
	public String getC_rmid5() {
		return c_rmid5;
	}
	
	public void setC_rmid5(String c_rmid5) {
		this.c_rmid5 = c_rmid5;
	}
	
	public String getA_amt11() {
		return a_amt11;
	}
	
	public void setA_amt11(String a_amt11) {
		this.a_amt11 = a_amt11;
	}
	
	public String getA_amt12() {
		return a_amt12;
	}
	
	public void setA_amt12(String a_amt12) {
		this.a_amt12 = a_amt12;
	}
	
	public String getA_amt21() {
		return a_amt21;
	}
	
	public void setA_amt21(String a_amt21) {
		this.a_amt21 = a_amt21;
	}
	
	public String getA_amt22() {
		return a_amt22;
	}
	
	public void setA_amt22(String a_amt22) {
		this.a_amt22 = a_amt22;
	}
	
	public String getA_amt31() {
		return a_amt31;
	}
	
	public void setA_amt31(String a_amt31) {
		this.a_amt31 = a_amt31;
	}
	
	public String getA_amt32() {
		return a_amt32;
	}
	
	public void setA_amt32(String a_amt32) {
		this.a_amt32 = a_amt32;
	}
	
	public String getA_amt41() {
		return a_amt41;
	}
	
	public void setA_amt41(String a_amt41) {
		this.a_amt41 = a_amt41;
	}
	
	public String getA_amt42() {
		return a_amt42;
	}
	
	public void setA_amt42(String a_amt42) {
		this.a_amt42 = a_amt42;
	}
	
	public String getA_amt51() {
		return a_amt51;
	}
	
	public void setA_amt51(String a_amt51) {
		this.a_amt51 = a_amt51;
	}
	
	public String getA_amt52() {
		return a_amt52;
	}
	
	public void setA_amt52(String a_amt52) {
		this.a_amt52 = a_amt52;
	}
	
	public String getA_rmid1() {
		return a_rmid1;
	}
	
	public void setA_rmid1(String a_rmid1) {
		this.a_rmid1 = a_rmid1;
	}
	
	public String getA_rmid2() {
		return a_rmid2;
	}
	
	public void setA_rmid2(String a_rmid2) {
		this.a_rmid2 = a_rmid2;
	}
	
	public String getA_rmid3() {
		return a_rmid3;
	}
	
	public void setA_rmid3(String a_rmid3) {
		this.a_rmid3 = a_rmid3;
	}
	
	public String getA_rmid4() {
		return a_rmid4;
	}
	
	public void setA_rmid4(String a_rmid4) {
		this.a_rmid4 = a_rmid4;
	}
	
	public String getA_rmid5() {
		return a_rmid5;
	}
	
	public void setA_rmid5(String a_rmid5) {
		this.a_rmid5 = a_rmid5;
	}
	
	public String getCa_cardType1() {
		return ca_cardType1;
	}
	
	public void setCa_cardType1(String ca_cardType1) {
		this.ca_cardType1 = ca_cardType1;
	}
	
	public String getCa_cardType2() {
		return ca_cardType2;
	}
	
	public void setCa_cardType2(String ca_cardType2) {
		this.ca_cardType2 = ca_cardType2;
	}
	
	public String getCa_cardType3() {
		return ca_cardType3;
	}
	
	public void setCa_cardType3(String ca_cardType3) {
		this.ca_cardType3 = ca_cardType3;
	}
	
	public String getCa_cardType4() {
		return ca_cardType4;
	}
	
	public void setCa_cardType4(String ca_cardType4) {
		this.ca_cardType4 = ca_cardType4;
	}
	
	public String getCa_cardType5() {
		return ca_cardType5;
	}
	
	public void setCa_cardType5(String ca_cardType5) {
		this.ca_cardType5 = ca_cardType5;
	}
	
	public String getCa_amt11() {
		return ca_amt11;
	}
	
	public void setCa_amt11(String ca_amt11) {
		this.ca_amt11 = ca_amt11;
	}
	
	public String getCa_amt12() {
		return ca_amt12;
	}
	
	public void setCa_amt12(String ca_amt12) {
		this.ca_amt12 = ca_amt12;
	}
	
	public String getCa_amt21() {
		return ca_amt21;
	}
	
	public void setCa_amt21(String ca_amt21) {
		this.ca_amt21 = ca_amt21;
	}
	
	public String getCa_amt22() {
		return ca_amt22;
	}
	
	public void setCa_amt22(String ca_amt22) {
		this.ca_amt22 = ca_amt22;
	}
	
	public String getCa_amt31() {
		return ca_amt31;
	}
	
	public void setCa_amt31(String ca_amt31) {
		this.ca_amt31 = ca_amt31;
	}
	
	public String getCa_amt32() {
		return ca_amt32;
	}
	
	public void setCa_amt32(String ca_amt32) {
		this.ca_amt32 = ca_amt32;
	}
	
	public String getCa_amt41() {
		return ca_amt41;
	}
	
	public void setCa_amt41(String ca_amt41) {
		this.ca_amt41 = ca_amt41;
	}
	
	public String getCa_amt42() {
		return ca_amt42;
	}
	
	public void setCa_amt42(String ca_amt42) {
		this.ca_amt42 = ca_amt42;
	}
	
	public String getCa_amt51() {
		return ca_amt51;
	}
	
	public void setCa_amt51(String ca_amt51) {
		this.ca_amt51 = ca_amt51;
	}
	
	public String getCa_amt52() {
		return ca_amt52;
	}
	
	public void setCa_amt52(String ca_amt52) {
		this.ca_amt52 = ca_amt52;
	}
	
	public String getCa_rmid1() {
		return ca_rmid1;
	}
	
	public void setCa_rmid1(String ca_rmid1) {
		this.ca_rmid1 = ca_rmid1;
	}
	
	public String getCa_rmid2() {
		return ca_rmid2;
	}
	
	public void setCa_rmid2(String ca_rmid2) {
		this.ca_rmid2 = ca_rmid2;
	}
	
	public String getCa_rmid3() {
		return ca_rmid3;
	}
	
	public void setCa_rmid3(String ca_rmid3) {
		this.ca_rmid3 = ca_rmid3;
	}
	
	public String getCa_rmid4() {
		return ca_rmid4;
	}
	
	public void setCa_rmid4(String ca_rmid4) {
		this.ca_rmid4 = ca_rmid4;
	}
	
	public String getCa_rmid5() {
		return ca_rmid5;
	}
	
	public void setCa_rmid5(String ca_rmid5) {
		this.ca_rmid5 = ca_rmid5;
	}
	
	public String getIc_issuerid1() {
		return ic_issuerid1;
	}
	
	public void setIc_issuerid1(String ic_issuerid1) {
		this.ic_issuerid1 = ic_issuerid1;
	}
	
	public String getIc_issuerid2() {
		return ic_issuerid2;
	}
	
	public void setIc_issuerid2(String ic_issuerid2) {
		this.ic_issuerid2 = ic_issuerid2;
	}
	
	public String getIc_issuerid3() {
		return ic_issuerid3;
	}
	
	public void setIc_issuerid3(String ic_issuerid3) {
		this.ic_issuerid3 = ic_issuerid3;
	}
	
	public String getIc_issuerid4() {
		return ic_issuerid4;
	}
	
	public void setIc_issuerid4(String ic_issuerid4) {
		this.ic_issuerid4 = ic_issuerid4;
	}
	
	public String getIc_issuerid5() {
		return ic_issuerid5;
	}
	
	public void setIc_issuerid5(String ic_issuerid5) {
		this.ic_issuerid5 = ic_issuerid5;
	}
	
	public String getIc_cardType1() {
		return ic_cardType1;
	}
	
	public void setIc_cardType1(String ic_cardType1) {
		this.ic_cardType1 = ic_cardType1;
	}
	
	public String getIc_cardType2() {
		return ic_cardType2;
	}
	
	public void setIc_cardType2(String ic_cardType2) {
		this.ic_cardType2 = ic_cardType2;
	}
	
	public String getIc_cardType3() {
		return ic_cardType3;
	}
	
	public void setIc_cardType3(String ic_cardType3) {
		this.ic_cardType3 = ic_cardType3;
	}
	
	public String getIc_cardType4() {
		return ic_cardType4;
	}
	
	public void setIc_cardType4(String ic_cardType4) {
		this.ic_cardType4 = ic_cardType4;
	}
	
	public String getIc_cardType5() {
		return ic_cardType5;
	}
	
	public void setIc_cardType5(String ic_cardType5) {
		this.ic_cardType5 = ic_cardType5;
	}
	
	public String getIc_rmid1() {
		return ic_rmid1;
	}
	
	public void setIc_rmid1(String ic_rmid1) {
		this.ic_rmid1 = ic_rmid1;
	}
	
	public String getIc_rmid2() {
		return ic_rmid2;
	}
	
	public void setIc_rmid2(String ic_rmid2) {
		this.ic_rmid2 = ic_rmid2;
	}
	
	public String getIc_rmid3() {
		return ic_rmid3;
	}
	
	public void setIc_rmid3(String ic_rmid3) {
		this.ic_rmid3 = ic_rmid3;
	}
	
	public String getIc_rmid4() {
		return ic_rmid4;
	}
	
	public void setIc_rmid4(String ic_rmid4) {
		this.ic_rmid4 = ic_rmid4;
	}
	
	public String getIc_rmid5() {
		return ic_rmid5;
	}
	
	public void setIc_rmid5(String ic_rmid5) {
		this.ic_rmid5 = ic_rmid5;
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
