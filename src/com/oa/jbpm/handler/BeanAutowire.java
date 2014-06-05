package com.oa.jbpm.handler;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
@Component
public class BeanAutowire implements BeanFactoryAware{

	private static  BeanFactory beanFactory;

	public BeanAutowire() {

		if (null != beanFactory) {
			((AutowireCapableBeanFactory)beanFactory).autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
		}
	}
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		BeanAutowire.beanFactory=arg0;
	}

}