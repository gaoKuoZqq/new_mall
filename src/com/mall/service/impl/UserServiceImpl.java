package com.mall.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.dao.UserDao;
import com.mall.pojo.User;
import com.mall.responce.ServerResponse;
import com.mall.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	UserDao userDao;
	
	public ServerResponse<?> addUser(User user) {
		if (userDao.checkName(user.getUsername()) > 0) {
			return ServerResponse.createError("已存在的用户名");
		}
		userDao.addUser(user);
		return ServerResponse.createSuccess();
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.modifyUser(user) > 0;
	}

	@Override
	public boolean checkName(String username) {
		return userDao.checkName(username) > 0;
	}

	@Override
	public boolean checkLogin(User user) {
		return userDao.checkLogin(user) > 0;
	}

	@Override
	public Integer findUserIdByUsername(String username) {
		return userDao.findUserIdByUsername(username);
	}
	
	
}
