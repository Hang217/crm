package com.jyh.crm.service;

import java.util.List;

import com.jyh.crm.domain.User;

/**
 *用户管理的Service的接口
 * @author jt
 */
public interface UserService {

	void regist(User user);

	User login(User user);

	List<User> findAll();

}
