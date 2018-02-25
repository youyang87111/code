package com.pax.busi.facade;

public class ActivationRsp extends BaseRsp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//逻辑商户名
	private String merchantName;
	//逻辑商户号
	private String merchantId;
	//逻辑终端号
	private String terminalId;
	
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	
}
