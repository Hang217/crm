package com.jyh.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jyh.crm.domain.Customer;
import com.jyh.crm.domain.PageBean;
import com.jyh.crm.service.CustomerService;
import com.jyh.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * �ͻ������Action��
 * 
 * @author jt
 *
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	// ģ������ʹ�õĶ���
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	// ע��Service
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// ʹ��set�����ķ�ʽ��������:
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	// ʹ��set��������ÿҳ��ʾ��¼��
	private Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}
	
	/**
	 * �ļ��ϴ��ṩ����������:
	 */
	private String uploadFileName; // �ļ�����
	private File upload; // �ϴ��ļ�
	private String uploadContentType; // �ļ�����

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	/**
	 * �ͻ�������ת�����ҳ��ķ���:saveUI
	 */
	public String saveUI() {
		return "saveUI";
	}
	
	/**
	 * ����ͻ��ķ�����save
	 * 
	 * @throws IOException
	 */
	public String save() throws IOException {
		// �ϴ�ͼƬ:
		if (upload != null) {
			// �ļ��ϴ���
			// �����ļ��ϴ�·��:
			String path = "D:/MyEclipse/upload";
			// һ��Ŀ¼�´�ŵ���ͬ�ļ���������ļ���
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�´�ŵ��ļ����ࣺĿ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼:
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �ļ��ϴ�:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			// ����image�����Ե�ֵ:
			customer.setCust_image(url + "/" + uuidFileName);
		}
		// ����ͻ�
		customerService.save(customer);
		return "saveSuccess";
	}
	
	/**
	 * ��ҳ��ѯ�ͻ��ķ�����findAll
	 */
	public String findAll() {
		// ���ղ�������ҳ����
		// ���ʹ��DetachedCriteria����������ѯ--����ҳ��
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// ��������������web������������
		if (customer.getCust_name() != null) {
			// ��������:
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		if (customer.getBaseDictLevel() != null) {
			if (customer.getBaseDictLevel().getDict_id() != null
					&& !"".equals(customer.getBaseDictLevel().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if (customer.getBaseDictSource() != null) {
			if (customer.getBaseDictSource().getDict_id() != null
					&& !"".equals(customer.getBaseDictSource().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}

		}
		if (customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null) {
			if (customer.getBaseDictIndustry().getDict_id() != null
					&& !"".equals(customer.getBaseDictIndustry().getDict_id())) {
				detachedCriteria
						.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		// ����ҵ����ѯ:
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	/**
	 * ɾ���ͻ��ķ�����delete
	 */
	public String delete() {
		// �Ȳ�ѯ����ɾ����
		customer = customerService.findById(customer.getCust_id());
		// ɾ��ͼƬ:
		if (customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if (file.exists()) {
				file.delete();
			}
		}
		// ɾ���ͻ�:
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	/**
	 * �༭�ͻ��ķ���:edit
	 */
	public String edit() {
		// ����id��ѯ����תҳ�棬��������
		customer = customerService.findById(customer.getCust_id());
		// ��customer���ݵ�ҳ�棺
		// ���ַ�ʽ��һ�֣��ֶ�ѹջ�����֣���Ϊģ�������Ķ���Ĭ����ջ����
		// ���ʹ�õ�һ�ַ�ʽ����������: <s:property value="cust_name"/> <s: name="cust_name" value="">
		// ActionContext.getContext().getValueStack().push(customer);
		// ���ʹ�õڶ��ַ�ʽ����������: <s:property value="model.cust_name"/>
		// ��תҳ��
		return "editSuccess";
	}

	/**
	 * �޸Ŀͻ��ķ���:update
	 * 
	 * @throws IOException
	 */
	public String update() throws IOException {
		// �ļ����Ƿ��Ѿ�ѡ�����ѡ���ˣ���ɾ��ԭ���ļ����ϴ����ļ������û��ѡ��ʹ��ԭ�м��ɡ�
		if (upload != null) {
			// �Ѿ�ѡ����:
			// ɾ��ԭ���ļ�:
			String cust_image = customer.getCust_image();
			if (cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				file.delete();
			}
			// �ļ��ϴ���
			// �����ļ��ϴ�·��:
			String path = "D:/MyEclipse/upload";
			// һ��Ŀ¼�´�ŵ���ͬ�ļ���������ļ���
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			// һ��Ŀ¼�´�ŵ��ļ����ࣺĿ¼����
			String realPath = UploadUtils.getPath(uuidFileName);
			// ����Ŀ¼:
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �ļ��ϴ�:
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	
	public String findAllCustomer() throws IOException{
		List<Customer> list = customerService.findAll();
		// ��listת��JSON:
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		// ת��JSOn:
		JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
