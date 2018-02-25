package com.pax.busi.resourcemgr.input;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MerTermImportInput {
	
	@NotNull(message = "商户来源不能为空")
	private String	mcr;
	
	@NotNull(message = "上传文件不能为空,请选择上传文件")
	private MultipartFile	file;
	
	private String	fileContentType;
	
	private String	fileFileName;
	
	private String	buildoper;			//创建操作员
	private String	builddatetime;		//创建时间
										
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}
	
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}
	
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public String getBuildoper() {
		return buildoper;
	}
	
	public void setBuildoper(String buildoper) {
		this.buildoper = buildoper;
	}
	
	public String getBuilddatetime() {
		return builddatetime;
	}
	
	public void setBuilddatetime(String builddatetime) {
		this.builddatetime = builddatetime;
	}
	
	public String getMcr() {
		return mcr;
	}
	
	public void setMcr(String mcr) {
		this.mcr = mcr;
	}
	
}
