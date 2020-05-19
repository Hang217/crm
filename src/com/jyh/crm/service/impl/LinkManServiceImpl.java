package com.jyh.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.crm.dao.LinkManDao;
import com.jyh.crm.domain.LinkMan;
import com.jyh.crm.domain.PageBean;
import com.jyh.crm.service.LinkManService;
/**
 * ��ϵ�˵�Service��ʵ����
 * @author jt
 *
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	@Override
	// ҵ����ҳ��ѯ��ϵ�˵ķ���
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		// ���õ�ǰҳ��:
		pageBean.setCurrPage(currPage);
		// ����ÿҳ��ʾ��¼��:
		pageBean.setPageSize(pageSize);
		// �����ܼ�¼��
		Integer totalCount = linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// ÿҳ��ʾ���ݵļ���
		Integer begin = (currPage - 1) * pageSize;
		List<LinkMan> list = linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	// ҵ��㱣����ϵ�˵ķ���
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
	}

	@Override
	// ҵ������ID��ѯ��ϵ�˵ķ���
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	// ҵ����޸���ϵ�˵ķ���
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	// ҵ���ɾ����ϵ�˵ķ���:
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
