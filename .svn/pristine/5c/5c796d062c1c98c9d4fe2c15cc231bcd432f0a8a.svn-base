package com.boot.config.security;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;


/**
 * 类名: com.boot.config.security.SecurityMessageSource
 * <p>
 * 描述:
 * </p>
 * 日期: 30/03/2017 15:38
 *
 * @author 蓝斌
 * @since JDK1.8
 */
public class SecurityMessageSource extends ResourceBundleMessageSource {

    public SecurityMessageSource() {
        setBasenames("security-messages", "org.springframework.security.message");
    }


    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new SecurityMessageSource());
    }

}
