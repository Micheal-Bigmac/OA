package com.oa.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.oa.model.ContractProductRecord;
import com.oa.model.OrderProductRecord;
import com.oa.model.PaymentPlan;
import com.oa.model.Users;
import com.oa.service.FindService;
import com.opensymphony.xwork2.ActionSupport;

public class FindAction extends ActionSupport {

	private FindService findService;
	private int index;
	private String findCondition;
	private String textfield;
	private String className;
	private String className2;
	private String documentType;
	public String findByCondition() {
		System.out.println("findcondition is "+findCondition);
		System.out.println("text si "+textfield);
		String hql = "";
		int total = 0;
		List newClassList = null;
		if(findCondition.equals("accountid")) {
			System.out.println("equals acccoutnid");
			String name = "Users";
			
			if(!textfield.trim().equals("")) {
				System.out.println("if");
				hql = "and s.account = "+Integer.valueOf(textfield);
				List newClassListUsers = findService.findByCondition((index==0 ? 1 : index), name, findCondition, textfield, hql);
				System.out.println("list class user is "+newClassListUsers);
				if(newClassListUsers.size() != 0) {
					newClassList = findService.findByCondition(((Users)newClassListUsers.get(0)).getPersonid().getId());
					total = 1;
				} else { 
					System.out.println("ok");
					newClassList = null;
					total = 0;
				}
			} else {
				hql = "";
				newClassList = findService.findByCondition((index==0 ? 1 : index), name, hql);
				total = 1;
			}
			System.out.println(hql);
			String newClass = className.toLowerCase() + "s";
			System.out.println("new class is "+newClass);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("listObject", newClassList);
			request.setAttribute("currentIndex", (index==0 ?  1 : index));
			
			System.out.println("total is " + total);
			request.setAttribute("totalSize", total);
			request.setAttribute("url", "FindAction!findByCondition");
			return "userList";
		}  else if(findCondition.equals("personName")) {
			findCondition = "name";
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
			}
			
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			System.out.println(hql);
			String newClass = className.toLowerCase() + "s";
			System.out.println("new class is "+newClass);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("listObject", newClassList);
			request.setAttribute("currentIndex", (index==0 ?  1 : index));
			
			System.out.println("total is " + total);
			request.setAttribute("totalSize", total);
			request.setAttribute("url", "FindAction!findByCondition");
			return "userList";
		} else if(findCondition.equalsIgnoreCase("id")) {
			System.out.println("idididididiidididiididi");
			System.out.println("opkkkkkkkkkkkkkkkkk");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s.id = " + textfield;
			}
			if(className.equalsIgnoreCase("Module")) {
				hql = "and s.pid is null";
			}
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("url", "FindAction!findByCondition?className=Module&findCondition=id&textfield="+textfield);
		} else if(findCondition.equalsIgnoreCase("supplierCode")) {
			System.out.println("supplierCode");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s.supplierCode = '" + textfield + "'";			
			}
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("url", "FindAction!findByCondition");
		} else if(findCondition.equals("productName")) {
			System.out.println("ok");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "from ContractProductRecord c where c.product.productName like '%"+textfield+"%'";
			}
			List<ContractProductRecord> listCPR= findService.findByConditions(hql);
			System.out.println(hql);
			className = "ContractProductRecord";
			String seqName = (String) className.subSequence(0, 1);
			String otherName = (String)className.subSequence(1,className.length());
			className = seqName.toLowerCase()+otherName;
			String newClass = className + "s";
			System.out.println("new class is "+newClass);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("listObject", listCPR);
			request.setAttribute("currentIndex", (index==0 ?  1 : index));
			
			System.out.println("total is " + total);
			request.setAttribute("totalSize", total);
			request.setAttribute("url", "FindAction!findByCondition");
			return "contractProductRecordList";
		} else if(findCondition.equals("salesName")) {
			if(className.equals("PaymentPlan")) {
				System.out.println("ok");
				System.out.println("paymentplan");
				if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
					hql = "";
				} else {
					hql = "from PaymentPlan c where c.order.salesName like '%"+textfield+"%'";
				}
				List<PaymentPlan> listPP= findService.findByConditionsPP(hql);
				System.out.println(hql);
				String seqName = (String) className.subSequence(0, 1);
				String otherName = (String)className.subSequence(1,className.length());
				className = seqName.toLowerCase()+otherName;
				String newClass = className + "s";
				System.out.println("new class is "+newClass);
				
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("listObject", listPP);
				request.setAttribute("currentIndex", (index==0 ?  1 : index));
				
				System.out.println("total is " + total);
				request.setAttribute("totalSize", total);
				request.setAttribute("url", "FindAction!findByCondition");
				return "paymentPlanList";
			} else {
				System.out.println("ok");
				if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
					hql = "";
				} else {
					hql = "from ContractProductRecord c where c.agreement.salesName like '%"+textfield+"%'";
				}
				List<ContractProductRecord> listCPR= findService.findByConditions(hql);
				System.out.println(hql);
				className = "ContractProductRecord";
				String seqName = (String) className.subSequence(0, 1);
				String otherName = (String)className.subSequence(1,className.length());
				className = seqName.toLowerCase()+otherName;
				String newClass = className + "s";
				System.out.println("new class is "+newClass);
				
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("listObject", listCPR);
				request.setAttribute("currentIndex", (index==0 ?  1 : index));
				
				System.out.println("total is " + total);
				request.setAttribute("totalSize", total);
				request.setAttribute("url", "FindAction!findByCondition");
				return "contractProductRecordList";
			}
		} else if(findCondition.equals("orderName")) {
			if(className.equals("PayPlan")) { 
				if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
					hql = "";
				} else {
					hql = "and s.order." + findCondition + " like '%" + textfield + "%'";
				}
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
				total = findService.getAllContents(className, hql).size();
				System.out.println(hql);
				String newClass = className.toLowerCase() + "s";
				System.out.println("new class is "+newClass);
				
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("listObject", newClassList);
				request.setAttribute("currentIndex", (index==0 ?  1 : index));
				
				System.out.println("total is " + total);
				request.setAttribute("totalSize", total);
				request.setAttribute("url", "FindAction!findByCondition");
				return "payPlanList";
			}
		} else if(findCondition.equals("name")) { 
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
				System.out.println("hql is "+hql);
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
				System.out.println("hql is "+hql);
			}
			   
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("url", "FindAction!findByCondition?className=Module&findCondition=name&textfield="+textfield);
		}
		/*else if(className!=null) {
			if(className.equals("OrderProductRecord")) {
			System.out.println("ok");
			List<OrderProductRecord> listOP = null;
			if(findCondition.equals("productName")) {
				listOP= findService.findByConditionsOPR("from OrderProductRecord c where c.product.productName like '%"+textfield+"%'");
			}
			if(findCondition.equals("orderName")) {
				listOP= findService.findByConditionsOPR("from OrderProductRecord c where c.order.orderName like '%"+textfield+"%'");
			}
			System.out.println(hql);
			className = "OrderProductRecord";
			String seqName = (String) className.subSequence(0, 1);
			String otherName = (String)className.subSequence(1,className.length());
			className = seqName.toLowerCase()+otherName;
			String newClass = className + "s";
			System.out.println("new class is "+newClass);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("listObject", listOP);
			request.setAttribute("currentIndex", (index==0 ?  1 : index));
			
			System.out.println("total is " + total);
			request.setAttribute("totalSize", total);
			request.setAttribute("url", "FindAction!findByCondition");
			return "orderProductRecordList";
		} }else {
			if(className2 != null) {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
				total = findService.getAllContents(className, hql).size();
				System.out.println(hql);
				String seqName = (String) className.subSequence(0, 1);
				String otherName = (String)className.subSequence(1,className.length());
				className = seqName.toLowerCase()+otherName;
				String newClass = className + "s";
				System.out.println("new class is "+newClass);
				
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("listObject", newClassList);
				request.setAttribute("currentIndex", (index==0 ?  1 : index));
				
				System.out.println("total is " + total);
				request.setAttribute("totalSize", total);
				request.setAttribute("url", "FindAction!findByCondition");
				return "dynamicFormList";
			} else {
				System.out.println("name");
				if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
					hql = "";
					System.out.println("hql is "+hql);
				} else {
					hql = "and s." + findCondition + " like '%" + textfield + "%'";
					System.out.println("hql is "+hql);
				}
				
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
				total = findService.getAllContents(className, hql).size();
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("url", "FindAction!findByCondition?className=Module&findCondition=name&textfield="+textfield);
			}
	}*/
		System.out.println(hql);
		String seqName = (String) className.subSequence(0, 1);
		String otherName = (String)className.subSequence(1,className.length());
		className = seqName.toLowerCase()+otherName;
		String newClass = className + "s";
		System.out.println("new class is "+newClass);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", newClassList);
		request.setAttribute("currentIndex", (index==0 ?  1 : index));
		
		System.out.println("total is " + total);
		request.setAttribute("totalSize", total);
		
		return className+"List";
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public String getTextfield() {
		return textfield;
	}

	public void setTextfield(String textfield) {
		this.textfield = textfield;
	}

	public String getFindCondition() {
		return findCondition;
	}

	public void setFindCondition(String findCondition) {
		this.findCondition = findCondition;
	}

	public FindService getFindService() {
		return findService;
	}
	@Resource
	public void setFindService(FindService findService) {
		this.findService = findService;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName2() {
		return className2;
	}

	public void setClassName2(String className2) {
		this.className2 = className2;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

}
