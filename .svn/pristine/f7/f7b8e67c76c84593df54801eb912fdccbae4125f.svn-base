package com.boot.common.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

import com.boot.common.cache.AbstractCache;

public abstract class AbstractService extends AbstractCache{

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired 
	private JmsMessagingTemplate jmsTemplate;  
	
	public <T> Object get(Class<T> entityClass,Object id){
		return manager.find(entityClass, id);
	}
	
	public void convertAndSend(String destination,Object obj){
		jmsTemplate.convertAndSend(destination, obj);
	}
}
