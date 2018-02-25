package com.pax.busi.facade;

public class ActivationReq extends BaseReq{

	private static final long serialVersionUID = 1L;
	
	//激活码
	private String regCode;
	
	//终端型号
	private String termType;
	
	//终端序列号
	private String termSn;
	
	//应用版本号,收银台应用软件版本号
	private String appVer;
	

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

}
