package com.boot.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class AppPath {
	private static AppPath APP_PATH = null;
	
	public static synchronized AppPath getInstance() {
		if (APP_PATH == null) {
			APP_PATH = new AppPath();
		}

		return APP_PATH;
	}
	
	/**
	 * 获取应用部署的根路径
	 * 
	 * @return
	 */
	public static String getRootPath() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 获取应用部署的根路径
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		return request;
	}
	
	
	
	
	/**
	 * 获取应用部署的resp
	 * 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
		return resp;
	}
	
	/**
	 * 获取项目网址
	 * @return
	 */
	public static String getProjectUrl(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		String path = request.getContextPath();
		String port = "";
		if(80!=request.getServerPort()){
			port = ":"+request.getServerPort();
		}
		String basePath = (null==request.getHeader("X-Forwarded-Proto")?request.getScheme():request.getHeader("X-Forwarded-Proto"))+"://"+request.getServerName()+port+path;
		return basePath;
	}

	   /** 
  * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
  *  
  * @return 
  */  
 public static String getIpAddress(){  
 	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
     String ip = request.getHeader("x-forwarded-for");  
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("Proxy-Client-IP");  
     }  
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("WL-Proxy-Client-IP");  
     }  
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("HTTP_CLIENT_IP");  
     }  
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
     }  
     if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
         ip = request.getRemoteAddr();  
     }  
     return ip.split(",")[0];  
 }
 /**
  * @Title: getMyIp   
  * @Description: 获取当前服务器的IP地址   
  * @param: @return      
  * @return:String
  * @author: SSY 
  * @date: 2018年12月17日 下午5:07:37     
  * @throws
  */
 public static String getCurrentServerIp() {
		InetAddress address =null;
		try {
			address = InetAddress.getLocalHost();
			return address.getHostAddress();//192.168.0.121   
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	 	}
	
}