package com.ambition.agile.modules.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 文件帮助类
 * 
 * @author OAK
 * @since 2017/12/06
 * @version 1.0
 * 
 * <blockquote>
 * 支持MultipartFile文件本地保存
 * </blockquote>
 * 
 */
public class FileUtils {
	
	public static String transfer(MultipartFile file, String path) throws Exception {
		Long millis = System.currentTimeMillis();
		File f = new File(path + File.separator + millis);
		return transfer(file, f, millis.toString());
	}

	/**
	 * 上传文件存放到服务器的指定目录
	 * @param file
	 * @return
	 * @throws Exception
	 */
	private static String transfer(MultipartFile file, File rest, String dir) throws Exception {
		File transferFile = null;
		byte[] uploadByte;
		FileOutputStream fos = null;
		try {
			if (!rest.exists()) {
				rest.mkdirs();
			}
			uploadByte = file.getBytes();
			transferFile = new File(rest.getAbsolutePath() + File.separator + file.getOriginalFilename());
			fos = new FileOutputStream(transferFile);
			fos.write(uploadByte);
			return dir + File.separator + file.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("文件上传异常！");
		} finally {
			try {
				uploadByte = null;
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("上传文件时关闭文件流异常");
			}
		}
	}
	
}
