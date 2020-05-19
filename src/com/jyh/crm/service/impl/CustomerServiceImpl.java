package com.jyh.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.crm.dao.CustomerDao;
import com.jyh.crm.domain.Customer;
import com.jyh.crm.domain.PageBean;
import com.jyh.crm.service.CustomerService;

/**
 * �ͻ������Service��ʵ����
 * @author jt
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {
	// ע��ͻ���DAO
	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	// ҵ��㱣��ͻ��ķ���:
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	// ҵ����ҳ��ѯ�ͻ��ķ���:
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		// ��װ��ǰҳ��:
		pageBean.setCurrPage(currPage);
		// ��װÿҳ��ʾ��¼��:
		pageBean.setPageSize(pageSize);
		// ��װ�ܼ�¼��:
		// ����DAO:
		Integer totalCount  = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		// ��װ��ҳ��:
		Double tc = totalCount.doubleValue();
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		// ��װÿҳ��ʾ���ݵļ���
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	// ҵ������ID��ѯ�ͻ��ķ���
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	// ҵ���ɾ���ͻ��ķ���
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	// ҵ����޸Ŀͻ��ķ���:
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	// ҵ����ѯ���пͻ��ķ���:
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
	
}
