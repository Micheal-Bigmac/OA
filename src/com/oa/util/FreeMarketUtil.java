package com.oa.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.model.DynamicForm;
import com.oa.service.DynamicFormService;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
@Component("freeMarketUtil1")
public class FreeMarketUtil {
	private static Configuration configuration=new Configuration();
	private static DynamicFormService dynamicFormService;
	static{
		configuration.setTemplateLoader(new ClassTemplateLoader(FreeMarketUtil.class,"template"));
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		configuration.setEncoding(Locale.CHINA, "UTF-8");
	}
	public static String TemplateToString(int workflowid){
		System.out.println(workflowid+"|}}}}}}}}}}}}}}"+dynamicFormService);
		DynamicForm form=dynamicFormService.getDynamicFormByWorkFlow(workflowid);
//		System.out.println(form.toString());
		if(form==null){
			return null;
		}
		try {
			Template template = configuration.getTemplate(form.getTemplate());
			
			//最终输出的位置
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
	public DynamicFormService getDynamicFormService() {
		return dynamicFormService;
	}
	@Resource
	public void setDynamicFormService(DynamicFormService dynamicFormService) {
		FreeMarketUtil.dynamicFormService = dynamicFormService;
	}

}
