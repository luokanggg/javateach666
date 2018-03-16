package com.ctbu.javateach666;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.ctbu.javateach666.pojo.po.User;
import com.ctbu.javateach666.service.interfac.UserService;




@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMybatis {
	private static Logger logger = Logger.getLogger(TestMybatis.class); 
	
	@Resource  
    private UserService userService = null; 
	
    @Test  
    public void test1() {  
        User user = userService.queryUserById(1);  
        System.out.println(user.getUsername());  
        logger.info("值："+user.getUsername());  
    }
}
