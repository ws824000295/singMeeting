package com.boot.common.util.environment;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 类名: com.dns.common.utils.environment.SystemPropertiesUtils
 * <p>
 * 描述: 系统配置文件的工具类
 * </p>
 * 日期: 2014-6-24 下午10:53:11
 * 
 * @author 蓝斌
 * @version V1.0
 * @since JDK1.5
 */
public class SystemPropertiesUtils {

	/**
	 * 描述: 保存系统配置为Properties对象
	 */
	private static Properties props;

	// 静态初始化块，用于加载系统配置文件
	static {
		Resource resource = new ClassPathResource("/application.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
			props = new Properties();
		}
	}

	/**
	 * <p>
	 * 描述: 从系统配置文件中获取指定的属性值
	 * </p>
	 * 
	 * @param key
	 *            属性的键
	 * @param defaultValue
	 *            如果未获取到指定属性，则返回该默认值
	 * @return 属性的值
	 */
	public static String get(String key, String defaultValue) {
		return SystemPropertiesUtils.props.getProperty(key, defaultValue);
	}
	
	public static String get(String key) {
		return SystemPropertiesUtils.props.getProperty(key, "");
	}

}
