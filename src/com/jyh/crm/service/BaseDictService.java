package com.jyh.crm.service;

import java.util.List;

import com.jyh.crm.domain.BaseDict;

/**
 * �ֵ��ҵ���Ľӿ�
 * @author jt
 *
 */
public interface BaseDictService {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
