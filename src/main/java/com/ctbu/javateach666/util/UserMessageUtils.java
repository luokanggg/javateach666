package com.ctbu.javateach666.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 集合操作工具类
 *
 * @author Direct
 */
public class UserMessageUtils {
	
	/**
	 * 获取当前用户的用户名
	 * @return
	 */
    public static String getNowUserName() {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return userDetails.getUsername();
    }
    
//    /**
//     * 获取当前用户的用户名
//     * @return
//     */
//    public static Account getNowUserAccount() {
//    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	Account account = new Account();
//        account.setUsername(userDetails.getUsername());
//        List<Account> accountlist = AccountService.findList(account);
//        if(CollectionUtils.isNotBlank(accountlist)) {
//        	account = accountlist.get(0);
//        }
//    	return account;
//    }
    
    

}
