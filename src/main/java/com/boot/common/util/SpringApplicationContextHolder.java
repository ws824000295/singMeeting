package com.boot.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) {
		SpringApplicationContextHolder.context = context;
	}

	public static Object getSpringBean(String beanName) {
		if (beanName == null || "".equals(beanName)) {
			return "beanName不能为空";
		}
		return context == null ? null : context.getBean(beanName);
	}

	public static String[] getBeanDefinitionNames() {
		return context.getBeanDefinitionNames();
	}
}
