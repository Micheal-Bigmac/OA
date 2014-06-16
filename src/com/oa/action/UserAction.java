package com.oa.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.oa.model.Module;
import com.oa.model.Person;
import com.oa.model.Role;
import com.oa.model.UserPrivilege;
import com.oa.model.Users;
import com.oa.model.UsersRoles;
import com.oa.service.ModuleService;
import com.oa.service.PersonService;
import com.oa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import java.net.URLDecoder;
/**
 * @author Big mac
 *
 */
public class UserAction extends ActionSupport {

	private UserService userService;
	private PersonService personService;
	private ModuleService moduleService;
	private Users user;
	private String returns;
	private int index;
	private Person person;
	private Role role;
	private UsersRoles usersRoles;
	private int userid;
	private Integer moduleId;
	private Integer userId;
	private Integer boxValue;
	private Integer boxValues;
	private File uploadFile;	//用来封装上传的图片(头像)
	private String uploadFileContentTypeString;	//用来封装上传图片的类型
	private String uploadFileFileName;	//用来封装上传图片的图片名
	
	private String currentPw;
	private String newPd;
	private String rePd;
	
	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UsersRoles getUsersRoles() {
		return usersRoles;
	}

	public void setUsersRoles(UsersRoles usersRoles) {
		this.usersRoles = usersRoles;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String login(){
		System.out.println("this is login");
		personService.getSeletsValue();
		Users admin = (Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
		Users login=null;
		
		if(admin == null){
			if(user != null) {
				login = userService.login("from Users u where u.account = ? and u.password= ?", new Object[]{user.getAccount(),user.getPassword()});
				if(login == null){ 
					return "login_failure"; 
				}
				Users thisUser = (Users)userService.getThisUser(Users.class, login.getId());
				ServletActionContext.getRequest().getSession().setAttribute("photoPath", login.getPersonid().getPhotoPath());
				ServletActionContext.getRequest().getSession().setAttribute("admin", login);
				ServletActionContext.getRequest().getSession().setAttribute("modules", moduleService.getCategory(login));
				ServletActionContext.getRequest().setAttribute("personName",thisUser.getPersonid().getName());
				System.out.println("we have send login");
			}
		}
		return "login_success";
	}
	
	public String chat() {
		Users login = (Users)ServletActionContext.getRequest().getSession().getAttribute("admin");
		System.out.println("login.getpersonId is "+login.getPersonid());
		ServletActionContext.getRequest().getSession().setAttribute("adminName",login.getPersonid().getName());
		
		return "chat";
	}
	
	public String inputChat() {
		String msg = ServletActionContext.getRequest().getParameter("msg");
		Users login = (Users)ServletActionContext.getRequest().getSession().getAttribute("admin");
		ArrayList msgs = (ArrayList)ServletActionContext.getRequest().getSession().getServletContext().getAttribute("msgs");
		if(msg!=null && !msg.equals("")) {
			msgs.add(login.getPersonid().getName()+"说："+msg);
		}
		return "chat";
	}
	
	public String exits(){
		Users exits=userService.exits(user.getAccount());
		return null;
	}
	
	
	
	public String modifyPassword() throws IOException{
		Users users=(Users) ServletActionContext.getRequest().getSession().getAttribute("admin");
		 PrintWriter writer=ServletActionContext.getResponse().getWriter();
		System.out.println("=========");
		if( users.getPassword().equals(currentPw)){
			if(newPd.equals(rePd)){
				System.out.println("==----------");
				users.setPassword(newPd);
				userService.update(users);
				writer.println(2);
				return null;
			}
			writer.println(1);
			return null;
		}
		writer.println(0);
		return null;
	}
	public String addAccount(){
		user.setPersonid(person);
		Serializable flag=userService.addUser(user);
		return flag!=null ?"operator_success" :"operator_failure";
	}
	public String accountModify(){
		returns="JSP/gerenxinxi.jsp";
		System.out.println(user.toString());
		Users thisUser = (Users)userService.getThisUser(Users.class, user.getId());
//		thisUser.setAccount(user.getAccount());
//		thisUser.setPassword(user.getPassword());
		/*	userService.update(thisUser);*/
		System.out.println("person id i s "+thisUser.getPersonid());
//		Users newUser = (Users)userService.getThisUser(Users.class, thisUser.getId());

		try {
			InputStream is = new FileInputStream(uploadFile);					//文件输出流
			String uploadPath = ServletActionContext		
					.getServletContext().getRealPath("/photo");//设置图片上传目录
			Users loginUser = (Users)ServletActionContext.getRequest().getSession().getAttribute("admin");
			
			String photoName = loginUser.getAccount()+this.getUploadFileFileName().substring(this.getUploadFileFileName().lastIndexOf("."));
			System.out.println("photo name is "+photoName);
			File toFile = new File(uploadPath, photoName);	//设置目标图片
			if(!toFile.getParentFile().exists()){
				toFile.getParentFile().mkdirs();
			}
			OutputStream os = new FileOutputStream(toFile);
			if(!toFile.getParentFile().exists()){
				toFile.getParentFile().mkdirs();
			}
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length=	is.read(buffer))>0) {
				os.write(buffer,0,length);
			}
			is.close();
			os.close();
			String path = ServletActionContext.getRequest().getContextPath();
			String basePath = ServletActionContext.getRequest().getScheme() + "://"
					+ ServletActionContext.getRequest().getServerName() + ":" + ServletActionContext.getRequest().getServerPort()
					+ path + "/photo/"+photoName;
			Person person = thisUser.getPersonid();
			System.out.println("person is "+person);
			person.setPhotoPath(basePath);
			userService.update(person);
			//user.getPersonid().setPhotoPath(basePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().getSession().setAttribute("photoPath", thisUser.getPersonid().getPhotoPath());
		ServletActionContext.getRequest().getSession().setAttribute("admin", thisUser);
		return "operator_success";
	}
	
	
	public String userList() {
		String hql = "";
		List<Person> listPersons = userService.getPageUsers((index==0 ? 1 : index), Person.class, hql);
		int total = userService.getAllUsers(Person.class, hql).size();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("currentIndex", (index==0 ?  1 : index ));
		request.setAttribute("totalSize",total);
		request.setAttribute("url", "UserAction!userList");
		request.setAttribute("listObject", listPersons);
		
		return "userList";
	}
	
	public String add() {
		user.setCreateTime(new java.util.Date());
		Person personid = userService.getPersonId(Person.class, person.getId());
		user.setPersonid(personid);
		userService.addUser(user);
		returns = "UserAction!userList";
		
		return "operator_success";
	}

	public String deleteAccount() {		
		userService.dealDeleteAccount(user.getPersonid().getId());
		returns = "UserAction!userList";	
		return "operator_success";
	}
	
	public String distributeRole() {
		Person personid = userService.getPersonId(Person.class, user.getPersonid().getId());
		user = userService.getsUser(personid);
		List<UsersRoles> usersRolesList = userService.getUsersRoles(user);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userId", user.getId());
		request.setAttribute("usersRoles", usersRolesList);
		
		return "distributeRole";
	}
	
	public String listRole() {
		List<Role> roles = userService.listRole();
		//System.out.println("roles size is "+roles.size());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roles", roles);
		request.setAttribute("userid", userid);
		
		return "listRole";
	}

	public String distributeRoleToUser() throws IOException {
		System.out.println("role name is "+role.getName());
		//System.out.println("usersRoles priority is "+usersRoles.getPriority());
		System.out.println("user is "+user.toString());
		System.out.println("useid is "+user.getId());
		user = userService.getThisUser(Users.class, user.getId());
		role = userService.getThisRole(role.getName());
		
		UsersRoles usersRoles = new UsersRoles();
		usersRoles.setRoleId(role);
		usersRoles.setUserId(user);
		userService.addUsersRoles(usersRoles);
		ServletActionContext.getResponse().getWriter().print("UserAction!distributeRole?user.personid.id="+user.getPersonid().getId());
		
		returns = "UserAction!userList";
		return null;
	}
	
	public String updateDistributeRole() {
		//System.out.println("usersRoles id is "+usersRoles.getId());
		usersRoles = userService.getThisUsersRoles(usersRoles.getId());
		//System.out.println("is usersRoles is null "+usersRoles==null? "yes":"no");
		List<Role> roles = userService.listRole();
		//System.out.println("usersRoles priority is "+usersRoles.getPriority());
		//System.out.println("sersRolesId is "+usersRoles.getRoleId().getId());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("roles", roles);
		request.setAttribute("usersRolesId", usersRoles.getRoleId().getId());
		
		return "updateDistributeRole";
	}
	
	public String updateRoleToUser() {
		//System.out.println("usersrles id "+usersRoles.getId());
		//System.out.println("usersroles priority is "+usersRoles.getPriority());
		//System.out.println("usersroles roles name is "+role.getName());
		usersRoles = userService.getUsersRolesById(UsersRoles.class, usersRoles.getId());
		userService.updateUsersRoles(usersRoles, role.getName());
		returns = "UserAction!userList";
		
		return "operator_success";
	}
	
	public String deleteDistributeRole() {
		usersRoles = userService.getUsersRolesById(UsersRoles.class, usersRoles.getId());
		userService.deleteUsersRoles(usersRoles);
		returns = "UserAction!userList";
		return "operator_success";
	}

	public String back() {
		ServletActionContext.getRequest().getSession().removeAttribute("admin");
		
//		Map<String, String> loginUsers = (Map<String, String>)ServletActionContext.getRequest().getSession().getServletContext().getAttribute("loginUsers");
	/*	Users user = (Users)ServletActionContext.getRequest().getSession().getAttribute("admin");
		System.out.println("user is "+user.getAccount());*/
//		String IP = ServletActionContext.getRequest().getRemoteAddr();

	/*	String index = null;
		if(loginUsers.size() != 0) {
			Iterator it=loginUsers.entrySet().iterator();           
			System.out.println(loginUsers.entrySet().size());    
			String key;           
			String value;    
			while(it.hasNext()){    
			        Map.Entry entry = (Map.Entry)it.next();           
			        key=entry.getKey().toString();           
			        value=entry.getValue().toString();    
			        if(key.equals(user.getAccount().toString()) && value.equals(IP)) {
//			        	index = key;
//			        	loginUsers.remove(index);
			        	loginUsers.remove(key);
			        }
			} 
		}*/
//		System.out.println("index is "+index);
		
		
//		System.out.println("list size is "+loginUsers.size());
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession();
//		session.invalidate();
		
		return "logout";
	}
	
	public String distributeUser() {		
		/////////////
		///////////
		///////
		Person personid = userService.getPersonId(Person.class, user.getPersonid().getId());
		user = userService.getsUser(personid);
		ServletActionContext.getRequest().setAttribute("userPrivileges", moduleService.getUserPrivilege(user));
		ServletActionContext.getRequest().setAttribute("username", personid.getName());
		ServletActionContext.getRequest().setAttribute("personId", user.getPersonid().getId());
		ServletActionContext.getRequest().setAttribute("userId", user.getId());
		return "distributePrivilegeToUser";
	}
	
	public String privilegeToUser() {
		System.out.println("module id is "+moduleId);
		System.out.println("boxValue is "+boxValue);
		System.out.println("boxValues is "+boxValues);
		System.out.println("userId is "+userId);
		UserPrivilege up = new UserPrivilege();
		Module module = userService.getThisModule(Module.class, moduleId);
		Users user = userService.getThisUser(Users.class, userId);
		
		if(boxValues != null) {
			//不继承
			up = userService.getUserPrivilege("from UserPrivilege up where up.userId = ? and up.moduleId = ?", new Object[]{user,module});
			
			if(up != null) {
				int oldBoxValue = up.getUserValue();
				up.setModuleId(module);
				up.setUserId(user);
				up.setInheritance(-1);
				userService.updateUserPrivilege(up);
			}
		} else {
			//继承
			up = userService.getUserPrivilege("from UserPrivilege up where up.userId = ? and up.moduleId = ?", new Object[]{user,module});
			
			if(up != null) {
				System.out.println("module id is "+up.getModuleId().getId());
				System.out.println("userId is is "+up.getUserId().getId());
				up.setModuleId(module);
				up.setUserId(user);
				up.setInheritance(0);
				userService.updateUserPrivilege(up);
			} 
			
		}
		ServletActionContext.getRequest().setAttribute("userPrivileges", moduleService.getUserPrivilege(user));
		return "distributePrivilegeToUser";
	}

	
	public String privilegeToUserPart() {
		System.out.println("module id is "+moduleId);
		System.out.println("boxValue is "+boxValue);
		System.out.println("userId is "+userId);
		UserPrivilege up = new UserPrivilege();
		Module module = userService.getThisModule(Module.class, moduleId);
		Users user = userService.getThisUser(Users.class, userId);
		
		up = userService.getUserPrivilege("from UserPrivilege up where up.userId = ? and up.moduleId = ?", new Object[]{user,module});
		if(up != null) {
			int oldBoxValue = up.getUserValue();
			up.setModuleId(module);
			up.setUserId(user);
			up.setInheritance(-1);
			up.setUserValue(boxValue.intValue()+oldBoxValue);
			userService.updateUserPrivilege(up);
		} else {
			UserPrivilege up2 = new UserPrivilege();
			up2.setModuleId(module);
			up2.setUserId(user);
			up2.setInheritance(-1);
			up2.setUserValue(boxValue);
			userService.addUserPrivilege(up2);
		}
		
		ServletActionContext.getRequest().setAttribute("userPrivileges", moduleService.getUserPrivilege(user));
		return "distributePrivilegeToUser";
	}
	
	public String privilegeToUserAllChose() {
		
		System.out.println("module id is "+moduleId);
		
		System.out.println("userId is "+userId);
		UserPrivilege up = new UserPrivilege();
		Module module = userService.getThisModule(Module.class, moduleId);
		Users user = userService.getThisUser(Users.class, userId);
		
		up = userService.getUserPrivilege("from UserPrivilege up where up.userId = ? and up.moduleId = ?", new Object[]{user,module});
		if(up != null) {
			System.out.println("module id is "+up.getModuleId().getId());
			System.out.println("userId is is "+up.getUserId().getId());
			up.setModuleId(module);
			up.setUserId(user);
			up.setUserValue(15);
			userService.updateUserPrivilege(up);
		} else {
			UserPrivilege up2 = new UserPrivilege();
			up2.setModuleId(module);
			up2.setUserId(user);
			up2.setUserValue(15);
			up2.setInheritance(-1);
			userService.addUserPrivilege(up2);
		}
		ServletActionContext.getRequest().setAttribute("userPrivileges", moduleService.getUserPrivilege(user));
		return "distributePrivilegeToUser";
	}
	
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBoxValue() {
		return boxValue;
	}

	public void setBoxValue(Integer boxValue) {
		this.boxValue = boxValue;
	}

	public Integer getBoxValues() {
		return boxValues;
	}

	public void setBoxValues(Integer boxValues) {
		this.boxValues = boxValues;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentTypeString() {
		return uploadFileContentTypeString;
	}

	public void setUploadFileContentTypeString(String uploadFileContentTypeString) {
		this.uploadFileContentTypeString = uploadFileContentTypeString;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getCurrentPw() {
		return currentPw;
	}

	public void setCurrentPw(String currentPw) {
		this.currentPw = currentPw;
	}

	public String getNewPd() {
		return newPd;
	}

	public void setNewPd(String newPd) {
		this.newPd = newPd;
	}

	public String getRePd() {
		return rePd;
	}

	public void setRePd(String rePd) {
		this.rePd = rePd;
	}

}
