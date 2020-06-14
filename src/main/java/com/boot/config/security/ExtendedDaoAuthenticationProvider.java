package com.boot.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.boot.common.util.AppPath;
import com.boot.domain.AdminUser;


/**
 * 类名: com.boot.config.security.ExtendedDaoAuthenticationProvider
 * <p>
 * 描述:
 * </p>
 * 日期: 30/03/2017 19:03
 *
 * @author 蓝斌
 * @since JDK1.8
 */
public class ExtendedDaoAuthenticationProvider extends DaoAuthenticationProvider {

    protected MessageSourceAccessor messages = SecurityMessageSource.getAccessor();



    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            logger.error("Authentication failed: Must be an UsernamePasswordAuthenticationToken instance");
            throw new IllegalArgumentException("Authentication failed: Must be an UsernamePasswordAuthenticationToken instance");
        }

        UsernamePasswordAuthenticationToken extendedAuthentication = (UsernamePasswordAuthenticationToken) authentication;
      //    AdminUser user = (AdminUser) authentication.getPrincipal();

        Object salt = null;
        if (getSaltSource() != null)
            salt = getSaltSource().getSalt(userDetails);

        if (extendedAuthentication.getCredentials() == null) {
            logger.error("Authentication failed: Password cannot be empty");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.emptyPassword", "Password cannot be empty"));
        }
        
         
        AdminUser adminUser =  (AdminUser)userDetails;
        HttpServletResponse resp = AppPath.getResponse();
         if( StringUtils.isNotBlank(adminUser.getManageServerName())  && !AppPath.getProjectUrl().contains(adminUser.getManageServerName())){
        	 try {
        		 resp.sendRedirect(adminUser.getManageServerName());
				 return;
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
        
        String presentedPassword = extendedAuthentication.getCredentials().toString();
        if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.error("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.passwordNotMatch", "Password don't match"));
        }
        
        
        
    }

}
