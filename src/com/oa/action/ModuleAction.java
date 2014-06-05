package com.oa.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oa.model.Module;
import com.oa.service.ModuleService;
import com.opensymphony.xwork2.ActionSupport;

public class ModuleAction extends ActionSupport {

	private Module module;
	private ModuleService moduleService;
	private int index;
	private Integer pid;
	private String returns;
	public String moduleList(){
		String hql ="and s.pid ="+(module==null ? "null": module.getId());
		System.out.println(hql);
		List<Module> modules=moduleService.getPageModules((index==0 ? 1 : index), Module.class, hql);
		for(Module m: modules){
			System.out.println(m.toString());
		}
		HttpServletRequest  request=ServletActionContext.getRequest();
		request.setAttribute("moduleList", modules);
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		int total=moduleService.getAllModules(Module.class, hql).size();
		request.setAttribute("pid",(module==null ? "": module.getId()));
		request.setAttribute("totalSize",total);
		return "moduleList";
		
	}
	
	public String addModule(){
		 if(pid!=null){
				module.setPid(moduleService.getModle(pid));
		 }
		 returns="ModuleAction!moduleList";
		Serializable flag=moduleService.addModule(module);
		return flag==null ? "operator_failure" : "operator_success";
	}
	
	public String deletemodule(){
		System.out.println("deleteperson");
		returns="ModuleAction!moduleList";
		HttpServletRequest request=ServletActionContext.getRequest();
		String []ids=request.getParameterValues("delid");
		System.out.println(ids.length+"sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		moduleService.deleteModules(ids);
		returns="ModuleAction!moduleList";
		return "operator_success";
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public ModuleService getModuleService() {
		return moduleService;
	}
	@Resource
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

}
