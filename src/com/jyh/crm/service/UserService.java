package com.jyh.crm.service;

import java.util.List;

import com.jyh.crm.domain.User;

/**
 *�û������Service�Ľӿ�
 * @author jt
 */
public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
