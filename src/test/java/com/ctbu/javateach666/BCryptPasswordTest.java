package com.ctbu.javateach666;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctbu.javateach666.util.BCryptEncoderUtil;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class BCryptPasswordTest {
	
	@Test
    public void testBC() {  
         String password = "123456";
         String hashpass = BCryptEncoderUtil.passwordEncoder(password);
         System.out.println("加密密码：" + hashpass);
    }
	
	//@Test
    public void testBC2() {  
         String password = "123";
         String hashpass1 = BCryptEncoderUtil.passwordEncoder(password);
         String hashpass2 = BCryptEncoderUtil.passwordEncoder(password);
         System.out.println("是否匹配：" + BCryptEncoderUtil.passwordMatch(password, hashpass1));
    }
}
