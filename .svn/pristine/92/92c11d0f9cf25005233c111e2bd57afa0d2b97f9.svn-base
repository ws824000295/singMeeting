package com.boot.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 类名: com.lanbin.tyrannosaurus.chatservice.security.UsernamePasswordCaptchaAuthenticationFilter
 * <p>
 * 描述:
 * </p>
 * 日期: 28/03/2017 17:56
 *
 * @author 蓝斌
 * @since JDK1.8
 */
public class UsernamePasswordCaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	 protected MessageSourceAccessor messages = SecurityMessageSource.getAccessor();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST"))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

    //    checkCaptcha(request);

        UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(obtainUsername(request), obtainPassword(request));

        setDetails(request, authRequest);

        return getAuthenticationManager().authenticate(authRequest);
    }


    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = super.obtainUsername(request);
        return username == null ? "" : username.trim();
    }


    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = super.obtainPassword(request);
        return password == null ? "" : password;
    }


    protected String obtainCaptcha(HttpServletRequest request) {
        String captcha = request.getParameter("captcha");
        return captcha == null ? "" : captcha;
    }


    protected void checkCaptcha(HttpServletRequest request) throws BadCaptchaException {
      String captcha = obtainCaptcha(request);
      Object cko = request.getSession().getAttribute("benchmarkCaptcha");
      if(cko == null)
          throw new BadCaptchaException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.captchaIsNull", "Captcha is null"));
      String benchmarkCaptcha = cko.toString();

      if(StringUtils.isBlank(captcha) || !captcha.equalsIgnoreCase(benchmarkCaptcha))
          throw new BadCaptchaException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.captchaNotMatch", "Captcha don't match"));
      else
          request.getSession().removeAttribute("benchmarkCaptcha");
    }

}
