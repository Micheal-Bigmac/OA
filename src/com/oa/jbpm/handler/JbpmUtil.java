package com.oa.jbpm.handler;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.jbpm.graph.exe.Token;

public class JbpmUtil {

	public static Object getNodeType(Session session, long nodeId) {
		String sql = "SELECT CLASS_ FROM	JBPM_NODE where ID_=?";
		Object result = session.createSQLQuery(sql).setParameter(0, nodeId).uniqueResult();
		return result;
	}

	/**
	 * 递归获取Token中子TOKEN所在节点的名称
	 * 
	 * @param parenToken
	 * @param set
	 * @return
	 */
	public static Set getCurrentNodeNames(Token parenToken, Set set) {
		Iterator<Token> it = parenToken.getChildren().values().iterator();
		while (it.hasNext()) {
			Token currentToken = it.next();
			// 如果还有活动的子节点，说明还未全到达Join节点，需要对所有的节点的所在节点进行记录
			if (currentToken.hasActiveChildren()) {

				getCurrentNodeNames(currentToken, set);

			} else if (!currentToken.hasEnded()) {
				set.add(currentToken.getNode().getName());
			}
		}
		return set;
	}

	public static Set getChildToken(Token parentToken) {
		Iterator<Token> it = parentToken.getChildren().values().iterator();
		return null;
	}

	public static String getTokenName(Token parent, String transitionName) {
		String tokenName = null;
		if (transitionName != null) {
			if (!parent.hasChild(transitionName)) {
				tokenName = transitionName;
			} else {
				int i = 2;
				tokenName = transitionName + Integer.toString(i);
				while (parent.hasChild(tokenName)) {
					i++;
					tokenName = transitionName + Integer.toString(i);
				}
			}
		} else { // no transition name
			int size = (parent.getChildren() != null ? parent.getChildren().size() + 1 : 1);
			tokenName = Integer.toString(size);
		}
		return tokenName;
	}

	public static void rollbackFork(long currentToken,long processInstance,String nodeName,Session session,long nodeId) {
		//将所有任务实例设置成完成状态  0 当今currentToken的ID
		String sql1 = "UPDATE jbpm_taskinstance SET END_ = NOW() ,ISOPEN_=0,ISSIGNALLING_=0 where TOKEN_ in ( SELECT a.ID_ FROM jbpm_token a, jbpm_token b 	WHERE	a.PARENT_ = b.PARENT_ AND b.ID_ = ? ) and ISNULL(END_)";
		//激活fork 节点 1 流程实例ID 2 当前fork节点的名字
		String sql2="UPDATE jbpm_taskinstance SET END_=NULL,ISOPEN_=1,ISSIGNALLING_=1 where PROCINST_=? AND NAME_=?";
		//1 修改token执行 fork上面的节点ID  2 currentToken 的ID
		String sql3="UPDATE jbpm_token a INNER JOIN jbpm_token b SET a.NODE_=? WHERE a.PARENT_=b.PARENT_ and b.ID_=?" ;
		
//		session.beginTransaction();
		session.createSQLQuery(sql1).setParameter(0, currentToken).executeUpdate();
//		session.createSQLQuery(sql2).setParameter(0, processInstance).setParameter(1, nodeName).executeUpdate();
		session.createSQLQuery(sql3).setParameter(0, nodeId).setParameter(1, currentToken).executeUpdate();
//		session.getTransaction().commit();
	}

}
