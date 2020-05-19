package com.jyh.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.crm.dao.SaleVisitDao;
import com.jyh.crm.domain.PageBean;
import com.jyh.crm.domain.SaleVisit;
import com.jyh.crm.service.SaleVisitService;

/**
 * �ͻ��ݷü�¼��ҵ����ʵ����
 * 
 * @author jt
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	// ע��ͻ��ݷü�¼��DAO:
	@Resource(name = "saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	// ҵ����ҳ��ʾ�ݷü�¼�ķ���:
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		// ���õ�ǰҳ��:
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ��¼��:
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��:
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ������ҳ����
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ����ÿҳ��ʾ�����ݵļ���:
		Integer begin = (currPage - 1) * pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}
}
