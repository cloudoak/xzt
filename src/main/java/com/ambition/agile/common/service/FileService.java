/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.DataEntity;
import com.ambition.agile.modules.utils.FileUtils;
import com.ambition.agile.modules.utils.WebUtil;

/**
 * 
 * File Service
 * 
 * @author OAK
 * @since 2017/12/30
 * @version 1.0
 */
public abstract class FileService<D extends CrudDao<T>, T extends DataEntity<T>> extends CrudService<D, T> {
	
	@Value(value = "${file.appversion.upload}")
	private String filePath;
	
	@Value(value = "${file.appversion.absolute.upload}")
	private String fileAbsolutePath;
	
	@Value(value = "${file.appversion.port}")
	private Integer port;
	
	public String transferTo(MultipartFile file) {
		String message = "";
		boolean flags = true;
		try {
			if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
				String path = FileUtils.transfer(file, filePath);
				message = path;
			}
		}catch(Exception ex) {
			flags = false;
			message = ex.getMessage();
		}
		return "{\"success\": " + flags + ", \"message\": \"" + message + "\"}";
	}
	
	public String getOriginalFilePath() {
		return getOriginalFilePath("");
	}
	
	public String getOriginalFilePath(String originalFileName) {
		StringBuffer contextPath = new StringBuffer();
		if(fileAbsolutePath != null) {
			if(!fileAbsolutePath.startsWith("/")) {
				contextPath.append("/");
			}
			contextPath.append(fileAbsolutePath);
			if(!fileAbsolutePath.endsWith("/")) {
				contextPath.append("/");
			}
			contextPath.append(originalFileName);
		}
		return WebUtil.getRealPath(contextPath.toString(), port);
	}

}
