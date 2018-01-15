package com.hfy.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitCtx implements ServletContextListener {

	/**
	 * 关闭项目时运行的代码
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		
	}

	/**
	 * 开启项目时运行的代码//将ctx系统变量放入系统中
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		String ctx = servletContext.getContextPath();
		servletContext.setAttribute("ctx", ctx);
	}

}
