package hfy.action.init;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TestListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		loadIni(event);
	}
	
	public void loadIni(ServletContextEvent event){
		//获取项目路径
		ServletContext context = event.getServletContext();
		String ctxPath = context.getContextPath();
		context.setAttribute("ctx", ctxPath);
		//获取项目的标题（页面的title属性）
		ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
        String title = rb.getString("title");
        context.setAttribute("title", title);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("关闭项目！");
	}
}
