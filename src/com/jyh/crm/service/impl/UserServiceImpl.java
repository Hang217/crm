package com.jyh.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jyh.crm.dao.UserDao;
import com.jyh.crm.domain.User;
import com.jyh.crm.service.UserService;
import com.jyh.crm.utils.MD5Utils;
/**
 * �û������Service��ʵ����
 * @author jt
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	// ע��DAO
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	// ҵ���ע���û��ķ���
	public void regist(User user) {
		// ��������м��ܴ���
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		// ����DAO
		userDao.save(user);
	}

	@Override
	// ҵ����û���¼�ķ���
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
