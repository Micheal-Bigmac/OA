package com.oa.action;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.oa.model.Organization;
import com.oa.service.OrganizationService;
import com.opensymphony.xwork2.ActionSupport;

public class OrganizationAction extends ActionSupport {
	
	
	private Organization organization;
	private OrganizationService organizationService;
	private Integer updateOrgId;
	private Integer deleteOrgId;
	private int index;
	private Integer parentid;
	private String returns;
	
	/*public String listOrgChild() {
		String hql = " and pid = " + parentid;
		List<Organization> listOrgChild = organizationService.getPageOrganizations(index, Organization.class, hql);
		int total = organizationService.getAllOrganizations(Organization.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		request.setAttribute("listOrg", listOrgChild);
		request.getSession().setAttribute("parentid",parentid);
		
		return "listOrg";
	}*/
	
	public String add() {
		if(organization.getId() != null) {
			//organization = organizationService.check("from Organization o where o.id=?", new Object[]{updateOrgId});
			organization.setPid(organizationService.getModel(parentid));
			organizationService.update(organization);
			return "operator_success";
		} else {
			if(parentid==null)
				organization.setPid(null);
			else {
				organization.setPid(organizationService.getModel(parentid));
			}
			organization.setSn(null);
			Serializable flag = organizationService.add(organization);
			if(flag != null) {
				returns="OrganizationAction!find";
				return "operator_success";
			} 
			return "operator_failure";
		}
	}
	
	/*获取某个Id值所对应的organization信息*/
	public String update() {
		organization = organizationService.select(Organization.class, updateOrgId);
		//organizationService.update(organization);
		return "updateOrg";
	}
	
	public String delete() {
		System.out.println("delete organization");
		HttpServletRequest request=ServletActionContext.getRequest();
		String []ids=request.getParameterValues("delid");
		System.out.println(ids.length+"sdfsadf");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
			organization = organizationService.select(Organization.class, Integer.valueOf(ids[i]));
			organizationService.delete(organization);
		}
		return "operator_success";
	}
	
	public String find() {
		if(parentid != null) {
			String hql = " and pid = " + parentid;
			List<Organization> listOrgChild = organizationService.getPageOrganizations(index, Organization.class, hql);
			int total = organizationService.getAllOrganizations(Organization.class, hql).size();
			
			HttpServletRequest request = ServletActionContext.getRequest();
			
			request.setAttribute("currentIndex", (index==0 ?  1 : index ));
			request.setAttribute("totalSize",total);
			request.setAttribute("listObject", listOrgChild);
			request.setAttribute("parentid",parentid);
			request.setAttribute("url", "OrganizationAction!find?parentid="+parentid);
			return "listOrg";
		} else {
			String hql ="and pid is null";
			
			List<Organization> listOrg = organizationService.getPageOrganizations((index==0 ? 1 : index), Organization.class, hql);
			int total = organizationService.getAllOrganizations(Organization.class, hql).size();
			
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setAttribute("currentIndex", (index==0 ?  1 : index ));
			request.setAttribute("totalSize",total);
			request.setAttribute("listObject", listOrg);
			request.setAttribute("url", "OrganizationAction!find?");
			return "listOrg";
		}
	}
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}
	@Resource
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	
	@Override
	public String execute() throws Exception {
		return "success";
	}

	public Integer getUpdateOrgId() {
		System.out.println("orgis si "+updateOrgId);
		return updateOrgId;
	}

	public void setUpdateOrgId(Integer updateOrgId) {
		this.updateOrgId = updateOrgId;
	}

	public Integer getDeleteOrgId() {
		return deleteOrgId;
	}

	public void setDeleteOrgId(Integer deleteOrgId) {
		this.deleteOrgId = deleteOrgId;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	
	
}
