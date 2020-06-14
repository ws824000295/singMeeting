package com.boot.common.util;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Encoder;

  
@SuppressWarnings("restriction")  
public class TripleDES {  
    static {  
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
    }  
  
    private static final String MCRYPT_TRIPLEDES = "DESede";  
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";  
  
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {  
        DESedeKeySpec spec = new DESedeKeySpec(key);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);  
        SecretKey sec = keyFactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);  
        IvParameterSpec IvParameters = new IvParameterSpec(iv);  
        cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);  
        return cipher.doFinal(data);  
    }  
  
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {  
        DESedeKeySpec spec = new DESedeKeySpec(key);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
        SecretKey sec = keyFactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);  
        IvParameterSpec IvParameters = new IvParameterSpec(iv);  
        cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);  
        return cipher.doFinal(data);  
    }  
  
  
  
    public static void main(String args[]) throws Exception {  
        String plainText = "a12*&1c中文";  
        //key
        String key = "23cc0aa480c1948c";  
        //iv
        String iv = "342352f6";  
        
        if(key.length()<24){
        	int len = key.length();
        	for(int i=0;i<(24-len);i++){
        		key+="0";
        	}
        }
        
        BASE64Encoder en = new BASE64Encoder();
        System.out.println("plain text: " + plainText);  
        byte[] encrypt = TripleDES.encrypt(plainText.getBytes(), key.getBytes(), iv.getBytes());  
        System.out.println("cipher text: " + en.encode(encrypt));  
        System.out.println("decrypt text: " + new String(TripleDES.decrypt(encrypt, key.getBytes(), iv.getBytes()), "UTF-8"));  
    }  
  
}  