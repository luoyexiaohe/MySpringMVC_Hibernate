package hfy.action.books;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import hfy.action.init.BaseAction;
import hfy.model.books.BooksFacade;
import util.JsonUtil;

@Controller
@RequestMapping(value="/books/")
public class BooksAction extends BaseAction{
	
	@Autowired
	private BooksFacade facade ; 
	
	@RequestMapping(value="showBook.do")
	public String showBook(HttpServletRequest request ,ModelMap model) throws SQLException{
		Map bookMap = this.facade.getBook();
		model.addAttribute("book", bookMap);
		return "/book/showBook";
	}
	
	@RequestMapping(value="showBook1.do")
	public String showBook1(HttpServletRequest request ,ModelMap model) throws SQLException{
		Map bookMap = this.facade.getBook();
		model.addAttribute("book", bookMap);
		return "/book/showBook";
	}
	
	@RequestMapping(value="addBook.do")
	public void addBook(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			this.facade.addBook();
		}catch(Exception e) {
			System.out.println("异常已抛出");
		}
		String param = request.getParameter("param");
		Map resultMap = new HashMap();
		resultMap.put("param", 123);
		JsonUtil.forwardJson(resultMap, response);
	}
	
	@RequestMapping(value="testBook.do")
	public void TestBook(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map resultMap = new HashMap();
		resultMap.put("name", "胡飞扬");
		resultMap.put("age", "23");
		resultMap.put("adr", "国基生活区9号楼9110宿舍");
		JsonUtil.forwardJson(resultMap, response,"GBK");
	}
	
	
}
