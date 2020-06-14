package com.boot.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 类名: com.dns.common.utils.FileUtils
 * <p>
 * 描述: 文件操作的辅助类
 * </p>
 * 日期: 2014-3-10 上午12:11:44
 * 
 * @author 蓝斌
 * @version V1.0
 * @since JDK1.5
 */
public class FileUtils {

	/**
	 * 读写缓冲区大小，默认是2MB
	 */
	private static final long FILE_COPY_BUFFER_SIZE = 1024 * 1024 * 2;

	/**
	 * <p>
	 * 描述: 创建一个目录
	 * </p>
	 * 
	 * @param dirname
	 *            目录名
	 * @return 指定路径的目录文件对象，如果创建目录失败则返回null
	 */
	public static File createDir(String dirname) {
		File dir = new File(dirname);
		if (!dir.isDirectory()) {
			if (!dir.mkdirs()) {
				return null;
			}
		}
		return dir;
	}

	/**
	 * <p>
	 * 描述: 拷贝文件辅助方法
	 * </p>
	 * 
	 * @param ifile
	 *            源文件对象
	 * @param ofile
	 *            目标文件对象
	 * @return true-拷贝成功 false-拷贝失败
	 */
	public static boolean copyFile(File ifile, File ofile) {
		Assert.notNull(ifile);
		Assert.notNull(ofile);

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel input = null;
		FileChannel output = null;

		try {
			if (ifile.exists() == false || ifile.isDirectory() || ifile.getCanonicalPath().equals(ofile.getCanonicalPath())) {
				return false;
			}

			File tmpfile = ofile.getParentFile();
			if (tmpfile != null) {
				if (!tmpfile.mkdirs() && !tmpfile.isDirectory()) {
					return false;
				}
			}
			if (ofile.exists() && (ofile.canWrite() == false || ofile.isDirectory())) {
				return false;
			}

			fis = new FileInputStream(ifile);
			fos = new FileOutputStream(ofile);
			input = fis.getChannel();
			output = fos.getChannel();
			long size = input.size();
			long pos = 0;
			long count = 0;
			while (pos < size) {
				count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
				pos += output.transferFrom(input, pos, count);
			}

			if (ifile.length() != ofile.length()) {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			FileUtils.close(output);
			FileUtils.close(fos);
			FileUtils.close(input);
			FileUtils.close(fis);
		}

		return true;
	}

	/**
	 * <p>
	 * 描述: 关闭流对象
	 * </p>
	 * 
	 * @param closeable
	 *            实现了Closeable接口的流对象
	 */
	public static void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 描述: 获取指定路径中的文件名
	 * </p>
	 * 
	 * @param filepath
	 *            指定路径
	 * @return 文件名
	 */
	public static String getFilename(String filepath) {
		if (Assert.isEmpty(filepath)) {
			return null;
		}
		int i = filepath.lastIndexOf(GConstants.FOLDER_SEPARATOR);
		return (i != -1 ? filepath.substring(i + 1) : filepath);
	}

	/**
	 * <p>
	 * 描述: 获取指定路径中的文件扩展名
	 * </p>
	 * 
	 * @param filepath
	 *            指定路径
	 * @return 文件扩展名
	 */
	public static String getFilenameExtension(String filepath) {
		if (Assert.isEmpty(filepath)) {
			return null;
		}

		int extIndex = filepath.lastIndexOf(GConstants.EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}

		int folderIndex = filepath.lastIndexOf(GConstants.FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}

		return filepath.substring(extIndex + 1);
	}

	public static String isEncoding(String... encoding) {
		String isEncoding;
		if (encoding.length > 0) {
			isEncoding = encoding[0];
		} else {
			isEncoding = GConstants.UTF8_ENCODING;
		}

		return isEncoding;
	}

}
