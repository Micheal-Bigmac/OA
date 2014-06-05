package com.oa.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.oa.dao.ModuleDao;
import com.oa.model.Module;
import com.oa.service.ModuleService;
import com.oa.model.Users;
@Component("moduleService")
public class ModuleServiceImp implements ModuleService {

	private ModuleDao moduleDao;
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ModuleService#addModule(com.oa.model.Module)
	 */
	public Serializable addModule(Module module){
		return moduleDao.addModule(module);
	}
	
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ModuleService#deleteModule(com.oa.model.Module)
	 */
	public void deleteModule(Module module){
		moduleDao.deleteModule(module);
	}
	
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ModuleService#updateModule(com.oa.model.Module)
	 */
	public void updateModule(Module module){
		moduleDao.updateModule(module);
	}
	
	/* (non-Javadoc)
	 * @see com.oa.service.impl.ModuleService#getModle(java.io.Serializable)
	 */
	public Module getModle(Serializable id){
		return moduleDao.getModule(id);
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	@Resource
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public List<Module> getAllModules(Class clazz, String hql) {
		return moduleDao.getAllModules(clazz, hql);
	}

	public List<Module> getPageModules(int index, Class clazz, String hql) {
		return moduleDao.getPageModules(index, clazz, hql);
	}
	
	public  void deleteModules(String[] ids) {
		moduleDao.deleteModules(Module.class, ids, "delete from Module p where p.id ");
	}

	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getCategory(Users user) {
		moduleDao.getCategory(user);
		return moduleDao.getMenus();
	}

	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getUserPrivilege(Users user) {
		moduleDao.getUserPrivilege(user);
		return moduleDao.getUserPrivileges();
	}
}
