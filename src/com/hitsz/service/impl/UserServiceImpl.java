package com.hitsz.service.impl;

import com.hitsz.dao.UserDao;
import com.hitsz.dao.impl.UserDaoImpl;
import com.hitsz.domain.User;
import com.hitsz.service.UserService;
import com.hitsz.utils.MailUtils;

public class UserServiceImpl implements UserService {

	//业务层用户注册的方法
	public void regist(User user) throws Exception {
		// 将数据存入到数据库
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		
		//发送一封激活邮件
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}

	//根据激活码去查询用户
	public User findByCode(String code) throws Exception {
		UserDao userDao = new UserDaoImpl();
		return userDao.findByCode(code);
	}

	public User findByCode() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//业务层修改用户的方法
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}
	
}
