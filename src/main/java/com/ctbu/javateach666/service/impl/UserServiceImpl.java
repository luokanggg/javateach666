package com.ctbu.javateach666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ctbu.javateach666.dao.UserDao;
import com.ctbu.javateach666.pojo.po.User;
import com.ctbu.javateach666.service.interfac.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public User queryUserById(int id) {
		return userDao.queryUserById(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertUser(User user) {
		System.out.println("执行1次");
		//userDao.insertUser(user);
		System.out.println("执行2次");
		return userDao.insertUser(user);
	}


}
