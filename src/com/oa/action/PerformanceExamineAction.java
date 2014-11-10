package com.oa.action;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.oa.model.ListPerformanceExamine;
import com.oa.model.PerformanceExamine;
import com.oa.model.PerformanceParameters;
import com.oa.model.Person;
import com.oa.model.Users;
import com.oa.service.PerformanceExamineSerivce;
import com.opensymphony.xwork2.ActionSupport;

public class PerformanceExamineAction extends ActionSupport{

	private PerformanceExamineSerivce performanceExamineService;
	private Integer peId;
	private PerformanceExamine performanceExamine;
	private ListPerformanceExamine listPerformanceExamine;
	private int index;
	private List<PerformanceExamine> props;
	public String addPerformanceExamine() {
		System.out.println("name is "+listPerformanceExamine.getName());
		Calendar cal = Calendar.getInstance();
		int y=cal.get(Calendar.YEAR);    
		int m=cal.get(Calendar.MONTH);    
		int d=cal.get(Calendar.DATE);
		String date = String.valueOf(y) + "-" + String.valueOf(m) + "-" + String.valueOf(d);
		Users user = (Users)ServletActionContext.getRequest().getSession().getAttribute("admin");
	
		Integer id = (Integer) performanceExamineService.addListPerformanceExamine(listPerformanceExamine.getName(),listPerformanceExamine.getInstruction(),date,user.getPersonid().getName());
		for(int i=0; i<props.size(); i++) {
			System.out.println(props.get(i));
			System.out.println(id);
			listPerformanceExamine.setId(id);
			props.get(i).setLpe(listPerformanceExamine);
			performanceExamineService.addPerformanceExamine(props.get(i));
		}
		return "selectPerformanceExamine";
	}
	
	public String deletePerformanceExamine() {
		System.out.println("peId  is "+peId);
		ListPerformanceExamine lpe = performanceExamineService.getLpe(peId);
		List<PerformanceExamine> pe = performanceExamineService.selectPerformanceExamine(lpe);
		for(int i=0; i<pe.size(); i++) {
			performanceExamineService.deletePerformanceExamine(pe.get(i));
		}
		performanceExamineService.deleteListPerformanceExamine(lpe);
		/*String hql = "";
		List<ListPerformanceExamine> performanceExamines = performanceExamineService.getPerformanceExaminePages((index==0 ? 1 : index), ListPerformanceExamine.class, hql);
		int total = performanceExamineService.getAllPerformanceExamines(ListPerformanceExamine.class, hql);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listPerformanceExamine", performanceExamines);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);*/
		return "operator_success";
	}
	
	public String find(){
		String hql = "";
		List<ListPerformanceExamine> performanceExamines = performanceExamineService.getPerformanceExaminePages((index==0 ? 1 : index), ListPerformanceExamine.class, hql);
		int total = performanceExamineService.getAllPerformanceExamines(ListPerformanceExamine.class, hql);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", performanceExamines);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		request.setAttribute("url", "PerformanceExamineAction!find?");
		return "selectPerformanceExamine";
	}
	
	public String getAddData() {
		List<PerformanceParameters> listParameters = performanceExamineService.getAllParams();
		List<Person> listPersons = performanceExamineService.getAllPerson(peId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listPersons", listPersons);
		request.setAttribute("itemCount", listParameters.size()*listPersons.size());
		request.setAttribute("listParameters", listParameters);
		return "addPerformanceExamine";
	}
	
	public String getPerformanceExamineInfo() {
		/*System.out.println("peId  is "+peId);
		Map<Integer,List<PerformanceExamine>> maps = performanceExamineService.getAllPE(peId);
		List<PerformanceParameters> listParameters = performanceExamineService.getAllParams();
		List<Person> listPersons = performanceExamineService.getAllPerson();
		List<PerformanceExamine> listPerformanceExamines = performanceExamineService.getAllPerformanceExamines(peId);
		HttpServletRequest request = ServletActionContext.getRequest();
		PerformanceExamine pe = listPerformanceExamines.get(0);
		for(PerformanceExamine p: listPerformanceExamines) {
//			System.out.println(p.toString());
		}
		request.setAttribute("performanceExamine", pe);
		request.setAttribute("listPersons", listPersons);
		request.setAttribute("listParameters", listParameters);
		request.setAttribute("listPerformanceExamines", listPerformanceExamines);*/
		ListPerformanceExamine lpe = performanceExamineService.select(peId);
		List listParameters = performanceExamineService.getAllParams(peId);
		List<Person> listPersons = performanceExamineService.getAllPerson(peId);
		Map<Integer, Map<String, List>> data = performanceExamineService.getAllData(peId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listPerformanceExamine", lpe);
		request.setAttribute("listPersons", listPersons);
		request.setAttribute("itemCount", listParameters.size()*listPersons.size());
		request.setAttribute("listParameters", listParameters);
		request.setAttribute("mapData", data);
		return "showSpecialPerformanceExamine";
	}
	
	public PerformanceExamineSerivce getPerformanceExamineService() {
		return performanceExamineService;
	}
	@Resource
	public void setPerformanceExamineService(PerformanceExamineSerivce performanceExamineService) {
		this.performanceExamineService = performanceExamineService;
	}
	
	public PerformanceExamine getPerformanceExamine() {
		return performanceExamine;
	}
	public void setPerformanceExamine(PerformanceExamine performanceExamine) {
		this.performanceExamine = performanceExamine;
	}
	public Integer getAgreeId() {
		return peId;
	}
	public void setAgreeId(Integer peId) {
		this.peId = peId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Integer getPeId() {
		return peId;
	}
	public void setPeId(Integer peId) {
		this.peId = peId;
	}

	public ListPerformanceExamine getListPerformanceExamine() {
		return listPerformanceExamine;
	}

	public void setListPerformanceExamine(
			ListPerformanceExamine listPerformanceExamine) {
		this.listPerformanceExamine = listPerformanceExamine;
	}

	public List<PerformanceExamine> getProps() {
		return props;
	}

	public void setProps(List<PerformanceExamine> props) {
		this.props = props;
	}


}
