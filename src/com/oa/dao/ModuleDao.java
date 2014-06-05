package com.oa.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oa.model.Module;
import com.oa.service.UserService;
import com.oa.model.Users;

public interface ModuleDao {
	

	public abstract Serializable addModule(Module module);

	public abstract void deleteModule(Module module);

	public abstract void updateModule(Module module);

	public abstract Module getModule(Serializable id);
	
	
	public List<Module> getAllModules(Class clazz,String hql);
	
	public List<Module> getPageModules(int index,Class clazz, String hql);
	

	public abstract void deleteModules(Class<Module> class1, String[] ids,
			String string);
	
	public abstract void getCategory(Users user);
	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getMenus();


	public abstract void getUserPrivilege(Users user);

	public abstract LinkedHashMap<Module, LinkedHashMap<Module, List>> getUserPrivileges();

}