package com.jyh.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.jyh.crm.domain.PageBean;
import com.jyh.crm.domain.SaleVisit;

/**
 * �ͻ��ݷü�¼��ҵ���Ľӿ�
 * @author jt
 *
 */
public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
