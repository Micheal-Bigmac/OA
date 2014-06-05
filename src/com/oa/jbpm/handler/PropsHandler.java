package com.oa.jbpm.handler;

import java.util.Map;
import java.util.Map.Entry;


import org.hibernate.Session;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;


public class PropsHandler implements ActionHandler {

	public void execute(ExecutionContext arg0) throws Exception {
		/* ContextInstance instance=arg0.getContextInstance();
		Integer docId= (Integer) instance.getVariable("document");
		System.out.println("document ActionHandler "+ docId);
//		Session session=instance.get
		if(docId!=null){
			instance.getVariables();
			Map<String ,String > map=instance.getVariables();
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}*/
		Session session=arg0.getJbpmContext().getSession();
		/*ContextInstance instance=arg0.getContextInstance();
		Integer docId= (Integer) instance.getVariable("document");
		if(docId!=null){
			instance.getVariables();
			Map<String ,Object> map=instance.getVariables();
			for (Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}*/
	}

}
