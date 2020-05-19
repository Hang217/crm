package com.jyh.crm.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jyh.crm.domain.PageBean;
import com.jyh.crm.domain.SaleVisit;
import com.jyh.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * �ͻ��ݷü�¼��Action����
 * @author jt
 *
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{

	// ģ������ʹ�õĶ���:
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	// ��Action��ע��Service:
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	// ���շ�ҳ ����:
	private Integer currPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrPage(Integer currPage) {
		if(currPage == null){
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	// ��������:
	private Date visit_end_time;
	
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}
	
	public Date getVisit_end_time() {
		return visit_end_time;
	}

	/**
	 * ��ѯ�ݷü�¼�б�ķ�����findAll
	 */
	public String findAll(){
		// ��������������ѯ����:
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// ��������:
		if(saleVisit.getVisit_time() != null){
			detachedCriteria.add(Restrictions.ge("visit_time",saleVisit.getVisit_time()));
		}
		if(visit_end_time != null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		// ����ҵ���:
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		// ���뵽ֵջ:
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * �ݷü�¼��ת�����ҳ��ķ���:saveUI
	 */
	public String saveUI(){
		return "saveUI";
	}
	
	/**
	 * ����ͻ��ݷü�¼�ķ���:save
	 */
	public String save(){
		// ����ҵ���:
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
}
