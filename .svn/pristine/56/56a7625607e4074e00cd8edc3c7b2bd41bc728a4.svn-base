package com.boot.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class SendUtil {
	
	public SendUtil() {
	}

	public static String sendToUrl(String sendtext, String urll, String encoding) throws Exception {
		return sendToUrl(sendtext, urll, encoding, Integer.valueOf(10));
	}

	public static String sendToUrl(String sendtext, String urll, String encoding, Integer seconds) throws Exception {
		URL url = new URL(urll);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		int httpTimeOut = seconds.intValue() * 1000;
		conn.setConnectTimeout(httpTimeOut);
		conn.setReadTimeout(httpTimeOut);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "text/xml charset=" + encoding);
		conn.setRequestProperty("Accept-Charset", encoding);
		conn.setRequestMethod("POST");
		conn.connect();
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encoding));
			output.write(sendtext);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.flush();
				output.close();
			}
		}

		return readStreamText(conn.getInputStream(), encoding);
	}

	public static String readStreamText(InputStream stream, String encoding) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(stream, encoding));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return sb.toString();
	}
	
	 /**
     * 发送请求
     * */
    public static String ssl(String url,String data,String mchId){
        StringBuffer message = new StringBuffer();
        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            //E:\eclipce_workspace\icfo-training-center\src\main\resources\static\cert\1462930802\apiclient_cert.p12
            String certFilePath =  AppPath.getRootPath()+"cert"+File.separator+mchId+ File.separator+"apiclient_cert.p12";
            System.out.println("*********************"+certFilePath);
            System.out.println(AppPath.getProjectUrl()+"################");
            FileInputStream instream = new FileInputStream(new File(certFilePath));
            keyStore.load(instream, mchId.toCharArray());
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            System.out.println("executing request" + httpost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        message.append(text);
                    }
                }
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return message.toString();
    }
    public static List<String> getSort(Map<String, Object> map) {
        List<String> list = new ArrayList<String>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            list.add(key);
        }
        Collections.sort(list);
        return list;
    }
}
