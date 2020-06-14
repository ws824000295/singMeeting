package com.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 类名: com.lanbin.tyrannosaurus.chatservice.configuration.SpringWebConfiguration
 * <p>
 * 描述: TODO
 * </p>
 * 日期: 2016年11月8日 下午10:33:13
 *
 * @author 蓝斌
 * @since JDK1.8
 */
@Configuration
public class SpringWebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("index");
	}

}
