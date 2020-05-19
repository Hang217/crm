package com.jyh.crm.dao.impl;

import java.util.List;

import com.jyh.crm.dao.BaseDictDao;
import com.jyh.crm.domain.BaseDict;

/**
 * �ֵ�DAO��ʵ����
 * 
 * @author jt
 *
 */
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	@Override
	// �������ͱ����ѯ�ֵ�����
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?", dict_type_code);
	}

}
