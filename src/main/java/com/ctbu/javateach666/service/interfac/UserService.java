package com.ctbu.javateach666.service.interfac;

import com.ctbu.javateach666.pojo.po.User;

public interface UserService {
	public User queryUserById(int id);
	public int insertUser(User user);
}
