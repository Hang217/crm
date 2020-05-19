package com.jyh.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jyh.crm.domain.Customer;
import com.jyh.crm.domain.PageBean;

/**
 * �ͻ������Service�Ľӿ�
 * @author jt
 */
public interface CustomerService {

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);
	
	List<Customer> findAll();

}
