package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.boot.common.domain.AbstractEntity;

/**
 * @ClassName: Device
 * @Description:各区县设备表
 * @author 王朔
 * @date 2020年1月8日 下午4:14:02
 */
@Entity
@Table(name = "tb_device")
public class Device extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String account; //终端账号   对应音视频中心服务器创建的账号  eg: pzdj0101
	
	private String pwd;	//终端密码	 对应音视频中心服务器创建的账号密码  eg: 111111
	
	private String deviceIp;//设备IP
	
	private String region_name; //区县简写  朝阳：CY  大兴：DX
	
	private String state = "0";	//状态   0未使用   1使用中
	
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}