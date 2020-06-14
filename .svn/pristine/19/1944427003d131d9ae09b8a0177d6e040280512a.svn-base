package com.boot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.boot.common.repository.AbstractRepository;
import com.boot.common.util.Page;

public class UserRepositoryImpl extends AbstractRepository implements UserRepositoryAdd{

	

	@Override
	public Page findshareListPage(Page page, Integer status, String key,String userid) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT t.create_time,fl.welfare_name,fl.billing_type ,t.page_views,(SELECT count(1) from tb_coins_records c, tb_user u WHERE u.id = c.click_user_id and c.share_user_id = :userid AND c.welfare_id = fl.id ) validCount ,t.coins_total FROM tb_share_records t ,tb_welfare fl WHERE  fl.id = t.welfare_id  and  t.share_user_id = :userid  ");
		
		
		if(status != -1 ){
			sql.append(" and fl.billing_type = :type ");
			param.put("type", status);
		}
		
		if(key != null && !"".equals(key.trim())  ){
			sql.append("  and fl.welfare_name LIKE :key ");
			param.put("key", "%"+key+"%");
		}
		
		sql.append(" order by t.create_time desc ");
		
		param.put("userid", userid);
		
		return findSqlPage(page, sql.toString(), param);
	}

	@Override
	public Page findlowerListPage(Page page, Integer status, String key,String userid) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT u.id,u.user_icon_url,u.nick_name,a.level,a.create_time,(SELECT IFNULL(SUM(tc.coins_num),'0.00') FROM `tb_coins_records` tc WHERE tc.share_user_id = :userid  and tc.click_user_id = u.id and tc.billing_type = 8 ) coinsChildren FROM tb_account a ,tb_user u WHERE a.user_id = u.id and a.parent_userid = :userid ");
		
		
		if(key != null && !"".equals(key.trim()) ){
			sql.append(" and u.nick_name LIKE :key ");
			param.put("key", "%"+key+"%");
		}
		
		if(status != -1 ){
			if(status == 0){
				sql.append(" ORDER BY  coinsChildren DESC ");
			}else{
				sql.append(" ORDER BY  coinsChildren ASC ");
			}
		}else if(status == -1){
			sql.append(" ORDER BY a.create_time DESC ");
		}
		
		
		param.put("userid", userid);
		
		return findSqlPage(page, sql.toString(), param);
	}

	@Override
	public Page findpointsListPage(Page page,String userid) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT * FROM tb_points_records t WHERE t.user_id = :userid  order by t.create_time desc  ");
		
		param.put("userid", userid);
		
		return findSqlPage(page, sql.toString(),param);
	}

	@Override
	public Page findcoinsListPage(Page page ,String userid) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		StringBuffer sql = new StringBuffer();
		
//		sql.append(" SELECT * FROM tb_coins_records t WHERE t.share_user_id = :userid  order by t.create_time desc  ");
		sql.append(" SELECT t.create_time, t.billing_type, t.coins_num , u.nick_name FROM tb_coins_records t, 	tb_user u WHERE  t.coins_num !=0  AND  t.click_user_id = u.id  AND  t.share_user_id = :userid order by t.create_time desc ");
		
		param.put("userid", userid);
		
		return findSqlPage(page, sql.toString(),param);
	}

	@Override
	public List<Map<String, Object>> findUserBlockPage(int index, Integer limit) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select u.id,u.admin_id,u.user_icon_url,u.openID,u.nick_name,a.coins_total,u.create_time,a.lower_level_count from tb_user u,tb_account a where u.id=a.user_id and u.user_type=1 and a.coins_total>0 ORDER BY u.id limit :index ,:limit");
		param.put("index", index);
		param.put("limit", limit);
		return findSqlQuery(sql.toString(), param);
	}

	@Override
	public List<Map<String, Object>> findUserAllTwo() {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("select u.id,u.nick_name,a.coins_total,u.create_time,a.lower_level_count from tb_user u,tb_account a where u.id=a.user_id and u.user_type=1 and a.coins_total>0");
		return findSqlQuery(sql.toString(),param);
	}
	@Override
	public Page findAllUserByPage(Page page,String adminId) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.openID FROM tb_user t WHERE t.admin_id=:adminId and t.user_type=2 and t.subscribe_state=1 ORDER BY t.create_time ");
		param.put("adminId", adminId);
		
		return findSqlPage(page, sql.toString(),param);
	}

	@Override
	public Page findUserByOrderPage(Page page, Integer status,String sortType, String key, String adminId) {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT u.id, u.user_icon_url, u.nick_name, u.create_time, u.LEVEL, a.points_total, a.coins_total,ta.parent_userid, a.coins_remain,ifnull(a.lower_level_count,0) lower_level_count, ");
		sql.append(" count(o.id) order_num ");
		sql.append(" FROM (select * from tb_user q where q.user_type!=0 and q.admin_id = :adminId ");
		param.put("adminId", adminId);
		if(StringUtils.isNotBlank(key)){
			sql.append(" and  INSTR(q.nick_name,:key)> 0 ");
			param.put("key", key);
		}
		if(status != -1 ){
			sql.append(" and q.level = :level ");
			param.put("level", status);
		}
		sql.append(" ) u LEFT JOIN tb_account a ON a.user_id = u.id left join tb_account ta on u.id = ta.parent_userid ");
		sql.append("LEFT JOIN tb_order o ON o.userid = u.id group by u.id ");
		if(sortType==null || sortType=="") {
			sql.append(" order by u.create_time desc ");
		}else if(sortType.equals("1")){
			sql.append(" order by count(o.id) desc ");
		}else if(sortType.equals("-1")){
			sql.append(" order by count(o.id) asc ");
		}
		return findSqlPage(page, sql.toString(), param);
	}

	@Override
	public List<Map<String, Object>> findExcelUser() {
		Map<String,Object> param = new HashMap<String,Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tu.id,tu.nick_name,tu.user_sex,tu.admin_id,DATE_FORMAT(tu.create_time,'%Y-%m-%d') create_time,DATE_FORMAT(tu.update_time,'%Y-%m-%d') update_time FROM tb_user tu WHERE tu.admin_id='1' and tu.id IN (SELECT ta.`parent_userid` FROM tb_account ta )");
		return findSqlQuery(sql.toString(),param);
	}
}
