package com.pax.busi.transreport.entity;

import java.io.Serializable;

import com.pax.core.util.DateUtils;

public class RealtimeNotion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String transactionNo;
	
	private String paymentTime;
	
	private String mchId;
	
	private String mchName;
	
	private String terId;
	
	private String agentId;
	
	
	private String totalAmount;
	
	private String tradeType;
	
	private String sendTimes;
	
	private String sendNextTime;
	
	private String status1;
	
	private String msg;

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}	
	
	public String getPaymentTime() {
		if (this.paymentTime != null) {
			return DateUtils.stringDateFormat(this.paymentTime, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getTerId() {
		return terId;
	}

	public void setTerId(String terId) {
		this.terId = terId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}


	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(String sendTimes) {
		this.sendTimes = sendTimes;
	}

	public String getSendNextTime() {
		return sendNextTime;
	}

	public void setSendNextTime(String sendNextTime) {
		this.sendNextTime = sendNextTime;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
