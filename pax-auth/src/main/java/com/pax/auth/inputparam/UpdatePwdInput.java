package com.pax.auth.inputparam;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdatePwdInput {
	private String loginname;
	@NotEmpty(message = "旧密码不能为空")
	private String	oldPwd;
	@NotEmpty(message = "新密码不能为空")
	private String	newPwd1;
	@NotEmpty(message = "确认密码不能为空")
	private String	newPwd2;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getOldPwd() {
		return oldPwd;
	}
	
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	public String getNewPwd1() {
		return newPwd1;
	}
	
	public void setNewPwd1(String newPwd1) {
		this.newPwd1 = newPwd1;
	}
	
	public String getNewPwd2() {
		return newPwd2;
	}
	
	public void setNewPwd2(String newPwd2) {
		this.newPwd2 = newPwd2;
	}
	
}
