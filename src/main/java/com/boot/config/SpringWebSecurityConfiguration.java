package com.boot.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.boot.config.filter.ViewFilter;
import com.boot.config.security.CsrfSecurityRequestMatcher;
import com.boot.config.security.CustomAuthenticationSuccessHandler;
import com.boot.config.security.ExtendedDaoAuthenticationProvider;
import com.boot.config.security.UserService;
import com.boot.config.security.UsernamePasswordCaptchaAuthenticationFilter;

/**
 * 类名: com.lanbin.tyrannosaurus.chatservice.configuration.
 * SpringWebSecurityConfiguration
 * <p>
 * 描述:
 * </p>
 * 日期: 21/03/2017 10:11
 *
 * @author 蓝斌
 * @since JDK1.8
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(usernamePasswordCaptchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).httpBasic().disable().authorizeRequests()
				.antMatchers(this.getSecurityPermitUrls()).permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.successHandler(this.chatroomAuthenticationSuccessHandler()).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().csrf().requireCsrfProtectionMatcher(this.csrfSecurityRequestMatcher())
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		// 测试时使用，默认在springboot项目中不能加载iframe（为避免了点击劫持clickjacking攻击），导致无法正常访问/h2db
		if (!this.isDisableXFrameOptions())
			http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(extendedDaoAuthenticationProvider());
	}

	@Bean
	public UsernamePasswordCaptchaAuthenticationFilter usernamePasswordCaptchaAuthenticationFilter() throws Exception {
		UsernamePasswordCaptchaAuthenticationFilter filter = new UsernamePasswordCaptchaAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(chatroomAuthenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}

	private CustomAuthenticationSuccessHandler chatroomAuthenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
		handler.setUserService(this.userService);
		return handler;
	}

	/**
	 * 登录失败跳转页面
	 * 
	 * @return
	 */
	private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/");
	}

	@Bean
	public ExtendedDaoAuthenticationProvider extendedDaoAuthenticationProvider() {
		ExtendedDaoAuthenticationProvider provider = new ExtendedDaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setHideUserNotFoundExceptions(false);
		// provider.setPasswordEncoder(passwordEncoder());
		// provider.setSaltSource(saltSource());
		return provider;
	}

	// @Bean
	// public Md5PasswordEncoder passwordEncoder() {
	// return new Md5PasswordEncoder();
	// }

	// @Bean
	// public SaltSource saltSource() {
	// ReflectionSaltSource saltSource = new ReflectionSaltSource();
	// saltSource.setUserPropertyToUse("username");
	// return saltSource;
	// }

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		ViewFilter httpBasicFilter = new ViewFilter();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/view/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	@Bean
	public RedisTemplate redisTemplateInit() {
		// 设置序列化Key的实例化对象
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		// 设置序列化Value的实例化对象
		// redisTemplate.setValueSerializer(new
		// GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

	private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
		CsrfSecurityRequestMatcher csrfSecurityRequestMatcher = new CsrfSecurityRequestMatcher();
		csrfSecurityRequestMatcher.setExecludeUrls(Arrays.asList(this.getSecurityCsrfExecludeUrls()));
		return csrfSecurityRequestMatcher;
	}

	private String[] getSecurityPermitUrls() {
		return this.environment.getProperty("project.security.permit-urls", "/,/login").split(",");
	}

	private String[] getSecurityCsrfExecludeUrls() {
		return this.environment.getProperty("project.security.csrf.execlude-urls").split(",");
	}

	private boolean isDisableXFrameOptions() {
		String frame = this.environment.getProperty("project.security.headers.frame", "true");
		return Boolean.parseBoolean(frame);
	}

}
