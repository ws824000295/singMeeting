package com.boot.common.util.meet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.rpc.ServiceException;

import com.boot.common.util.meet.v14.ConfWebServiceInterfacePortType;
import com.boot.common.util.meet.v14.ConfWebServiceInterface_ServiceLocator;

public class CXFUtils {

	public static Object protType = null;
	
	//public static Object interfaceObject = null;
	
	public final static ThreadLocal<Object> interfaceObject = new ThreadLocal<Object>();
	static {
		try {
			//getPortType("com.axis.service.hw.v14.ConfWebServiceInterfacePortType","com.axis.service.hw.v14.ConfWebServiceInterface_ServiceLocator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object getPortType(String portTypeName,String serviceLocatorName) throws ServiceException {
		if (null == protType||!portTypeName.equals(protType.getClass().getName())) {
			try {
				Class portTypeClass=null;
				if(portTypeName!=null&&!portTypeName.equals("")){
					portTypeClass=Class.forName(portTypeName);
				}else{
					portTypeClass=Class.forName("com.axis.service.hw.v13.ConfWebServiceInterfacePortType");
				}
				synchronized (portTypeClass) {
					Class serviceLocatorClass=null;
					
					if(serviceLocatorName!=null&&!serviceLocatorName.equals("")){
						serviceLocatorClass=Class.forName("com.axis.service.hw.v14.ConfWebServiceInterface_ServiceLocator");
					}else{
						serviceLocatorClass=Class.forName("com.axis.service.hw.v13.ConfWebServiceInterface_ServiceLocator");
					}
					//使用反射创建实例
					Object serviceLocator = serviceLocatorClass.newInstance();
					//使用反射调用getConfWebServiceInterface
					Method method = serviceLocatorClass.getMethod("getConfWebServiceInterface", null);
					protType = method.invoke(serviceLocator, null);
					System.out.println(protType);
				}
			} catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage(),e.getCause());
			}
			
		}
		return protType;
	}
	
	/**
	 * 获取webService实例化接口
	 * @return
	 */
	public static Object getInterface(int version){
		if(interfaceObject.get()!=null){
			protType = interfaceObject.get();
			return interfaceObject.get();
		}else{
			try {
				switch (version) {
				case 13:
					com.boot.common.util.meet.v13.ConfWebServiceInterface_ServiceLocator locator13 = new com.boot.common.util.meet.v13.ConfWebServiceInterface_ServiceLocator();
					com.boot.common.util.meet.v13.ConfWebServiceInterfacePortType object13 = locator13.getConfWebServiceInterface();
					interfaceObject.set(object13);
					protType = object13;
					break;
				case 14:
					ConfWebServiceInterface_ServiceLocator locator14 = new ConfWebServiceInterface_ServiceLocator();//14
					ConfWebServiceInterfacePortType object14 = locator14.getConfWebServiceInterface();
					interfaceObject.set(object14);
					protType = object14;
					break;
				default:
					ConfWebServiceInterface_ServiceLocator locator = new ConfWebServiceInterface_ServiceLocator();//14
					ConfWebServiceInterfacePortType object = locator.getConfWebServiceInterface();
					interfaceObject.set(object);
					protType = object;
					break;
				}
				
				return interfaceObject.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
