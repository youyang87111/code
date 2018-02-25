package com.pax.auth.entity;

public class User extends AuthBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8316444964135374564l;

	private int				id;
	
	private String			errortimes;
	
	private Site			site;
	
	private String			name;
	
	private String			loginname;
	
	private String			password;
	
	private String			salt;
	
	private String			status;
	
	private String			email;
	private String			phoneno;
	private String			lastlogintime;
	private String			lastupdatepwdtime;
	private String			frozentime;
	private String			frozenreason;
	
	


	public String getErrortimes() {
		return errortimes;
	}

	public void setErrortimes(String errortimes) {
		this.errortimes = errortimes;
	}

	private Organization	org;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public Site getSite() {
		return site;
	}
	
	public void setSite(Site site) {
		this.site = site;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneno() {
		return phoneno;
	}
	
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public Organization getOrg() {
		return org;
	}
	
	public void setOrg(Organization org) {
		this.org = org;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	public String getLastupdatepwdtime() {
		return lastupdatepwdtime;
	}

	public void setLastupdatepwdtime(String lastupdatepwdtime) {
		this.lastupdatepwdtime = lastupdatepwdtime;
	}

	public String getFrozentime() {
		return frozentime;
	}

	public void setFrozentime(String frozentime) {
		this.frozentime = frozentime;
	}

	public String getFrozenreason() {
		return frozenreason;
	}

	public void setFrozenreason(String frozenreason) {
		this.frozenreason = frozenreason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
