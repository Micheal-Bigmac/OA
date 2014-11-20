package com.oa.listenner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class persintenceListenner implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		Persistence.saveToFile();
	}

	public void contextInitialized(ServletContextEvent arg0) {
	}

}
