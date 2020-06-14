package com.boot.common.util;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class SocketPrintWriter {
	//保存所有socket通信通道
    public static Map<String, PrintWriter> printMap =new HashMap<String, PrintWriter>();
    //线程间通信传递参数
    public static Map<String, JSONObject> threadMsg = new HashMap<String, JSONObject>();
    //存储总控默认显示的会议画面
    public static Map<String, String> defaultProject = new HashMap<String, String>();
    
    private static long timeout(long time){
    	return (new Date().getTime()-time);
    }
    
    /**
     * 线程间通信（存值）
     * @param ip
     * @param json
     */
    public static void setThreadMsg(String ip, JSONObject json){
    	threadMsg.put(ip, json);
    }
    
    /**
     * 线程间通信（取值）
     * @param ip
     * @return
     */
    public static JSONObject getThreadMsg(String clientName, int timeout){
    	long time = new Date().getTime();
    	while(timeout(time)<timeout){
    		if(threadMsg.get(clientName) != null){
    			return threadMsg.get(clientName);
    		}
    	}
    	return null;
    }

    /**
     * 给指定客户端发送指定消息
     * @param ip
     * @param msg
     */
    public static Boolean sendMsg(String clientName,Object msg){
    	try {
    		threadMsg.remove(clientName);
    		PrintWriter printWriter = printMap.get(clientName);
    		printWriter.write(msg.toString()+"\r\n");
    		printWriter.flush();
    		JSONObject threadMsg = getThreadMsg(clientName, 2000);
    		if(threadMsg!=null){
    			System.out.println(threadMsg);
    			threadMsg.remove(clientName);
    			return true;
    		}else{
    			//没有接收到客户端返回的数据
    			return false;
    		}
		} catch (Exception e) {
			deleteSocket(clientName);
			e.printStackTrace();
			return false;
		}
    }

    /**
     * 删除指定通信通道
     * @param clientName
     */
    public static void deleteSocket(String clientName){
        printMap.remove(clientName);
        defaultProject.remove(clientName);
    }
 

}
