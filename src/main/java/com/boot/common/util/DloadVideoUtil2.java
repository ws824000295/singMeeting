package com.boot.common.util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


/**
 * @author WangShuo
 * @date 创建时间：2018年10月18日 下午1:57:06
 * @version 1.0
 * @Description:  
 * @since 1.8
 * @return
 */
public class DloadVideoUtil2 {
	
//	private final static String FFMPEG_PATH  ;
	
	 //windows系统使用, FFmpeg文件和DloadImgUtil 同一目录下
	//static {  FFMPEG_PATH = DloadVideoUtil2.class.getResource("ffmpeg.exe").getFile(); } 
	 
	
	// linux 系统使用
//	 static {    FFMPEG_PATH =DloadVideoUtil2.class.getResource("ffmpeg").getFile(); }
	
//	public static String downloadMedia(String mediaId, String amrfile,String mp3File,String token) {
//		String filePath = null;
//		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaId;
//		String returnstr="0";
//		BufferedInputStream bis = null;
//		FileOutputStream fos = null;
//		HttpsURLConnection conn = null;
//		try {
//		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
//		TrustManager[] tm = { new MyX509TrustManager() };
//		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//		sslContext.init(null, tm, new java.security.SecureRandom());
//		// 从上述SSLContext对象中得到SSLSocketFactory对象
//		SSLSocketFactory ssf = sslContext.getSocketFactory();
//		URL url = new URL(requestUrl);
//		conn = (HttpsURLConnection) url.openConnection();
//		conn.setSSLSocketFactory(ssf);
//		conn.setDoOutput(true);
//		conn.setDoInput(true);
//		conn.setUseCaches(false);
//		// 设置请求方式（GET/POST）
//		conn.setRequestMethod("GET");
//		filePath = amrfile;
//
//		File amrPath = new File(filePath);
//
//		bis = new BufferedInputStream(conn.getInputStream());
//		fos = new FileOutputStream(amrPath);
//	    new File(mp3File);
//
//		byte[] buf = new byte[8096];
//		int size = 0;
//		while ((size = bis.read(buf)) != -1)
//		fos.write(buf, 0, size);
//
//			amr2mp31(filePath, mp3File);
//
//			//删除amr文件
//            if(amrPath.isFile() && amrPath.exists()){
//            	amrPath.delete();
//            }
//
//		} catch (Exception e) {
//			returnstr="-1";
//			e.printStackTrace();
//		} finally {
//		conn.disconnect();// 释放资源
//			try {
//				// 关闭流，释放资源
//				if (fos != null) { fos.close(); }
//				if (bis != null) { bis.close(); }
//			 }catch (Exception e) {
//				e.getStackTrace();
//			}
//
//		}
//		return returnstr;
//	}



	public static String aliyUpload(String mediaId, String fileName,String token,String uploadUrl) {
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaId;
		String returnstr="0";
		HttpsURLConnection conn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod("GET");
			InputStream inputStream = conn.getInputStream();
			returnstr = HttpTool.uploadFile(inputStream,uploadUrl,fileName);
		} catch (Exception e) {
			returnstr="-1";
			e.printStackTrace();
		} finally {
			conn.disconnect();// 释放资源
		}
		return returnstr;
	}



//	public static void amr2mp31(String amrFileName, String mp3FileName) throws IOException, InterruptedException {
//			Runtime runtime = Runtime.getRuntime();
//			Process process = runtime.exec(FFMPEG_PATH + " -i "+amrFileName+" -ar 8000 -ac 1 -y -ab 12.4k " + mp3FileName); //12.4
//			InputStream in = process.getErrorStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			try {
//				String line = null;
//				while((line = br.readLine())!=null){
//					int exitValue = process.waitFor();
//				}
//			} finally {
//				in.close();
//			}
//	}



}
