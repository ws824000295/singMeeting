package com.boot.common.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.boot.common.util.environment.SystemPropertiesUtils;
import com.dns.common.terminal.base.SendUtil;
import com.google.gson.Gson;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtil {

	private static final CacheManager manager = CacheManager.create(CacheUtil.class.getClassLoader().getResource("/ehcache-user.xml").getPath());

	//测试公众号
//	private static final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxa55ed0a04c33b172&secret=a7a0ef2679e97e1d8dd53b8fca71d9f9";
	private static final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+SystemPropertiesUtils.get("APP_ID")+"&secret="+SystemPropertiesUtils.get("APP_SECRET")+"";

	public static String getCacheTicket() {
		String ticket = "";
		Cache cache = manager.getCache("AccessToken");
		String key = "WeiXinTicket";
		Element element = cache.get(key);
		if (element == null) {
			ticket = getTicket();
			if (!StringUtils.isBlank(ticket)) {
				cache.put(new Element(key, ticket));
			}
		} else {
			ticket = (String) element.getObjectValue();
		}
		return ticket;
	}

	private static String getTicket() {
		try {
			String token = getCacheAccessToken();
			String ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
			String json = SendUtil.sendToUrl("", ticket, "utf-8");
			Map<String, Object> obj = new Gson().fromJson(json, Map.class);
			return obj.get("ticket").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getCacheAccessToken() {
		String token = "";
		Cache cache = manager.getCache("AccessToken");
		String key = "WeiXinAccessToken";
		Element element = cache.get(key);
		if (element == null) {
			token = getToken();
			if (!StringUtils.isBlank(token)) {
				cache.put(new Element(key, token));
			}
		} else {
			token = (String) element.getObjectValue();
		}
		return token;
	}
	
	private static String getToken() {
		String url =  tokenUrl;
		try {
			String json = SendUtil.sendToUrl("", url, "utf-8");
			System.out.println(json);
			Map<String, Object> obj = new Gson().fromJson(json, Map.class);
			return obj.get("access_token").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
}
