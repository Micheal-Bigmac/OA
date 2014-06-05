package com.oa.service.impl;

import java.io.Serializable;
import com.oa.model.UsersRoles;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import com.oa.dao.RoleDao;
import com.oa.dao.SuperDaoInte;
import com.oa.dao.impl.SuperDao;
import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.service.RoleService;
@Component("roleService")
public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	private Users user;
	private LinkedHashMap<Module, LinkedHashMap<Module, List>> mapModules = new LinkedHashMap<Module, LinkedHashMap<Module, List>>();
	
	public RoleDao getRoleDao() {
		return roleDao;
	}
	
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public List<Role> find() {
		String sql =  "from Role";
		return roleDao.find(sql);
	}
	
	public void deleteRole(Integer id) {
		Role role = roleDao.getRoleById(id);
		roleDao.deleteRole(role);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void add(Role role) {
		roleDao.add(role);
	}

	public List<Role> getAllRole(Class<Role> clazz, String hql) {
		return roleDao.getAllRole(clazz, hql);
	}

	public Role getUpdateRole(Serializable id) {
		return roleDao.getRoleById(id);
	}

	public Acl getsAcl(Role role) {
		String sql = "from Acl a where a.principalId.id = ?";
		return roleDao.getsAcl(sql, new Object[]{role.getId()});
	}

	public Module getsModule(Class<Module> clazz, Serializable id) {
		return roleDao.getsModule(clazz, id);
	}

	public List<Module> getModules() {
		String sql = "from Module m where pid is null";
		return roleDao.getsModules(sql);
	}

	public List<Module> getChildModules(Integer id) {
		String sql = "from Module m where m.pid.id = " + id;
		return roleDao.getsModules(sql);
	}

	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getLinkedHashMapModules() {
		return mapModules;
	}

	public void setLinkedHashMapModules(LinkedHashMap<Module, LinkedHashMap<Module, List>> mapModules) {
		this.mapModules = mapModules;
	}
	
	private List<Module> objectToList(List<Object> list) {
		List<Module> modules = new ArrayList<Module>();
		for (Object o : list) {
			modules.add((Module) o);
		}
		return modules;
	}
	
	public List<Module> getAllModules(Class clazz, String hql) {
		return objectToList(roleDao.getAllObjects(clazz, hql));
	}
	
	private List getAclState(int id) {
		System.out.println("id is "+id);
		List listInt = new ArrayList();
		
		listInt.add(id&1);
		listInt.add(id&2);
		listInt.add(id&4);
		listInt.add(id&8);
		
		System.out.println(id+"&1 "+String.valueOf(id&1));
		System.out.println(id+"&2 "+String.valueOf(id&2));
		System.out.println(id+"&4 "+String.valueOf(id&4));
		System.out.println(id+"&8 "+String.valueOf(id&8));
		return listInt;
	}
	
	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getCategories(Role role) {
		if (mapModules.size()==0) {
			System.err.println("mapModules is null");
			
			List<Module> parentModule = getAllModules(Module.class,
					" and s.pid is null");
			Acl acl = null;
			String sql2 = "from Acl a where a.principalId = ? and a.moduleId = ?";
			for (int i = 0; i < parentModule.size(); i++) {
				LinkedHashMap<Module, List> maps = new LinkedHashMap<Module, List>();
				System.err.println("parent module "+parentModule.get(i).toString());
				Set<Module> temp = parentModule.get(i).getModules();
				if (temp.size() == 0) {
					mapModules.put(parentModule.get(i), null);
				} else {
				for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
					List<Integer> listInt = null;
					Module module = (Module) iterator.next();
					System.out.println("children module " + module.toString());
					acl = roleDao.getAclById(sql2, new Object[]{role, module});
					
						if(acl != null) {
							listInt = getAclState(acl.getAclState());
							for(Object o : listInt) {
								System.out.println("crud"+o);
							}
							maps.put(module, listInt);
						} else {
							listInt = new ArrayList<Integer>();
							for(int j=0; j<4; j++) {
								listInt.add(0);
							}
							for(Object o : listInt) {
								System.out.println("crud"+o);
							}
							maps.put(module, listInt);
						}
				}
					mapModules.put(parentModule.get(i), maps);
				}
			}
			ServletActionContext.getRequest().getSession().setAttribute("aclId", -1);
		} else {
			Iterator iterator = mapModules.keySet().iterator();   
			while (iterator.hasNext()) {  
			    Module key = (Module) iterator.next();  
			    iterator.remove();        //添加该行代码  
			    mapModules.remove(key);      
			}  
			System.out.println("mapppppp size is "+mapModules.size());
			System.out.println("role issssssssssssssssssss "+role.getName());
			getCategories(role);
		}
		return mapModules;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void addAcl(Acl acl) {
		roleDao.addAcl(acl);
	}

	public boolean ExistsAcl(Role role, Module module) {
		String sql = "from Acl a where a.principalId = ? and a.moduleId = ?";
		return roleDao.ExistsAcl(sql, new Object[]{role,module});
	}

	public Acl getOrgBoxValue(Role role, Module module) {
		String sql = "from Acl a where a.principalId = ? and a.moduleId = ?";
		return roleDao.getAcl(sql, new Object[]{role,module});
	}

	public void updateAcl(Acl acl) {
		roleDao.updateAcl(acl);
	}

	public Users getsUsers(Class<Users> clazz,Serializable userId) {
		return roleDao.getsUsers(clazz, userId);
	}

	public void addUserPrivilege(UserPrivilege up) {
		roleDao.addUserPrivilege(up);
	}


}
