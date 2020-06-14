package com.boot.common.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public abstract class AbstractCache {

	    @Autowired
	    private RedisTemplate redisTemplate;
	    
	    @Autowired
	    private StringRedisTemplate stringRedisTemplate;
	    
	    //递增
	    public Long increment(final String key) {
	    	RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
	    	Long increment = entityIdCounter.getAndIncrement();
	    	return increment;
	    }
	    
		@SuppressWarnings("unchecked")
		public void saveCache(final String key,final Object obj) {
			redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
				//	operations.watch(key);
				//	operations.multi();
					
					BoundValueOperations<String,Object> boundValueOperations = operations.boundValueOps(key);
					boundValueOperations.set(obj);
					
				//	operations.exec();
					return null;
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public void saveCache(final String key,final Object obj,final long time) {
			redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
				//	operations.watch(key);
				//	operations.multi();
					
					BoundValueOperations<String,Object> boundValueOperations = operations.boundValueOps(key);
					boundValueOperations.set(obj);
					boundValueOperations.expire(time, TimeUnit.SECONDS);
					
				//	operations.exec();
					return null;
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public Object getCache(final String key) {
			
			return redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
					BoundValueOperations<String,Object> boundValueOperations = operations.boundValueOps(key);
					return boundValueOperations.get();
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public void delCache(final String key) {
			redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
				//	operations.watch(key);
				//	operations.multi();
					
					operations.delete(key);
					
				//	operations.exec();
					return null;
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public void pushQueue(final String key,final Object obj) {
			redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
					
					BoundListOperations<String,Object> boundListOps = operations.boundListOps(key);
					boundListOps.leftPush(obj);
					
					return null;
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		public Object popQueue(final String key) {
			return redisTemplate.execute(new SessionCallback<Object>() {
				public Object execute(final RedisOperations operations) throws DataAccessException {
					BoundListOperations<String,Object> boundListOps = operations.boundListOps(key);
					return boundListOps.rightPop();
				}
			});
		}
		
		public void publish(String channel, String message){
			stringRedisTemplate.convertAndSend(channel, message);
		}
}
