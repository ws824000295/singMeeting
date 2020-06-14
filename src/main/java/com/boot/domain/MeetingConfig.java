package com.boot.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.boot.common.domain.AbstractEntity;

/**
* @ClassName: Meeting 
* @Description: 会议表 
* @author 王朔
* @date 2020年1月8日 下午4:13:35
 */
@Entity
@Table(name = "tb_meeting_config")
public class MeetingConfig extends AbstractEntity {

	private static final long serialVersionUID = 894215381394445620L;
	
	private String id;
	
	private String creator; //拉会账号 
	
	private String recordip;	//录制服务器IP
	
	private String recordport;	//端口

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRecordip() {
		return recordip;
	}

	public void setRecordip(String recordip) {
		this.recordip = recordip;
	}

	public String getRecordport() {
		return recordport;
	}

	public void setRecordport(String recordport) {
		this.recordport = recordport;
	}

	 
	 
}
