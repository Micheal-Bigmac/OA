package com.oa.jbpm.handler;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.Token;
import org.jbpm.graph.node.EndState;
import org.jbpm.graph.node.Fork;
import org.jbpm.graph.node.Join;
import org.jbpm.graph.node.TaskNode;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oa.util.Constant;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class RollbackTransitionAction implements ActionHandler {

	private Logger logger = LoggerFactory.getLogger(RollbackTransitionAction.class);

	TaskMgmtInstance getTaskMgmtInstance(Token token) {
		return (TaskMgmtInstance) token.getProcessInstance().getInstance(TaskMgmtInstance.class);
	}

	public void execute(ExecutionContext executionContext) throws Exception {
		Token rootToken = executionContext.getProcessInstance().getRootToken();
		Token currentToken = executionContext.getToken();
		logger.debug("进入Action");
		String forkName = (String) executionContext.getContextInstance().getVariable("forkName" + currentToken.getId());
		String preNodeName = (String) executionContext.getContextInstance().getVariable("preNodeName" + currentToken.getId());
		Session session = executionContext.getJbpmContext().getSession();
		TaskNode taskNode = (TaskNode) executionContext.getNode();
//		executionContext.getProcessInstance().getRootToken().getId()
		Set transitionSet=currentToken.getAvailableTransitions();
		for (Iterator iterator = transitionSet.iterator(); iterator.hasNext();) {
			Transition t = (Transition) iterator.next();
			Node fromNode = t.getTo();
			String forkname = fromNode.toString();
			if (forkname.matches(Constant.forkRegex)) {
//				executionContext.getProcessInstance().getContextInstance().getVariable("currentNode");
//				executionContext.getProcessInstance().getContextInstance().setVariable("currentNode", taskNode.getName());
				JbpmUtil.rollbackFork(currentToken.getId(), executionContext.getProcessInstance().getId(), taskNode.getName(), session,taskNode.getId());
			}
		}
		

		boolean isForkOrJoin = false;
		logger.info("流程定义节点Token" + rootToken == null ? "null" : rootToken + "==");
		logger.info("当前节点Token" + currentToken);
		logger.info("当前节点父亲Token" + currentToken.getParent());
		logger.info(executionContext.toString());

		// 如果当前节点是在结束节点，则无需创建任何返回Transition
		if (executionContext.getNode() instanceof EndState) {
			return;
		}

		// 到达该节点的transition
		Set<Transition> arrayingTransition = taskNode.getArrivingTransitions();
		// 该节点要离开的路由
		List<Transition> leavingTransitions = taskNode.getLeavingTransitions();

		// 防止流程流向成为一个蜘蛛网
		if (arrayingTransition.size() < 2) {
			boolean ignore = false;
			boolean isReturn = false;

			// 得到当前指向的节点所有可以使用的transition对象列表
			Set ts = executionContext.getToken().getAvailableTransitions();

			for (Iterator iterator = ts.iterator(); iterator.hasNext();) {
				Transition t = (Transition) iterator.next();
				String transitionName = t.getName();
				if (transitionName.matches(Constant.rollbackRegex)) {
					ignore = true;
					executionContext.getContextInstance().setVariable("forkName" + currentToken.getId(), taskNode.getName());
					// break;
				}
				Node fromNode = t.getTo();
				String forkname = fromNode.toString();
				logger.info("当前节点来自" + fromNode.getName());
				logger.info("当前节点指向下一个节点为" + t.getTo().getName());

				if (forkname.matches(Constant.forkRegex)) {
					List<Transition> forkTransitions = fromNode.getLeavingTransitions();
					// forkTransitions.get(0).getTo()
					for (Transition tra : forkTransitions) {
						// tra.getFrom();
						Node childNameNode = tra.getTo();

						Transition transition = new Transition();
						transition.setName(Constant.rollback + executionContext.getNode().getName());

						childNameNode.addLeavingTransition(transition);
						executionContext.getNode().addArrivingTransition(transition);
					}
					executionContext.getContextInstance().setVariable("preNodeName" + currentToken.getId(), executionContext.getNode().getName());
					isReturn = true;
				}

			}
			if (isReturn)
				return;

			// 如果尚未创建回退Transition对象，就可以继续创建
			if (!ignore) {
				if (rootToken.getId() != currentToken.getId()) {
					// isForkOrJoin = true;
					String nodeName = (String) executionContext.getContextInstance().getVariable("forkName" + currentToken.getId());
					// String nodeName = (String)
					// executionContext.getContextInstance().getVariable(temp1);
					preNodeName = nodeName;
				}
				if (preNodeName != null) { // 前一个节点非空，需要创建回退Transition
					// 从当前节点
					Node from = executionContext.getNode();

					Node to = null;

					to = executionContext.getProcessDefinition().getNode(preNodeName);

					// 创建回退Transition对象
					Transition transition = new Transition();
					transition.setName(Constant.rollback + preNodeName);

					from.addLeavingTransition(transition);
					to.addArrivingTransition(transition);
				}
				if (rootToken.getId() == currentToken.getId()) {
					executionContext.getContextInstance().setVariable("preNodeName" + currentToken.getId(), executionContext.getNode().getName());

				} else {
					executionContext.getContextInstance().setVariable("forkName" + currentToken.getId(), taskNode.getName());
				}
			}

		}

	}
}
