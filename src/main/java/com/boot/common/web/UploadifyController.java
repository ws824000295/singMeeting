//package com.boot.common.web;
//
//import java.io.File;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.aliyun.oss.OSSClient;
//import com.boot.common.util.FileUtil;
//import com.boot.common.util.ImageUtils;
//import com.boot.common.util.LogUtil;
//import com.boot.config.Constants;
//import com.boot.domain.AdminUser;
//
//import net.sf.json.JSONObject;
//
//@Controller
//@RequestMapping("/common/uploadify")
//public class UploadifyController {
//	
//	@Autowired
//	private Environment environment;
//	
//	@RequestMapping("/upload")
//	public void upload(HttpServletRequest request,HttpServletResponse response,MultipartFile fileUpload,String size)  {
//		response.setContentType("text/html;charset=utf-8");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("result", "no");
//		PrintWriter pw = null;
//		try{
//			pw = response.getWriter();
//			AdminUser en = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_KEY);
//			String uploadPath = request.getSession().getServletContext().getRealPath("/")+Constants.IMAGE_SUFFIX_PATH + "/" + en.getId() + "/image/";
//			String fileName =  fileUpload.getOriginalFilename(); 
//			//校验
//			if(fileUpload.getSize()/1024>2048){
//				map.put("message", "请上传小于2M的图片");
//			}else if(!FileUtil.checkIsImage(fileName)||!checkImageTypeVailable(fileUpload.getBytes())){
//				map.put("message", "请上传正式格式的图片");
//			}else{
//				fileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
//				File file = new File(uploadPath+"/original/"+fileName);
//				if(!file.getParentFile().exists()){
//				   file.getParentFile().mkdirs();
//				}
//				file.createNewFile();
//				fileUpload.transferTo(file);  
//				for(String s:size.split(",")){
//					ImageUtils.imageScaleWidth(file, new File(uploadPath+s+"/"+fileName), Integer.parseInt(s));
//					map.put("path_"+s, Constants.IMAGE_SUFFIX_PATH + "/" + en.getId() + "/image/"+s+"/" + fileName);
//					
//					String uppath = Constants.IMAGE_SUFFIX_PATH + "/" + en.getId() + "/image/"+s+"/" + fileName;
//					if(uppath.startsWith("/")){
//						uppath = uppath.substring(1);
//					}
//					//上传阿里云
//					String endpoint = environment.getProperty("ali_endpoint");
//					String accessKeyId = environment.getProperty("ali_accessKeyId");
//					String accessKeySecret = environment.getProperty("ali_accessKeySecret");
//					String bucketName = environment.getProperty("ali_bucketName");
//					OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//					ossClient.putObject(bucketName, uppath, new File(uploadPath+s+"/"+fileName));
//					ossClient.shutdown();
//				}
//				
//				map.put("fileName", fileName);
//				map.put("result", "yes");
//			}
//		}catch(Exception e){
//			LogUtil.error(e);
//		}finally{
//			if(null!=pw){
//				pw.print(JSONObject.fromObject(map).toString());
//				pw.close();
//			}
//		}
//	}
//	@RequestMapping("/uploadVideo")
//	public void uploadVideo(HttpServletRequest request,HttpServletResponse response,MultipartFile fileUpload)  {
//		LogUtil.info("======================sah上传视频===============================");
//		response.setContentType("text/html;charset=utf-8");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("result", "no");
//		PrintWriter pw = null;
//		try{
//			pw = response.getWriter();
//			AdminUser en = (AdminUser) request.getSession().getAttribute(Constants.ADMIN_USER_KEY);
//			String uploadPath = request.getSession().getServletContext().getRealPath("/")+Constants.VIDEO_SUFFIX_PATH + "/" + en.getId();
//			String fileName =  fileUpload.getOriginalFilename(); 
//			LogUtil.info("======================sah上传视频fileName="+fileName+"===============================");
//			//校验
//			if(fileUpload.getSize()/(1024*2048*5)>1){
//				map.put("message", "请上传小于5M的视频");
//			}else if(!FileUtil.checkIsVideo(fileName)){//||!checkImageTypeVailable(fileUpload.getBytes())
//				map.put("message", "请上传正式格式的视频");
//			}else{
//				fileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
//				File file = new File(uploadPath+"/"+fileName);
//				if(!file.getParentFile().exists()){
//				   file.getParentFile().mkdirs();
//				}
//				file.createNewFile();
//				fileUpload.transferTo(file);  
//				map.put("videoPath", Constants.VIDEO_SUFFIX_PATH + "/" + en.getId() +"/" + fileName);
//				
//				map.put("fileName", fileName);
//				map.put("result", "yes");
//				LogUtil.info("======================sah上传视频map="+map+"===============================");
//
//				////上传阿里云
//				System.out.println("上传阿里云开始");
//				String uppath = Constants.VIDEO_SUFFIX_PATH + "/" + en.getId() +"/" + fileName;
//				if(uppath.startsWith("/")){
//					uppath = uppath.substring(1);
//				}
//				String endpoint = environment.getProperty("ali_endpoint");
//				String accessKeyId = environment.getProperty("ali_accessKeyId");
//				String accessKeySecret = environment.getProperty("ali_accessKeySecret");
//				String bucketName = environment.getProperty("ali_bucketName");
//				OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//				ossClient.putObject(bucketName, uppath, new File(uploadPath+"/"+fileName));
//				ossClient.shutdown();
//				System.out.println("上传阿里云结束");
//			}
//		}catch(Exception e){
//			LogUtil.error(e);
//		}finally{
//			if(null!=pw){
//				pw.print(JSONObject.fromObject(map).toString());
//				pw.close();
//			}
//		}
//	}
//
//	public static boolean checkImageTypeVailable(byte[] imgContent) {
//		if (imgContent == null) {
//			return false;
//		}
//		try {
//			int len = imgContent.length;
//			byte n1 = imgContent[len - 2];
//			byte n2 = imgContent[len - 1];
//			byte b0 = imgContent[0];
//			byte b1 = imgContent[1];
//			byte b2 = imgContent[2];
//			byte b3 = imgContent[3];
//			byte b4 = imgContent[4];
//			byte b5 = imgContent[5];
//			byte b6 = imgContent[6];
//			byte b7 = imgContent[7];
//			byte b8 = imgContent[8];
//			byte b9 = imgContent[9];
//			// GIF(G I F 8 7 a)
//			if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8' && b4 == (byte) '7'
//					&& b5 == (byte) 'a') {
//				return true;
//				// GIF(G I F 8 9 a)
//			} else if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F' && b3 == (byte) '8' && b4 == (byte) '9'
//					&& b5 == (byte) 'a') {
//				return true;
//				// PNG(89 P N G 0D 0A 1A)
//			} else if (b0 == -119 && b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G' && b4 == 13 && b5 == 10
//					&& b6 == 26) {
//				return true;
//				// JPG JPEG(FF D8 --- FF D9)
//			} else if (b0 == -1 && b1 == -40 && n1 == -1 && n2 == -39) {
//				return true;
//			} else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
//				return true;
//			} else if (b6 == (byte) 'E' && b7 == (byte) 'x' && b8 == (byte) 'i' && b9 == (byte) 'f') {
//				return true;
//				// BMP(B M)
//			} else if (b0 == (byte) 'B' && b1 == (byte) 'M') {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			return false;
//		}
//	}
//}
