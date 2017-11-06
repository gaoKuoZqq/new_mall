package com.mall.service;

import com.mall.pojo.User;
import com.mall.responce.ServerResponse;

public interface UserService {
	
	ServerResponse<?> addUser(User user);
	
	boolean modifyUser(User user);
	
	//用于注册验证用户名
	boolean checkName(String username);
		
	//登陆时用于验证用户名和密码
	boolean checkLogin(User user);
	
	Integer findUserIdByUsername(String username);
}
