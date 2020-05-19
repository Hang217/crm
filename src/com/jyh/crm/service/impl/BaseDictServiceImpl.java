package com.jyh.crm.service.impl;

import java.util.List;

import com.jyh.crm.dao.BaseDictDao;
import com.jyh.crm.domain.BaseDict;
import com.jyh.crm.service.BaseDictService;
/**
 * 字典的业务层的实现类
 * @author jt
 *
 */
public class BaseDictServiceImpl implements BaseDictService {
	// 注入DAO
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
