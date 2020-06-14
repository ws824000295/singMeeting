package com.boot.common.util;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CiphertextUtil {

//	private static String key = "dzeA2bm4ff63QnD9jTbdvikymphAfhkf";

	private static byte[] desEncrypt(byte[] plainText,String phpKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = phpKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}

	private byte[] desDecrypt(byte[] encryptText,String phpKey) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = phpKey.getBytes();
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte encryptedData[] = encryptText;
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	/**
	* @Description:  phpKey
	* @author WangShuo
	* @date 2018年8月13日 下午1:12:24 
	* @param input
	* @param phpKey
	* @return
	* @throws Exception
	 */
	public static String encrypt(String input,String phpKey) throws Exception {
		return base64Encode(desEncrypt(input.getBytes(),phpKey));
	}

	private String decrypt(String input,String phpKey) throws Exception {
		try {
			byte[] result = base64Decode(input);
			return new String(desDecrypt(result,phpKey));
		} catch (Exception e) {
			return null;
		}
	}

	private static String base64Encode(byte[] s) {
		if (s == null)
		return null;
		BASE64Encoder b = new sun.misc.BASE64Encoder();
		return b.encode(s);
	}

	private byte[] base64Decode(String s) throws IOException {
		if (s == null)
		return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);
		return b;
	}

}