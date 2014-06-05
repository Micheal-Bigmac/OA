package com.oa.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import com.oa.model.Module;
import com.oa.model.Users;
public interface ModuleService {

	public abstract Serializable addModule(Module module);

	public abstract void deleteModule(Module module);

	public abstract void updateModule(Module module);

	public abstract Module getModle(Serializable id);
	
	public List<Module> getAllModules(Class clazz,String hql);
	
	public List<Module> getPageModules(int index,Class clazz,String hql);

	public  void deleteModules(String[] ids);
	
	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getCategory(Users user);

	public abstract LinkedHashMap<Module, LinkedHashMap<Module, List>> getUserPrivilege(Users user);
}