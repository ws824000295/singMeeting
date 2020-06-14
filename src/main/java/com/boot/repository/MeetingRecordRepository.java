package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.MeetingRecord;

public interface MeetingRecordRepository extends JpaRepository<MeetingRecord, String>, JpaSpecificationExecutor<MeetingRecord>{

	/** 
	* @Title: queryRecordByMeetCode 
	* @Description: 根据会议号查询  该会议中的PC端账号信息
	* @param @param valueOf
	* @param @return设定文件 
	* @author 王朔
	* @return List<MeetingRecord>返回类型 
	* @throws 
	*/
	@Query(value = " from MeetingRecord where meetCode = ?1 ")
	List<MeetingRecord> queryRecordByMeetCode(String meetCode);

	/** 
	* @Title: findRecordByAccount 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param account
	* @param @return设定文件 
	* @author 王朔
	* @return MeetingRecord返回类型 
	* @throws 
	*/
	@Query(value = " from MeetingRecord where account = ?1 order by createTime desc  ")
	List<MeetingRecord> findRecordByAccount(String account);

	/** 
	* @Title: findByMeetId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param meetId
	* @param @return设定文件 
	* @author 王朔
	* @return List<MeetingRecord>返回类型 
	* @throws 
	*/
	@Query(value = " from MeetingRecord where meetId = ?1  ")
	List<MeetingRecord> findByMeetId(String meetId);
 
	 
}
