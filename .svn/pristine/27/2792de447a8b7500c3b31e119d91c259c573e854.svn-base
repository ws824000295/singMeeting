/**   
* @Title: Main.java 
* @Package com.boot.common.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2020年1月9日 下午4:43:16 
* @version V1.0   
*/
package com.boot.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: Main 
 * @Description: 获取某个文件夹下 所有的文件名称
 * @author 王朔
 * @date 2020年1月9日 下午4:43:16   
 */
public class FileAllUtils {
	
	public static List<String> pathList = new ArrayList<String>();
	
	public static void main(String[] args) {
		String path = "D:/a/b/c"; 
		File f = new File(path);
		FileAllUtils.getFile(f);
    }
	
	
	
	
	public static void getFile(File file){
		if(file != null){
			File[] f = file.listFiles();
			if(f != null){
				for(int i=0;i<f.length;i++){
					getFile(f[i]);
				}
			}else{
				System.out.println(file);
				pathList.add(file.getPath());
			}
		}
	} 
	
}
