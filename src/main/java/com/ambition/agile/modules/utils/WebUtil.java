package com.ambition.agile.modules.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * WEB 帮助类
 * 获取IP路径
 * 
 * @author OAK
 * @since 2017/12/19
 *
 */
public class WebUtil {
	
	public static String getRealPath(HttpServletRequest request){
		String path = null;
		if(request != null)
			path = request.getSession().getServletContext().getRealPath("upload");
		return path;
	}
	
	public static String getRealPath() {
		return WebUtil.getRealPath("", null);
	}
	
	@SuppressWarnings("rawtypes")
    public static String getRealPath(String contextPath, Integer port) {
        String localAddress = null, remoteAddress = null;
        if(contextPath == null && "".equals(contextPath)) {
        	contextPath = "";
        }
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress inetAddress = null;
            boolean finished = false;
            while (netInterfaces.hasMoreElements() && !finished) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                	inetAddress = (InetAddress) address.nextElement();
                    if (!inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(":") == -1) {
                    	remoteAddress = inetAddress.getHostAddress();
                    	finished = true;
                        break;
                    } else if (inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(":") == -1) {
                    	localAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (remoteAddress != null && !"".equals(remoteAddress)) {
            return "http://" + remoteAddress  + (port != null ? ":" + port : "") + contextPath;
        } else {
            return "http://" + localAddress + (port != null ? ":" + port : "") + contextPath;
        }
    }
	
}
