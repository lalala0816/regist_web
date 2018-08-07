package com.hitsz.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hitsz.dao.UserDao;
import com.hitsz.domain.User;
import com.hitsz.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {
	//Dao中保存用户的方法
	public void regist(User user) throws Exception{
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(),
				user.getPassword(), user.getNickname(), user.getEmail(),
				user.getState(), user.getCode() };
		queryRunner.update(sql, params);
	
	}

	//Dao中根据激活码查询用户的方法
	public User findByCode(String code) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code = ?";
		User user = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
		return user;
	}

	//Dao中修改用户的方法
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username = ?, password = ?, nickname = ?, email = ?, code = ? where uid = ?";
		Object[] params = { user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getState(), 
				user.getCode(), user.getUid()};
		queryRunner.update(sql, params);
	}
}
