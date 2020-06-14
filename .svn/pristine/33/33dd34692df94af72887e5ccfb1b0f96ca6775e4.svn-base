package com.boot.common.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


public class SpringMVC {
	/**
	 * <p>
	 * 描述: 获取工程的部署的文件系统路径
	 * </p>
	 * 
	 * @return 文件系统路径
	 */
	public static String getRealPath() {
		return getServletContext().getRealPath("");
	}
	/**
	 * <p>
	 * 描述: 取得ServletContent对象
	 * </p>
	 * 
	 * @return ServletContext对象
	 */
	public static ServletContext getServletContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		return webApplicationContext.getServletContext(); 
	}
	
}
