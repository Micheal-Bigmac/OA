package com.oa.action;

import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.oa.model.PerformanceParameters;
import com.oa.model.Users;
import com.oa.service.PerformanceParametersService;
import com.opensymphony.xwork2.ActionSupport;

public class PerformanceParametersAction extends ActionSupport{

	private PerformanceParametersService performanceParametersService;
	private Integer performanceParametersId;
	private PerformanceParameters performanceParameters;
	private int index;
	public String addPerformanceParameters() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("admin");
		Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int year = cal.get(Calendar.YEAR);
	    String date = year + "-" + month +"-" + day;
		
		if(performanceParameters.getId() == null) {
			performanceParameters.setDate(date);
			performanceParameters.setPersonId(user.getPersonid());
			performanceParametersService.addPerformanceParameters(performanceParameters);
			System.out.println("add");
		} else {
			performanceParameters.setDate(date);
			performanceParameters.setPersonId(user.getPersonid());
			performanceParametersService.updatePerformanceParameters(performanceParameters);
			System.out.println("update");
		}
		String hql = "";
		List<PerformanceParameters> performanceParameters = performanceParametersService.getPerformanceParametersPages((index==0 ? 1 : index), PerformanceParameters.class, hql);
		int total = performanceParametersService.getAllPerformanceParameterss(PerformanceParameters.class, hql).size();
		
		request.setAttribute("listPerformanceParameters", performanceParameters);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return "selectPerformanceParameters";
	}
	public String updatePerformanceParameters() {
		System.out.println("performanceParametersId  is "+performanceParametersId);
		PerformanceParameters performanceParameters = performanceParametersService.selectPerformanceParameters(PerformanceParameters.class,performanceParametersId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("performanceParameters", performanceParameters);
		return "addPerformanceParameters";
	}
	public String deletePerformanceParameters() {
		System.out.println("performanceParametersId  is "+performanceParametersId);
		PerformanceParameters performanceParametersDel = performanceParametersService.selectPerformanceParameters(PerformanceParameters.class,performanceParametersId);
		performanceParametersService.deletePerformanceParameters(performanceParametersDel);
		
		String hql = "";
		List<PerformanceParameters> performanceParameters = performanceParametersService.getPerformanceParametersPages((index==0 ? 1 : index), PerformanceParameters.class, hql);
		int total = performanceParametersService.getAllPerformanceParameterss(PerformanceParameters.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listPerformanceParameters", performanceParameters);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return "operator_success";
	}
	
	public String find(){
		String hql = "";
		List<PerformanceParameters> performanceParameters = performanceParametersService.getPerformanceParametersPages((index==0 ? 1 : index), PerformanceParameters.class, hql);
		int total = performanceParametersService.getAllPerformanceParameterss(PerformanceParameters.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listPerformanceParameters", performanceParameters);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		
		return "selectPerformanceParameters";
	}
	public PerformanceParametersService getPerformanceParametersService() {
		return performanceParametersService;
	}
	@Resource
	public void setPerformanceParametersService(PerformanceParametersService performanceParametersService) {
		this.performanceParametersService = performanceParametersService;
	}
	
	public PerformanceParameters getPerformanceParameters() {
		return performanceParameters;
	}
	public void setPerformanceParameters(PerformanceParameters performanceParameters) {
		this.performanceParameters = performanceParameters;
	}
	public Integer getPerformanceParametersId() {
		return performanceParametersId;
	}
	public void setPerformanceParametersId(Integer performanceParametersId) {
		this.performanceParametersId = performanceParametersId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
