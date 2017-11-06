package com.mall.dao;

import com.mall.pojo.User;

public interface UserDao {
	
	Integer addUser(User user);
	
	Integer modifyUser(User user);
	
	Integer checkName(String username);
	
	//登陆时用于验证用户名和密码
	Integer checkLogin(User user);
	
	Integer queryUserByUsername(String username);
	
	Integer findUserIdByUsername(String username);
}
