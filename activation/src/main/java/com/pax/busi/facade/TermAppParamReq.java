package com.pax.busi.facade;

public class TermAppParamReq extends BaseReq{

	private static final long serialVersionUID = 1L;
	
	//终端型号
	private String termType;
	//终端序列号
	private String termSn;
	//应用版本号
	private String appVer;
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
