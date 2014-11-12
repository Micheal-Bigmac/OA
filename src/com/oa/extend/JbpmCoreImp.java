package com.oa.extend;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.stereotype.Component;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

import com.oa.model.Document;
import com.oa.model.DocumentProperty;
import com.oa.util.Constant;

@Component("jbpmCore")
public class JbpmCoreImp implements JbpmCore {
	private JbpmTemplate jbpmTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#addProcessInstance(java.lang.String,
	 * com.oa.model.Document)
	 */
	public long addProcessInstance(String processName, Document document, List<DocumentProperty> props) {

		System.out.println("addprocessinstance 流成名字" + processName);
		JbpmContext context = getJbpmContext();

		ProcessDefinition def = context.getGraphSession().findLatestProcessDefinition(processName);
		System.out.println(def + "sfsdfsd");
		ProcessInstance instance = new ProcessInstance(def);

		instance.getContextInstance().setVariable("document", document.getId());

		// 将公文标题也提交到流程实例变量中，以便在E-Mail中能够提示这个公文的名称
		instance.getContextInstance().setVariable("docTitle", document.getTitle());

		// 将公文的相关属性设置进入流程实例变量 ============================没有出去空的选项
		if (props != null) {
			for (DocumentProperty property : props) {
				if (!property.isNull()) {
					String propertyname = property.getPropertyName();
					Object value = property.getValue();
					System.out.println(property.getPropertyName());
					System.out.println(property.getValue());
					instance.getContextInstance().setVariable(propertyname, value);
				}
			}
		}
		// Set props=document
		// Map props = document.getProperties();
		// if(props != null){
		// Set entries = props.entrySet();
		// for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
		// Map.Entry entry = (Map.Entry) iterator.next();
		// String propertyName = (String)entry.getKey();
		// Object obj = document.getProperty(propertyName);
		// //将变量放入流程实例变量
		// instance.getContextInstance().setVariable(propertyName, obj);
		// }
		// }

		context.save(instance);
		context.close();
		return instance.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#delProcessDefinition(java.lang.String)
	 */
	public void delProcessDefinition(String processName) {
		JbpmContext context = getJbpmContext();
		List defs = context.getGraphSession().findAllProcessDefinitionVersions(processName);
		for (Iterator iterator = defs.iterator(); iterator.hasNext();) {
			ProcessDefinition def = (ProcessDefinition) iterator.next();
			context.getGraphSession().deleteProcessDefinition(def);
		}
		context.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#delProcessInstance(long)
	 */
	public void delProcessInstance(long processInstanceId) {
		JbpmContext context = getJbpmContext();
		context.getGraphSession().deleteProcessInstance(processInstanceId);
		context.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#deployProcessDefinition(byte[])
	 */
	public String deployProcessDefinition(byte[] processDef) {
		JbpmContext context = getJbpmContext();
		ProcessDefinition def = ProcessDefinition.parseXmlInputStream(new ByteArrayInputStream(processDef));
		context.deployProcessDefinition(def);
		context.close();
		return def.getName();
	}

	public String deployProcessDefinition(String processdefintion) throws FileNotFoundException {
		System.err.println("jbpmCoreImp");
		JbpmContext context = getJbpmContext();
		ProcessDefinition processDefinition = ProcessDefinition.parseXmlInputStream(new FileInputStream(new File(processdefintion)));
		if (context == null) {
			System.out.println(" context is null");
		}
		context.deployProcessDefinition(processDefinition);
		String name = processDefinition.getName();
		context.close();
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#nextStep(long, java.lang.String,
	 * java.lang.String)
	 */
	public String nextStep(long processInstanceId, String actorId, String transitionName) {

		JbpmContext context = getJbpmContext();
		ProcessInstance instance = context.getProcessInstance(processInstanceId);

		// 当前节点
		String currentNodeName = instance.getRootToken().getNode().getName();

		// 起点的名称
		String startNodeName = instance.getProcessDefinition().getStartState().getName();

		// 如果是在起点
		if (startNodeName.equals(currentNodeName)) {
			if (transitionName == null) {
				instance.signal();
			} else {
				instance.signal(transitionName);
			}
		} else {
			List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
			for (Iterator iterator = taskInstances.iterator(); iterator.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if (ti.getProcessInstance().getId() == processInstanceId) {
					if (transitionName == null) {
						ti.end();
					} else {
						if(transitionName.matches(Constant.rollbackRegex)){
							Collection<TaskInstance> taskinstances = ti.getTaskMgmtInstance().getUnfinishedTasks(ti.getToken());
							for (TaskInstance taskInstance : taskinstances) {
								taskInstance.end(transitionName);
							}
						}else {
							ti.end(transitionName);
						}
					/*	if (transitionName.equals("回退")) {
							Collection<TaskInstance> taskinstances = ti.getTaskMgmtInstance().getUnfinishedTasks(ti.getToken());
							for (TaskInstance taskInstance : taskinstances) {
								taskInstance.end("回退");
							}
						} else {
							ti.end(transitionName);
						}*/
					}
					break;
				}
			}

			// 查找所属组的任务实例
			List pooledTaskInstances = context.getTaskMgmtSession().findPooledTaskInstances(actorId);
			for (Iterator iterator = pooledTaskInstances.iterator(); iterator.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if (ti.getProcessInstance().getId() == processInstanceId) {
					if (transitionName == null) {
						ti.end();
					} else {
						ti.end(transitionName);
					}
					break;
				}
			}
		}
		String name = instance.getRootToken().getNode().getName();
		// 返回转向之后的节点名称
		context.close();
		return name;
	}

	// 回退
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#backStep(long, java.lang.String)
	 */
	public Object[] backStep(long processInstanceId, String actorId) {
		JbpmContext context = getJbpmContext();

		// 根据流程实例标识查找流程实例
		ProcessInstance instance = context.getProcessInstance(processInstanceId);
		Object[] os = new Object[2];
		// 搜索用户对应的所有的任务实例
		List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
		for (Iterator iterator = taskInstances.iterator(); iterator.hasNext();) {
			TaskInstance ti = (TaskInstance) iterator.next();
			System.out.println("outter " + ti.getName());
			if (ti.getProcessInstance().getId() == processInstanceId) {
				System.out.println("equals+++++++++++++++++");
				// 先判断是否是要回退到起点

				Set set = ti.getToken().getNode().getArrivingTransitions();
				// Set set = (Set)
				// ti.getToken().getNode().getLeavingTransitions();
				for (Iterator iterator2 = set.iterator(); iterator2.hasNext();) {
					Transition t = (Transition) iterator2.next();
					System.out.println("inner transition" + t.getName());
					// 如果它需要回退到起点
					if (t.getFrom().equals(ti.getProcessInstance().getProcessDefinition().getStartState())) {
						System.out.println("inner node equals");
						int docId = (Integer) ti.getProcessInstance().getContextInstance().getVariable("document");
						// 结束当前的流程实例
						ti.getProcessInstance().end();
						// 结束当前任务实例
						ti.end();

						// 重新创建流程实例对象
						ProcessInstance pi = new ProcessInstance(ti.getProcessInstance().getProcessDefinition());
						pi.getContextInstance().setVariable("document", docId);
						// 将流程实例对象重新持久化到数据库
						context.save(pi);

						os[0] = Document.New;
						os[1] = pi.getId();
						context.close();
						return os;
					}
				}

				System.out.println("===------------=====hewr");
				// 如果不需要回退到起点
				System.out.println("back====");
				Collection<TaskInstance> taskinstances = ti.getTaskMgmtInstance().getUnfinishedTasks(ti.getToken());
				for (TaskInstance taskInstance : taskinstances) {
					
					taskInstance.end("回退");
				}
				// ti.end("回退");
				break;
			}
		}

		os[0] = instance.getRootToken().getNode().getName();
		os[1] = processInstanceId;
		context.close();
		return os;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#searchMyTaskList(java.lang.String)
	 */
	/*
	 * public List searchMyTaskList(String actorId) {
	 * 
	 * JbpmContext context = getJbpmContext(); List docIds = new ArrayList();
	 * List taskInstances =
	 * context.getTaskMgmtSession().findTaskInstances(actorId); for (Iterator
	 * iterator = taskInstances.iterator(); iterator.hasNext();) { TaskInstance
	 * ti = (TaskInstance) iterator.next(); Object docId =
	 * ti.getProcessInstance().getContextInstance().getVariable("document");
	 * docIds.add(docId); }
	 * 
	 * //查找所属组的任务实例 List pooledTaskInstances =
	 * context.getTaskMgmtSession().findPooledTaskInstances(actorId); for
	 * (Iterator iterator = pooledTaskInstances.iterator(); iterator
	 * .hasNext();) { TaskInstance ti = (TaskInstance) iterator.next(); Integer
	 * docId =
	 * (Integer)ti.getProcessInstance().getContextInstance().getVariable(
	 * "document"); System.out.println(docId+
	 * " jbpmCoreIMp searchMyTaskList findPooledTaskInstances");
	 * docIds.add(docId); } context.close(); return docIds; }
	 */
	public List searchMyTaskList(String actorId) {

		JbpmContext context = getJbpmContext();
		List docIds = new ArrayList();
		List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
		for (Iterator iterator = taskInstances.iterator(); iterator.hasNext();) {
			TaskInstance ti = (TaskInstance) iterator.next();
			Integer docId = (Integer) ti.getProcessInstance().getContextInstance().getVariable("document");
			docIds.add(docId);
		}

		// 查找所属组的任务实例
		List pooledTaskInstances = context.getTaskMgmtSession().findPooledTaskInstances(actorId);
		for (Iterator iterator = pooledTaskInstances.iterator(); iterator.hasNext();) {
			TaskInstance ti = (TaskInstance) iterator.next();
			Integer docId = (Integer) ti.getProcessInstance().getContextInstance().getVariable("document");
			docIds.add(docId);
		}
		context.close();
		return docIds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#searchNextTransitions(long, java.lang.String)
	 */
	public List searchNextTransitions(long processInstanceId, String actorId) {
		JbpmContext context = getJbpmContext();
		ProcessInstance instance = context.getProcessInstance(processInstanceId);
		System.out.println("JbpmCoreImp searchNextTransitions  " + processInstanceId);

		// 当前节点
		String currentNodeName = instance.getRootToken().getNode().getName();

		// 起点的名称
		String startNodeName = instance.getProcessDefinition().getStartState().getName();

		Collection transitions = null;

		// 如果是在起点
		if (startNodeName.equals(currentNodeName)) {
			transitions = instance.getRootToken().getAvailableTransitions();
		} else {
			List taskInstances = context.getTaskMgmtSession().findTaskInstances(actorId);
			for (Iterator iterator = taskInstances.iterator(); iterator.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if (ti.getProcessInstance().getId() == processInstanceId) {
					transitions = ti.getAvailableTransitions();
					break;
				}
			}

			// 查找所属组的任务实例
			List pooledTaskInstances = context.getTaskMgmtSession().findPooledTaskInstances(actorId);
			for (Iterator iterator = pooledTaskInstances.iterator(); iterator.hasNext();) {
				TaskInstance ti = (TaskInstance) iterator.next();
				if (ti.getProcessInstance().getId() == processInstanceId) {
					transitions = ti.getAvailableTransitions();
				}
			}
		}

		List transitionNames = new ArrayList();

		if (transitions != null) {
			// 为了不把Transition对象直接暴露给OA系统，需要将其转换为名称列表
			for (Iterator iterator = transitions.iterator(); iterator.hasNext();) {
				Transition transition = (Transition) iterator.next();
				transitionNames.add(transition.getName());
			}
		}
		context.close();
		return transitionNames;
	}

	private JbpmContext getJbpmContext() {
		JbpmContext context = jbpmTemplate.getJbpmConfiguration().createJbpmContext();
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.oa.extend.JbpmCore#getJbpmTemplate()
	 */
	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.oa.extend.JbpmCore#setJbpmTemplate(org.springmodules.workflow.jbpm31
	 * .JbpmTemplate)
	 */
	@Resource
	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}
}
