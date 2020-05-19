package com.jyh.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.jyh.crm.domain.LinkMan;
import com.jyh.crm.domain.PageBean;

/**
 * ��ϵ�˵�Service�Ľӿ�
 * @author jt
 *
 */
public interface LinkManService {
	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);
}
