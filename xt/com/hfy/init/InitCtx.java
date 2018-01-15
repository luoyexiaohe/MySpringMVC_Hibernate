package com.hfy.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitCtx implements ServletContextListener {

	/**
	 * �ر���Ŀʱ���еĴ���
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		
	}

	/**
	 * ������Ŀʱ���еĴ���//��ctxϵͳ��������ϵͳ��
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		String ctx = servletContext.getContextPath();
		servletContext.setAttribute("ctx", ctx);
	}

}
