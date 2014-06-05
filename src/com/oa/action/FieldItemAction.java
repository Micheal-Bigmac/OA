package com.oa.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.DynamicField;
import com.oa.model.FieldItem;
import com.oa.model.WorkFlow;
import com.oa.service.FieldItemService;
import com.opensymphony.xwork2.ActionSupport;

public class FieldItemAction extends ActionSupport {
	
	private FieldItemService fieldItemService;
	private FieldItem item;
	private int  id;
	private String returns;

	private int index;
	
	public String ListItems(){
		String hql = " and s.dynamicField.id = "+id;
//		List<WorkFlow> workFlows = workFlowService.getPageWorkFlows(
//				(index == 0 ? 1 : index), hql);
		
		List<FieldItem> items=fieldItemService.getPageFieldItems((index == 0 ? 1 : index), hql);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("fieldItemList", items);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = fieldItemService.getAllFieldItems(hql).size();
		request.setAttribute("totalSize", total);
		return "listItems";
	}
	
	public String addFieldItem(){
		System.out.println(item.toString()+" "+id);
		DynamicField field=new DynamicField();
		field.setId(id);
		item.setDynamicField(field);
		fieldItemService.addFieldItem(item);
		returns="FieldItemAction!ListItems?id="+id;
		return "operator_success";
	}
	public String deleteFieldItem(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String []ids=request.getParameterValues("delid");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		fieldItemService.DeleteListFieldItems(ids);
		returns="FieldItemAction!ListItems";
		return "operator_success";
	}


	public FieldItemService getFieldItemService() {
		return fieldItemService;
	}

	@Resource
	public void setFieldItemService(FieldItemService fieldItemService) {
		this.fieldItemService = fieldItemService;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public FieldItem getItem() {
		return item;
	}


	public void setItem(FieldItem item) {
		this.item = item;
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
