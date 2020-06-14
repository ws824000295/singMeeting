package com.boot.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 类名: com.dns.common.utils.QRCodeEncoderHandler
 * <p>
 * 描述: 二维码生成
 * </p>
 * 日期: 2015-2-3 上午10:56:46
 * 
 * @author 李鸿源
 * @version V1.0
 * @since JDK1.5
 */
public class QRCodeEncoderHandler {

	/**
	 * 
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 * 
	 * @param imgPath
	 */

	public static void encoderQRCode(char QrcodeErrorCorrect, char QrcodeEncodeMode, int setQrcodeVersion, String content, String imgPath) {

		try {

			Qrcode qrcodeHandler = new Qrcode();
			// 设置纠错级别
			/**
			 * 错误修正容量 L水平 7%的字码可被修正 M水平 15%的字码可被修正 Q水平 25%的字码可被修正 H水平 30%的字码可被修正
			 */

			qrcodeHandler.setQrcodeErrorCorrect(QrcodeErrorCorrect);
			// 设置编码方式
			qrcodeHandler.setQrcodeEncodeMode(QrcodeEncodeMode);
			// 二维码版本号
			/**
			 * 第三个参数“7”是二维码的版本号，也象征着二维码的信息容量； 二维码可以看成一个黑白方格矩阵，版本不同，矩阵长宽方向方格的总数量分别不同， 版本1为21*21矩阵，版本每增1，二维码的两个边长都增4； 所以版本7为45*45的矩阵；
			 * 最高版本为是40，是177*177的矩阵
			 */

			// 计算单边长
			int width = (setQrcodeVersion - 1) * 4 + 21;
			int point = 5;
			int imgWidth = point * width + point * 2;
			qrcodeHandler.setQrcodeVersion(setQrcodeVersion);
			byte[] contentBytes = content.getBytes("UTF-8");
			BufferedImage bufImg = new BufferedImage(imgWidth, imgWidth,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgWidth, imgWidth);
			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = point - 1;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 400) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * point + pixoff, i * point + pixoff, point, point);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
			}
			gs.dispose();
			bufImg.flush();
			File imgFile = new File(imgPath);
			if (!imgFile.exists()) {
				imgFile.mkdirs();
			}
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "jpg", imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getImageByUrl(String imageurl, String savepath, String name) {

		try {
			// 构造URL
			URL url = new URL(imageurl);
			// 打开连接
			URLConnection con = url.openConnection();
			// 输入流
			InputStream is = con.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// Map<String, Object> property =
			// getProperties("/gbtags.properties");
			File file = new File(savepath);// (String) property.get("ewmPath"));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 输出的文件流
			OutputStream os = new FileOutputStream(savepath + name);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}
	
	/**
	 * 
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {
		String imgPath = "D:/QRCode.png";
		// String content = "http://epay.10010.com/client";
		String content = "又得瑟了，好好工作吧，晚上投产。。。。T_T";
		QRCodeEncoderHandler handler = new QRCodeEncoderHandler();
		// 纠错级别
		char QrcodeErrorCorrect = 'M';
		// 编码格式
		char QrcodeEncodeMode = 'B';
		// 二维码版本
		int setQrcodeVersion = 3;
		QRCodeEncoderHandler.encoderQRCode(QrcodeErrorCorrect, QrcodeEncodeMode, setQrcodeVersion, content, imgPath);
		System.out.println("encoder QRcode success");
	}
}
