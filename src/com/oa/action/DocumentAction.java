package com.oa.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.runner.Request;

import com.oa.model.ApproveHistory;
import com.oa.model.Document;
import com.oa.model.DocumentProperty;
import com.oa.model.Users;
import com.oa.model.WorkFlow;
import com.oa.service.ApproveHistoryService;
import com.oa.service.DocumentService;
import com.oa.service.WorkFlowService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocumentAction extends ActionSupport {

	private DocumentService documentService;
	private WorkFlowService workFlowService;
	private ApproveHistoryService approveHistoryService;
	private Document document;
	
	/*
	 * private File uploadFiles; private String uploadFilesContentType; private
	 * String uploadFilesFileName;
	 */

	private List<File> uploadFiles;
	private List<String> uploadFilesContentType;
	private List<String> uploadFilesFileName;
	private List<File> currentFileName = new ArrayList<File>();

	private List<DocumentProperty> props;

	private int index;
	private Integer workflowId;
	private String transition;
	private String returns;

	// private Map props = new HashMap();

	// approveHistory

	private String idea;
	private int back;

	public String approve() {
		Users users = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
		ApproveHistory history = new ApproveHistory();
		history.setComments(idea);
		history.setApproveTime(new Date());
		approveHistoryService.addApproveHistory(history, document.getId(),
				users.getId(), (back == 1 ? true : false));
		returns = "DocumentAction!ApprovingDocumentList";
		return "operator_success";
	}

	public String finishDocument() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute("admin");
		int userId = user.getId();
		System.out.println(user.toString());
		List<Document> documents = documentService.serachPageFinishDocuments(
				(index == 0 ? 1 : index), userId);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		request.setAttribute("myDocumentList", documents);
		int totals = documentService.serachAllFinishDocuments(userId).size();
		request.setAttribute("totalSize", totals);
		request.setAttribute("url", "DocumentAction!finishDocument");
		request.setAttribute("type", "finishDocument");
		return "finishDocumentList";
	}

	/**
	 * 
	 * no problem
	 * 
	 * @return
	 */
	public String listMyDocument() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute("admin");
		System.out.println(user.toString());
		List<Document> documents = documentService.searchMyDocumentsPage(
				(index == 0 ? 1 : index), user.getId());
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		request.setAttribute("myDocumentList", documents);
		int totals = documentService.searchAllMyDocument(user.getId()).size();
		request.setAttribute("totalSize", totals);
		request.setAttribute("url", "DocumentAction!listMyDocument");
		request.setAttribute("type", "document");
		return "documentList";
	}

	/**
	 * 
	 * @return
	 */
	public String ApprovingDocumentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute("admin");
		String username = user.getAccount();
		List<Document> documents = documentService
				.searchPageApprovingDocuments(username,
						(index == 0 ? 1 : index));
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		request.setAttribute("myDocumentList", documents);
		System.out.println(username+"  ========= zy");
		List temp = documentService.SearchAllApprovingDocuments(username);
		int totals = (temp == null ? 0 : temp.size());
		request.setAttribute("totalSize", totals);
		request.setAttribute("url", "DocumentAction!ApprovingDocumentList");
		request.setAttribute("type", "approvingDocument");
		return "documentList";
	}

	/**
	 * no problem
	 * 
	 * @return
	 */
	public String approveHistoryList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute("admin");

		List<ApproveHistory> documents = approveHistoryService
				.searchPageApproveHistory((index == 0 ? 1 : index),
						document.getId());
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		request.setAttribute("approveHistoryList", documents);
		int totals = approveHistoryService.searchAllApproveHistory(
				document.getId()).size();
		request.setAttribute("totalSize", totals);
		request.setAttribute("url", "DocumentAction!approveHistoryList");
		request.setAttribute("type", "approveHistory");
		request.setAttribute("documentId", document.getId());
		return "approveHistoryList";
	}

	/***
	 * 
	 * no problem
	 * 
	 * @return
	 */
	public String ApprovedDocumentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute("admin");

		List<Document> documents = documentService.searchPageApprovedDocuments(
				user.getId(), index);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		request.setAttribute("myDocumentList", documents);
		int totals = documentService.searchAllApprovedDocuments(user.getId())
				.size();
		request.setAttribute("totalSize", totals);
		request.setAttribute("url", "DocumentAction!ApprovedDocumentList");
		request.setAttribute("type", "approvedDocument");
		return "documentList";
	}

	public String addDocument() throws IOException {
		System.out.println("DocumentAction ===-- ");
		HttpServletRequest request = ServletActionContext.getRequest();
		Users users = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
		
		if (addtoServer()) {
			System.out.println(" upload file success");
			show();
			System.out.println("===========================");
//			System.out.println(props.toString());
			/*for(DocumentProperty property : props){
				System.err.println(property.toString()+"");
			}*/
		
			System.out.println(" workflow " +workflowId);
			 documentService.addDocument(document, workflowId,users.getId(),props);
		}
		returns = "DocumentAction!listMyDocument";
		return "operator_success";
	}

	public void show() {
//		System.out.println(props.size());
		int i=0;
		if(props == null){
			return ;
		}
		for (DocumentProperty property : props) {
			if(property.getJava_io_File()!=null){
				property.setJava_io_File(currentFileName.get(0).getAbsolutePath());
				props.set(i, property);
				currentFileName.remove(0);
			} else if (property.isNull()){
				props.set(i, null);
			}
			System.out.println(property.toString());
			i++;
		}
	}

	public String deleteDocument() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids[] = request.getParameterValues("delid");
		for (String a : ids) {
			System.out.println(a);
		}
		returns = "DocumentAction!listMyDocument";
		documentService.deleteDocuments(ids);
		return null;
	}

	public String submitView() {
		Users users = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
		System.out.println(document.getId());
		List nextTransation = documentService.searchNextStep(document.getId(),users.getAccount());
		ServletActionContext.getRequest().setAttribute("transitionList",
				nextTransation);
		ServletActionContext.getRequest().setAttribute("id", document.getId());
		return "submitToNextOne";
	}

	/**
	 * no problem
	 * 
	 * @return
	 */
	public String submit() {
		Users users = (Users) ServletActionContext.getRequest().getSession()
				.getAttribute("admin");
		documentService.submitToWorkFlow(users.getId(), document.getId(),
				transition);
		returns = "DocumentAction!listMyDocument";
		return "operator_success";
	}

	private boolean addtoServer() throws IOException {
		for (int i = 0; i < uploadFiles.size(); i++) {
			if (uploadFiles.get(i).length() != 0) {
				String upload = ServletActionContext.getServletContext()
						.getRealPath("/upload");
				String filename = uploadFilesFileName.get(i);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddhhmmss");
				String temp = filename.substring(0, filename.lastIndexOf("."));
				String temp2 = filename.substring(filename.lastIndexOf("."));
				String filepath = temp + simpleDateFormat.format(new Date())
						+ temp2;
			
				File savefile = new File(upload, filepath);
				System.out.println(savefile.toString());
				currentFileName.add(savefile);
				if (i == 0) {
					document.setDoc(savefile.getAbsolutePath());
					currentFileName.remove(0);
				}
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();
				FileUtils.copyFile(uploadFiles.get(i), savefile);
			} else {
				return false;
			}
		}
		return true;
	}

	public String download() {
		DownLoad(document.getDoc());
		return null;
	}

	public void DownLoad(String path) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			response.setContentType("application/x-download;charset=GBK");
			response.setHeader("Content-Disposition",
					"attachment;filename=temp.doc");
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[inputStream.available()];

			inputStream.read(buffer);
			outputStream.write(buffer);

			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toAddDocumentView() {
		System.out.println("sfasf");
		List<WorkFlow> workFlows = workFlowService.getAllWorkFlows("");
		System.out.println(workFlows.size());
		ServletActionContext.getRequest().setAttribute("workFlows", workFlows);
		return "toAddDocumentView";
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	@Resource
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public WorkFlowService getWorkFlowService() {
		return workFlowService;
	}

	@Resource
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		System.out.println(" set  document ");
		this.document = document;
	}

	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}

	/*
	 * public File getUploadFiles() { return uploadFiles; }
	 * 
	 * public void setUploadFiles(File uploadFiles) { this.uploadFiles =
	 * uploadFiles; }
	 * 
	 * public String getUploadFilesContentType() { return
	 * uploadFilesContentType; }
	 * 
	 * public void setUploadFilesContentType(String uploadFilesContentType) {
	 * this.uploadFilesContentType = uploadFilesContentType; }
	 * 
	 * public String getUploadFilesFileName() { return uploadFilesFileName; }
	 * 
	 * public void setUploadFilesFileName(String uploadFilesFileName) {
	 * this.uploadFilesFileName = uploadFilesFileName; }
	 */

	public ApproveHistoryService getApproveHistoryService() {
		return approveHistoryService;
	}

	@Resource
	public void setApproveHistoryService(
			ApproveHistoryService approveHistoryService) {
		this.approveHistoryService = approveHistoryService;
	}

	public String getTransition() {
		return transition;
	}

	public void setTransition(String transition) {
		this.transition = transition;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public int getBack() {
		return back;
	}

	public void setBack(int back) {

		this.back = back;
	}

	public List<File> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<File> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public List<String> getUploadFilesContentType() {
		return uploadFilesContentType;
	}

	public void setUploadFilesContentType(List<String> uploadFilesContentType) {
		this.uploadFilesContentType = uploadFilesContentType;
	}

	public List<String> getUploadFilesFileName() {
		return uploadFilesFileName;
	}

	public void setUploadFilesFileName(List<String> uploadFilesFileName) {
		this.uploadFilesFileName = uploadFilesFileName;
	}

	public List<DocumentProperty> getProps() {
		return props;
	}

	public void setProps(List<DocumentProperty> props) {
		System.out.println(props.toString());
		System.out.println("set list <documentProperty ");
		this.props = props;
		for(DocumentProperty property : props){
			System.err.println(property.toString()+"");
		}
	}
}
