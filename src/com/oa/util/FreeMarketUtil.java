package com.oa.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.oa.listenner.Persistence;
import com.oa.model.DocumentProperty;
import com.oa.model.DynamicForm;
import com.oa.service.DynamicFormService;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Component("freeMarketUtil1")
public class FreeMarketUtil {
	private static Configuration configuration = new Configuration();
	private static DynamicFormService dynamicFormService;
	static {
		configuration.setTemplateLoader(new ClassTemplateLoader(FreeMarketUtil.class, "template"));
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		configuration.setEncoding(Locale.CHINA, "UTF-8");
	}

	public static String TemplateToString(int workflowid) {
		DynamicForm form = dynamicFormService.getDynamicFormByWorkFlow(workflowid);
		if (form == null) {
			return null;
		}
		try {
			Template template = configuration.getTemplate(form.getTemplate());

			// 最终输出的位置
			Writer out = new StringWriter();

			Map rootMap = new HashMap();
			rootMap.put("form", form);

			template.process(rootMap, out);

			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String TemplateToString2(int workflowid, String type) {
		DynamicForm form = dynamicFormService.getDynamicFormByWorkFlow(workflowid);
		List<DocumentProperty> props = null;
		String[] key = type.split("\\|");
		if ("properties".equals(key[1])) {
			props = (List<DocumentProperty>) Persistence.getVariable(key[0]);
		}
		if (form == null) {
			return null;
		}
		try {
			Template template = configuration.getTemplate(form.getTemplate());

			// 最终输出的位置
			Writer out = new StringWriter();

			Map rootMap = new HashMap();
			rootMap.put("form", form);
			if (props != null) {
				rootMap.put("properties", props);
			}

			template.process(rootMap, out);

			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DynamicFormService getDynamicFormService() {
		return dynamicFormService;
	}

	@Resource
	public void setDynamicFormService(DynamicFormService dynamicFormService) {
		FreeMarketUtil.dynamicFormService = dynamicFormService;
	}

}
