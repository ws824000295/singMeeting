package com.boot.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * title : Description : 常量类 Date : 2010-7-28 Company : 新网互联
 * 
 * @version : 1.0
 */
public class Constants {

	/**
	 * 企业ID session Key
	 */
	public static final String ENTERPRISE_ID = "enterpriseId";

	/**
	 * 企业session key
	 */
	public static final String ENTERPRISE = "enterprise";

	public static final String DATA_NAME = "data";

	public static final String IMG_NAME = "userImg";

	public static final String DATA_DIR = AppPath.getRootPath()
			+ DATA_NAME + "/";

	public static final String USERIMG_DIR = DATA_DIR + IMG_NAME + "/";

	// 需要动态通过原生sql查询数据的sql语句组
	public static Map<String, String> sqlMap = new HashMap<String, String>();

	public static Map<String, String> urlMap = new HashMap<String, String>();

	public final static int httpTimeOut = 5 * 60 * 1000;// 调用远程连接时的超时设置,如超过一分钟连不上,报超时异常

	public final static Long IMG_MAX_SIZE = 2 * 1024 * 1024L;

	public final static String UTF8_ENCODING = "UTF-8";

	public final static String GBK_ENCODING = "GBK";

	/**
	 * 图片上传路径后缀
	 */
	public static final String IMAGE_SUFFIX_PATH = "files" + "/" + "data";

	/**
	 * 描述: 用户上传的图片路径
	 */
	public static final String UPLOAD_IMAGE_PATH = GConstants.PROJECT_REAL_PATH
			+ File.separator + IMAGE_SUFFIX_PATH;
	
	/**
	 * 微博推送消息时附加的用户id参数名字
	 */
	public static final String WB_USER_ID = "userId";
	
	public static final String TPID = "tpID";
	
	public static Lock lock = new ReentrantLock();
	
    /**
     * 描述： 提现状态
     * 4:提现失败。3:发放成功，退回。2:发放成功，已领取。1发放成功，未领取。0:提现记录刚创建，未发放
     */
    public static final int WITHDRAW_STATUS_SEND_FAIL = 4;
    public static final int WITHDRAW_STATUS_SEND_SUCCESS_RETURN = 3;
    public static final int WITHDRAW_STATUS_SEND_SUCCESS_RECEIVED = 2;
    public static final int WITHDRAW_STATUS_SEND_SUCCESS_UNRECEIVED = 1;
    public static final int WITHDRAW_STATUS_CREATED = 0;
	
}