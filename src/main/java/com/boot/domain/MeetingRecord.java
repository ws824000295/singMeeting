package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.boot.common.domain.AbstractEntity;

/**
* @ClassName: Meeting 
* @Description: 会议中的终端记录表
* @author 王朔
* @date 2020年1月8日 下午4:13:35
 */
@Entity
@Table(name = "tb_meeting_record")
public class MeetingRecord extends AbstractEntity {

	private static final long serialVersionUID = 894215381394445620L;
	
	private String id;
	
	private String meetId; 	//会议id
	
	private String meetCode; //会议号
	
	private String deviceId; //设备终端Id

	private String account;	//拉入会议的终端账号

	private Date createTime;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public String getMeetCode() {
		return meetCode;
	}

	public void setMeetCode(String meetCode) {
		this.meetCode = meetCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	 

}
