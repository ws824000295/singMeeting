package com.boot.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private static final int BUFFER = 8192;   
  
    public static void compress(File file, ZipOutputStream out, String basedir) {    
        /* 判断是目录还是文件 */    
        if (file.isDirectory()) {    
            System.out.println("压缩：" + basedir + file.getName());    
            compressDirectory(file, out, basedir);    
        } else {    
            System.out.println("压缩：" + basedir + file.getName());    
            compressFile(file, out, basedir);    
        }    
    }   
    
    public static void compress(File file, ZipOutputStream out) {
    	String basedir = "";    
        /* 判断是目录还是文件 */    
        if (file.isDirectory()) {    
            System.out.println("压缩：" + basedir + file.getName());    
            compressDirectory(file, out, basedir);    
        } else {    
            System.out.println("压缩：" + basedir + file.getName());    
            compressFile(file, out, basedir);    
        }    
    }  
  
    /** 压缩一个目录 */    
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {    
        if (!dir.exists())    
            return;   
  
        File[] files = dir.listFiles();    
        for (int i = 0; i < files.length; i++) {    
            /* 递归 */    
            compress(files[i], out, basedir + dir.getName() + "/");    
        }    
    }   
  
    /** 压缩一个文件 */    
    private static void compressFile(File file, ZipOutputStream out, String basedir) {    
        if (!file.exists()) {    
            return;    
        }    
        try {    
            BufferedInputStream bis = new BufferedInputStream(    
                    new FileInputStream(file));    
            ZipEntry entry = new ZipEntry(basedir + file.getName());    
            out.putNextEntry(entry);    
            int count;    
            byte data[] = new byte[BUFFER];    
            while ((count = bis.read(data, 0, BUFFER)) != -1) {    
                out.write(data, 0, count);    
            }    
            bis.close();    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }  
}
