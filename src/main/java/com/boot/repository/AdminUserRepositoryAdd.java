package com.boot.repository;

import java.util.List;
import java.util.Map;

public interface AdminUserRepositoryAdd {
	public List<Map<String, Object>>  findAdminByParentName(String partnerName);
}
