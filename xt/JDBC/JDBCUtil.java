package JDBC;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoaderListener;

import hfy.action.init.InitSpring;

@Repository
public class JDBCUtil extends JdbcTemplate {
//    private static String url = null;  
//    private static String name = null;  
//    private static String user = null;  
//    private static String password = null;  
	
    private static Connection conn = null;
//    private Connection conn = DataSourceUtils.getConnection(getDataSource());
    private static PreparedStatement pst = null;  

//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private InitSpring initSpring = null;
    
	public JDBCUtil() {
//		initSpring.test();
//    	try {
//			conn = dataSource.getConnection();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    /**
     * ʵ�������ݿ�����
     */
//    public JDBCUtil() {
//        ResourceBundle rb = ResourceBundle.getBundle("cfg/cfg", Locale.ENGLISH);
//        name = rb.getString("jdbcDriver");
//        url = rb.getString("jdbcUrl");
//        user = rb.getString("jdbcUser");
//        password = rb.getString("jdbcPassword");
//        
//        try {  
//            Class.forName(name);//ָ����������  
//            conn = DriverManager.getConnection(url, user, password);//��ȡ����  
////            Savepoint point = conn.setSavepoint();
////            conn.rollback(point);
////            pst = conn.prepareStatement(sql);//׼��ִ�����  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            try {
//            	if(conn != null) {
//            		conn.close();
//            	}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//        } finally{
//            
//        }
//    }
    
    /**
     * ��ѯsql��䣬���������װ��hashMap������
     * @author John
     * @param sql
     * @return
     * @throws SQLException
     * 2017��10��17�� ����9:29:40
     */
    public Map query(String sql) throws SQLException{
    	if(conn == null) {
    		conn = DataSourceUtils.getConnection(getDataSource());
    	}
        pst = conn.prepareStatement(sql);//׼��ִ�����  
        printSql(sql);
        ResultSet ret = pst.executeQuery();
        if(!ret.next()) {
        	System.out.println("��sql���δ�ܲ�ѯ������");
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
    	if(conn == null) {
    		conn = DataSourceUtils.getConnection(getDataSource());
    	}
    	return null;
    }

    public Bean queryExact(Bean bean) throws SQLException {
    	if(conn == null) {
    		conn = DataSourceUtils.getConnection(getDataSource());
    	}
    	return null;
    }

    
    private void printSql(String sql ) {
    	System.out.println(sql);
    }

	public List listAll(Object obj) {
		if(conn == null) {
    		conn = DataSourceUtils.getConnection(getDataSource());
    	}
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
				 // ���������String
                if (field.getGenericType().toString().equals("class java.lang.String")) { // ���type�������ͣ���ǰ�����"class "�����������
                    // �õ������Ե�gettet����
                    /**
                     * ������Ҫ˵��һ�£����Ǹ���ƴ�յ��ַ�������д��getter������
                     * ��Booleanֵ��ʱ����isXXX��Ĭ��ʹ��ide����getter�Ķ���isXXX��
                     * �������NoSuchMethod�쳣 ��˵�����Ҳ����Ǹ�gettet���� ��Ҫ�����淶
                     */
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
 
                    String val = (String) m.invoke(obj);// ����getter������ȡ����ֵ
                    if (val != null) {
                        //System.out.println("String type:" + val);
                    	if(sql.toString().contains(" where ")) {
                    		sql.append(" and ").append(field.getName()).append(" = ").append(val);
                    	}else {
                    		sql.append(" where  ").append(field.getName()).append(" = ").append(val);
                    	}
                    }
 
                }
 
                // ���������Integer
                if (field.getGenericType().toString().equals("class java.lang.Integer")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Integer type:" + val);
                    }
 
                }
 
                // ���������Double
                if (field.getGenericType().toString().equals("class java.lang.Double")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Double type:" + val);
                    }
 
                }
 
                // ���������Boolean �Ƿ�װ��
                if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
                    Method m = (Method) bean.getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Boolean type:" + val);
                    }
 
                }
 
                // ���������boolean �����������Ͳ�һ�� �����е�˵������������� isXXX�� �Ǿ�ȫ����isXXX��
                // �����Ҳ���getter�ľ�����
                if (field.getGenericType().toString().equals("boolean")) {
                    Method m = (Method) bean.getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(obj);
                    if (val != null) {
                        System.out.println("boolean type:" + val);
                    }
 
                }
                // ���������Date
                if (field.getGenericType().toString().equals("class java.util.Date")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Date type:" + val);
                    }
 
                }
                // ���������Short
                if (field.getGenericType().toString().equals("class java.lang.Short")) {
                    Method m = (Method) bean.getMethod("get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(obj);
                    if (val != null) {
                        System.out.println("Short type:" + val);
                    }
 
                }
                // �������Ҫ�������������Լ�����չ
 
            }//for() --end
		}
		return null;
	}

	//��һ���ַ����ĵ�һ����ĸ��д��Ч������ߵġ�
	private static String getMethodName(String fildeName) throws Exception{
	    byte[] items = fildeName.getBytes();
	    items[0] = (byte) ((char) items[0] - 'a' + 'A');
	    return new String(items);
	}
}
