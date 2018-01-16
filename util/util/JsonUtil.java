package util;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class JsonUtil {

	/**
	 * ����utf-8�ı��룬�����󷵻ص�ǰ̨
	 * @author Administrator
	 * @param resultMap
	 * @param response
	 * @throws Exception
	 * 2017��12��25�� ����11:09:30
	 */
	public static void forwardJson(Object obj,HttpServletResponse response) throws Exception{
		forwardJson(obj,response,"utf-8");
	}
	
	/**
	 * ʹ�����õı��뷽ʽ�������󷵻ص�ǰ̨
	 * @author Administrator
	 * @param resultMap
	 * @param response
	 * @param CharacterEncoding
	 * @throws Exception
	 * 2017��12��25�� ����11:14:18
	 */
	public static void forwardJson(Object obj,HttpServletResponse response,String CharacterEncoding) throws Exception{
		String resultData = getStrJson(obj);
		response.setCharacterEncoding(CharacterEncoding);
		response.getWriter().write(resultData);
		response.getWriter().flush();
	}
	
	/**
	 * ��һ����ת��json�ַ���
	 * @author Administrator
	 * @param obj
	 * @return
	 * @throws Exception
	 * 2017��12��25�� ����11:08:46
	 */
	private static String getStrJson(Object obj) throws Exception{
		return JSONObject.valueToString(obj);
	}
}
