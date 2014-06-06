package com.oa.extend;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.action.UserAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SecurityInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181217632709500253L;

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		String clazz=invocation.getAction().getClass().getName();
//		System.out.println(clazz+invocation.getAction());
		
		if (session.getAttribute("admin") == null ){
			if(UserAction.class.getName().equals(clazz)){
				return invocation.invoke();
			}
			return Action.LOGIN;
		}else {
			if(UserAction.class.getName().equals(clazz)){
				Map parameters = invocation.getInvocationContext().getParameters();
				if(parameters.get("user.account")!=null){
					System.out.println("-====");
					System.out.println("user.account");
					session.removeAttribute("admin");
				}
			}
			return invocation.invoke();
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}