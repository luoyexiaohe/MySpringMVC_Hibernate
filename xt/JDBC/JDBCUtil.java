package JDBC;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCUtil {
    private static String url = null;  
    private static String name = null;  
    private static String user = null;  
    private static String password = null;  
  
    private static Connection conn = null;  
    private static PreparedStatement pst = null;  
    
    /**
     * 实例化数据库连接
     */
    public JDBCUtil() {
        ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
        name = rb.getString("jdbcDriver");
        url = rb.getString("jdbcUrl");
        user = rb.getString("jdbcUser");
        password = rb.getString("jdbcPassword");
        
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
//            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
            
        }
    }
    
    /**
     * 查询sql语句，将结果集封装到hashMap对象中
     * @author John
     * @param sql
     * @return
     * @throws SQLException
     * 2017年10月17日 上午9:29:40
     */
    public Map query(String sql) throws SQLException{
        pst = conn.prepareStatement(sql);//准备执行语句  
        printSql(sql);
        ResultSet ret = pst.executeQuery();
        if(!ret.next()) {
        	System.out.println("该sql语句未能查询到数据");
        	return null;
        }
        Map resultMap = new HashMap();
        int count = pst.getMetaData().getColumnCount();
        for (int i = 1; i <= count; i++) {
            String key = pst.getMetaData().getColumnName(i);
            Object val = ret.getObject(i);
            resultMap.put(key, val);
        }
        return resultMap;
    }
    
    public List<Bean> query(Bean bean) throws SQLException {
    	return null;
    }

    public Bean queryExact(Bean bean) throws SQLException {
    	return null;
    }
    

    public void update(String sql) throws Exception{
    	 pst = conn.prepareStatement(sql);//准备执行语句  
    	 printSql(sql);
    	 pst.executeUpdate();
    }
    
    private void printSql(String sql ) {
    	System.out.println(sql);
    }

	public List listAll(Object obj) {
		Class bean = obj.getClass();
		String tableName = bean.getName();
		String sql = "select * from " + tableName;
		return null;
	}
	
	public List listExact(Object obj) throws Exception {
		Class bean = obj.getClass();
		String tableName = bean.getName();
		StringBuffer sql = new StringBuffer("select * from ").append(tableName);
		Field[] arrField = bean.getDeclaredFields();
		if(arrField != null ) {
			for(int i=0;i<arrField.length;i++) {
				Field field = arrField[i];
				 // 如果类型是String
                if (field.getGenericType().toString().equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                     */
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
 
                    String val = (String) m.invoke(obj);// 调用getter方法获取属性值
                    if (val != null) {
                        //System.out.println("String type:" + val);
                    	if(sql.toString().contains(" where ")) {
                    		sql.append(" and ").append(field.getName()).append(" = ").append(val);
                    	}else {
                    		sql.append(" where  ").append(field.getName()).append(" = ").append(val);
                    	}
                    }
 
                }
 
                // 如果类型是Integer
                if (field.getGenericType().toString().equals("class java.lang.Integer")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Integer type:" + val);
                    }
 
                }
 
                // 如果类型是Double
                if (field.getGenericType().toString().equals("class java.lang.Double")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Double type:" + val);
                    }
 
                }
 
                // 如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
                    Method m = (Method) bean.getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Boolean type:" + val);
                    }
 
                }
 
                // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
                // 反射找不到getter的具体名
                if (field.getGenericType().toString().equals("boolean")) {
                    Method m = (Method) bean.getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(obj);
                    if (val != null) {
                        System.out.println("boolean type:" + val);
                    }
 
                }
                // 如果类型是Date
                if (field.getGenericType().toString().equals("class java.util.Date")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Date type:" + val);
                    }
 
                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals("class java.lang.Short")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Short type:" + val);
                    }
 
                }
                // 如果还需要其他的类型请自己做扩展
 
            }//for() --end
		}
		return null;
	}

	//把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception{
	    byte[] items = fildeName.getBytes();
	    items[0] = (byte) ((char) items[0] - 'a' + 'A');
	    return new String(items);
	}
}
