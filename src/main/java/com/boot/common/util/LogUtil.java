package com.boot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	 private static Logger log = LoggerFactory.getLogger(LogUtil.class); 
	 
	 public static void info(String message){
		 log.info(message);
	 }
	 
     public static void error(String message, Throwable e) {
		log.error(message, e);
	 }
     
     public static void error(Throwable e) {
 		log.error("异常：", e);
 	 }

}
