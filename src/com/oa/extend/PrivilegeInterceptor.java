package com.oa.extend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.oa.dao.SuperDaoInte;
import com.oa.dao.impl.SuperDao;
import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.oa.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class PrivilegeInterceptor implements Interceptor {

	private static final long serialVersionUID = -8181217632709500253L;

	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("privilegeInterceptor start working");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		Map<Module, Map<Module, List>> maps = (Map<Module, Map<Module, List>>) session
				.getAttribute("modules");
		String method = request.getParameter("method");
		int value = 0;
		if (maps != null) {
			if (method != null) {
				if (method.trim().equals("1")) {
					value = 1;
				}
				if (method.trim().equals("2")) {
					value = 2;
				}
				if (method.trim().equals("4")) {
					value = 4;
				}
				if (method.trim().equals("8")) {
					value = 8;
				}
				System.out.println("value is "+value);
				System.out.println("map size is " + maps.size());
				String url = request.getRequestURL().toString();
				System.out.println("url is " + url);
				url = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("!"));
				System.out.println("url is " + url);
				Iterator<Module> iter = maps.keySet().iterator();
				while (iter.hasNext()) {
					Module key = iter.next();
					if(key == null) {
						System.out.println("key is null");
					}
					Map<Module, List> mapValue = maps.get(key);
					if(key != null)
					System.out.println("map parent name is " + key.getName());
					Iterator<Module> iterChild = mapValue.keySet().iterator();
					while (iterChild.hasNext()) {
						Module keyChild = iterChild.next();
						System.out.println("map child name is "
								+ keyChild.getName());
						System.out.println("map child Value is "
								+ mapValue.get(keyChild));
						System.out.println("map child size  is "
								+ mapValue.get(keyChild).size());
						String keyChildUrl = keyChild.getUrl();
						System.out.println("keyChildUrl is "+keyChildUrl);
						if(keyChildUrl.contains("!")) {
							keyChildUrl = keyChildUrl.substring(0, keyChildUrl.lastIndexOf("!"));
							System.out.println("keyChildUrl is "+keyChildUrl);
							if(keyChildUrl.equals(url)) {
								System.out.println("that is ok");
								int addValue = mapValue.get(keyChild).get(0)
										.hashCode();
								int readValue = mapValue.get(keyChild).get(1)
										.hashCode();
								int updateValue = mapValue.get(keyChild).get(2)
										.hashCode();
								int deleteValue = mapValue.get(keyChild).get(3)
										.hashCode();
								System.out.println("delete value is"+deleteValue);
								if(addValue == value) {
									return invocation.invoke();
								} else if(readValue == value) {
									return invocation.invoke();
								} else if(updateValue == value) {
									return invocation.invoke();
								} else if(deleteValue == value) {
									return invocation.invoke();
								} else {
									return Action.ERROR;
								}
								
							}
						}
					}
				}
			} 
		}
		return invocation.invoke();
	}

	public void destroy() {

	}

	public void init() {

	}

}