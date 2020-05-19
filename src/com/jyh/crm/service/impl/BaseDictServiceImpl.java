package com.jyh.crm.service.impl;

import java.util.List;

import com.jyh.crm.dao.BaseDictDao;
import com.jyh.crm.domain.BaseDict;
import com.jyh.crm.service.BaseDictService;
/**
 * �ֵ��ҵ����ʵ����
 * @author jt
 *
 */
public class BaseDictServiceImpl implements BaseDictService {
	// ע��DAO
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
