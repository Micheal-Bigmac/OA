package com.oa.extend;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;

import org.springmodules.workflow.jbpm31.JbpmTemplate;

import com.oa.model.Document;
import com.oa.model.DocumentProperty;

public interface JbpmCore {

	public abstract long addProcessInstance(String processName,
			Document document,List<DocumentProperty> props);

	public abstract void delProcessDefinition(String processName);

	public abstract void delProcessInstance(long processInstanceId);

	public abstract String deployProcessDefinition(byte[] processDef);
	
	public String deployProcessDefinition(String processdefintion) throws FileNotFoundException ;

	public abstract String nextStep(long processInstanceId, String actorId,
			String transitionName);

	//回退
	public abstract Object[] backStep(long processInstanceId, String actorId);

	public abstract List searchMyTaskList(String actorId);

	public abstract List searchNextTransitions(long processInstanceId,
			String actorId);

	public abstract JbpmTemplate getJbpmTemplate();

	@Resource
	public abstract void setJbpmTemplate(JbpmTemplate jbpmTemplate);

}