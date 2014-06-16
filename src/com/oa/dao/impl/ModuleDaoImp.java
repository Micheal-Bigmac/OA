package com.oa.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.Inheritance;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import com.oa.dao.ModuleDao;
import com.oa.dao.SuperDaoInte;
import com.oa.model.Acl;
import com.oa.model.Module;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.sun.org.apache.commons.digester.rss.Image;

@Component("moduleDao")
public class ModuleDaoImp implements ModuleDao {

	private SuperDaoInte superDao;

	static final LinkedHashMap<Module, LinkedHashMap<Module, List>> category = new LinkedHashMap<Module, LinkedHashMap<Module, List>>();
	private LinkedHashMap<Module, LinkedHashMap<Module, List>> userPrivileges = new LinkedHashMap<Module, LinkedHashMap<Module, List>>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oa.dao.impl.ModuleDao#addModule(com.sun.xml.internal.ws.api.server
	 * .Module)
	 */
	public Serializable addModule(Module module) {

		return superDao.add(module);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oa.dao.impl.ModuleDao#deleteModule(com.sun.xml.internal.ws.api.server
	 * .Module)
	 */
	public void deleteModule(Module module) {
		superDao.delete(module);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oa.dao.impl.ModuleDao#updateModule(com.sun.xml.internal.ws.api.server
	 * .Module)
	 */
	public void updateModule(Module module) {
		superDao.update(module);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.dao.impl.ModuleDao#getModule(java.io.Serializable)
	 */
	public Module getModule(Serializable id) {
		return (Module) superDao.select(Module.class, id);
	}

	public SuperDaoInte getSuperDao() {
		return superDao;
	}

	@Resource
	public void setSuperDao(SuperDaoInte superDao) {
		this.superDao = superDao;
	}

	public List<Module> getAllModules(Class clazz, String hql) {
		return objectToList(superDao.getAllObjects(clazz, hql));
	}

	public List<Module> getPageModules(int index, Class clazz, String hql) {
		return objectToList(superDao.getPage(index, clazz, hql));
	}

	public LinkedHashMap<Module, LinkedHashMap<Module, List>> getUserPrivileges() {
		System.out.println("userPrivileges size is "+userPrivileges.size());
		return userPrivileges;
	}

	public void setUserPrivileges(LinkedHashMap<Module, LinkedHashMap<Module, List>> userPrivileges) {
		this.userPrivileges = userPrivileges;
	}
	
	private List<Module> objectToList(List<Object> list) {
		List<Module> modules = new ArrayList<Module>();

		for (Object o : list) {
			modules.add((Module) o);
		}
		return modules;
	}

	public void deleteModules(Class clazz, String[] ids, String hql) {
		superDao.deleteList(clazz, ids, hql);
	}

	public List<UsersRoles> convertoListUsersRoles(List<Object> objects) {
		List<UsersRoles> list = new ArrayList<UsersRoles>();
		for (int i = 0; i < objects.size(); i++) {
			list.add((UsersRoles) objects.get(i));
		}
		return list;
	}

	public List<Module> convertToListModule(List<Object> objects) {
		List<Module> list = new ArrayList<Module>();
		for (int i = 0; i < objects.size(); i++) {
			list.add((Module) objects.get(i));
		}
		return list;
	}

	private List getAclState(int id, int inheritanceId) {
		List listInt = new ArrayList();

		listInt.add(id & 1);
		listInt.add(id & 2);
		listInt.add(id & 4);
		listInt.add(id & 8);
		listInt.add(inheritanceId);
		System.out.println(id + "&1 " + String.valueOf(id & 1));
		System.out.println(id + "&2 " + String.valueOf(id & 2));
		System.out.println(id + "&4 " + String.valueOf(id & 4));
		System.out.println(id + "&8 " + String.valueOf(id & 8));
		return listInt;
	}
	
	private List getAclState(int id) {
		List listInt = new ArrayList();
		
		listInt.add(id & 1);
		listInt.add(id & 2);
		listInt.add(id & 4);
		listInt.add(id & 8);
		System.out.println(id + "&1 " + String.valueOf(id & 1));
		System.out.println(id + "&2 " + String.valueOf(id & 2));
		System.out.println(id + "&4 " + String.valueOf(id & 4));
		System.out.println(id + "&8 " + String.valueOf(id & 8));
		return listInt;
	}
	/*流程图片在com.oa.action目录下 */
	public void getCategory(Users user) {
		if (category.size() == 0) {
			List<Acl> listAcls = convertToListAcl(superDao.find("from Acl a where a.aclState ="+"0"));
			for(int i=0; i<listAcls.size(); i++) {
				superDao.delete(listAcls.get(i));
			}
			List<UserPrivilege> listUP = convertToUserPrivilege(superDao.find("from UserPrivilege up where up.userValue ="+"0"));
			for(int i=0; i<listUP.size(); i++) {
				superDao.delete(listUP.get(i));
			}
			String sql = "from UsersRoles ur where ur.userId.id = "
					+ user.getId();
			System.out.println("sql is " + sql);
			List<UsersRoles> usersRoles = convertoListUsersRoles(superDao
					.find(sql));
			System.out.println("usersRoles size is " + usersRoles.size());
			
			//判断UserPrivilege中user的继承状态
			List<UserPrivilege> listUserPrige = convertToListUserPrige(superDao.find("from UserPrivilege up where up.userId = "+user.getId()));
			int inheritance = 2;
			if(listUserPrige.size()>0) {
				for(int n=0; n<listUserPrige.size(); n++) {
					inheritance = listUserPrige.get(n).getInheritance();
					if(inheritance == -1) {
						break;
					}
				}
				System.out.println("inheritance value is "+inheritance);
				if(inheritance == -1) {
					UserNotDistributeRole(user);
					System.out.println("-----------------");
					System.out.println("inheritance == -1");
					System.out.println("-----------------");
				} else {
					System.out.println("-----------------");
					System.out.println("inheritance != -1");
					System.out.println("-----------------");
					if (usersRoles.size() > 0) {
						// 用户有角色
						String strRole = "";
						for(int k=0; k<usersRoles.size(); k++) {
							strRole += usersRoles.get(k).getRoleId().getId()+",";
						}
						strRole = strRole.substring(0,strRole.lastIndexOf(","));
						List<Acl> acls = convertToListAcl(superDao.find(
								"from Acl a where a.principalId in ("+ strRole+")"));
						System.out.println("acl size issssssssssssss  "+acls.size());

						String str = "";
						if(acls.size()>0) {
							for (int i = 0; i < acls.size(); i++) {
								Acl acl = acls.get(i);
								//不继承（按照角色进行查找）
								List<Module> RoleModuleChildsList = convertToListModule(superDao.getDistinctAllObject(Module.class,  " and s.id = " + acl.getModuleId().getId()));
								for(int p=0; p<RoleModuleChildsList.size(); p++) {
									str += RoleModuleChildsList.get(p).getId() + ",";
								}
							}
							System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
							str = str.substring(0,str.lastIndexOf(","));
							System.out.println("str is "+str);
							//select distinct(m.pid) 
							String sql6 = "select distinct(m.pid) from Module m where m.id in (" + str +")";
							List<Module> parentModules = convertToListModule(superDao.getAllObjects(sql6));
							System.out.println("parentModule size is "+parentModules.size());
							for(int y=0; y<parentModules.size(); y++) {
								System.out.println("parentModuls is "+parentModules.get(y).getName());
							}
							String sql7 = "select distinct(m) from Module m where m.id in (" + str +")";
							//part child
							List<Module> childrenModules = convertToListModule(superDao.getAllObjects(sql7));
							for(int y=0; y<childrenModules.size(); y++) {
								System.out.println("childrenModuls is "+childrenModules.get(y).getName());
							}
							
							for(int y=0; y<parentModules.size(); y++) {
								LinkedHashMap<Module, List> childModuleLinkedHashMap = new LinkedHashMap<Module, List>();
								Module parent = parentModules.get(y);
								//all
								List<Module> childModules = convertToListModule(superDao.getAllObjects(Module.class, "and s.pid.id =" + parent.getId()));
								List list = null;
								for(int i=0; i<childModules.size(); i++) {
									Module child = childModules.get(i);
									List<Acl> acls2 = convertToListAcl(superDao.find(
											"select distinct(a) from Acl a where a.principalId in ("+ strRole+") and a.moduleId = "+child.getId()));
									System.out.println("acl size issssssssssssss  "+acls2.size());
									System.out.println("str Is "+"select distinct(a) from Acl a where a.principalId in ("+ strRole+") and a.moduleId = "+child.getId());
									int temp = 0;
									List aclValueList = new ArrayList();
									if(acls2.size()>0) {
										for(int k=0; k<acls2.size(); k++) {
											System.out.println("acl module name  is "+acls2.get(k).getModuleId().getName());
											System.out.println("acl role name is "+acls2.get(k).getPrincipalId().getName());
											System.out.println("acl aclState  is "+acls2.get(k).getAclState());
											/////////////////////////
											int aclValue = acls2.get(k).getAclState();
											if(aclValueList != null) {
												if(!aclValueList.contains(1)) {
													System.out.println("not include 1");
													if((aclValue&1)==1) {
														temp+=1;
														aclValueList.add(1);
													}
												}
											}
											if(aclValueList != null) {
												if(!aclValueList.contains(2)) {
													System.out.println("not include 2");
													if((aclValue&2)==2) {
														temp+=2;
														aclValueList.add(2);
													}
												}
											}
											if(aclValueList != null) {
												if(!aclValueList.contains(4)) {
													System.out.println("not include 4");
													if((aclValue&4)==4) {
														temp+=4;
														aclValueList.add(4);
													}
												}
											}
											if(aclValueList != null) {
												if(!aclValueList.contains(8)) {
													System.out.println("not include 8");
													if((aclValue&8)==8) {
														temp+=8;
														aclValueList.add(8);
													}
												}
											}
										}
										System.out.println("temp is "+temp);
										list = getAclState(temp);
									}
									childModuleLinkedHashMap.put(childModules.get(i),list);
								}
								category.put(parent,childModuleLinkedHashMap);
							}
						} else {
							//Acl中没有数据，到Userprivilege中查找
							UserNotDistributeRole(user);
						}
							
					} else {
						// 用户没有被分配角色
						UserNotDistributeRole(user);
					}
				}
			} else {
				System.out.println("-----------------");
				System.out.println("listuserP日veze ==0");
				System.out.println("-----------------");
				// 判断登陆用户是否有角色
				
			if (usersRoles.size() > 0) {
				// 用户有角色
				String strRole = "";
				for(int k=0; k<usersRoles.size(); k++) {
					strRole += usersRoles.get(k).getRoleId().getId()+",";
				}
				strRole = strRole.substring(0,strRole.lastIndexOf(","));
				List<Acl> acls = convertToListAcl(superDao.find(
						"from Acl a where a.principalId in ("+ strRole+")"));
				System.out.println("acl size issssssssssssss  "+acls.size());

				String str = "";
				if(acls.size()>0) {
					for (int i = 0; i < acls.size(); i++) {
						Acl acl = acls.get(i);
						//不继承（按照角色进行查找）
						List<Module> RoleModuleChildsList = convertToListModule(superDao.getDistinctAllObject(Module.class,  " and s.id = " + acl.getModuleId().getId()));
						for(int p=0; p<RoleModuleChildsList.size(); p++) {
							str += RoleModuleChildsList.get(p).getId() + ",";
						}
					}
					System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
					str = str.substring(0,str.lastIndexOf(","));
					System.out.println("str is "+str);
					//select distinct(m.pid) 
					String sql6 = "select distinct(m.pid) from Module m where m.id in (" + str +")";
					List<Module> parentModules = convertToListModule(superDao.getAllObjects(sql6));
					System.out.println("parentModule size is "+parentModules.size());
					for(int y=0; y<parentModules.size(); y++) {
						System.out.println("parentModuls is "+parentModules.get(y).getName());
					}
					String sql7 = "select distinct(m) from Module m where m.id in (" + str +")";
					//part child
					List<Module> childrenModules = convertToListModule(superDao.getAllObjects(sql7));
					for(int y=0; y<childrenModules.size(); y++) {
						System.out.println("childrenModuls is "+childrenModules.get(y).getName());
					}
					
					for(int y=0; y<parentModules.size(); y++) {
						LinkedHashMap<Module, List> childModuleLinkedHashMap = new LinkedHashMap<Module, List>();
						Module parent = parentModules.get(y);
						//all
						List<Module> childModules = convertToListModule(superDao.getAllObjects(Module.class, "and s.pid.id =" + parent.getId()));
						List list = null;
						for(int i=0; i<childModules.size(); i++) {
							Module child = childModules.get(i);
							List<Acl> acls2 = convertToListAcl(superDao.find(
									"select distinct(a) from Acl a where a.principalId in ("+ strRole+") and a.moduleId = "+child.getId()));
							System.out.println("acl size issssssssssssss  "+acls2.size());
							System.out.println("str Is "+"select distinct(a) from Acl a where a.principalId in ("+ strRole+") and a.moduleId = "+child.getId());
							int temp = 0;
							List aclValueList = new ArrayList();
							if(acls2.size()>0) {
								for(int k=0; k<acls2.size(); k++) {
									System.out.println("acl module name  is "+acls2.get(k).getModuleId().getName());
									System.out.println("acl role name is "+acls2.get(k).getPrincipalId().getName());
									System.out.println("acl aclState  is "+acls2.get(k).getAclState());
									/////////////////////////
									int aclValue = acls2.get(k).getAclState();
									if(aclValueList != null) {
										if(!aclValueList.contains(1)) {
											System.out.println("not include 1");
											if((aclValue&1)==1) {
												temp+=1;
												aclValueList.add(1);
											}
										}
									}
									if(aclValueList != null) {
										if(!aclValueList.contains(2)) {
											System.out.println("not include 2");
											if((aclValue&2)==2) {
												temp+=2;
												aclValueList.add(2);
											}
										}
									}
									if(aclValueList != null) {
										if(!aclValueList.contains(4)) {
											System.out.println("not include 4");
											if((aclValue&4)==4) {
												temp+=4;
												aclValueList.add(4);
											}
										}
									}
									if(aclValueList != null) {
										if(!aclValueList.contains(8)) {
											System.out.println("not include 8");
											if((aclValue&8)==8) {
												temp+=8;
												aclValueList.add(8);
											}
										}
									}
								}
								System.out.println("temp is "+temp);
								list = getAclState(temp);
							}
							childModuleLinkedHashMap.put(childModules.get(i),list);
						}
						category.put(parent,childModuleLinkedHashMap);
					}
				} else {
					//Acl中没有数据，到Userprivilege中查找
					UserNotDistributeRole(user);
				}
					
			} else {
				// 用户没有被分配角色
				UserNotDistributeRole(user);
			}
		}
		} else {
			Iterator iterator = category.keySet().iterator();
			while (iterator.hasNext()) {
				Module key = (Module) iterator.next();
				iterator.remove(); // 添加该行代码
				category.remove(key);
			}
			System.out.println("mapppppp size is " + category.size());
			getCategory(user);
		}
	}

	
	private List<UserPrivilege> convertToUserPrivilege(List<Object> objects) {
		List<UserPrivilege> up = new ArrayList<UserPrivilege>();
		for(int i=0; i<objects.size(); i++) {
			up.add((UserPrivilege)objects.get(i));
		}
		return up;
	}

	public List<UserPrivilege> convertToListUserPrige(List<Object> listUsers) {
		List<UserPrivilege> listUP = new ArrayList<UserPrivilege>();
		for(int i=0; i<listUsers.size(); i++) {
			listUP.add((UserPrivilege)listUsers.get(i));
		}
		return listUP;
	}
	
	public List<Acl> convertToListAcl(List<Object> objects) {
		List<Acl> listAcls = new ArrayList<Acl>();
		for (int i = 0; i < objects.size(); i++) {
			listAcls.add((Acl) objects.get(i));
		}
		return listAcls;
	}

	public static LinkedHashMap<Module, LinkedHashMap<Module, List>> getCategory() {
		return category;
	}

	public LinkedHashMap<Module,LinkedHashMap<Module, List>> getMenus() {
		return category;
	}
	
	public List<Role> convertToListUsersRoles(List<Object> objects) {
		List<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < objects.size(); i++) {
			roles.add((Role)objects.get(i));
		}
		return roles;
	}
	
	public void UserNotDistributeRole(Users user) {
		
		List<UserPrivilege> listUserPrige = convertToListUserPrige(superDao.find("from UserPrivilege up where up.userId = "+user.getId()));
		String str = "";
		if(listUserPrige.size() > 0) {
			for(int i=0; i<listUserPrige.size(); i++) {
				str+=listUserPrige.get(i).getModuleId().getId()+",";
			}
			str = str.substring(0,str.lastIndexOf(","));
			System.out.println("str is "+str);
			String sql3 = "select distinct(m.pid) from Module m where m.id in (" + str + ")";
			List<Module> listParentModule = convertToListModule(superDao.find(sql3));
			List<Module> listChildModule = convertToListModule(superDao.getDistinctAllObject(Module.class, " and s.id in("+str+")"));
			Set<Module> allModules = new HashSet<Module>();
			
			for(int i=0; i<listParentModule.size(); i++) {
				LinkedHashMap<Module, List> categoryChildLinkedHashMap = new LinkedHashMap<Module, List>();
				Set<Module> childModules  = listParentModule.get(i).getModules();
				if(category.containsKey(listParentModule.get(i))) {
					continue;
				}
				if(childModules.size()>0) {
					for (Iterator iterator2 = childModules.iterator(); iterator2
							.hasNext();) {
						Module module = (Module) iterator2.next();
						for (int j = 0; j < listChildModule.size(); j++) {
							if(listChildModule.get(j).getId() == module.getId()) {
								allModules.add(listChildModule.get(j));
								String sql4 = "from UserPrivilege ur where ur.inheritance = -1 and ur.moduleId = ?";
								UserPrivilege ur = (UserPrivilege)superDao.check(sql4, new Object[]{listChildModule.get(j)});
								if(ur != null) {
									List list = getAclState(ur.getUserValue());
									categoryChildLinkedHashMap.put(listChildModule.get(j), list);
									category.put(listParentModule.get(i), categoryChildLinkedHashMap);
									System.out.println("category is not null");
								} else {
									System.out.println("else, category is null");
									category.put(null,null);
								}
							}
						}
					}
					
				} else {
					System.out.println("else2, category is null");
					category.put(listParentModule.get(i), null);
				}
			}
			
		} else {
			category.put(null, null);
		}
	}

	public void getUserPrivilege(Users user) {
		System.out.println("user id "+user.getId());
		if (userPrivileges.size() == 0) {
		List<UserPrivilege> listUserPrige = convertToListUserPrige(superDao.find("from UserPrivilege up where up.userId = "+user.getId()));
		System.out.println("user name is "+user.getId());
		if(listUserPrige.size() > 0) {
			//用户权限表中有数据
			System.out.println("用户权限表中有数据");
			List<Module> parentModule = getAllModules(Module.class,
					" and s.pid is null");
			List<UserPrivilege> listUserPrivileges = convertToListUserPrige(superDao.getAllObjects(UserPrivilege.class,"and s.userId = "+user.getId()));
			System.out.println("list size is "+listUserPrivileges.size());
			for (int i = 0; i < parentModule.size(); i++) {
				LinkedHashMap<Module, List> maps = new LinkedHashMap<Module, List>();
				System.err.println("parent module "+parentModule.get(i).toString());
				Set<Module> temp = parentModule.get(i).getModules();
				if (temp.size() == 0) {
					userPrivileges.put(parentModule.get(i), null);
				} else {
					for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
						List<Integer> listInt = null;
						Module module = (Module) iterator.next();
						System.out.println("children module " + module.toString());
						listInt = new ArrayList<Integer>();
						for(int j=0; j<listUserPrivileges.size(); j++) {
							System.out.println("module id is "+module.getId());
							System.out.println("listUserPrivielge id is "+listUserPrivileges.get(j).getModuleId().getId());
							if(module.getId() == listUserPrivileges.get(j).getModuleId().getId()) {
								System.out.println("=====");
								System.out.println("listUserPrivileges.get(j).getInheritance() is "+listUserPrivileges.get(j).getInheritance());
								listInt = getAclState(listUserPrivileges.get(j).getUserValue(),listUserPrivileges.get(j).getInheritance());
							} else {
								for(int k=0; k<4; k++) {
									listInt.add(0);
								}
								listInt.add(2);
							}
							maps.put(module, listInt);
						}
					}
				}
					userPrivileges.put(parentModule.get(i), maps);
				}
		} else {
			//用户权限表中没有数据
			System.out.println("用户权限表中没有数据");
			List<Module> parentModule = getAllModules(Module.class,
					" and s.pid is null");
			for (int i = 0; i < parentModule.size(); i++) {
				LinkedHashMap<Module, List> maps = new LinkedHashMap<Module, List>();
				System.err.println("parent module "+parentModule.get(i).toString());
				Set<Module> temp = parentModule.get(i).getModules();
				if (temp.size() == 0) {
					userPrivileges.put(parentModule.get(i), null);
				} else {
					for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
						List<Integer> listInt = null;
						Module module = (Module) iterator.next();
						System.out.println("children module " + module.toString());
						
						listInt = new ArrayList<Integer>();
						for(int j=0; j<4; j++) {
							listInt.add(0);
						}
							maps.put(module, listInt);
						}
					}
					userPrivileges.put(parentModule.get(i), maps);
				}
		} 
	} else {
		Iterator iterator = userPrivileges.keySet().iterator();
		while (iterator.hasNext()) {
			Module key = (Module) iterator.next();
			iterator.remove(); // 添加该行代码
			userPrivileges.remove(key);
		}
		System.out.println("mapppppp size is " + userPrivileges.size());
		getUserPrivilege(user);
	}
}
}






