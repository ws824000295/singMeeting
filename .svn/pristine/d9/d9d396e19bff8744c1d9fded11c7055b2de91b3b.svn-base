package com.boot.common.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;  
  
  
public class ModifyPic {  
  
    private Font font = new Font("华文彩云", Font.PLAIN, 40);// 添加字体的属性设置  
  
    private Graphics2D g = null;  
  
    private int fontsize = 0;  
  
    private int x = 0;  
  
    private int y = 0;  
    
    private Color fontColor = null;
  
    /** 
     * 导入本地图片到缓冲区 
     */  
    public BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) { 
        	
        }  
        return null;  
    }  
  
    /** 
     * 导入网络图片到缓冲区 
     */  
    public BufferedImage loadImageUrl(String imgName) {  
        try {  
            URL url = new URL(imgName);  
            return ImageIO.read(url);  
        } catch (IOException e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
        return null;  
    }  
  
    /** 
     * 生成新图片到本地 
     */  
    public void writeImageLocal(String newImage, BufferedImage img) {  
        if (newImage != null && img != null) {  
            try {  
                File outputfile = new File(newImage);  
                ImageIO.write(img, "jpg", outputfile);  
            } catch (IOException e) {  
            	e.printStackTrace();
                System.out.println(e.getMessage());  
            }  
        }  
    }  
  
    /** 
     * 设定文字的字体等 
     */  
    public void setFont(String fontStyle, int fontSize,Color fontColor) {  
        this.fontsize = fontSize;  
        //加粗Font.BOLD  正常Font.PLAIN
        this.font = new Font(fontStyle, Font.BOLD, fontSize);  
        this.fontColor = fontColor;
    }  
  
    /** 
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本） 
     */  
    public BufferedImage modifyImage(BufferedImage img, Object content, int x,int y) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            //消除字体锯齿
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setBackground(Color.WHITE);  
            if(this.fontColor==null){
            	g.setColor(Color.orange);//设置字体颜色  
            }else{
            	g.setColor(this.fontColor);//设置字体颜色  
            }
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标  
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (content != null) {  
                g.drawString(content.toString(), this.x, this.y);  
            }  
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    } 
    
    /** 
     * 修改图片（只输出一行文本） 居中显示
     */
    public BufferedImage modifyMiddleImage(BufferedImage img, Object content,int x,int y) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();
            //消除字体锯齿
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setBackground(Color.WHITE);  
            if(this.fontColor==null){
            	g.setColor(Color.orange);//设置字体颜色  
            }else{
            	g.setColor(this.fontColor);//设置字体颜色  
            }
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标  
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                JLabel label = new JLabel(content.toString());
                FontMetrics metrics = label.getFontMetrics(label.getFont());
                int textW = metrics.stringWidth(label.getText()); //字符串的宽
                x = (w/2)-textW;
            }  
            if (content != null) {  
                g.drawString(content.toString(), x, y);  
            }  
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
        return img;  
    } 
    
    /** 
     * 修改图片（只输出一行文本） 居中显示: 邀请卡专用
     * z : 居中左移z像素
     */
    public BufferedImage modifyMiddleImageInvite(BufferedImage img, Object content,int x,int y,int z) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();
            //消除字体锯齿
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setBackground(Color.WHITE);  
            if(this.fontColor==null){
            	g.setColor(Color.orange);//设置字体颜色  
            }else{
            	g.setColor(this.fontColor);//设置字体颜色  
            }
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标  
            JLabel label = new JLabel(content.toString());
            FontMetrics metrics = label.getFontMetrics(label.getFont());
            int textW = metrics.stringWidth(label.getText()); //字符串的宽
            x = (w/2)-textW;
            if (content != null) {
                g.drawString(content.toString(), x-z, y);  
            }  
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
        return img;  
    }
  
    /** 
     * 修改图片,返回修改后的图片缓冲区（输出多个文本段） xory：true表示将内容在一行中输出；false表示将内容多行输出 
     */  
    public BufferedImage modifyImage(BufferedImage img, Object[] contentArr,  
            int x, int y, boolean xory) {  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.RED);  
            if (this.font != null)  
                g.setFont(this.font);  
            // 验证输出位置的纵坐标和横坐标  
            if (x >= h || y >= w) {  
                this.x = h - this.fontsize + 2;  
                this.y = w;  
            } else {  
                this.x = x;  
                this.y = y;  
            }  
            if (contentArr != null) {  
                int arrlen = contentArr.length;  
                if (xory) {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.x += contentArr[i].toString().length()  
                                * this.fontsize / 2 + 5;// 重新计算文本输出位置  
                    }  
                } else {  
                    for (int i = 0; i < arrlen; i++) {  
                        g.drawString(contentArr[i].toString(), this.x, this.y);  
                        this.y += this.fontsize + 2;// 重新计算文本输出位置  
                    }  
                }  
            }  
            g.dispose();  
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  
  
    /** 
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本） 
     *  
     * 时间:2007-10-8 
     *  
     * @param img 
     * @return 
     */  
    public BufferedImage modifyImageYe(BufferedImage img) {  
  
        try {  
            int w = img.getWidth();  
            int h = img.getHeight();  
            g = img.createGraphics();  
            g.setBackground(Color.WHITE);  
            g.setColor(Color.blue);//设置字体颜色  
            if (this.font != null)  
                g.setFont(this.font);  
            g.drawString("reyo.cn", w - 85, h - 5);  
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
  
        return img;  
    }  
  
    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d,int x,int y,int w,int h) {  
        try {  
            g = d.createGraphics();  
            g.drawImage(b, x, y, w, h, null);  
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
        return d;  
    }  
  
    //制作圆头像
    public BufferedImage modifyImagetogeterRound(BufferedImage b, BufferedImage d,int x,int y,int w,int h) {  
        try {  
            g = d.createGraphics();  
            //图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(x, y, w, h);
            //需要保留的区域
            g.setClip(shape);
            g.drawImage(b, x, y, w, h, null);
            g.dispose();  
        } catch (Exception e) {  
        	e.printStackTrace();
            System.out.println(e.getMessage());  
        }  
        return d;  
    }
    
    /** 
     * 改版设定文字的字体等 
     */  
    public void setNewFont(String fontStyle, int fontSize,Color fontColor,BufferedImage d) {  
        this.fontsize = fontSize;
        g = d.createGraphics();  
        //加粗Font.BOLD  正常Font.PLAIN
        this.font = new Font(fontStyle, Font.BOLD, fontSize);  
        this.fontColor = fontColor;
    }
    
    public static void main(String[] args) {  
  
    	/*测试圆形头像有问题 
    	ModifyPic tt = new ModifyPic();  
        BufferedImage beiJingTu = tt.loadImageLocal("f://aa/one_new2.jpg");//背景图
        try {
			makeCircularImg("f://aa/userIcon.jpg","f://aa/userIcon.jpg",110,110);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedImage b = tt.loadImageLocal("f://aa/userIcon.jpg");//头像
        tt.setFont("华文宋体", 30, Color.white);//设置图片中文字显示格式
        beiJingTu = tt.modifyImage(beiJingTu,"鲜酿啤酒大比拼活动",200,510);// 向图片上写文字
    	//合成新图路径
        tt.writeImageLocal("f://aa/one_new.jpg",tt.modifyImagetogeter(b, beiJingTu,30,272,110,110));//距左  居上 宽度 长度
    	*/    	
    	ModifyPic tt = new ModifyPic();  
	    try {
			makeCircularImg("f:/aa/touxiang.jpg","f:/aa/touxiang_round.jpg",110,110);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //背景图
        BufferedImage d = tt.loadImageLocal("f:/aa/share_1.jpg");  
        //将多张图片合在一起  
        BufferedImage b = tt.loadImageLocal("f:/aa/touxiang_round.jpg");  
        
        //往图片上写文件  
        tt.setFont("黑体", 25, Color.black);
        d = tt.modifyImage(d,"送您一份好礼",208,120);
        d = tt.modifyImage(d,"愤怒的小鸟",208,150);
        d = tt.modifyImage(d,"周立国",208,180);
       
        tt.writeImageLocal("f:/aa/new_touxiang.jpg", tt.modifyImagetogeter(b, d,75,73,120,110));  
        System.out.println("success");  
    }  
    
    public static String makeCircularImg(String srcFilePath, String circularImgSavePath,int targetSize, int cornerRadius) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(srcFilePath));
        BufferedImage circularBufferImage = roundImage(bufferedImage,targetSize,cornerRadius);
        ImageIO.write(circularBufferImage, "png", new File(circularImgSavePath));
        return circularImgSavePath;
    }

    private static BufferedImage roundImage(BufferedImage image, int targetSize, int cornerRadius) {
        BufferedImage outputImage = new BufferedImage(targetSize+10, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(10, 0, targetSize, targetSize, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }
  
}  