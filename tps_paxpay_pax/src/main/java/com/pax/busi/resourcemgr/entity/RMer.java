package com.pax.busi.resourcemgr.entity;

import com.pax.core.entity.BaseEntity;


public class RMer extends BaseEntity{
	
	private String			mid;
	
	private String mcr;
	
	private String mcrName;
	
	
	private String			name;
	
	private String	orgName;
	
	private String depid;
	
	private String	builddatetime_short;
	private String	modifydatetime_short;
	private String	auditdatetime_short;
	/*private Organization org;
	
	
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}*/

	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	


	public String getMcr() {
		return mcr;
	}

	public void setMcr(String mcr) {
		this.mcr = mcr;
	}

	public String getMcrName() {
		return mcrName;
	}

	public void setMcrName(String mcrName) {
		this.mcrName = mcrName;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
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
		RMer other = (RMer) obj;
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