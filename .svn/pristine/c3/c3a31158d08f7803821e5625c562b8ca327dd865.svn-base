package com.boot.common.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.boot.common.util.DateEditor;
import com.boot.common.util.StringEscapeEditor;
import com.boot.config.Constants;
import com.boot.domain.AdminUser;
import com.boot.domain.User;

public abstract class AbstractController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	public AdminUser getAdminUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_KEY);
	}
	
	public User getWxUser(String adminId){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return (User) request.getSession().getAttribute(adminId+"_"+Constants.WEIXIN_USER_KEY);
	}
	
	public String getProductUrl(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = (null==request.getHeader("X-Forwarded-Proto")?request.getScheme():request.getHeader("X-Forwarded-Proto"))+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
}
