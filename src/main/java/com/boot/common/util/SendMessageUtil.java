package com.boot.common.util;

import java.net.URLEncoder;

import com.boot.common.util.environment.SystemPropertiesUtils;

/**
 * @author  WangShuo 
 * @date 创建时间：2018年1月9日 上午10:44:55 
 * @version 1.0 
 * @Description: 短信发送
 * @since 1.8
 * @return  
 */
public class SendMessageUtil {

	/**
	* @Description: 发送短信 
	* @author WangShuo
	* @date 2018年4月10日 上午9:44:19 
	* @param phone 电话
	* @param param 参数  多参数以@#分隔         eg: @#小明@#haha@#aa
	* @param srcid 短信模板 id
	* @param adminId
	 * @throws Exception 
	 */
	public static void  send(String phone,String param,String srcid,String adminId) throws Exception{
		StringBuffer listParam = new StringBuffer();
		listParam.append("uid="+  SystemPropertiesUtils.get("12114uuid"));
		listParam.append("&pwd="+SystemPropertiesUtils.get("12114pwd"));
		listParam.append("&mobile="+phone);
		listParam.append("&msg="+URLEncoder.encode(param, "GBK"));
		listParam.append("&srcid="+srcid);
		listParam.append("&para="+SystemPropertiesUtils.get("12114para"));
	    String back = SendUtil.sendToUrl(listParam.toString(), SystemPropertiesUtils.get("12114message.url", ""), "utf-8");
	    LogUtil.info("手机号="+phone+" param="+param+" srcid="+srcid+"  adminId="+adminId+" 发送返回："+back);
		
	}
}
