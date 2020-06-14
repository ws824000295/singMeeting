package com.boot.common.util;

import java.io.File;

/**
 * @author  WangShuo 
 * @date 创建时间：2018年1月12日 下午5:46:18 
 * @version 1.0 
 * @Description: 删除文件下的所有内容（不删除自己）
 * @since 1.8
 * @return  
 */
public class FileDeleteUtil {

	
	public static void main(String[] args) {  
        String fileRoot = "F:/aa";  
        delFolder(fileRoot);  
        System.out.println("deleted");  
    }  
	
 //  folderPath 文件夹完整绝对路径  
    public static void delFolder(String folderPath) {  
        try {  
            delAllFile(folderPath); // 删除完里面所有内容  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 删除指定文件夹下所有文件  
    public static boolean delAllFile(String path) {  
        boolean flag = false;  
        File file = new File(path);  
        if (!file.exists()) {  
            return flag;  
        }  
        if (!file.isDirectory()) {  
            return flag;  
        }  
        String[] tempList = file.list();  
        File temp = null;  
        for (int i = 0; i < tempList.length; i++) {  
            if (path.endsWith(File.separator)) {  
                temp = new File(path + tempList[i]);  
            } else {  
                temp = new File(path + File.separator + tempList[i]);  
            }  
            if (temp.isFile()) {  
                temp.delete();  
            }  
            if (temp.isDirectory()) {  
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件  
//              delFolder(path + "/" + tempList[i]);// 再删除空文件夹  
                flag = true;  
            }  
        }  
        return flag;  
    }  
}
