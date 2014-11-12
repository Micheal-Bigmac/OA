package com.oa.jbpm.handler;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.spi.LoggerFactory;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Event;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.slf4j.Logger;

import com.oa.model.Role;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.oa.service.UserService;

public class DynamicDeployTask extends BeanAutowire implements ActionHandler {

	private String roleName;
	private UserService userService;
	private static Logger logger=org.slf4j.LoggerFactory.getLogger(DynamicDeployTask.class);

	public void execute(ExecutionContext arg0) throws Exception {
		TaskMgmtInstance taskmagInstance = arg0.getTaskMgmtInstance();

		if(roleName==null){
			throw new RuntimeException("角色  roleName 不能为空");
		}
		Role role = userService.getThisRole(roleName);
		TaskNode  taskNode=(TaskNode) arg0.getNode();
		Set tasSet=taskNode.getTasks();
		Task task=(Task) tasSet.iterator().next();
//		Task task = (Task) ((TaskNode) arg0.getNode()).getTasks().iterator()
//				.next();
		Set<UsersRoles> usersRoleList = role.getUsersRoles();
		for (UsersRoles usersRoles : usersRoleList) {
			Users users = usersRoles.getUserId();
			System.out.println(users.getAccount());
			logger.info("该账户为==============="+users.getAccount());
			if(users.getAccount()!=null||users.getAccount().trim()!=""){
				TaskInstance taskInstance = taskmagInstance.createTaskInstance(
						task, arg0.getToken());
				taskInstance.setActorId(users.getAccount());
			}
		}

	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
