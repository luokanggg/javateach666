package com.ctbu.javateach666;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctbu.javateach666.pojo.po.User;
import com.ctbu.javateach666.util.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class RedisUtilTest {
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Test
	public void test1(){
		User user = redisUtil.getData("lk3");
		System.out.println(user.toString());
	}
	
	//@Test
	public void test2(){
		List<User> users = redisUtil.getDatas("lk2");
		System.out.println(users.toString());
	}
	
	//@Test
	public void test3(){
		redisUtil.deleteData("user2");
	}
	
	//@Test
	public void test4(){
		User u = new User();
		u.setUsername("abc");
		u.setPassword("98765");
		redisUtil.saveData("lk3", u);
	}
	
	//@Test
	public void test5(){
		User user = new User();
        user.setUsername("thc");
        user.setPassword("0000334");
        user.setAge(10);
        User user2 = new User();
        user2.setUsername("wsj");
        user2.setPassword("66666");
        user2.setAge(18);
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        list.add(user);
        redisUtil.saveDatas("lk2", list);
	}
	
	//@Test
	public void test6(){
		System.out.println(redisUtil.exist("lk23"));
	}
}
