package com.jyh.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jyh.crm.domain.User;
import com.jyh.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
/**
 * �û������Action����
 * @author jt
 * 
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	//ģ������ʹ�õĶ���
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}

	//ע��Service
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �û�ע��ķ���:regist
	 */
	public String regist(){
		userService.regist(user);
		return LOGIN;
	}
	
	/**
	 * �û���¼�ķ���:login
	 */
	public String login() {
		//����ҵ����ѯ�û�
		User existUser = userService.login(user);
		if(existUser == null) {
			// ��½ʧ��
			// ��Ӵ�����Ϣ
			this.addActionError("�û������������!");
			return LOGIN;
		}else {
			// ��½�ɹ�
			//ServletActionContext.getRequest().getSession().setAttribute(name, value);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		}
	}
	
	public String findAllUser() throws IOException{
		List<User> list = userService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
