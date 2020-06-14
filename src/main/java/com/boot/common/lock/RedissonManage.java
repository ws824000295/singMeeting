package com.boot.common.lock;

import java.io.FileInputStream;
import java.util.Properties;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonManage {
	
	private static RedissonClient redisson = null;
	
	public static RLock getLock(String lockName){
		if(redisson==null){
			initRedisson();
		}
		RLock lock = redisson.getLock(lockName);
		return lock;
	}

	private static void initRedisson(){
		try{
		  Properties pps = new Properties();
	      pps.load(new FileInputStream(RedissonManage.class.getClassLoader().getResource("application.properties").getPath()));
	      String ip = pps.getProperty("spring.redis.host");
	      String port = pps.getProperty("spring.redis.port");
		  Config config=new Config();  
	      config.useSingleServer().setAddress(ip+":"+port).setConnectionPoolSize(500);  
	      redisson = Redisson.create(config);
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
}
