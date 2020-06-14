package com.boot.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.boot.common.util.Page;

public interface UserRepositoryAdd {
	
	
	public Page findshareListPage(Page page, Integer status, String key,String userid );

	public Page findlowerListPage(Page page, Integer status, String key,String userid);

	public Page findpointsListPage(Page page,String userid);

	public Page findcoinsListPage(Page page,String userid);
	
	public List<Map<String, Object>> findUserBlockPage(int index, Integer limit);
	
	public List<Map<String, Object>> findUserAllTwo();

	public Page findAllUserByPage(Page page,String adminId);
	
	Page findUserByOrderPage(Page page, Integer status,String sortType, String key, String adminId);
	
	@Query(value="SELECT tu.id,tu.nick_name,tu.user_sex,tu.admin_id,DATE_FORMAT(tu.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(tu.update_time,'%Y-%m-%d') update_time FROM tb_user tu WHERE tu.admin_id='1' and tu.id IN (SELECT ta.`parent_userid` FROM tb_account ta )", nativeQuery = true)
	public List<Map<String, Object>> findExcelUser();
}
