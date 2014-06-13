package com.oa.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.oa.model.WorkFlow;
import com.oa.service.WorkFlowService;
import com.opensymphony.xwork2.ActionSupport;

public class WorkFlowAction extends ActionSupport {
	private WorkFlowService workFlowService;
	private WorkFlow workFlow;
	private List<File> uploadFiles;
	private List<String> uploadFilesContentType;
	private List<String> uploadFilesFileName;
	private int index;
	private String returns;
	private List<File> currentFileName = new ArrayList<File>();

	public String listWorkFlow() {
		String hql = "";
		List<WorkFlow> workFlows = workFlowService.getPageWorkFlows(
				(index == 0 ? 1 : index), hql);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("listObject", workFlows);
		request.setAttribute("currentIndex", (index == 0 ? 1 : index));
		int total = workFlowService.getAllWorkFlows(hql).size();
		request.setAttribute("totalSize", total);
		request.setAttribute("url", "WorkFlowAction!listWorkFlow?");
		return "listWorkFlow";
	}

	public String deleteWorkFlow(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String ids[]=request.getParameterValues("delid");
		for(int i=0; i<ids.length;i++){
			System.out.println(ids[i]);
		}
		returns = "WorkFlowAction!listWorkFlow";
		workFlowService.deleteWorkFLow(ids);
		return null;
	}

	public String addWorkFlow() {
		System.out.println("here=====");
		// System.out.println(workFlow.getId()+" add workflow");
		if (workFlow.getId() == null) {
			System.out.println("WorkFlowAction addWorkFlow() id  is null");
			boolean flag = false;
			try {
				for (int i = 0; i < uploadFiles.size(); i++) {
					flag = addtoServer(i);
					if (flag == false) {
						break;
					}
				}
				if(currentFileName.size()==2){
					workFlowService.add(currentFileName.get(0).getAbsolutePath(),currentFileName.get(1).getAbsolutePath());
//					workFlowService.addOrUpdateWorkflow(currentFileName.get(0).getAbsolutePath(),currentFileName.get(1).getAbsolutePath());
				}else if(currentFileName.size()==1){
					workFlowService.add(currentFileName.get(0).getAbsolutePath(), "");
//					workFlowService.addOrUpdateWorkflow(currentFileName.get(0).getAbsolutePath(), "");
				}
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
			returns = "";
			return flag == false ? "operator_failure" : "operator_success";
		} else {
			System.out.println("WorkFlowAction addWorkFlow() id is not null"+workFlow.getId());
			boolean flag = false;
			int size=uploadFiles.size();
			try {
				for (int i = 0; i < size; i++) {
					flag = addtoServer(i);
					if (flag == false) {
						break;
					}
				}
				if(currentFileName.size()==2){
					workFlowService.updateWorkFlow(currentFileName.get(0).getAbsolutePath(),currentFileName.get(1).getAbsolutePath(),workFlow.getId());
				}else if(currentFileName.size()==1){
					workFlowService.updateWorkFlow(currentFileName.get(0).getAbsolutePath(), "",workFlow.getId());
				}
			} catch (IOException e) {
				flag = false;
			}
			returns = "";
			return flag == false ? "operator_failure" :"operator_success" ;
		}
	}



	private boolean addtoServer(int i) throws IOException {
		if (uploadFiles.get(i).length() != 0) {
			String upload = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			String filename = uploadFilesFileName.get(i);
			System.err.println(upload);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyyMMddhhmmss");
			String temp = filename.substring(0, filename.lastIndexOf("."));
			String temp2 = filename.substring(filename.lastIndexOf("."));
			String filepath = temp + simpleDateFormat.format(new Date())
					+ temp2;
			System.err.println(filepath);
			File savefile = new File(upload, filepath);
			currentFileName.add(savefile);
			System.out.println(savefile.toString());
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(uploadFiles.get(i), savefile);
			return true;
		}
		return false;
		// FileUtils.copyFile(image, savefile);
		// ActionContext.getContext().put("message", "文件上传成功");
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

	public WorkFlow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
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

	public String getReturns() {
		return returns;
	}

	public void setReturns(String returns) {
		this.returns = returns;
	}
}
