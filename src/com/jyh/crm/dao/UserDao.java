package com.jyh.crm.dao;

import com.jyh.crm.domain.User;

/**
 * �û������DAO�Ľӿ�
 * @author jt
 *
 */
public interface UserDao extends BaseDao<User> {

	User login(User user);

}
