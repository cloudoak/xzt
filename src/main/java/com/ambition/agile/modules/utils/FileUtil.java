package com.ambition.agile.modules.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	
//	public static String downloadFile(String path,  HttpServletRequest request, HttpServletResponse response){
//		
//		return "";
//	}
	
	/**
	 * @param response 
	 * @param filePath 文件完整路径(包括文件名和扩展名)
	 * @param fileName 下载文件名
	 * @return  文件名
	 */
	public static void downloadFile(HttpServletResponse response, String filePath, String fileName) throws Exception{  
		     
		    byte[] data = FileUtil.toByteArray(filePath+"\\"+fileName);  
		    fileName = URLEncoder.encode(fileName, "UTF-8");  
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
		    response.addHeader("Content-Length", "" + data.length);  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
		    outputStream.write(data);  
		    outputStream.flush();  
		    outputStream.close();
		    response.flushBuffer();
		    
		} 
	
	/**
	 * 读取到字节数组
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
