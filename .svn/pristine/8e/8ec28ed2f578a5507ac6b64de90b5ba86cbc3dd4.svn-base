package com.boot.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;


/**
 * 文件操作公共类
 * @author 刘勇涛
 *
 */
public class FileUtil 
{
    public static void copyFile(String sourceFile, String targetFile) throws Exception
    {
    	copyFile(new File(sourceFile), new File(targetFile));
    }
    
    /**
     * 拷贝文件
     * @param sourceFile		源文件
     * @param targetFile			目的地文件
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws Exception
    {
    	isDir(targetFile.getParentFile());
    	FileInputStream input = null;
    	BufferedInputStream inBuff = null;
    	FileOutputStream output = null;
    	BufferedOutputStream outBuff = null;
    	try
    	{
    		// 新建文件输入流并对它进行缓冲 
            input = new FileInputStream(sourceFile); 
            inBuff=new BufferedInputStream(input); 
     
            // 新建文件输出流并对它进行缓冲 
            output = new FileOutputStream(targetFile); 
            outBuff=new BufferedOutputStream(output); 
             
            // 缓冲数组 
            byte[] b = new byte[1024 * 5]; 
            int len; 
            while ((len =inBuff.read(b)) != -1) { 
                outBuff.write(b, 0, len); 
            } 
    	}
    	finally
    	{
    		// 刷新此缓冲的输出流 
    		if(outBuff != null)
    		{
    			outBuff.flush(); 
    		}
            //关闭流 
    		IOUtils.closeQuietly(inBuff);
    		IOUtils.closeQuietly(outBuff);
    		IOUtils.closeQuietly(output);
    		IOUtils.closeQuietly(input);
    	}
    }
    
    public static void copyDirectiory(String sourceDir, String targetDir) throws Exception
    {
    	copyDirectiory(new File(sourceDir), new File(targetDir));
    }
    
    /**
     * 拷贝目录里的所有目录及文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyDirectiory(File sourceDir, File targetDir) throws Exception 
    {
    	deleteAll(targetDir);
    	
    	// 判断目标目录是否存在
    	isDir(targetDir);

        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i]; 
                // 目标文件 
               File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                copyFile(sourceFile, targetFile); 
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                // 准备复制的目标文件夹 
                File target = new File(targetDir,  file[i].getName()); 
                copyDirectiory(source, target); 
            } 
        } 
    }
    
    public static void copyOnlyFile(String sourceDir, String targetDir) throws Exception
    {
    	copyOnlyFile(new File(sourceDir), new File(targetDir));
    }
    
    /**
     * 拷贝目录下的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFile(File sourceDir, File targetDir) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i]; 
                // 目标文件 
               File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                copyFile(sourceFile, targetFile); 
            }
        } 
    }
    
    /**
     * 拷贝目录下任意后缀名但文件名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileByName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = getNameAndSuffix(sourceFile.getName())[0].toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录下后缀名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileBySuffix(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String[] suffixArray = getNameAndSuffix(sourceFile.getName());
                String suffix = "";
                if(suffixArray.length > 1)
                {
                	suffix = suffixArray[1].toLowerCase();
                }
                
                for(String fileName : fileNames)
                {
                	if(suffix.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录下文件名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileByFileName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = sourceFile.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录下任意后缀名但文件名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = getNameAndSuffix(sourceFile.getName())[0].toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录下后缀名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeSuffix(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String[] suffixArray = getNameAndSuffix(sourceFile.getName());
                String suffix = "";
                if(suffixArray.length > 1)
                {
                	suffix = suffixArray[1].toLowerCase();
                }
                for(String fileName : fileNames)
                {
                	if(suffix.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录下文件名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeFileName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = sourceFile.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下任意后缀名但文件名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileByNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = getNameAndSuffix(sourceFile.getName())[0].toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileByNameChild(source, target, fileNames);
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下后缀名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileBySuffixChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String[] suffixArray = getNameAndSuffix(sourceFile.getName());
                String suffix = "";
                if(suffixArray.length > 1)
                {
                	suffix = suffixArray[1].toLowerCase();
                }
                
                for(String fileName : fileNames)
                {
                	if(suffix.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileBySuffixChild(source, target, fileNames);
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下文件名是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileByFileNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = sourceFile.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileByFileNameChild(source, target, fileNames);
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下任意后缀名但文件名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = getNameAndSuffix(sourceFile.getName())[0].toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileExcludeNameChild(source, target, fileNames);
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下后缀名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeSuffixChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String[] suffixArray = getNameAndSuffix(sourceFile.getName());
                String suffix = "";
                if(suffixArray.length > 1)
                {
                	suffix = suffixArray[1].toLowerCase();
                }
                for(String fileName : fileNames)
                {
                	if(suffix.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileExcludeSuffixChild(source, target, fileNames);
            }
        } 
    }
    
    /**
     * 拷贝目录及其子目录下文件名不是fileNames的所有文件
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws IOException
     */
    public static void copyOnlyFileExcludeFileNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = sourceFile.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 目标文件 
                    File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                     copyFile(sourceFile, targetFile); 
                }
            }
            if(file[i].isDirectory())
            {
            	File source = file[i];
            	File target = new File(targetDir,  file[i].getName()); 
            	copyOnlyFileExcludeFileNameChild(source, target, fileNames);
            }
        } 
    }
    
    public static void copyOnlyDirectiory(String sourceDir, String targetDir) throws Exception
    {
    	copyOnlyDirectiory(new File(sourceDir), new File(targetDir));
    }
    
    /**
     * 拷贝目录下的所有目录
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws Exception	
     */
    public static void copyOnlyDirectiory(File sourceDir, File targetDir) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                // 准备复制的目标文件夹 
                File target = new File(targetDir,  file[i].getName()); 
                copyDirectiory(source, target); 
            } 
        } 
    }
    
    /**
     * 拷贝目录下目录名是fileNames的所有目录
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws Exception	
     */
    public static void copyOnlyDirectioryByName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                String name = source.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(exist)
                {
                	// 准备复制的目标文件夹 
                    File target = new File(targetDir,  file[i].getName()); 
                    copyDirectiory(source, target); 
                }
            } 
        } 
    }
    
    /**
     * 拷贝目录下目录名不是fileNames的所有目录
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws Exception	
     */
    public static void copyOnlyDirectioryExcludeName(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                String name = source.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	// 准备复制的目标文件夹 
                    File target = new File(targetDir,  file[i].getName()); 
                    copyDirectiory(source, target); 
                }
            } 
        } 
    }
    
    /**
     * 拷贝目录及其子目录下目录名是fileNames的所有目录
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws Exception	
     */
    public static void copyOnlyDirectioryByNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                String name = source.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                File target = new File(targetDir,  file[i].getName()); 
                if(exist)
                {
                	// 准备复制的目标文件夹 
                    copyDirectiory(source, target); 
                }
                else
                {
                	copyOnlyDirectioryByNameChild(source, target, fileNames);
                }
            } 
        } 
    }
    
    /**
     * 拷贝目录及其子目录下目录名不是fileNames的所有目录
     * @param sourceDir		源目录
     * @param targetDir		目的地目录
     * @throws Exception	
     */
    public static void copyOnlyDirectioryExcludeNameChild(File sourceDir, File targetDir, String... fileNames) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
        	boolean exist = false;
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                String name = source.getName().toLowerCase();
                for(String fileName : fileNames)
                {
                	if(name.equals(fileName.toLowerCase()))
                	{
                		exist = true;
    					break;
                	}
                }
                
                if(!exist)
                {
                	File target = new File(targetDir,  file[i].getName()); 
                	if(existsDirectoryNameChild(source, fileNames))
                	{
                		copyOnlyDirectioryExcludeNameChild(source, target, fileNames);
                	}
                	else
                	{
                		// 准备复制的目标文件夹 
                        copyDirectiory(source, target);
                	}
                }
            } 
        } 
    }
    
    public static void copyDirectioryByName(String sourceDir, String targetDir) throws Exception
    {
    	copyDirectioryByName(new File(sourceDir), new File(targetDir));
    }
    
    /**
     * 拷贝目录下的所有目录及文件,文件名相同后缀名不同时会被覆盖
     * @param sourceDir
     * @param targetDir
     * @throws Exception
     */
    public static void copyDirectioryByName(File sourceDir, File targetDir) throws Exception
    {
    	// 判断目标目录是否存在
    	isDir(targetDir);

        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()){
                // 源文件 
                File sourceFile=file[i];
                String name = getNameAndSuffix(sourceFile.getName())[0];
                deleteFileByName(targetDir, name);
                // 目标文件 
               File targetFile = new File(targetDir.getAbsolutePath()+File.separator+file[i].getName()); 
                copyFile(sourceFile, targetFile); 
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                // 准备复制的目标文件夹 
                File target = new File(targetDir,  file[i].getName()); 
                copyDirectioryByName(source, target); 
            } 
        } 
    }
    
    public static void copyOnlyDirectioryByName(String sourceDir, String targetDir) throws Exception
    {
    	copyOnlyDirectioryByName(new File(sourceDir), new File(targetDir));
    }
    
    /**
     * 拷贝目录下的所有目录,文件名相同后缀名不同时会被覆盖
     * @param sourceDir
     * @param targetDir
     * @throws Exception
     */
    public static void copyOnlyDirectioryByName(File sourceDir, File targetDir) throws Exception
    {
        // 获取源文件夹当前下的文件或目录 
        File[] file = (sourceDir).listFiles(); 
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹 
                File source = file[i]; 
                // 准备复制的目标文件夹 
                File target = new File(targetDir,  file[i].getName()); 
                copyDirectioryByName(source, target); 
            } 
        } 
    }
    
    public static void deleteDirectory(String file) throws Exception
    {
    	deleteDirectory(new File(file));
    }
    
    /**
     * 删除目录下的所有目录及目录下的文件
     * @param  file
     * @throws Exception
     */
    public static void deleteDirectory(File file) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isDirectory())
            		{
            			deleteAll(files[i]);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFile(String file) throws Exception
    {
    	deleteFile(new File(file));
    }
    
    /**
     * 删除目录下的所有文件
     * @param file
     * @throws Exception
     */
    public static void deleteFile(File file) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			deleteAll(files[i]);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileChild(String file) throws Exception
    {
    	deleteFileChild(new File(file));
    }
    
    /**
     * 删除所有目录下的文件,目录不删除
     * @param file
     * @throws Exception
     */
    public static void deleteFileChild(File file) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			deleteAll(files[i]);
            		}
            		else
            		{
            			deleteFileChild(files[i]);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteAll(String f) throws Exception
    {
    	deleteAll(new File(f));
    }
    
    /**
     * 删除整个目录及文件
     * @param f		目录文件
     */
    public static void deleteAll(File f) throws Exception
    {
    	if(f.exists())
    	{
    		//文件
        	if(f.isFile())
        	{
        		 if (!f.delete()) 
        		 {
        			String message = "不能删除文件 " + f + ".";
       	          	throw new Exception(message);
        		 }
        	}
        	else
        	{ 
        		//文件夹
        		//获得当前文件夹下的所有子文件和子文件夹
        		File f1[] = f.listFiles();

        		//循环处理每个对象

        		int len = f1.length;

        		for(int i = 0;i < len;i++)
        		{
        			//递归调用，处理每个文件对象
        			deleteAll(f1[i]);
        		}
        		
        		//删除当前文件夹
        		 if (!f.delete()) 
        		 {
        	         String message = "不能删除目录 " + f + ".";
        	          throw new Exception(message);
        	     }
        	}
    	}
   }
    
    public static void deleteFileByName(String file, String... fileNames) throws Exception
    {
    	deleteFileByName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下任意后缀名但文件名是fileNames的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileByName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileByFileName(String file, String... fileNames) throws Exception
    {
    	deleteFileByFileName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下文件名是fileNames的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileByFileName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeName(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下任意后缀名但文件名不是fileNames的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeFileName(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeFileName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下文件名不是fileNames的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeFileName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileBySuffix(String file, String... fileNames) throws Exception
    {
    	deleteFileBySuffix(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下后缀名是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileBySuffix(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String[] suffixArray = getNameAndSuffix(files[i].getName());
                        String suffix = "";
                        if(suffixArray.length > 1)
                        {
                        	suffix = suffixArray[1].toLowerCase();
                        }
            			for(String fileName : fileNames)
            			{
            				if(suffix.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeSuffix(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeSuffix(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下后缀名不是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeSuffix(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String[] suffixArray = getNameAndSuffix(files[i].getName());
                        String suffix = "";
                        if(suffixArray.length > 1)
                        {
                        	suffix = suffixArray[1].toLowerCase();
                        }
            			for(String fileName : fileNames)
            			{
            				if(suffix.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileByNameChild(String file, String... fileNames) throws Exception
    {
    	deleteFileByNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下任意后缀名但文件名是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileByNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            		else
            		{
            			deleteFileByNameChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileByFileNameChild(String file, String... fileNames) throws Exception
    {
    	deleteFileByFileNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下文件名是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileByFileNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            		else
            		{
            			deleteFileByFileNameChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeNameChild(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下任意后缀名但文件名不是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            		else
            		{
            			deleteFileExcludeNameChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeFileNameChild(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeFileNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下文件名不是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeFileNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            		else
            		{
            			deleteFileExcludeFileNameChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileBySuffixChild(String file, String... fileNames) throws Exception
    {
    	deleteFileBySuffixChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下后缀名是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileBySuffixChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isFile())
            		{
            			String[] suffixArray = getNameAndSuffix(files[i].getName());
                        String suffix = "";
                        if(suffixArray.length > 1)
                        {
                        	suffix = suffixArray[1].toLowerCase();
                        }
            			for(String fileName : fileNames)
            			{
            				if(suffix.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            		else
            		{
            			deleteFileBySuffixChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteFileExcludeSuffixChild(String file, String... fileNames) throws Exception
    {
    	deleteFileExcludeSuffixChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下后缀名不是fileName的所有文件
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteFileExcludeSuffixChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isFile())
            		{
            			String[] suffixArray = getNameAndSuffix(files[i].getName());
                        String suffix = "";
                        if(suffixArray.length > 1)
                        {
                        	suffix = suffixArray[1].toLowerCase();
                        }
            			for(String fileName : fileNames)
            			{
            				if(suffix.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            		else
            		{
            			deleteFileExcludeSuffixChild(files[i], fileNames);
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteDirectoryByName(String file, String... fileNames) throws Exception
    {
    	deleteDirectoryByName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下目录名是fileNames的所有目录
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteDirectoryByName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		if (files[i].isDirectory())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
                				deleteAll(files[i]);
                			}
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteDirectoryExcludeName(String file, String... fileNames) throws Exception
    {
    	deleteDirectoryExcludeName(new File(file), fileNames);
    }
    
    /**
     * 删除file目录下目录名不是fileNames的所有目录
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteDirectoryExcludeName(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isDirectory())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				deleteAll(files[i]);
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteDirectoryByNameChild(String file, String... fileNames) throws Exception
    {
    	deleteDirectoryByNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下文件名是fileName的所有目录
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteDirectoryByNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isDirectory())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(exist)
            			{
            				deleteAll(files[i]);
            			}
            			else
            			{
            				deleteDirectoryByNameChild(files[i], fileNames);
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static void deleteDirectoryExcludeNameChild(String file, String... fileNames) throws Exception
    {
    	deleteDirectoryExcludeNameChild(new File(file), fileNames);
    }
    
    /**
     * 删除file目录及其子目录下文件名不是fileName的所有目录
     * @param file
     * @param fileNames
     * @throws Exception
     */
    public static void deleteDirectoryExcludeNameChild(File file, String... fileNames) throws Exception
    {
    	if(file.exists())
    	{
    		if(file.isDirectory())
        	{
        		File[] files = file.listFiles();
            	for (int i = 0; i < files.length; i++)
            	{
            		boolean exist = false;
            		
            		if (files[i].isDirectory())
            		{
            			String name = files[i].getName().toLowerCase();
            			for(String fileName : fileNames)
            			{
            				if(name.equals(fileName.toLowerCase()))
                			{
            					exist = true;
            					break;
                			}
            			}
            			
            			if(!exist)
            			{
            				if(existsDirectoryNameChild(files[i], fileNames))
            				{
            					deleteDirectoryExcludeNameChild(files[i], fileNames);
            				}
            				else
            				{
            					deleteAll(files[i]);
            				}
            			}
            		}
            	}
        	}
        	else
        	{
        		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
        	}
    	}
    }
    
    public static boolean existsName(String file, String... fileNames) throws Exception
    {
    	return existsName(new File(file), fileNames);
    }
    
    /**
     * 查找file目录下后缀名任意但文件名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsName(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsSuffix(String file, String... fileNames) throws Exception
    {
    	return existsSuffix(new File(file), fileNames);
    }
    
    /**
     * 查找file目录下后缀名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsSuffix(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String[] suffixArray = getNameAndSuffix(files[i].getName());
                    String suffix = "";
                    if(suffixArray.length > 1)
                    {
                    	suffix = suffixArray[1].toLowerCase();
                    }
        			for(String fileName : fileNames)
        			{
        				if(suffix.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsFileName(String file, String... fileNames) throws Exception
    {
    	return existsFileName(new File(file), fileNames);
    }
    /**
     * 查找file目录下文件名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsFileName(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String name = files[i].getName().toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsDirectoryName(String file, String... fileNames) throws Exception
    {
    	return existsDirectoryName(new File(file), fileNames);
    }
    /**
     * 查找file目录下目录名是fileName的目录,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsDirectoryName(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isDirectory())
        		{
        			String name = files[i].getName().toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsNameChild(String file, String... fileNames) throws Exception
    {
    	return existsNameChild(new File(file), fileNames);
    }
    
    /**
     * 查找file目录及其子目录下后缀名任意但文件名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsNameChild(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String name = getNameAndSuffix(files[i].getName())[0].toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        		else
        		{
        			boolean back = existsNameChild(files[i], fileNames);
        			if(back)
        			{
        				return back;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsSuffixChild(String file, String... fileNames) throws Exception
    {
    	return existsSuffixChild(new File(file), fileNames);
    }
    /**
     * 查找file目录及其子目录下后缀名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsSuffixChild(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String[] suffixArray = getNameAndSuffix(files[i].getName());
                    String suffix = "";
                    if(suffixArray.length > 1)
                    {
                    	suffix = suffixArray[1].toLowerCase();
                    }
        			for(String fileName : fileNames)
        			{
        				if(suffix.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        		else
        		{
        			boolean back = existsSuffixChild(files[i], fileNames);
        			if(back)
        			{
        				return back;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsFileNameChild(String file, String... fileNames) throws Exception
    {
    	return existsFileNameChild(new File(file), fileNames);
    }
    /**
     * 查找file目录及其子目录下文件名是fileName的文件,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsFileNameChild(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isFile())
        		{
        			String name = files[i].getName().toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        		}
        		else
        		{
        			boolean back = existsFileNameChild(files[i], fileNames);
        			if(back)
        			{
        				return back;
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
    public static boolean existsDirectoryNameChild(String file, String... fileNames) throws Exception
    {
    	return existsDirectoryNameChild(new File(file), fileNames);
    }
    /**
     * 查找file目录及其子目录下目录名是fileName的目录,存在返回true,否则返回false
     * @param file
     * @param fileNames
     * @return
     * @throws Exception
     */
    public static boolean existsDirectoryNameChild(File file, String... fileNames) throws Exception
    {
    	boolean exists = false;
    	if(file.isDirectory())
    	{
    		File[] files = file.listFiles();
        	for (int i = 0; i < files.length; i++)
        	{
        		if (files[i].isDirectory())
        		{
        			String name = files[i].getName().toLowerCase();
        			for(String fileName : fileNames)
        			{
        				if(name.equals(fileName.toLowerCase()))
            			{
            				exists = true;
            				break;
            			}
        			}
        			if(exists)
        			{
        				break;
        			}
        			else
        			{
        				boolean back = existsDirectoryNameChild(files[i], fileNames);
            			if(back)
            			{
            				return back;
            			}
        			}
        		}
        	}
    	}
    	else
    	{
    		throw new Exception("不是一个目录文件:"+file.getAbsolutePath());
    	}
    	
    	return exists;
    }
    
   
    
    /**
     * 读取指定的输入流内容
     * @param    stream	输入流
     * @return		读取的内容
     * @throws Exception
     */
    public static String readStreamText(InputStream stream, String... encoding) throws Exception
    {
    	String isEncoding;
		if(encoding.length > 0)
		{
			isEncoding = encoding[0];
		}
		else
		{
			isEncoding = "UTF-8";
		}
		
    	BufferedReader br = null;
    	StringBuilder str = new StringBuilder();
    	try
    	{
    		br = new BufferedReader(new InputStreamReader(stream, isEncoding));

    		String   line   =   null;
            while((line   =   br.readLine())!=null)
            {
            	str.append(line).append("\r\n");
            }
    	}
    	finally
    	{
    		IOUtils.closeQuietly(br);
    	}
    	
        return str.toString();
    }
    
    
        
	public static File isDir(String dirName) throws Exception
	{
		return isDir(new File(dirName));
	}
	
    /**
	 * 判断目录是否存在,不存在创建一个，存在就返回目录
	 * 
	 * @param dirName	目录名字
	 * @return
	 */
	public static File isDir(File dirName) throws Exception
	{
		if(!dirName.exists())//不存在就创建目录
		{
			dirName.mkdirs();
		}
		
		return dirName;
	}
	
	public static File isFile(String fileName) throws Exception
	{
		return isFile(new File(fileName));
	}
	
	/**
	 * 判断文件是否存在,不存在创建一个，存在就返回文件
	 * 
	 * @param fileName	文件名字
	 * @return
	 */
	public static File isFile(File fileName) throws Exception
	{
		isDir(fileName.getParentFile());
		if(!fileName.exists())//不存在就创建文件
		{
			fileName.createNewFile();
		}
		
		return fileName;
	}
	
	//压缩文件夹内的文件
	 public static void doZip(String sourceFile, String targetFile) throws Exception
	 {
		 doZip(new File(sourceFile), new File(targetFile));
	 }
	 
	//压缩文件夹内的文件
	 public static void doZip(File sourceFile, File targetFile) throws Exception
	 {
		 isDir(targetFile.getParentFile());
		 ZipOutputStream zipOut = null;
	
		  try
		  {
			  zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)));
			   if(sourceFile.isFile())
			   {
				   FileInputStream fileIn = null;
				   BufferedInputStream inBuff = null;
				   try
				   {
					   fileIn = new FileInputStream(sourceFile);
					   inBuff = new BufferedInputStream(fileIn);
						
				 	    zipOut.putNextEntry(new ZipEntry(sourceFile.getName()));
				
				 	     byte[]   buf = new byte[1024 * 5];
				 	     int len;
				 	     while((len = inBuff.read(buf))>0)
				 	     {
				 	    	 zipOut.write(buf , 0 , len);
				 	     }   
				   }
				   finally
				   {
					   zipOut.closeEntry();
					   IOUtils.closeQuietly(inBuff);
					   IOUtils.closeQuietly(fileIn);
				   }
			   }
			   else
			   {
				   String parentFile = sourceFile.getParent().replaceAll("\\\\", "/");
				   String str = parentFile.substring(parentFile.length()-1, parentFile.length());
				   if(!str.equals("/"))
				   {
					   parentFile = parentFile+"/";
				   }
				   
				   handleDir(sourceFile , zipOut, parentFile);
			   }
		  }
		  finally
		  {
			  //关闭流 
			  IOUtils.closeQuietly(zipOut);
		  }
	 }

	 //由doZip调用,递归完成目录文件读取
	 private static void handleDir(File dir , ZipOutputStream zipOut, String parentFile) throws Exception
	 {
		 String fileName = StringUtils.removeStart(dir.getAbsolutePath().replaceAll("\\\\", "/"), parentFile);
		 
		  if(dir.isFile())
		  {
			  FileInputStream fileIn = null;
			  BufferedInputStream inBuff = null;
			  try
			  {
				    fileIn = new FileInputStream(dir);
				    inBuff = new BufferedInputStream(fileIn);
					
				     zipOut.putNextEntry(new ZipEntry(fileName));
			
				     byte[]   buf = new byte[1024 * 5];
				     int len;
				     while((len = inBuff.read(buf))>0)
				     {
				    	 zipOut.write(buf , 0 , len);
				     }
			  }
			  finally
			  {
				  zipOut.closeEntry();
				  IOUtils.closeQuietly(inBuff);
				  IOUtils.closeQuietly(fileIn);
			  }
		  }
		  else
		  {
			  try
			  {
				  zipOut.putNextEntry(new ZipEntry(fileName+"/"));
			  }
			  finally
			  {
				  zipOut.closeEntry();
			  }
			  
			   File[] files = dir.listFiles();
		
				//循环处理每个对象
		
				int len = files.length;
		
				for(int i = 0;i < len;i++)
				{
					//递归调用，处理每个文件对象
					handleDir(files[i], zipOut, parentFile);
				}
		  }
	 }

	//解压指定zip文件
	 public static void unZip(String sourceFile, String targetFile) throws Exception
	 {
		 unZip(new File(sourceFile), new File(targetFile));
	 }
	 
	 //解压指定zip文件
	 public static void unZip(File sourceFile, File targetFile) throws Exception
	 {
		 ZipInputStream zipIn = null;

		 try
		 {
			 zipIn = new ZipInputStream (new BufferedInputStream(new FileInputStream(sourceFile)));
			 ZipEntry zipEntry;
			 while((zipEntry = zipIn.getNextEntry()) != null)
			 {
				 try
				 {
					 File file = new File(targetFile, zipEntry.getName());

					 if(zipEntry.isDirectory())
				    {
				    	file.mkdirs();
				    }
				    else
				    {
				    	BufferedOutputStream outBuff = null;
				    	FileOutputStream fileOut = null;
				    	try
				    	{
				    		 //如果指定文件的目录不存在,则创建之.
						     File parent = file.getParentFile();
						     if(!parent.exists())
						     {
						    	 parent.mkdirs();
						     }
						
						     fileOut = new FileOutputStream(file);
						     outBuff = new BufferedOutputStream(fileOut);
						     
						     byte[]   buf = new byte[1024 * 5];
						     int len;
						     while((len = zipIn.read(buf) ) > 0)
						     {
						    	 outBuff.write(buf , 0 , len);
						     }
				    	}
				    	finally
				    	{
				    		if(outBuff != null)
				    		{
				    			outBuff.flush();
				    		}
				    		IOUtils.closeQuietly(outBuff);
				    		IOUtils.closeQuietly(fileOut);
				    	}
				    }
				 }
				 finally
				 {
					 zipIn.closeEntry(); 
				 }
			 }
		}
		 finally
		 {
		 	IOUtils.closeQuietly(zipIn);
		 }
	 }
	
	 
	 /**
	     * 根据文件名获取文件名称和后缀名
	     * @param filename
	     * @return  str[0]:文件名称,str[1]:后缀名
	     */
	    public static String[] getNameAndSuffix(String filename)
	    {
	    	String[] str = null;
			int index = filename.lastIndexOf(".");
			if(index > -1)
			{
				str = new String[2];
				str[0] = filename.substring(0, index);
				str[1] = filename.substring(index+1);
			}
			else
			{
				str = new String[1];
				str[0] =  filename;
			}
			
			return str;
	    }
	    
		public static boolean checkIsImage(String imgStr) {
			boolean flag = false;
			if (imgStr != null) {
				if (imgStr.endsWith(".gif") || imgStr.endsWith(".jpg") || imgStr.endsWith(".jpeg")
						|| imgStr.endsWith(".png") || imgStr.endsWith(".JPG") || imgStr.endsWith(".PNG")) {
					flag = true;
				}
			}
			return flag;
		}
		public static boolean checkIsVideo(String videoStr) {
			boolean flag = false;
			if (videoStr != null) {
				if (videoStr.endsWith(".3gp") ||videoStr.endsWith(".mp4") || videoStr.endsWith(".MOV")
						|| videoStr.endsWith(".rmvb") || videoStr.endsWith(".flv") || videoStr.endsWith(".mov")
						|| videoStr.endsWith(".wmv") || videoStr.endsWith(".avi") || videoStr.endsWith(".mkv")) {
					flag = true;
				}
			}
			return flag;
		}
} 