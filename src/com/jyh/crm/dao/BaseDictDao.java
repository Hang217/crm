package com.jyh.crm.dao;

import java.util.List;

import com.jyh.crm.domain.BaseDict;

/**
 * ×ÖµäDAOµÄ½Ó¿Ú
 * @author jt
 *
 */
public interface BaseDictDao extends BaseDao<BaseDict> {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
