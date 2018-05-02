package com.ctbu.javateach666.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypt加密
 * @author luokan
 *
 */
public class BCryptEncoderUtil {
	
	public final static String passwordEncoder(String password){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
        String hashedPassword = passwordEncoder.encode(password);  
		return hashedPassword;
	}
	
	public final static boolean passwordMatch(String noEncoderPass, String encodedPass){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
        boolean ismatch = passwordEncoder.matches(noEncoderPass, encodedPass);  
		return ismatch;
	}
}
