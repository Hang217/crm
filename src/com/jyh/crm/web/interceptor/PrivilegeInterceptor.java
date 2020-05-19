package com.jyh.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.jyh.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
  * Ȩ��������
 * @author jt
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// �ж�session���Ƿ��е�¼�û�����Ϣ
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			// �������Ϣ,ҳ����ת����¼ҳ��
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("����û�е�¼��û��Ȩ�޷���");
			return actionSupport.LOGIN;
		}else{
			// �Ѿ���¼
			return invocation.invoke();
		}
	}

}
