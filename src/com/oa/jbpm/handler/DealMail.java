package com.oa.jbpm.handler;

import java.sql.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;

import com.oa.email.send.SendMail;
import com.oa.model.Users;
import com.oa.service.UserService;

public class DealMail extends BeanAutowire implements ActionHandler{
	
	private UserService userService;
	private SendMail sendMail;
	/**
	 * 
	 */
	

	public void execute(ExecutionContext arg0) throws Exception {
//		OutputStream out=new FileOutputStream(new File("c:/log.txt"));
//		OutputStreamWriter writer=new OutputStreamWriter(out);
		Session session=arg0.getJbpmContext().getSession();
//		String NodeName=arg0.getNode().getName();
//		writer.write("nodename "+ NodeName);
		
		TaskNode taskNode = (TaskNode)arg0.getNode();
		 String nodename=taskNode.getName();
		 Task task=taskNode.getTask("actor");
		 String actorid=task.getActorIdExpression();
		 String docTitle=(String) arg0.getContextInstance().getVariable("docTitle");
		 System.out.println(docTitle);
		 Users users=userService.getUsersByAccount(actorid);
		 String subject="有新的公文【"+docTitle+"】正在等待您的审批...";
//		 String content="公文【"+docTitle+"】正在等待您的审批...";
		 String content=" 您好,"+actorid+",您有新的公文【"+docTitle+"】已流转到您的手中，请尽快审批，谢谢！ 请登录公司网站";
		 String email=users.getPersonid().getEmail();
		 sendMail.setTo(email.trim());
		 sendMail.setSubject(subject);
		 sendMail.setContent(content);
		 while(!sendMail.send()) {
//			 System.out.println("send success");
		 }
//		 System.out.println(arg0.getTaskInstance().getActorId());
		System.out.println("==========--------------------===============");
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Resource
	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}
}
