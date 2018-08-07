package com.hitsz.service.impl;

import com.hitsz.dao.UserDao;
import com.hitsz.dao.impl.UserDaoImpl;
import com.hitsz.domain.User;
import com.hitsz.service.UserService;
import com.hitsz.utils.MailUtils;

public class UserServiceImpl implements UserService {

	//ҵ����û�ע��ķ���
	public void regist(User user) throws Exception {
		// �����ݴ��뵽���ݿ�
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		
		//����һ�⼤���ʼ�
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}

	//���ݼ�����ȥ��ѯ�û�
	public User findByCode(String code) throws Exception {
		UserDao userDao = new UserDaoImpl();
		return userDao.findByCode(code);
	}

	public User findByCode() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//ҵ����޸��û��ķ���
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}
	
}
