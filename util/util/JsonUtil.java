package util;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class JsonUtil {

	/**
	 * 采用utf-8的编码，将对象返回到前台
	 * @author Administrator
	 * @param resultMap
	 * @param response
	 * @throws Exception
	 * 2017年12月25日 上午11:09:30
	 */
	public static void forwardJson(Object obj,HttpServletResponse response) throws Exception{
		forwardJson(obj,response,"utf-8");
	}
	
	/**
	 * 使用设置的编码方式，将对象返回到前台
	 * @author Administrator
	 * @param resultMap
	 * @param response
	 * @param CharacterEncoding
	 * @throws Exception
	 * 2017年12月25日 上午11:14:18
	 */
	public static void forwardJson(Object obj,HttpServletResponse response,String CharacterEncoding) throws Exception{
		String resultData = getStrJson(obj);
		response.setCharacterEncoding(CharacterEncoding);
		response.getWriter().write(resultData);
		response.getWriter().flush();
	}
	
	/**
	 * 将一个类转成json字符串
	 * @author Administrator
	 * @param obj
	 * @return
	 * @throws Exception
	 * 2017年12月25日 上午11:08:46
	 */
	private static String getStrJson(Object obj) throws Exception{
		return JSONObject.valueToString(obj);
	}
}
