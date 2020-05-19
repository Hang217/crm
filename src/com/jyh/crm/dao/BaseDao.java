package com.jyh.crm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * ͨ�õ�DAO�Ľӿ�
 * @author jt
 *
 */
public interface BaseDao<T> {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public T findById(Serializable id);
	
	// ��ѯ����
	public List<T> findAll();
	
	// ͳ�Ƹ����ķ���
	public Integer findCount(DetachedCriteria detachedCriteria);
	
	// ��ҳ��ѯ�ķ���:
	public List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}