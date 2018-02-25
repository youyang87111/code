package com.pax.busi.resourcemgr.entity;

import com.pax.core.entity.BaseEntity;

public class CposMer extends BaseEntity {
	
	private String	mcr;
	private String	mcrName;
	private String	mid;
	private String	name;
	
	private String	depid;
	private String	orgName;
	
	private String	builddatetime_short;
	private String	modifydatetime_short;
	private String	auditdatetime_short;
	
	private String	storeid;
	private String	storename;
	private String	timeZoneNew;
	private String	conv_currency;
	private String	agent_id;
	private String provincename;
	private String cityname;
	private String districtname;
	private String address;
	
	public String getMcr() {
		return mcr;
	}
	
	public void setMcr(String mcr) {
		this.mcr = mcr;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMcrName() {
		return mcrName;
	}
	
	public void setMcrName(String mcrName) {
		this.mcrName = mcrName;
	}
	
	
	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	
	public String getStoreid() {
		return storeid;
	}
	
	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	
	public String getStorename() {
		return storename;
	}
	
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
	public String getTimeZoneNew() {
		return timeZoneNew;
	}
	
	public void setTimeZoneNew(String timeZoneNew) {
		this.timeZoneNew = timeZoneNew;
	}
	
	public String getConv_currency() {
		return conv_currency;
	}
	
	public void setConv_currency(String conv_currency) {
		this.conv_currency = conv_currency;
	}
	
	public String getAgent_id() {
		return agent_id;
	}
	
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	
	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mcr == null) ? 0 : mcr.hashCode());
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
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
		CposMer other = (CposMer) obj;
		if (mcr == null) {
			if (other.mcr != null)
				return false;
		} else if (!mcr.equals(other.mcr))
			return false;
		if (mid == null) {
			if (other.mid != null)
				return false;
		} else if (!mid.equals(other.mid))
			return false;
		return true;
	}
	
}
