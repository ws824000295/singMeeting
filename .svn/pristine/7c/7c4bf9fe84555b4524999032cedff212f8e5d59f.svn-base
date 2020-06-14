/**
 * ConfWebServiceInterface_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boot.common.util.meet.v14;

import com.boot.common.util.environment.SystemPropertiesUtils;




public class ConfWebServiceInterface_ServiceLocator extends org.apache.axis.client.Service implements com.boot.common.util.meet.v14.ConfWebServiceInterface_Service {
/**  */
	private static final long serialVersionUID = 1L;
/**
 * gSOAP 2.8.23 generated service definition
 */

	
    public ConfWebServiceInterface_ServiceLocator() {
//    	ConfWebServiceInterface_address = ConfigUtil.getConfig("url");
//    	ConfWebServiceInterface_address = this.getParamConfig("MEDIA_INVOKE_URL");
    	ConfWebServiceInterface_address = SystemPropertiesUtils.get("MEDIA_INVOKE_URL");
    }


    public ConfWebServiceInterface_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConfWebServiceInterface_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConfWebServiceInterface
    private java.lang.String ConfWebServiceInterface_address = "http://localhost";

    @Override
	public java.lang.String getConfWebServiceInterfaceAddress() {
        return ConfWebServiceInterface_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConfWebServiceInterfaceWSDDServiceName = "ConfWebServiceInterface";

    public java.lang.String getConfWebServiceInterfaceWSDDServiceName() {
        return ConfWebServiceInterfaceWSDDServiceName;
    }

    public void setConfWebServiceInterfaceWSDDServiceName(java.lang.String name) {
        ConfWebServiceInterfaceWSDDServiceName = name;
    }

    @Override
	public com.boot.common.util.meet.v14.ConfWebServiceInterfacePortType getConfWebServiceInterface() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConfWebServiceInterface_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConfWebServiceInterface(endpoint);
    }

    @Override
	public com.boot.common.util.meet.v14.ConfWebServiceInterfacePortType getConfWebServiceInterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	com.boot.common.util.meet.v14.ConfWebServiceInterfaceStub _stub = new com.boot.common.util.meet.v14.ConfWebServiceInterfaceStub(portAddress, this);
            _stub.setPortName(getConfWebServiceInterfaceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConfWebServiceInterfaceEndpointAddress(java.lang.String address) {
        ConfWebServiceInterface_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boot.common.util.meet.v14.ConfWebServiceInterfacePortType.class.isAssignableFrom(serviceEndpointInterface)) {
            	com.boot.common.util.meet.v14.ConfWebServiceInterfaceStub _stub = new com.boot.common.util.meet.v14.ConfWebServiceInterfaceStub(new java.net.URL(ConfWebServiceInterface_address), this);
                _stub.setPortName(getConfWebServiceInterfaceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ConfWebServiceInterface".equals(inputPortName)) {
            return getConfWebServiceInterface();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
	public javax.xml.namespace.QName getServiceName() {
//        return new javax.xml.namespace.QName(ConfigUtil.getConfig("wsdlUrl"), "ConfWebServiceInterface");
//        return new javax.xml.namespace.QName(this.getParamConfig("MEDIA_INVOKE_WSDL_URL"), "ConfWebServiceInterface");
        return new javax.xml.namespace.QName(SystemPropertiesUtils.get("MEDIA_INVOKE_WSDL_URL"), "ConfWebServiceInterface");
    }

    private java.util.HashSet ports = null;

    @Override
	public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
//            ports.add(new javax.xml.namespace.QName(this.getParamConfig("MEDIA_INVOKE_WSDL_URL"), "ConfWebServiceInterface"));
            ports.add(new javax.xml.namespace.QName(SystemPropertiesUtils.get("MEDIA_INVOKE_WSDL_URL"), "ConfWebServiceInterface"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConfWebServiceInterface".equals(portName)) {
            setConfWebServiceInterfaceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }
    
    /**
	 * 通过g2_t_parameter获取配置数据
	 * 
	 * @param key
	 * @return
	 */
	public String getParamConfig(String key) {
		String param = "";
//		try {
//			Parameter parameter = parameterService.getParameterByCode(key);
//			if(parameter!=null && StringUtils.isNotBlank(parameter.getValue())){
//				param = parameter.getValue();
//			}else{
//				throw new RuntimeException("{}参数不能为空！");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return param;
	}

}
