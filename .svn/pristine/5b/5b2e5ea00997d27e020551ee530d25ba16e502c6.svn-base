package com.boot.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.boot.config.Constants;
import com.boot.domain.AdminUser;


/**
 * 类名: com.lanbin.tyrannosaurus.chatservice.security.ChatroomAuthenticationSuccessHandler
 * <p>
 * 描述:
 * </p>
 * 日期: 24/03/2017 23:11
 *
 * @author 蓝斌
 * @since JDK1.8
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    private UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	if (authentication.getPrincipal() != null) {
            AdminUser user = (AdminUser) authentication.getPrincipal();
            request.getSession().setAttribute(Constants.ADMIN_USER_KEY, user);
            //登录成功后跳转页面
//            this.strategy.sendRedirect(request, response, "/manage/main/main");
            this.strategy.sendRedirect(request, response, "/single/meeting/queryMeeting");
        }
    }


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    

}
