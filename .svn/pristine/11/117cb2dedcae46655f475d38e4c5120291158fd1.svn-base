package com.boot.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring获取bean的工具类，可用于在线程里面获取bean 
 * 需要在 类上 标注 @Component ，否则没有将此工具类 注入到spring容器中
 * @author Lizheng
 * @since jdk1.6
 * 2019年5月29日
 */

@Component
public class SpringBeanUtil implements ApplicationContextAware{
    
    private static ApplicationContext applicationContext = null;  
      
    // 获取ApplicationContext对象
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        SpringBeanUtil.applicationContext = applicationContext;  
    }  
  
    /**
     * @Title: getBeanByName
     * @Description: TODO  通过bean的名字来获取Spring容器中的bean
     * @param beanName
     * @return
     * @return: Object
     */
    public static Object getBeanByName(String beanName) {  
        if (applicationContext == null){  
            return null;  
        }  
        return applicationContext.getBean(beanName);  
    }  
  
    public static <T> T getBean(Class<T> type) {  
        return applicationContext.getBean(type);  
    }  
}
