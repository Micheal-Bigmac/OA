package com.oa.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.oa.model.DynamicField;
import com.oa.model.DynamicForm;
import com.oa.model.FieldType;
import com.oa.model.FiledInput;
import com.oa.service.DynamicFieldService;
import com.oa.service.DynamicFormService;
import com.oa.service.WorkFlowService;
import com.opensymphony.xwork2.ActionSupport;

public class DynamicFieldAction extends ActionSupport {

	private DynamicFieldService dynamicFieldService;
	private DynamicFormService dynamicFormService;
	private DynamicField dynamicField;
	String returns;
	private int id;
//	private 
	private int fieldType;
	private int fieldInput;
	private int dynamicFormId;
	public String addField() throws IOException{
		HttpSession session=ServletActionContext.getRequest().getSession();
		FiledInput input= (FiledInput) ((List)session.getAttribute("fieldInput")).get(fieldInput);
		FieldType type=(FieldType) ((List) session.getAttribute("fieldType")).get(fieldType);
		dynamicField.setInput(input);
		dynamicField.setType(type);
		DynamicForm from=dynamicFormService.getDynamicForm(dynamicFormId);
		dynamicField.setDynamicForm(from);
		System.out.println(dynamicField.toString());
		dynamicFieldService.addDynamicField(dynamicField);
//		returns="DynamicFormAction!listFormField?workflowid="+from.getWorkFlow().getId();
		ServletActionContext.getResponse().getWriter().print(from.getWorkFlow().getId());
		System.out.println("========-------------------=================="+returns);
		return  null;
	}
	
	public String AddFieldView(){
		List fieldInput=dynamicFieldService.getAllFieldInput(null);
		List fieldType =dynamicFieldService.getAllFieldType(null);
		HttpSession session =ServletActionContext.getRequest().getSession();
		session.setAttribute("fieldType", fieldType);
		session.setAttribute("fieldInput", fieldInput);
		return "addfieldView";
	}
	
	public String modifyDynamicField(){
		System.out.println("modifyDynamciField"+id);
		
		ServletActionContext.getRequest().setAttribute("dynamicField", dynamicFieldService.getDynamicField(id));
		return "modifyDynamicField";
	}
	
	public String deleteDynamicField(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String []ids=request.getParameterValues("delid");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		String  hql="delete from DynamicField field where field.id ";
		dynamicFieldService.deleteDynamicFields(hql,ids);
		returns="DynamicFormAction!listFormField";
		return "operator_success";
	}
	public DynamicFieldService getDynamicFieldService() {
		return dynamicFieldService;
	}
	public void setDynamicFieldService(DynamicFieldService dynamicFieldService) {
		this.dynamicFieldService = dynamicFieldService;
	}
	public DynamicField getDynamicField() {
		return dynamicField;
	}
	public void setDynamicField(DynamicField dynamicField) {
		this.dynamicField = dynamicField;
	}
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	public int getFieldInput() {
		return fieldInput;
	}
	public void setFieldInput(int fieldInput) {
		this.fieldInput = fieldInput;
	}



	public DynamicFormService getDynamicFormService() {
		return dynamicFormService;
	}

	public void setDynamicFormService(DynamicFormService dynamicFormService) {
		this.dynamicFormService = dynamicFormService;
	}

	public int getDynamicFormId() {
		return dynamicFormId;
	}

	public void setDynamicFormId(int dynamicFormId) {
		this.dynamicFormId = dynamicFormId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

}
