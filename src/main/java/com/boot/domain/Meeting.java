package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.boot.common.domain.AbstractEntity;

/**
* @ClassName: Meeting 
* @Description: 会议表 
* @author 王朔
* @date 2020年1月8日 下午4:13:35
 */
@Entity
@Table(name = "tb_meeting")
public class Meeting extends AbstractEntity {

	private static final long serialVersionUID = 894215381394445620L;
	
	private String id;
	
	private String meetCode; //会议号
	
	private String meetName; //会议名称
	
	private Date createTime; //创建时间
	
	private String status;	//状态 是否结束 0 进行中   1结束

	private Date closeTime; //会议关闭时间
	
	private String liveTime; //持续时间 用于列表展示 
	
	private String des;	// 描述 用于列表展示

	private String createTimeBak;	// 描述创建时间用于列表展示
	
	
	@Transient
	public String getCreateTimeBak() {
		return createTimeBak;
	}

	public void setCreateTimeBak(String createTimeBak) {
		this.createTimeBak = createTimeBak;
	}

	@Transient
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	
	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	@Transient
	public String getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(String liveTime) {
		this.liveTime = liveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMeetCode() {
		return meetCode;
	}

	public void setMeetCode(String meetCode) {
		this.meetCode = meetCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
