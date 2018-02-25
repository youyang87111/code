package com.pax.busi.resourcemgr.input;

import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class SecretInput {
	@NotNull(message = "商户来源不能为空")
	private String	mcr;
	
	@NotNull(message = "上传文件不能为空,请选择上传文件")
	private MultipartFile	file;

	public String getMcr() {
		return mcr;
	}

	public void setMcr(String mcr) {
		this.mcr = mcr;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
	
}
