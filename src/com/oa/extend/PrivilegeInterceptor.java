package com.oa.extend;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.oa.model.Module;
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
		if(method != null) {
			System.out.println("method is "+method);
		}
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
					if(key != null) {
						System.out.println("map parent name is " + key.getName());
						Iterator<Module> iterChild = mapValue.keySet().iterator();
						while (iterChild.hasNext()) {
						Module keyChild = iterChild.next();
						System.out.println("map child name is "
								+ keyChild.getName());
						System.out.println("map child Value is "
								+ mapValue.get(keyChild));
						/*
						System.out.println("map child size  is "
								+ mapValue.get(keyChild).size());
						*/
						String keyChildUrl = keyChild.getUrl();
						System.out.println("keyChildUrl is "+keyChildUrl);
						if(keyChildUrl.contains("!")) {
							keyChildUrl = keyChildUrl.substring(0, keyChildUrl.lastIndexOf("!"));
							System.out.println("keyChildUrl is "+keyChildUrl);
							if(keyChildUrl.equals(url)) {
								System.out.println("that is ok");
								int addValue = 0;
								int readValue = 0;
								int updateValue = 0;
								int deleteValue = 0;
								if(mapValue.get(keyChild)!=null) {
									addValue = mapValue.get(keyChild).get(0)
										.hashCode();
									readValue = mapValue.get(keyChild).get(1)
										.hashCode();
								    updateValue = mapValue.get(keyChild).get(2)
										.hashCode();
								    deleteValue = mapValue.get(keyChild).get(3)
										.hashCode();
								    System.out.println("delete value is"+deleteValue);
								    System.out.println("read value is "+readValue);
								    System.out.println("update value is"+updateValue);
								    System.out.println("create value is "+addValue);
								}
								if(addValue == value) {
									return invocation.invoke();
								} else if(readValue == value) {
									return invocation.invoke();
								} else if(updateValue == value) {
									return invocation.invoke();
								} else if(deleteValue == value) {
									return invocation.invoke();
								} else {
									System.out.println("sorry you dont have this privilege");
									return Action.ERROR;
								}
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