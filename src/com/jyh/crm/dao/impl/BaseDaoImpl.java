package com.jyh.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jyh.crm.dao.BaseDao;
/**
 * ͨ�õ�DAO��ʵ����
 * @author jt
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private Class clazz;

	// �ṩ���췽�����ڹ��췽���д���������͵�class
	/**
	 * �����������й��췽���������ڸ������ṩ�޲����Ĺ��죬���޲ι����л�þ������͵�Class��
	 * �������͵�Class�ǲ��������е�ʵ�����Ͳ�����
	 */
	public BaseDaoImpl() {
		// ���䣺��һ�����Class
		Class clazz = this.getClass();// ���ڱ������Ǹ����Class,CustomerDaoImpl����LinkManDaoImpl��
		// �鿴JDK��API
		Type type = clazz.getGenericSuperclass();// ���������ͣ�BaseDaoImpl<Customer>��BaseDaoImpl<LinkMan>
		System.out.println(type);
		// �õ����type����һ�������������ͣ� ��typeǿת�ɲ�����������:
		ParameterizedType pType = (ParameterizedType) type;
		// ͨ�����������ͻ��ʵ�����Ͳ���:�õ�һ��ʵ�����Ͳ��������飿Map<String,Integer>.
		Type[] types = pType.getActualTypeArguments();
		// ֻ��õ�һ��ʵ�����Ͳ������ɡ�
		this.clazz = (Class) types[0];// �õ�Customer��LinkMan��User
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	// ��ѯ���еķ���
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	// ͳ�Ƹ����ķ���
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// ����ͳ�Ƹ���������:
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	@Override
	// ��ҳ��ѯ
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}
}