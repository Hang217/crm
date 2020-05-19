package com.jyh.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jyh.crm.domain.Customer;
import com.jyh.crm.domain.LinkMan;
import com.jyh.crm.domain.PageBean;
import com.jyh.crm.service.CustomerService;
import com.jyh.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ��ϵ�˵�Action����
 * @author jt
 *
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	// ģ������ʹ�õĶ���
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	// ע��Service:
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	// ע��ͻ������ervice
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// ��ҳ����:
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

	/**
	 * ��ѯ��ϵ���б��Action
	 * @return
	 */
	public String findAll(){
		// ��������������ѯ:
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// ��������
		if(linkMan.getLkm_name()!=null){
			// ���ð����Ʋ�ѯ������:
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		// �����Ա�����:
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
			// ���ð��Ա��ѯ������:
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		// ����ҵ���
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ��ת�����ҳ��ķ���saveUI
	 */
	public String saveUI(){
		// ��ѯ���пͻ�:
		List<Customer> list = customerService.findAll();
		// ��list���ϱ���ֵջ��:
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	
	/**
	 * ������ϵ�˵ķ���:save
	 */
	public String save(){
		// ����ҵ��㱣����ϵ��
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	/**
	 * ��ת���༭ҳ��ķ�����edit
	 */
	public String edit(){
		// ��ѯĳ����ϵ�ˣ���ѯ���пͻ���
		// ��ѯ���пͻ�:
		List<Customer> list = customerService.findAll();
		// ����id��ѯ��ϵ��:
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// ��list��linkMan�Ķ������ҳ���ϣ�
		ActionContext.getContext().getValueStack().set("list", list);
		// �������ֵ���뵽ֵջ:
		ActionContext.getContext().getValueStack().push(linkMan);
		return "editSuccess";
	}
	
	/**
	 * �޸���ϵ�˵ķ���:update
	 */
	public String update(){
		// ����ҵ���:
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	/**
	 * ɾ����ϵ�˵ķ�����delete
	 */
	public String delete(){
		// ����ҵ���:
		linkMan = linkManService.findById(linkMan.getLkm_id());
		// ɾ����ϵ��
		linkManService.delete(linkMan);
		// ҳ����ת
		return "deleteSuccess";
	}
	
}
