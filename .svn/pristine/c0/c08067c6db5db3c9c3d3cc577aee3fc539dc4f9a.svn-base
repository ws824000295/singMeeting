package com.boot.config.security;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.RequestMatcher;


/**
 * 类名: CsrfSecurityRequestMatcher
 * <p>
 * 描述:
 * </p>
 * 日期: 20/03/2017 12:15
 *
 * @author 蓝斌
 * @since JDK1.8
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsrfSecurityRequestMatcher.class);

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	private List<String> execludeUrls;



	@Override
	public boolean matches(HttpServletRequest request) {
		if (null!=execludeUrls && !execludeUrls.isEmpty()) {
			String serverPath = request.getServletPath();
			for (String url : this.execludeUrls) {
				if (!serverPath.endsWith("/")) serverPath += "/";
				if (serverPath.contains(url)) {
				//	LOGGER.info("Exelude URL (CSRF) : " + serverPath);
					return false;
				}
			}
		}
		return !allowedMethods.matcher(request.getMethod()).matches();
	}



	public List<String> getExecludeUrls() {
		return execludeUrls;
	}



	public void setExecludeUrls(List<String> execludeUrls) {
		this.execludeUrls = execludeUrls;
	}
	
	

}
