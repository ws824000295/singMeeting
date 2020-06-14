package com.boot.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boot.common.repository.AbstractRepository;

public class AdminUserRepositoryImpl extends AbstractRepository implements AdminUserRepositoryAdd{

	@Override
	public List<Map<String, Object>>  findAdminByParentName(String partnerName) {
		Map<String,Object> param = new HashMap<String,Object>();
		String sql="select u.*,DATE_FORMAT(u.partner_time_begin,'%Y-%m-%d') beginTime,DATE_FORMAT(u.partner_time_end,'%Y-%m-%d') endTime from admin_user u where u.partner_name = :partnerName limit 1";
		param.put("partnerName",partnerName );
		return this.findSqlQuery(sql, param);
	}

}
