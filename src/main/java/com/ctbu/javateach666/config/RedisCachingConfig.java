package com.ctbu.javateach666.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * Redis配置类
 *
 * @author luokan
 */
@Configuration
@EnableCaching
public class RedisCachingConfig {
	
	//配置Redis缓存管理器Bean
	@Bean(name="javateachcache")
	public CacheManager cacheManager(RedisTemplate<String,Object> redisTemplate){
		System.out.println("生成CacheManager");
		return new RedisCacheManager(redisTemplate);
	}
	
	//配置Redis连接工程Bean
	@Bean
	public JedisConnectionFactory redisConnectionFactory(){
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		//jedisConnectionFactory.setHostName("localhost");
		//jedisConnectionFactory.setPort(6379);
		jedisConnectionFactory.setPassword("123456");
		//jedisConnectionFactory.setTimeout(3000);
		jedisConnectionFactory.afterPropertiesSet();
		System.out.println("生成JedisConnectionFactory");
		return jedisConnectionFactory;
	}
	
	//配置RedisTemplate模板Bean
	@Bean
	public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisCF){
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;  
	    redisTemplate.setKeySerializer(redisSerializer);  
	    redisTemplate.setHashKeySerializer(redisSerializer);
		redisTemplate.setConnectionFactory(redisCF);
		redisTemplate.afterPropertiesSet();
		System.out.println("生成RedisTemplate");
		return redisTemplate;
	}
	
}
