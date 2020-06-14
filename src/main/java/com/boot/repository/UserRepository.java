package com.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.domain.User;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User>,UserRepositoryAdd{

	@Query(value="from User where adminId=?1 and openID=?2")
	User findUser(String adminId,String openID);
	
	@Query(value="select count(1) from tb_user where user_type != 0 " ,nativeQuery=true)
	int findUserByType();

//	@Query(value="SELECT u.id,u.user_icon_url,u.nick_name, u.update_time, a.level,a.points_total, a.coins_total,a.coins_remain,(SELECT count(a1.id) FROM tb_account a1 WHERE a1.parent_userid = u.id) lower_count,a.group_count,(SELECT count(id) FROM tb_withdraw_record wr WHERE wr.withdraw_id = w.id and wr.withdraw_status in(1,2,3)) withdrawCount,a.withdraw_count,u.user_sex,real_name,u.user_mobbile,u.city_name,u.occupation,u.education  FROM tb_user u ,tb_account a ,tb_withdraw w  WHERE a.user_id = u.id and w.user_id = u.id  and u.id =?1",nativeQuery=true)
	@Query(value="SELECT u.id, u.user_icon_url, u.nick_name, u.update_time, a. LEVEL, a.points_total, a.coins_total, a.coins_remain, ( 	SELECT count(a1.id) 	FROM tb_account a1 	WHERE a1.parent_userid = u.id )lower_count, a.group_count, u.user_sex, real_name, u.user_mobbile, u.city_name, u.occupation, u.education  ,a.parent_userid FROM 	tb_user u, tb_account a WHERE a.user_id = u.id AND u.id = ?1",nativeQuery=true)
	Object[] findUserBasicInfoByUserId(String userid);
	
	@Query(value="from User where nickName LIKE ?1")
	public List<User> search(String nickname);

	@Query(value="from User where openID=?1 and adminId = ?2 ")
	List<User> findUserByOpenidAndAdminId(String openID, String adminId);
	
	@Modifying
	@Query(value = " update tb_user set member_card = ?1 where id = ?2 ", nativeQuery = true)
	void updatUser(String memberCard, String id);

	@Query(value="SELECT count(*) from tb_user",nativeQuery=true)
	Integer findUserAllNum();

	@Query(value="from User where nickName LIKE ?1 and adminId = ?2")
	List<User> findUserByNickName(String nickName, String adminId);

	//分社下福粉总数
	@Query(value="select count(id) from tb_user where admin_id = ?1 and user_type!=0", nativeQuery = true)
	int findTotalUserNum(String adminId);
   
	@Modifying
	@Query(value = " update tb_user set status = ?1 where id = ?2 ", nativeQuery = true)
	void updatUserStatusByUserId(String status, String id);
	
	@Query(value="from User where unionid= ?1 and adminId = ?2 ")
	User findUserByUnionid(String unionid,String adminId);

	@Modifying
	@Query(value = " update tb_user set user_type = ?1 where id = ?2 ", nativeQuery = true)
	void updatUserTypeByUserId(int userType, String id);

	@Query(value="from User where unionid = ?1 and adminId = ?2 ")
	List<User> findUserByUnionIdAndAdminId(String unionid, String adminId);
	
	
	@Query(value="select id from tb_user where nick_name like %:nick_name% ", nativeQuery = true)
	List<String> findUserIdByNickName(@Param("nick_name") String userName);

	
	@Query(value="SELECT u.user_icon_url FROM tb_order t, tb_user u WHERE t.userid = u.id AND t.batch_id IN( SELECT b.id FROM tb_order a, tb_order_batch b WHERE a.id = b.order_id AND a.order_number = ?1 ) ", nativeQuery = true)
	List<Object> findUserIconUrlByOrder(String orderNumber);
 
	@Query(value="SELECT IFNULL(count(*),0) FROM tb_account taa WHERE taa.parent_userid = ?1 ",nativeQuery=true)
	Integer findLowerUserNumById(String id);	
	 
}
