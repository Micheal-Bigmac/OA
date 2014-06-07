package com.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;
import com.oa.model.WorkFlow;
import com.oa.service.DynamicFieldService;
import com.oa.service.DynamicFormService;
import com.oa.service.WorkFlowService;
import com.opensymphony.xwork2.ActionSupport;

public class DynamicFormAction extends ActionSupport{
	
	private WorkFlowService workFlowService;
	private DynamicFormService dynamicFormService;
	DynamicFieldService dynamicFieldService;
	private int index;
	private int workflowid;
	String returns;
public String list(){
		
		String hql = "";
		List<WorkFlow> workFlows = workFlowService.getPageWorkFlows(
				(index == 0 ? 1 : index), hql);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", workFlows);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = workFlowService.getAllWorkFlows(hql).size();
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "DynamicFormAction!list");
		return "workFlowList";
	}

	
	public String listFormField(){
		System.out.println(workflowid+"sfsadasdasdasd");
//		System.out.println(form.toString());
		DynamicForm dynamicForm =new DynamicForm(); 
		dynamicForm.setWorkFlow(workFlowService.getWorkFlow(workflowid));
		dynamicForm.setTemplate("document_form.ftl");
		int id =(Integer) dynamicFormService.addOrUpdateDynamicForm(dynamicForm);
		ServletActionContext.getRequest().setAttribute("id", id);
		
		getDynamicFieldList(id);
		return "listFormField";
	}
	private void getDynamicFieldList(int id){
		List<DynamicField> fields = dynamicFieldService.getPageDynamicFields((index == 0 ? 1 : index), " and s.dynamicForm.id ="+id);
		System.out.println(fields.size()+"||||||||||||");
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("dynamicFieldList", fields);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total =dynamicFieldService.getAllDynamicFields(" and s.dynamicForm.id ="+id).size();
		request.setAttribute("totalSize", total);
	}
	
	public String deleteDynamicForm(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String []ids=request.getParameterValues("delid");
		for (int i = 0; i < ids.length; i++) {
			int id=Integer.valueOf(ids[i]);
			DynamicForm form=dynamicFormService.getDynamicFormByWorkFlow(id);
			dynamicFormService.deleteDynamicForm(form);
		}
		returns="DynamicFormAction!list";
		return null;
	}
	public WorkFlowService getWorkFlowService() {
		return workFlowService;
	}
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public DynamicFormService getDynamicFormService() {
		return dynamicFormService;
	}
	@Resource
	public void setDynamicFormService(DynamicFormService dynamicFormService) {
		this.dynamicFormService = dynamicFormService;
	}
	public int getWorkflowid() {
		return workflowid;
	}
	public void setWorkflowid(int workflowid) {
		this.workflowid = workflowid;
	}
	public DynamicFieldService getDynamicFieldService() {
		return dynamicFieldService;
	}
	@Resource
	public void setDynamicFieldService(DynamicFieldService dynamicFieldService) {
		this.dynamicFieldService = dynamicFieldService;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}
}
