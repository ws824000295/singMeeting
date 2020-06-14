package com.boot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.domain.AdminUser;
import com.boot.repository.AdminUserRepository;


/**
 * 类名: com.lanbin.tyrannosaurus.chatservice.user.UserService
 * <p>
 * 描述:
 * </p>
 * 日期: 25/03/2017 21:55
 *
 * @author 蓝斌
 * @since JDK1.8
 */
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
	
	private MessageSourceAccessor messages = SecurityMessageSource.getAccessor();

    @Autowired
    private AdminUserRepository userRepository;
    
//    @Autowired
//    private Md5PasswordEncoder passwordEncoder;

//    @Autowired
//    private SaltSource saltSource;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser user = this.userRepository.getAdminUser(username);
        if (user == null)
        	throw new UsernameNotFoundException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.usernameNotFound", new String[]{username}, "Username not found"));
        return user;
    }

    
//    public AdminUser generatePassword(AdminUser user) {
//    	user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));
//        return user;
//    }
}
