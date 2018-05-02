package com.ctbu.javateach666.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类
 *
 * @author luokan
 */
@Component
public class RedisUtil {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
		
	@SuppressWarnings("unchecked")
	public <T>T getData(String key){
		Object data = new Object();
		data = redisTemplate.opsForValue().get(key);
		return (T) data;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getDatas(String key){
		List<T> datas = new ArrayList<T>();
		datas = (List<T>) redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key));
		return datas;
	}
	
	public <T> void saveData(String key, T data){
		redisTemplate.opsForValue().set(key, data); 
	}
	
	public <T> void saveDatas(String key, List<T> datas){
		for (Object obj : datas) {
			redisTemplate.opsForList().rightPush(key, obj); 
		}
	}
	
	public void deleteData(String key){
		redisTemplate.delete(key);
	}
	
	public boolean exist(String key){
		return redisTemplate.hasKey(key);
	}
	
	public boolean flushdb(){
		try {
			Set<String> keys = redisTemplate.keys("*");
	    	for (String string : keys) {
				System.out.println("key" + string);
			}
	    	redisTemplate.delete(keys);
	    	return true;
		} catch (Exception e) {
			return false;
		}
	}
}
