package com.oa.action;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.oa.model.ContractProductRecord;
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
	private String className3;
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
				hql = "and s.account = '"+textfield+"'";
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
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
				total = newClassList.size();
				//total = 1;
			}
			System.out.println(hql);
			String newClass = className.toLowerCase() + "s";
			System.out.println("new class is "+newClass);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("listObject", newClassList);
			request.setAttribute("currentIndex", (index==0 ?  1 : index));
			
			System.out.println("total is " + total);
			request.setAttribute("totalSize", total);
			System.out.println("testfield is "+textfield);
			System.out.println("url is "+"FindAction!findByCondition?className=Person&findCondition=accountid&textfield="+textfield);
			request.setAttribute("url", "FindAction!findByCondition?className=Person&findCondition=accountid&textfield="+textfield);
			return "userList";
		} else if(findCondition.equals("aggId")) {
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s." + findCondition + "=" + textfield;
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
			request.setAttribute("url", "FindAction!findByCondition?className=Agreement&findCondition=aggId&textfield="+textfield);
			
		} else if(findCondition.equals("type")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=Product&findCondition=type&textfield="+textfield);
			
		} else if(findCondition.equals("productName")) {
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
			}
			if(!textfield.trim().equalsIgnoreCase("") && !textfield.trim().equals("") && className2!=null) {
				hql = "and s.product.productName like '%" + textfield + "%'";
			}
			if(!textfield.trim().equalsIgnoreCase("") && !textfield.trim().equals("") && className.equals("OrderProductRecord")) {
				hql = "and s.product.productName like '%" + textfield + "%'";
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
			if(className2 != null) {
				request.setAttribute("url", "FindAction!findByCondition?className=ContractProductRecord&className2=1&findCondition=productName&textfield="+textfield);
			}
			if(className.equals("Product")) {
				request.setAttribute("url", "FindAction!findByCondition?className=Product&findCondition=productName&textfield="+textfield);
			}
			if(className.equals("OrderProductRecord")) {
				request.setAttribute("url", "FindAction!findByCondition?className=OrderProductRecord&findCondition=productName&textfield="+textfield);
			}
		} else if(findCondition.equals("contactName")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=SupplierContact&findCondition=contactName&textfield="+textfield);
		} else if(findCondition.equals("clientName")) {
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
			}
			if(!textfield.trim().equalsIgnoreCase("") && !textfield.trim().equals("") && className.equals("PayPlan")) {
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
			if(className.equals("PaymentPlan"))
				request.setAttribute("url", "FindAction!findByCondition?className=PaymentPlan&findCondition=clientName&textfield="+textfield);
			if(className.equals("PayPlan"))
				request.setAttribute("url", "FindAction!findByCondition?className=PayPlan&findCondition=clientName&textfield="+textfield);

		} else if(findCondition.equals("salesName")) {
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
			}
			if(!textfield.trim().equalsIgnoreCase("") && !textfield.trim().equals("") && className2 != null) {
				hql = "and s.agreement.salesName like '%" + textfield + "%'";
			}
			if(!textfield.trim().equalsIgnoreCase("") && !textfield.trim().equals("") && className3 != null) {
				hql = "and s.order.salesName like '%" + textfield + "%'";
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
			if(className3 != null) {
				request.setAttribute("url", "FindAction!findByCondition?className=PaymentPlan&className3=1&findCondition=salesName&textfield="+textfield);
			} else {
				if(className2 != null) {
					request.setAttribute("url", "FindAction!findByCondition?className=ContractProductRecord&className2=1&findCondition=salesName&textfield="+textfield);
				} else {
					request.setAttribute("url", "FindAction!findByCondition?className=SalesAgreement&findCondition=salesName&textfield="+textfield);
				}
			}
		}	
		else if(findCondition.equals("contractTitle")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=SalesAgreement&findCondition=contractTitle&textfield="+textfield);
		} else if(findCondition.equals("telephone")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=SupplierContact&findCondition=telephone&textfield="+textfield);
		} else if(findCondition.equals("aggType")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=Agreement&findCondition=aggType&textfield="+textfield);
		} else if(findCondition.equals("personName")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=Agreement&findCondition=aggType&textfield="+textfield);
			
		} else if(findCondition.equalsIgnoreCase("id")) {
			System.out.println("idididididiidididiididi");
			System.out.println("opkkkkkkkkkkkkkkkkk");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s.id = " + textfield;
			}
			if(className.equalsIgnoreCase("Module") && textfield.trim().equalsIgnoreCase("")) {
				hql = "and s.pid is null";
			}
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			System.out.println("url is "+"FindAction!findByCondition?className=Module&findCondition=id&textfield="+textfield);
			request.setAttribute("url", "FindAction!findByCondition?className=Module&findCondition=id&textfield="+textfield);
		} else if(findCondition.equalsIgnoreCase("supplierCode")) {
			System.out.println("supplierCode");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s.supplierCode like '%" + textfield + "%'";			
			}
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("url", "FindAction!findByCondition?className=SupplierManager&findCondition=supplierCode&textfield="+textfield);
		} else if(findCondition.equalsIgnoreCase("supplierName")) {
			System.out.println("supplierName");
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
			} else {
				hql = "and s.supplierName = '" + textfield + "'";			
			}
			newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("url", "FindAction!findByCondition?className=SupplierManager&findCondition=supplierName&textfield="+textfield);
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
		} else if(findCondition.equals("orderName")) {
			if(className.equals("PurchaseOrderRegisiter")) {
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
				request.setAttribute("url", "FindAction!findByCondition?className=PurchaseOrderRegisiter&findCondition=orderName&textfield="+textfield);
			}
			if(className.equals("OrderProductRecord")) {
				System.out.println("that is okkkk");
				if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
					hql = "";
					System.out.println("hql is "+hql);
				} else {
					hql = "and s.order.orderName like '%" + textfield + "%'";
					System.out.println("hql is "+hql);
				}
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
				total = findService.getAllContents(className, hql).size();
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("url", "FindAction!findByCondition?className=OrderProductRecord&findCondition=orderName&textfield="+textfield);
			}
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
				request.setAttribute("url", "FindAction!findByCondition?className=PayPlan&findCondition=orderName&textfield="+textfield);
				return "payPlanList";
			}
			
		} else if(findCondition.equals("orderType")) {
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
			request.setAttribute("url", "FindAction!findByCondition?className=PurchaseOrderRegisiter&findCondition=orderType&textfield="+textfield);	
		} else if(findCondition.equals("name")) { 
			if(textfield.trim().equalsIgnoreCase("") || textfield.trim().equals("")) {
				hql = "";
				System.out.println("hql is "+hql);
			} else {
				hql = "and s." + findCondition + " like '%" + textfield + "%'";
				System.out.println("hql is "+hql);
			}
			if(className.equals("DisciplinaryRecords") && !textfield.trim().equalsIgnoreCase("")) {
				hql = "and s.personId.name like '%" + textfield +"%'";
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			} else {  
				newClassList = findService.findByCondition((index==0 ? 1 : index), className, findCondition, textfield, hql);
			}
			total = findService.getAllContents(className, hql).size();
			HttpServletRequest request = ServletActionContext.getRequest();
			if(className.trim().equals("Module"))
				request.setAttribute("url", "FindAction!findByCondition?className=Module&findCondition=name&textfield="+textfield);
			if(className.trim().equals("Person"))
				request.setAttribute("url", "FindAction!findByCondition?className=Person&findCondition=name&textfield="+textfield);
			if(className.trim().equals("DisciplinaryRecords"))
				request.setAttribute("url", "FindAction!findByCondition?className=DisciplinaryRecords&findCondition=name&textfield="+textfield);
			if(className.trim().equals("ListPerformanceExamine"))
				request.setAttribute("url", "FindAction!findByCondition?className=ListPerformanceExamine&findCondition=name&textfield="+textfield);
			if(className2!=null && className.trim().equals("WorkFlow")) {
				System.out.println(hql);
				String seqName = (String) className.subSequence(0, 1);
				String otherName = (String)className.subSequence(1,className.length());
				className = seqName.toLowerCase()+otherName;
				String newClass = className + "s";
				System.out.println("new class is "+newClass);
				
				request.setAttribute("listObject", newClassList);
				request.setAttribute("currentIndex", (index==0 ?  1 : index));
				System.out.println("total is " + total);
				request.setAttribute("totalSize", total);
				
				request.setAttribute("url", "FindAction!findByCondition?className=WorkFlow&findCondition=name&textfield="+textfield);
				return "dynamicFormList";
			}
			if(className.trim().equals("WorkFlow") && className2 == null) 
				request.setAttribute("url", "FindAction!findByCondition?className=WorkFlow&findCondition=name&textfield="+textfield);
			
		} else if(findCondition.equals("awardUnit")) { 
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
			if(className.trim().equals("DisciplinaryRecords"))
				request.setAttribute("url", "FindAction!findByCondition?className=DisciplinaryRecords&findCondition=awardUnit&textfield="+textfield);
		} else if(findCondition.equals("recordUser")) { 
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
			if(className.trim().equals("ListPerformanceExamine"))
				request.setAttribute("url", "FindAction!findByCondition?className=ListPerformanceExamine&findCondition=recordUser&textfield="+textfield);
		} else if(findCondition.equals("phone")) { 
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
			if(className.trim().equals("Person"))
				request.setAttribute("url", "FindAction!findByCondition?className=Person&findCondition=phone&textfield="+textfield);
		}
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

	public String getClassName3() {
		return className3;
	}

	public void setClassName3(String className3) {
		this.className3 = className3;
	}

}
