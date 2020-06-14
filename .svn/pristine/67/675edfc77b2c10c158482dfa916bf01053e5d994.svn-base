package com.boot.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class AesTool {

	/**
	 * 加密
	 * 
	 * @param message
	 * @return
	 */
	public static String encrypt(String message,String keyString) {
		String result = ""; // DES加密字符串
		String newResult = "";// 去掉换行符后的加密字符串
		try {
			/*AES算法*/
			SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), "AES");//获得密钥
			/*获得一个私鈅加密类Cipher，DESede-》AES算法，ECB是加密模式，PKCS5Padding是填充方式*/
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 设置工作模式为加密模式，给出密钥
			byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
			BASE64Encoder enc = new BASE64Encoder();
			result = enc.encode(resultBytes);// 进行BASE64编码
			newResult = filter(result); // 去掉加密串中的换行符
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newResult;
	}
	
	public static String encrypt(String message){
		String keyString = "RNTDU89D3FchIkhKyMmarntd";
		return encrypt(message,keyString);
	}

	public static String decrypt(String message){
		String keyString = "RNTDU89D3FchIkhKyMmarntd";
		return decrypt(message,keyString);
	}
	
	/**
	 * 解密
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String message,String keyString){
		String result = "";
		try {
			/*AES算法*/
			SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), "AES");//获得密钥
			/*获得一个私鈅加密类Cipher，DESede-》AES算法，ECB是加密模式，PKCS5Padding是填充方式*/
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			
			BASE64Decoder dec = new BASE64Decoder();
			byte[] messageBytes = dec.decodeBuffer(message); // 进行BASE64编码
			cipher.init(Cipher.DECRYPT_MODE, secretKey); // 设置工作模式为解密模式，给出密钥
			byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
			result = new String(resultBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 去掉加密字符串换行符
	 * 
	 * @param str
	 * @return
	 */
	private static String filter(String str) {
		String output = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13) {
				sb.append(str.subSequence(i, i + 1));
			}
		}
		output = new String(sb);
		return output;
	}

}