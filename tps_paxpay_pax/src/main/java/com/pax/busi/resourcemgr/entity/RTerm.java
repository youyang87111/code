package com.pax.busi.resourcemgr.entity;

import org.apache.commons.lang.StringUtils;

import com.pax.core.entity.BaseEntity;


public class RTerm extends BaseEntity {
	// 商户号归属的应用类型
	private RMer	mer;
	private String	tid;
	
	private String orgName;
	
	private String mcrName;
	
	private String	depid;
	
	private String mcr;
	private String mid;
	private String	builddatetime_short;
	private String	modifydatetime_short;
	private String	auditdatetime_short;
	
	// 生成TMK的标志 '1'-需要生成TMK '0'-不需要生成TMK
	private String	gentmk		= "1";
	
	// 主密钥加密算法
	private Long	althtypek	= 1L;
	
	// LMK加密的终端PIN KEY
	private String	lmktmk;
	
	// ZMK加密的终端主密钥
	private String	tmk;
	
	// LMK加密的终端PIN KEY
	private String	lmkpinkey;
	
	// 终端PIN KEY
	private String	pinkey;
	
	// MAC加密算法
	private Long	althtypem	= 1L;
	
	// LMK加密的终端MAC KEY
	private String	lmkmackey;
	
	// 终端MAC KEY
	private String	mackey;
	
	// 敏感数据加密算法
	private Long	althtyped	= 1L;
	
	// LMK加密的终端DATA KEY
	private String	lmkdatakey;
	
	// 终端DATA KEY
	private String	datakey;
	
	private String  pk;
	
	
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

	public RMer getMer() {
		return mer;
	}
	
	public void setMer(RMer mer) {
		this.mer = mer;
	}
	
	public String getTid() {
		return tid;
	}
	
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getDepid() {
		return depid;
	}
	
	public void setDepid(String depid) {
		this.depid = depid;
	}
	
	public String getGentmk() {
		return gentmk;
	}
	
	public void setGentmk(String gentmk) {
		this.gentmk = gentmk;
	}
	
	public Long getAlthtypek() {
		return althtypek;
	}
	
	public void setAlthtypek(Long althtypek) {
		this.althtypek = althtypek;
	}
	
	public String getLmktmk() {
		return lmktmk;
	}
	
	public void setLmktmk(String lmktmk) {
		this.lmktmk = lmktmk;
	}
	
	public String getTmk() {
		return tmk;
	}
	
	public void setTmk(String tmk) {
		this.tmk = tmk;
	}
	
	public String getLmkpinkey() {
		return lmkpinkey;
	}
	
	public void setLmkpinkey(String lmkpinkey) {
		this.lmkpinkey = lmkpinkey;
	}
	
	public String getPinkey() {
		return pinkey;
	}
	
	public void setPinkey(String pinkey) {
		this.pinkey = pinkey;
	}
	
	public Long getAlthtypem() {
		return althtypem;
	}
	
	public void setAlthtypem(Long althtypem) {
		this.althtypem = althtypem;
	}
	
	public String getLmkmackey() {
		return lmkmackey;
	}
	
	public void setLmkmackey(String lmkmackey) {
		this.lmkmackey = lmkmackey;
	}
	
	public String getMackey() {
		return mackey;
	}
	
	public void setMackey(String mackey) {
		this.mackey = mackey;
	}
	
	public Long getAlthtyped() {
		return althtyped;
	}
	
	public void setAlthtyped(Long althtyped) {
		this.althtyped = althtyped;
	}
	
	public String getLmkdatakey() {
		return lmkdatakey;
	}
	
	public void setLmkdatakey(String lmkdatakey) {
		this.lmkdatakey = lmkdatakey;
	}
	
	public String getDatakey() {
		return datakey;
	}
	
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMcrName() {
		return mcrName;
	}

	public void setMcrName(String mcrName) {
		this.mcrName = mcrName;
	}
	public String getPk() {
		if (pk == null || StringUtils.isEmpty(pk))
			pk = mer.getMcr() + mer.getMid() + getTid();
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
		this.mcr = pk.substring(0, 4);
		this.mid = pk.substring(4, 19);
		this.tid = pk.substring(19);
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
		result = prime * result + ((mer == null) ? 0 : mer.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
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
		RTerm other = (RTerm) obj;
		if (mer == null) {
			if (other.mer != null)
				return false;
		} else if (!mer.equals(other.mer))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
	
	
}
