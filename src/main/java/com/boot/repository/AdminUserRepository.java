package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, String>,AdminUserRepositoryAdd{

	@Query(value="from AdminUser where username=?1")
	public AdminUser getAdminUser(String userName);

	@Query(value="from AdminUser where username=?1")
	public AdminUser findByUserName(String username);

	@Query(value=" select count(1) from admin_user  ",nativeQuery=true)
	public Integer findCount();

	@Query(value=" from AdminUser where agentNo =?1 ")
	public AdminUser findByAngetNo(String agentNo);

	@Query(value=" select t.wallet_name,t.username from admin_user t where t.agent_no = ?1 " ,nativeQuery=true)
	public List<Object[]> findByAngetNoList(String agentNo);
	
}
