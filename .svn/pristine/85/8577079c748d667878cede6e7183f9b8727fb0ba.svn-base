package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.MeetingConfig;

public interface MeetConfigRepository extends JpaRepository<MeetingConfig, String>, JpaSpecificationExecutor<MeetingConfig>{

	/** 
	* @Title: queryConfig 
	* @Description: 
	* @param @return设定文件 
	* @author 王朔
	* @return MeetingConfig返回类型 
	* @throws 
	*/
	@Query(value = " from MeetingConfig where 1=1 ")
	MeetingConfig queryConfig();
 
	
	 
}
