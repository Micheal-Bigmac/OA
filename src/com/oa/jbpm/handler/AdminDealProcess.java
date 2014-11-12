package com.oa.jbpm.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.Assignable;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

import com.oa.model.Role;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.oa.service.UserService;

public class AdminDealProcess extends BeanAutowire implements AssignmentHandler {

	private UserService userService;
	private String roleName;

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void assign(Assignable arg0, ExecutionContext arg1) throws Exception {
		Role role = userService.getThisRole(roleName);
		Set<UsersRoles> usersRoleList = role.getUsersRoles();
//		List actiors=new ArrayList();
		Set actiors=new HashSet();
		for (UsersRoles object : usersRoleList) {
			Users users=object.getUserId();
			actiors.add(users.getAccount());
		}
		String []poolactors=(String[]) actiors.toArray(new String[actiors.size()]);
		arg0.setPooledActors(poolactors);
	}

}
