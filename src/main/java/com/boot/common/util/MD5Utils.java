package com.boot.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * 类名: com.dns.common.util.MD5Utils
 * <p>
 * 描述: MD5加密工具类
 * </p>
 * 日期: 2013年12月4日 下午1:17:30
 * 
 * @author 蓝斌
 * @version V1.0
 * @since JDK1.5
 */
public class MD5Utils {

	/**
	 * <p>
	 * 描述: 将字符串MD5加密
	 * </p>
	 * 
	 * @param plainText
	 *            要进行加密的字符串
	 * @return MD5后的加密串
	 */
	public static String StringToMD5(String plainText) {
		String md5str = "";

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			md5str = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			// 默认密码："123456"
			md5str = "e10adc3949ba59abbe56e057f20f883e";
		}

		return md5str;
	}
	
	
	
	
	
	
	/**
     * MD5方法
     * 
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
        }

    /**
     * MD5验证方法
     * 
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(md5))
        {
            System.out.println("MD5验证通过");
            return true;
        }

            return false;
    }

}
