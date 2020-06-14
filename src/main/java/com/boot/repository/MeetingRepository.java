package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, String>, JpaSpecificationExecutor<Meeting>{

	/** 
	* @Title: queryListMeeting 
	* @Description: 加载所有的会议
	* @param @return设定文件 
	* @author 王朔
	* @return List<Meeting>返回类型 
	* @throws 
	*/
	@Query(value = " from Meeting where 1 = 1 order by createTime desc ")
	List<Meeting> queryListMeeting();

	/** 
	* @Title: queryMeetingByParam 
	* @Description: 根据条件查询
	* @param @param param
	* @param @return设定文件 
	* @author 王朔
	* @return List<Meeting>返回类型 
	* @throws 
	*/
	
	@Query(value = " from Meeting where meetName like ?1 order by createTime desc ")
	List<Meeting> queryMeetingByParam(String param);

 
	 
}
