package com.boot.common.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	
	public static final MediaTracker tracker = new MediaTracker(new Component() {
        private static final long serialVersionUID = 1234162663955668507L;} 
    );
	
	public static synchronized  void ImageScale(File fileName,File toFileName,String maxWidth,
			String maxHeight) throws Exception 
	{
		ImageScale(fileName, toFileName, Integer.parseInt(maxWidth), Integer.parseInt(maxHeight));
	}
	/**
	 * 
	 * @param path 图片所在目录
	 * @param fileName 图片名
	 * @param toFileName 压缩后图片名
	 * @throws Exception 
	 * 
	 */
	
	public static synchronized  void ImageScale(File fileName,File toFileName,int maxWidth,
			int maxHeight) throws Exception {
		resizeSizer(fileName,toFileName, maxWidth, maxHeight);
		//resizeMagick(fileName, toFileName, maxWidth, maxHeight);
	}

	public static void imageScaleWidth(File fileName,File toFileName,String maxWidth) throws Exception
	{
		imageScaleWidth(fileName, toFileName, Integer.parseInt(maxWidth));
	}
	/**
	 * 固定宽度等比例压缩
	 * @param path 图片所在目录
	 * @param fileName 图片名
	 * @param toFileName 压缩后图片名
	 */
	public static void imageScaleWidth(File fileName,File toFileName,int maxWidth)  throws Exception{
		resizeSizer(fileName,toFileName, maxWidth, null);
		//resizeMagick(fileName, toFileName, maxWidth, null);
	}
	
	/**
	 * 固定高度等比例压缩
	 * 
	 * @param path
	 *            图片所在目录
	 * @param fileName
	 *            图片名
	 * @param toFileName
	 *            压缩后图片名
	 */
	public static void imageScaleHeight(File fileName, File toFileName, int maxHeight) throws Exception {
		resizeSizer(fileName, toFileName, null, maxHeight);
		// resizeMagick(fileName, toFileName, maxWidth, null);
	}
	
	/**
     * @param originalFile 原图像
     * @param resizedFile 压缩后的图像
     * @param width 图像宽
     * @param format 图片格式 jpg, png, gif(非动画)
     * @throws IOException
     */
    public static void resizeSizer(File originalFile, File resizedFile, Integer width,Integer height) throws Exception
    {
    	FileUtil.isDir(resizedFile.getParentFile());
    	String format = getFileSuffix(originalFile);
        if(format!=null && "gif".equals(format.toLowerCase())){
        	resizeGif(originalFile, resizedFile, width, height);
        	return;
        }
        
        FileInputStream fis = null;
        ByteArrayOutputStream byteStream = null;
        byte[] in = null;
        try
        {
        	fis = new FileInputStream(originalFile);
            byteStream = new ByteArrayOutputStream();
            int readLength = -1;
            int bufferSize = 1024;
            byte bytes[] = new byte[bufferSize];
            while ((readLength = fis.read(bytes, 0, bufferSize)) != -1) {
                byteStream.write(bytes, 0, readLength);
            }
            
            in = byteStream.toByteArray();
        }
        finally
        {
        	if(byteStream != null)
			{
				byteStream.flush();
			}
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(byteStream);
        }
        
    	Image inputImage = Toolkit.getDefaultToolkit().createImage( in );
        waitForImage( inputImage );
        int imageWidth = inputImage.getWidth( null );
        if ( imageWidth < 1 ) 
           throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
        int imageHeight = inputImage.getHeight( null );
        if ( imageHeight < 1 ) 
           throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
        
        // Create output image.
        if(height == null){
			float scale =(float)imageWidth/imageHeight;//
			width = imageWidth<width?imageWidth:width;
			height = (int) (width /scale );
        }else if (width == null) {
			float scale = (float) imageHeight / imageWidth;//
			height = imageHeight < height ? imageHeight : height;
			width = (int) (height / scale);
		} else {
			float scale = getRatio(imageWidth, imageHeight, width, height);
			//scale = 2.0f;
			width = (int) (scale * imageWidth);
			height = (int) (scale * imageHeight);
        }
        
        Image outputImage = inputImage.getScaledInstance( width, height, java.awt.Image.SCALE_DEFAULT);
        checkImage( outputImage );        
        encode(resizedFile, outputImage, format);        
    }    

    /** Encodes the given image at the given quality to the output stream. */
    private static void encode(File resizedFile, Image outputImage, String format) 
       throws Exception {
       int outputWidth  = outputImage.getWidth( null );
       if ( outputWidth < 1 ) 
          throw new IllegalArgumentException( "output image width " + outputWidth + " is out of range" );
       int outputHeight = outputImage.getHeight( null );
       if ( outputHeight < 1 ) 
          throw new IllegalArgumentException( "output image height " + outputHeight + " is out of range" );

       // Get a buffered image from the image.
       BufferedImage bi = new BufferedImage( outputWidth, outputHeight,
          BufferedImage.TYPE_INT_RGB );                                                   
       Graphics2D biContext = bi.createGraphics();
       
       if(format.equalsIgnoreCase("png"))
       {
	         //增加下面的代码使得背景透明 在这里只适应PNG JPG加这个会出错 -固特殊处理
	         bi = biContext.getDeviceConfiguration().createCompatibleImage(outputWidth, outputHeight, Transparency.TRANSLUCENT); 
	         biContext.dispose(); 
	         biContext = bi.createGraphics(); 
       }
       
       biContext.drawImage( outputImage, 0, 0, null );
       
       OutputStream outputStream = null;
       try
       {
    	   outputStream = new FileOutputStream(resizedFile);
    	   ImageIO.write(bi, format, outputStream);
       }
       finally
       {
    	   if(outputStream != null)
    	   {
    		   outputStream.flush();  
    	   }
    	   IOUtils.closeQuietly(outputStream);
       }
    } 
	
    /** Checks the given image for valid width and height. 
     * @throws InterruptedException */
    private static void checkImage( Image image ) throws InterruptedException {
       waitForImage( image );
       int imageWidth = image.getWidth( null );
       if ( imageWidth < 1 ) 
          throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
       int imageHeight = image.getHeight( null );
       if ( imageHeight < 1 ) 
          throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
    }
    
    /** Waits for given image to load. Use before querying image height/width/colors. 
     * @throws InterruptedException */
    private static void waitForImage( Image image ) throws InterruptedException {
       try {
          tracker.addImage( image, 0 );
          tracker.waitForID( 0 );
          tracker.removeImage(image, 0);
       } catch( InterruptedException e ) 
       { 
    	   throw new InterruptedException();
       }
    } 

	/**
	 * 获取长宽比例
	 * @param width
	 * @param height
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	public static float getRatio(int width, int height, int maxWidth,
			int maxHeight) {
		float Ratio = 1.0f;
		float widthRatio;
		float heightRatio;
		widthRatio = (float) maxWidth / width;
		heightRatio = (float) maxHeight / height;
		if (widthRatio < 1.0 || heightRatio < 1.0) {
			Ratio = widthRatio <= heightRatio ? widthRatio : heightRatio;
		}
		return Ratio;
	}

	/**
	 * 获取长宽比例
	 * @param width
	 * @param height
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	public static float getRatio2(int width, int height, int maxWidth,
			int maxHeight) {
		float Ratio = 1.0f;
		float widthRatio;
		float heightRatio;
		widthRatio = (float) maxWidth / width;
		heightRatio = (float) maxHeight / height;
		if (widthRatio < 1.0 || heightRatio < 1.0) {
			Ratio = widthRatio <= heightRatio ? widthRatio : heightRatio;
		}
		return Ratio;
	}
	/**
	 * 获得新的文件名
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static synchronized String getImageNameByTime(String fileName) throws Exception {
		Thread.sleep(1);
		StringBuilder sdate = new StringBuilder();
		sdate.append(System.currentTimeMillis());
		sdate.append(fileName.substring(fileName.lastIndexOf("."), fileName
				.length()).toLowerCase());
		return sdate.toString();
	}
	
	/**
	 * 用流（图片URL）保存图片
	 * @param URL地址
	 * @param 新文件保存地址
	 * 	@param 新图片宽度
	 * @param 新图片高度
	 * @throws Exception 
	 */
	public static String uploadImgByUrl(String imgUrl, File file,int width,int height) throws Exception{	
		   try 
		   {   
		      URL url = new URL(imgUrl);
		      BufferedImage src = ImageIO.read(url.openStream()); // 读入文件
		       Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);  
		       BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
		       Graphics g = tag.getGraphics();  
		       g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
		       g.dispose();  
		       ImageIO.write(tag, "JPEG", file);// 输出到文件流 
		      return "OK";
		    } catch (Exception e) {   
					      throw new Exception();
			} 
	}
	
	/**
     * 获得文件的后缀
     * @param originalFile
     * @return
     */
    public static String getFileSuffix(File originalFile){
    	String format = originalFile.getName().substring(originalFile.getName().lastIndexOf(".")+1, originalFile.getName().length());
    	return format;
    }
    
    /**
	 * 缩放gif图片
	 * @param originalFile 原图片
	 * @param resizedFile 缩放后的图片
	 * @param newWidth 宽度
	 * @param quality 缩放比例 (等比例)
	 * @throws IOException
	 */
    private static void resizeGif(File originalFile, File resizedFile, Integer newWidth, Integer newHeight) throws Exception {

    	ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null; 
        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null); 
        float scale = 1;
		
		if(newHeight == null){
			scale =(float)iWidth/iHeight;//
			int width = iWidth<newWidth?iWidth:newWidth;
			int height = (int) (width /scale );
			resizedImage = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}else{
			scale = getRatio(iWidth, iHeight, newWidth, newHeight);
			int tempWidth = (int) (scale * iWidth);
			int tempheight = (int) (scale * iHeight);
			resizedImage = i.getScaledInstance(tempWidth, tempheight, Image.SCALE_SMOOTH);
		}
        
        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage(); 
        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),
                                                        BufferedImage.TYPE_INT_RGB); 
        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics(); 
        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose(); 
        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = {0, softenFactor, 0, softenFactor, 1-(softenFactor*4), softenFactor, 0, softenFactor, 0};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null); 
        // Write the jpeg to a file.
        
        FileOutputStream out = null;
        try
        {
        	out = new FileOutputStream(resizedFile);        
            // Encodes image as a JPEG data stream
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage); 
            param.setQuality(scale, true); 
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
        }
        finally
        {
        	if(out != null)
        	{
        		out.flush();
        	}
        	IOUtils.closeQuietly(out);
        }
    }
    
    
    /**
	*
	* @param filrDir 要copy的目标文件夹路经
	* @param srcFilePath 原图路径
	* @param ext 扩展名
	* @param fileName 图片名
	* @param w 目标宽
	* @param h 目标高
	* @param per 百分比
     * @throws IOException 
	* @info GOOGLE MAP 专用
	*/
	    public static String resizeUrl(String srcFilePath,File filrDir,String ext,String fileName,int w,int h,float per) throws IOException
	    {
	    		FileOutputStream newimage = null;
	            Image src;
	            try {
	               //从URL读取
	               URL url = new URL(srcFilePath);
	               src = javax.imageio.ImageIO.read(url.openStream()); //构造Image对象
	               String img_midname = filrDir+"/" + fileName.substring(0,fileName.indexOf("."))+"." + ext.toLowerCase(); 
	               int old_w=src.getWidth(null); //得到源图宽
	               int old_h=src.getHeight(null);
	               int new_w=0;
	               int new_h=0; //得到源图长
	               double w2=(old_w*1.00)/(w*1.00);
	               double h2=(old_h*1.00)/(h*1.00);
	               /*
	               //图片跟据长宽留白，成一个正方形图  目前不需要
	               BufferedImage oldpic;
	               if(old_w>old_h)
	               {
	                   oldpic=new BufferedImage(old_w,old_w,BufferedImage.TYPE_INT_RGB);
	               }else{if(old_w<old_h){
	                   oldpic=new BufferedImage(old_h,old_h,BufferedImage.TYPE_INT_RGB);
	               }else{
	                    oldpic=new BufferedImage(old_w,old_h,BufferedImage.TYPE_INT_RGB);
	               }
	               }
	                Graphics2D g = oldpic.createGraphics();
	                g.setColor(Color.white);
	                if(old_w>old_h)
	                {
	                    g.fillRect(0, 0, old_w, old_w);
	                    g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
	                }else{
	                    if(old_w<old_h){
	                    g.fillRect(0,0,old_h,old_h);
	                    g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
	                    }else{
	                        //g.fillRect(0,0,old_h,old_h);
	                        g.drawImage(src.getScaledInstance(old_w, old_h,  Image.SCALE_SMOOTH), 0,0,null);
	                    }
	                }             
	                g.dispose();
	                src = oldpic;
	                //图片调整为方形结束 
	                */          
	               if(old_w>w)
	               new_w=(int)Math.round(old_w/w2);
	               else
	                   new_w=old_w;
	               if(old_h>h)
	            	   new_h=(int)Math.round(old_h/h2);//计算新图长宽
	               else
	                   new_h=old_h;
	               BufferedImage tag = new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_RGB);       
	               tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h,  Image.SCALE_SMOOTH), 0,0,null);
	               newimage=new FileOutputStream(img_midname); //输出到文件流
	               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
	               JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag);
	                /* 压缩质量  1:为图片质量最佳 （0-1）*/
	               jep.setQuality(per, true);
	               encoder.encode(tag, jep);//近JPEG编码
	               return "OK";
	             } 
	            catch (IOException ex) 
	            {
	            	  throw new IOException();
	            }
	            finally
	            {
	            	if(newimage != null)
	            	{
	            		newimage.flush();
	            	}
	            	IOUtils.closeQuietly(newimage);
	            }
	    }
	
	/**
	*
	* @param filrDir 要copy的目标文件夹路经
	* @param srcFilePath 原图路径
	* @param ext 扩展名
	* @param fileName 图片名
	* @param w 目标宽
	* @param h 目标高
	* @param per 百分比
	 * @throws IOException 
	*/
	    public static String ImageScale(String srcFilePath,File filrDir,String ext,String fileName,int w,int h,float per) throws IOException
	    {
	    	return resizeUrl(srcFilePath,filrDir, ext, fileName,w,h,per);
	    }
	    
	    /**
	     * 剪切图片
	     * @param src 原图片路径
	     * @param dest 目标图片路径
	     * @param sw 开始宽度
	     * @param sh 开始高度
	     * @param w 图片宽度
	     * @param h 图片高度
	     */
		public static void readUsingImageReader(File src, File dest, int sw, int sh,
				int w, int h) throws Exception {

			// 取得图片读入器  
			String fileName = dest.getName();
			//得到图片的后缀名（即图片格式）
			String after=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			Iterator<?> readers = ImageIO.getImageReadersByFormatName(after);

			ImageReader reader = (ImageReader) readers.next();

			// 取得图片读入流
			InputStream source = new FileInputStream(src);

			ImageInputStream iis = ImageIO.createImageInputStream(source);

			reader.setInput(iis, true);

			// 图片参数
			ImageReadParam param = reader.getDefaultReadParam();
			// 100，200是左上起始位置，300就是取宽度为300的，就是从100开始取300宽，就是横向100~400，同理纵向200~350的区域就取高度150
			// Rectangle rect = new Rectangle(100, 200, 300, 150);
			Rectangle rect = new Rectangle(sw, sh, w, h);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);

			ImageIO.write(bi, after, dest);

		}
	    
	  //剪切图片（剪切中心部分）
		public static void readMiddleUsingImageReader(File src, File dest,int w, int h) throws Exception {
			Image im=ImageIO.read(src);
			int x=(im.getWidth(null)-w)/2;
			int y=(im.getHeight(null)-h)/2;
			readUsingImageReader(src, dest, x, y, w, h);
		}
		
		/**
		 * 删除上传的图片
		 * @param fileDirectory 图片目录文件路径格式为 ：Constants.IMG_LOGO_PATH+"/"+enterprise.getId()
		 * @param imgUrl 原来的图片名称
		 */
		public static void deleteImg(String fileDirectory, String imgUrl){
			
			if(imgUrl!=null&&!"".equals(imgUrl)){
				File file = new File(fileDirectory);
				if(!file.exists())return;
				File[] feliList =  file.listFiles();
				for (int i = 0; i < feliList.length; i++) {
					if(feliList[i].isDirectory()){
						deleteImg(feliList[i].getPath(),imgUrl);
					}else{
						 String filename = feliList[i].getName().trim();  
			             if(filename.equals(imgUrl.trim())){ 
			            	 System.out.println("delete:   "+filename);
			            	 feliList[i].delete();
			             }
					}
				}
				System.gc();
			}
		}
		
		/**
		 * 删除上传的图片
		 * @param fileDirectory 图片目录文件路径格式为 ：Constants.IMG_LOGO_PATH+"/"+enterprise.getId()
		 * @param imgUrl[] 原来的图片名称可以是多个
		 */
		public static void deleteImg(String fileDirectory, String imgUrl[]){	
			if(imgUrl!=null&&!"".equals(imgUrl)){
				File file = new File(fileDirectory);
				if(!file.exists())return;
				File[] feliList =  file.listFiles();
				for (int i = 0; i < feliList.length; i++) {
					if(feliList[i].isDirectory()){
						deleteImg(feliList[i].getPath(),imgUrl);
					}else{
						 String filename = feliList[i].getName().trim(); 
						 for (int j = 0; j < imgUrl.length; j++) {
							 if(filename.equals(imgUrl[i].trim())){ 
				            	 System.out.println(filename);
				            	 feliList[i].delete();
				             }
						}
			            
					}
				}
				System.gc();
			}
		}
	    
		/**
		 * @Description: 图片到byte数组
		 * @param path
		 * @return
		 * @return byte[]
		 * @throws 
		 * @Create Date 2017年6月6日 下午2:11:04 By gaoyan
		 */
		public static byte[] image2byte(String path) {
			byte[] data = null;
			FileImageInputStream input = null;
			try {
				input = new FileImageInputStream(new File(path));
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int numBytesRead = 0;
				while ((numBytesRead = input.read(buf)) != -1) {
					output.write(buf, 0, numBytesRead);
				}
				data = output.toByteArray();
				output.close();
				input.close();
			} catch (FileNotFoundException ex1) {
				ex1.printStackTrace();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
			return data;
		}
		
		/**
		 * @Description: 图片到byte数组
		 * @param path
		 * @return
		 * @return byte[]
		 * @throws 
		 * @Create Date 2017年6月6日 下午2:11:04 By gaoyan
		 */
		public static byte[] image2byteByUrl(String urlPath) {
			byte[] data = null;
			try{
				URL url = new URL(urlPath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				InputStream inStream = conn.getInputStream();
				
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				data = outStream.toByteArray();
				inStream.close();
				outStream.close();
				}catch(Exception e){
				e.printStackTrace();
				}
			return data;
		}
		
		
}