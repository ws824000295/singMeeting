package com.boot.config.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.boot.common.util.AppPath;
import com.boot.common.util.LogUtil;
import com.boot.config.Constants;

public class ViewFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String adminId = req.getParameter("adminId");
		if(null==session.getAttribute(adminId+"_"+Constants.WEIXIN_USER_KEY)){
			System.out.println("未登录状态");
			if(StringUtils.isBlank(adminId)){
				return;
			}
			
			String port = "";
			if(80!=req.getServerPort()){
				port = ":"+req.getServerPort();
			}
			String basePath = (null==req.getHeader("X-Forwarded-Proto")?req.getScheme():req.getHeader("X-Forwarded-Proto"))+"://"+req.getServerName()+port;
			basePath += req.getRequestURI()+"?"+req.getQueryString();
			LogUtil.info("basePath="+basePath);
	        String url = AppPath.getProjectUrl()+"/external/weixin/login?adminId="+adminId+"&basePath="+URLEncoder.encode(basePath, "utf-8");
			res.sendRedirect(url);
		}else{
			chain.doFilter(req, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}

