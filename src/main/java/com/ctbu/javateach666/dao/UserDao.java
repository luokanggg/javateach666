package com.ctbu.javateach666.dao;

import com.ctbu.javateach666.pojo.po.User;

public interface UserDao {
	public User queryUserById(int id);
	public int insertUser(User user);
}
