package com.ctbu.javateach666;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctbu.javateach666.pojo.po.User;



@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class RedisTest {
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
    //@Test
    public void testJedis() {  
        User user = new User();
        user.setUsername("thc");
        user.setPassword("0000334");
        user.setAge(10);
        //int count = userService.insertUser(user);       
        //System.out.println(count);  
        //logger.info("插入的行数："+count);  
        redisTemplate.opsForValue().set(user.getUsername(), user);      
    }
    
    //@Test
    public void testJedis4() {  
        User user = new User();
        user.setUsername("thc");
        user.setPassword("0000334");
        user.setAge(10);
        User user2 = new User();
        user2.setUsername("wsj");
        user2.setPassword("66666");
        user2.setAge(18);
        redisTemplate.opsForList().rightPush("user2", user); 
        redisTemplate.opsForList().rightPush("user2", user2);  
    }
    
    @Test
    public void testJedis2() {  
        User user2 = new User();
        user2 = (User) redisTemplate.opsForValue().get("thc");
        System.out.println(user2.toString());  
    }
    
    //@Test
    public void testJedis5() {  
        List<User> user5 = new ArrayList<User>();
        //user5 = redisTemplate.opsForList().range("user2", 0, redisTemplate.opsForList().size("user2"));
        System.out.println(user5.toString());  
    }
    
    //@Test
    public void testJedis3() {  
    	User user2 = new User();
        //user2 = redisTemplate.opsForValue().get("thc");
        System.out.println(user2.toString()); 
        System.out.println("删除thc"); 
        redisTemplate.delete("thc");
    }
    
    //@Test
    public void testJedis6() {  
    	List<User> user5 = new ArrayList<User>();
        //user5 = redisTemplate.opsForList().range("user", 0, redisTemplate.opsForList().size("user"));
        System.out.println(user5.toString());  
        System.out.println("删除"); 
        redisTemplate.delete("user");
    }
    
    //@Test
    public void testJedis7() {  
    	/*Set<String> keys = redisTemplate.keys("user2");
    	System.out.println(keys.size());
    	Iterator<String> i = keys.iterator();
    	while(i.hasNext()){
    		System.out.println(i.next()); 
    	}*/
    	//System.out.println(keys.toString());
    	boolean is = redisTemplate.hasKey("thc2");
    	System.out.println(is);
    }
}
