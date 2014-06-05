package com.oa.action;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.chainsaw.Main;
import org.apache.struts2.ServletActionContext;
import org.hibernate.ejb.criteria.predicate.ExistsPredicate;

import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Organization;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.service.*;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport{

	private RoleService roleService;
	private Role role;
	private int index;
	private String returns;
	private Acl acl;
	private Module module;
	private Integer aclId;
	private Integer roleId;
	private Integer moduleId;
	private Integer boxValue;
	private Integer userId;
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleService getRoleService() {
		return roleService;
	}
	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
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
	
	public Acl getAcl() {
		return acl;
	}

	public void setAcl(Acl acl) {
		this.acl = acl;
	}
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getAclId() {
		return aclId;
	}

	public void setAclId(Integer aclId) {
		this.aclId = aclId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	
	public Integer getBoxValue() {
		return boxValue;
	}

	public void setBoxValue(Integer boxValue) {
		this.boxValue = boxValue;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String listRole() {
System.out.println("index +  ===="+index);
		String hql ="";
		int total = roleService.getAllRole(Role.class, hql).size();
		List<Role> listRole = roleService.find();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		request.setAttribute("listRole", listRole);
		
		return "listRole";
	}
	
	public String deleteRole() {
		roleService.deleteRole(role.getId());
		returns = "RoleAction!listRole";
		return "operator_success";
	}
	
	public String addRole() {
		//System.out.println("role id isssssssss "+role.getId());
		if(role.getId() != null) {	
			roleService.update(role);
		} else {
			roleService.add(role);
		}
		//System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkk");
		returns = "RoleAction!listRole";
		return "operator_success";
	}

	public String updateShowRole() {
		role = roleService.getUpdateRole(role.getId());
		return "updateRole";
	}
	
	public String privilegeRole() {
		role = roleService.getUpdateRole(role.getId());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roleName", role.getName());
		request.setAttribute("roleId", role.getId());
		request.setAttribute("mapModules", roleService.getCategories(role));
		return "showRolePrivilege";
	}
	
	public String privilege() {
		System.out.println("aclId is "+aclId);
		System.out.println("module id is "+moduleId);
		System.out.println("roleId is "+roleId);
		System.out.println("boxValue is "+boxValue);
		role = roleService.getUpdateRole(roleId);
		//System.out.println("okkkkkkkkkkkkkkkkkk");
		module = roleService.getsModule(Module.class, moduleId);
		//System.out.println("okkkkkkkkkkkkkkkkkk");
	
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = "";
		if(aclId==-1) {
			//insert operation
			if(roleService.ExistsAcl(role,module)) {
				//update operation
				int OrgnialBoxVale = roleService.getOrgBoxValue(role,module).getAclState();
				System.out.println("acid is -1 exists");
				System.out.println("original box value is "+OrgnialBoxVale);
				
				System.out.println("now box value is "+(OrgnialBoxVale+boxValue.intValue()));
				System.out.println("acid is -1 exists");
				System.out.println("boxVale is " + boxValue);
				int newValue = OrgnialBoxVale+boxValue.intValue();
				System.out.println("newValue is "+newValue);
				Acl acl = roleService.getOrgBoxValue(role,module);
				acl.setPrincipalType("role");
				acl.setModuleId(module);
				acl.setPrincipalId(role);
				acl.setAclState(newValue);
				acl.setAlcTriState(-1);
				roleService.updateAcl(acl);
				str = privilegeRole();
			} else {
				//insert operation
				System.out.println("acid is -1");
				System.out.println("first box value is " + boxValue);
				System.out.println("acid is -1");
				
				Acl acl = new Acl();
				acl.setPrincipalType("role");
				acl.setModuleId(module);
				acl.setPrincipalId(role);
				acl.setAclState(boxValue);
				acl.setAlcTriState(-1);
				roleService.addAcl(acl);
				str = privilegeRole();
			}
		}
		return str;
	}
	
	public String privilegeToAllChoose() {
		System.out.println("module id is "+moduleId);
		System.out.println("roleId is "+roleId);
		
		role = roleService.getUpdateRole(roleId);
		//System.out.println("okkkkkkkkkkkkkkkkkk");
		module = roleService.getsModule(Module.class, moduleId);
		//System.out.println("okkkkkkkkkkkkkkkkkk");
		String str = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		if(roleService.ExistsAcl(role,module)) {
			//update operation
			Acl acl = roleService.getOrgBoxValue(role,module);
			acl.setPrincipalType("role");
			acl.setModuleId(module);
			acl.setPrincipalId(role);
			acl.setAclState(15);
			acl.setAlcTriState(-1);
			roleService.updateAcl(acl);
			str = privilegeRole();
		} else {
			//insert operation
			Acl acl = new Acl();
			acl.setPrincipalType("role");
			acl.setModuleId(module);
			acl.setPrincipalId(role);
			acl.setAclState(15);
			acl.setAlcTriState(-1);
			roleService.addAcl(acl);
			str = privilegeRole();
		}
		return str;
	}
}
