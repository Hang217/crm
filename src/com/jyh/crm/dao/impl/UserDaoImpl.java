package com.jyh.crm.dao.impl;

import java.util.List;

import com.jyh.crm.dao.UserDao;
import com.jyh.crm.domain.User;
/**
 * �û������DAO��ʵ����
 * @author jt
 *
 */
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	// DAO�и����û�����������в�ѯ�ķ���
	public User login(User user) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?", user.getUser_code(),user.getUser_password());
		// �ж�һ��
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
