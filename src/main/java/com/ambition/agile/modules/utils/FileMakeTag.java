package com.ambition.agile.modules.utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FileMakeTag extends TagSupport {
	
	private static final long serialVersionUID = 1755723090308106482L;
	
	private String fileName;//文件名
	private boolean isMutil;//是否上传多张
	private long size;//上传文件大小kb
	private String fileType;//上传文件类型
	private String url;//保存文件路径
	
	@Override
	public int doStartTag() throws JspException {
		
		/*pageContext.getAttribute("name");
		if(index<=end){
			pageContext.setAttribute(val, index);
			index++;
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}*/
		return SKIP_BODY;

	}

	@Override
	public int doAfterBody() throws JspException {
		/*if(index<=end){
			pageContext.setAttribute(val, index);
			index++;
			return EVAL_BODY_AGAIN;
		}else{
			return SKIP_BODY;
		}*/
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print("<img src=\""+url+"\" alt=\""+fileName+"\" onclick=\""+openFile()+"\" />");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	
	private String openFile(){
		try {
			Runtime.getRuntime().exec(new String[] {"cmd","/c","start","","c:"});
		} catch (IOException e) {
			return "打开文件失败";
		}
		return "";
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isMutil() {
		return isMutil;
	}

	public void setMutil(boolean isMutil) {
		this.isMutil = isMutil;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
