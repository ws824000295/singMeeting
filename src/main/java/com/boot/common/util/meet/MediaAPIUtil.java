/**
 * Copyright By Grandsoft Company Limited.  
 * 2016年3月30日 下午5:45:49
 */
package com.boot.common.util.meet;


import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;


/**
 * 
 * 
 * 
 * <p>
 * 此工具类封装多媒体webservice中API方法
 * </p>
 * @author Administrator
 * @since jdk1.8
 * 2019年3月15日
 *
 */

public class MediaAPIUtil {

	
	/**
	 * 会议创建
	 * 
	 * @param sSubject
	 *            会议主题
	 * @param sCreator
	 *            制定一个用户账号(系统用到)
	 * @return confId 创建的会议唯一标识，以后管理用到;大于0成功，等于零失败。
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public static long createConf(String sSubject, String sCreator,String recordip,int recordport,String portTypeName,String serviceLocatorName) throws RemoteException, ServiceException {
		
		changeInterface(portTypeName,serviceLocatorName);
		
		long result=0;
		try {
			if(portTypeName.equals("com.axis.service.hw.v14.ConfWebServiceInterfacePortType")){
				result= (long) CXFUtils.protType.getClass().getMethod("createConf", String.class,String.class,String.class,int.class).invoke(CXFUtils.protType,sSubject, sCreator,recordip,recordport);
			}else{
				result= (long) CXFUtils.protType.getClass().getMethod("createConf", String.class,String.class).invoke(CXFUtils.protType,sSubject, sCreator);
			}
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 邀请加入会议
	 * 
	 * @param confId
	 *            会议标识
	 * @param account
	 *            被添加到会议的用户账号
	 * @param signed
	 *            系统名称
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int inviteJoinConf(long confId, String account, String signed,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().getMethod("inviteJoinConf",long.class, String.class,String.class).invoke(CXFUtils.protType,confId, account, signed);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 请出会议
	 * 
	 * @param confId
	 *            会议标识
	 * @param account
	 *            用户账号
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int kickConf(long confId, String account,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().getMethod("kickConf",long.class, String.class).invoke(CXFUtils.protType,confId, account);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 结束会议
	 * 
	 * @param confId
	 *            会议标识
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int closeConf(long confId,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().getMethod("closeConf",long.class).invoke(CXFUtils.protType,confId);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}
	
	/**
	 * 销毁会议 会议号失效
	 * 
	 * @param confId==groupID
	 *            会议标识
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int destroyConf(long groupID,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().getMethod("destroyConf",long.class).invoke(CXFUtils.protType,groupID);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 
	 * @param confId
	 *            会议标识
	 * @param confBanner
	 *            会议横幅
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int SetConfBanner(long confId, String confBanner,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().getMethod("setConfBanner",long.class,String.class).invoke(CXFUtils.protType,confId,confBanner);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 会议添加IPCAMERA
	 * 
	 * @param confid:会议标识
	 * @param user：IPCamer的登录用户
	 * @param pwd：IPCamer用户的登录密码;
	 * @param ip：
	 *            IPCamera的网络地址
	 * @param port：
	 *            IPCamera的连接端口
	 * @param extenddata：场景类型（0：场地视频，1：头像视频，2：演示视频，3：桌面视频）
	 * @param streamType：
	 *            码流类型（默认 0：主码流，1副码流， 2：三码流）
	 * @param channel：连接通道（目前用的IPC设备只有一个通道1）。
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int addConfAccessibleIPC(long confId, String user, String pwd, String ip, int port, int extenddata,
			int streamType, int channel,String gw,String request ,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		try {
			if(portTypeName.equals("com.axis.service.hw.v14.ConfWebServiceInterfacePortType")){
				
				result= (int) CXFUtils.protType.getClass().
						getMethod("addConfAccessibleIPC",long.class,String.class,String.class,String.class,int.class,int.class,String.class,String.class).
						invoke(CXFUtils.protType,confId, user, pwd, ip, port, extenddata, gw,request);
			}else{
				result= (int) CXFUtils.protType.getClass().
						getMethod("addConfAccessibleIPC",long.class,String.class,String.class,String.class,int.class,int.class,int.class,int.class)
						.invoke(CXFUtils.protType,confId, user, pwd, ip, port, extenddata, streamType, channel);
			}
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 会议删除Camera
	 * 
	 * @param confId
	 *            会议标识
	 * @param ip
	 *            IPCamera的网络地址
	 * @param user
	 *            IPCamer的登录用户
	 * @param channel
	 *            连接通道（目前用的IPC设备只有一个通道1）。
	 * @param streamtype
	 *            码流类型（默认 0：主码流，1副码流， 2：三码流）
	 * @return 受理编码
	 * @throws RemoteException
	 */
	public static int delConfIPCDevice(long confId, String ip, String user, int channel, int streamtype,String request,String portTypeName,String serviceLocatorName)
			throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			if(portTypeName.equals("com.axis.service.hw.v14.ConfWebServiceInterfacePortType")){
				result= (int) CXFUtils.protType.getClass().
						getMethod("delConfIPCDevice",long.class,String.class,String.class,String.class).
						invoke(CXFUtils.protType,confId,ip, user, request);
			}else{
				result= (int) CXFUtils.protType.getClass().
						getMethod("delConfIPCDevice",long.class,String.class,String.class,int.class,int.class)
						.invoke(CXFUtils.protType,confId, ip, user, channel, streamtype);
			}
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 会议录制控制
	 * 
	 * @param confId
	 *            会议标识
	 * @param status
	 *            （true开始录制，false停止录制）
	 * @param model
	 *            (1单路和混合同时开始，2 单路录制,3混合录制)
	 * @param num
	 *            分割数
	 * @return
	 * @throws RemoteException
	 */
	public static int confRecordCtrl(long confId, boolean status, int model, int num,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().
					getMethod("confRecordCtrl",long.class,boolean.class,int.class,int.class).invoke(CXFUtils.protType, confId, status, model, num);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 用户通知
	 * 
	 * @param msg
	 *            发送到用户的消息内容
	 * @param userXml
	 *            为用户列表、
	 * @return faileUsersXml 发送失败的用户列表 格式如下： <notice> <account></account>
	 *         <account></account> … </ notice>
	 * @throws RemoteException
	 */
	public static String addNoticeToUser(String msg, String userXml,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		String result="";
		
		try {
			result= (String) CXFUtils.protType.getClass().getMethod("addNoticeToUser",String.class,String.class).invoke(CXFUtils.protType,msg, userXml);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 会议通知
	 * 
	 * @param confId
	 *            会议标识
	 * @param msg
	 *            消息内容
	 * @return 受理标识
	 * @throws RemoteException
	 */
	public static int noticeConfMember(long confId, String msg,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		
		try {
			result= (int) CXFUtils.protType.getClass().
					getMethod("noticeConfMember",long.class,String.class).invoke(CXFUtils.protType,confId, msg);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 单路录制文件
	 * 
	 * @param confId
	 *            会议标识
	 * @return retxml 格式如下 <videos> <video>url</video> … </videos> 失败请求里面为空
	 *         <videos></videos>。
	 * @throws RemoteException
	 */
	public static String getConfSingleRecordVideos(long confId,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		String  result="";
		
		try {
			result= CXFUtils.protType.getClass().
					getMethod("getConfSingleRecordVideos",long.class).invoke(CXFUtils.protType,confId).toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 混屏录制文件
	 * 
	 * @param confId
	 *            会议标识
	 * @return retxml 格式如下 <videos> <video>url</video> … </videos> 失败请求里面为空
	 *         <videos></videos>。
	 * @throws RemoteException
	 */
	public static String getConfMixRecordVideos(long confId,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		String  result="";
		
		try {
			result= CXFUtils.protType.getClass().
					getMethod("getConfMixRecordVideos",long.class).invoke(CXFUtils.protType,confId).toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}

	/**
	 * 
	 * @param account
	 *            账户
	 * @return
	 * @throws RemoteException
	 */
	public static int isInConf(String account,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		int result=0;
		try {
			result= (int) CXFUtils.protType.getClass().
					getMethod("isInConf",String.class).invoke(CXFUtils.protType, account);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}
	
	/**
	* @Title: addUser 
	* @Description: 新增用户
	* @param @param userXml
	* @param @param portTypeName
	* @param @param serviceLocatorName
	* @param @return
	* @param @throws RemoteException设定文件 
	* @author 王朔
	* @return long返回类型 
	* @throws
	 */
	public static long addUser( String userXml,String portTypeName,String serviceLocatorName) throws RemoteException {
		
		changeInterface(portTypeName,serviceLocatorName);
		long result=0;
		
		try {
			result= (long) CXFUtils.protType.getClass().getMethod("addUser",String.class).invoke(CXFUtils.protType, userXml);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RemoteException(e.getMessage(),e.getCause());
		}
		return result;
	}
	
	
	private static void changeInterface(String portTypeName,String serviceLocatorName){
		//CXFUtils.getPortType(portTypeName, serviceLocatorName);
		CXFUtils.getInterface(14);
	}
	
}
