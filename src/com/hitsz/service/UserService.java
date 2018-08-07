package com.hitsz.service;


import com.hitsz.domain.User;

public interface UserService {

	void regist(User user) throws Exception;

	User findByCode() throws Exception;

	void update(User user) throws Exception;

}
