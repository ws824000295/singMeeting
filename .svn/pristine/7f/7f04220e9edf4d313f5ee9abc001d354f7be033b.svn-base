package com.boot.common.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.boot.common.util.Page;

public abstract class AbstractRepository {
	
	@PersistenceContext
	private EntityManager manager;

	public Page findSqlPage(Page page,String sql,Map<String,Object> param){
		this.findSqlCount(page, sql, param);
		Query query = createSqlQuery(sql);
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getSize());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		page.setContent(query.getResultList());
		return page;
	}
	
	public Page findSqlPage(Page page,String sql){
		this.findSqlCount(page, sql);
		Query query = createSqlQuery(sql);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getSize());
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		page.setContent(query.getResultList());
		return page;
	}
	
	private void findSqlCount(Page page,String sql,Map<String,Object> param){
		Query query = createSqlQuery("select count(*) from ("+sql+") totalcount"); 
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		BigInteger t = (BigInteger) query.getSingleResult();
		page.setTotalElements(t.intValue());
	}
	
	private void findSqlCount(Page page,String sql){
		Query query = createSqlQuery("select count(*) from ("+sql+") totalcount"); 
		BigInteger t = (BigInteger) query.getSingleResult();
		page.setTotalElements(t.intValue());
	}
	
	public Query createSqlQuery(String sql){
		return manager.createNativeQuery(sql);
	}
	
	public List<Map<String,Object>> findSqlQuery(String sql,Map<String,Object> param){
		Query query = createSqlQuery(sql);
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		return query.getResultList();
	}
	
	public Query createQuery(String hql){
		return manager.createQuery(hql);
	}
	
	public <T> List<T> findQuery(String hql,Map<String,Object> param){
		Query query = createQuery(hql);
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		return query.getResultList();
	}
	
	public Page findHqlPage(Page page,String hql,Map<String,Object> param){
		this.findHqlCount(page, hql, param);
		Query query = createQuery(hql);
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getSize());
		page.setContent(query.getResultList());
		return page;
	}
	
	public Page findHqlPage(Page page,String hql){
		this.findHqlCount(page, hql);
		Query query = createQuery(hql);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getSize());
		page.setContent(query.getResultList());
		return page;
	}
	
	private void findHqlCount(Page page,String hql,Map<String,Object> param){
		Query query = createQuery("select count(*) "+hql); 
		Set<Entry<String, Object>> set = param.entrySet();
		for(Entry<String, Object> s:set){
			query.setParameter(s.getKey(), s.getValue());
		}
		Long t =  (Long) query.getSingleResult();
		page.setTotalElements(t.intValue());
	}
	
	private void findHqlCount(Page page,String hql){
		Query query = createQuery("select count(*) "+hql); 
		Long t =  (Long) query.getSingleResult();
		page.setTotalElements(t.intValue());
	}
	
}
