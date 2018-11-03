package Controller;

import Model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;;

public class DB_Controller {
	
	static Connection con = null;
	static Statement stmt = null; // �����͸� �����ϴ� ��ü
	
	static String ID = "tester";
	static String PW = "1q2w3e4r!";
	static String ip = "127.0.0.1";				// DB���� ip�ּ�
	static String url = "jdbc:mysql://" + ip + ":3306/band?serverTimezone=UTC&useSSL=false";
	
	public static void Connect() {
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, ID, PW);
			stmt = (Statement) con.createStatement();			
			System.out.println("Connect Success");
		} catch (Exception e) {
			System.out.println(e);			
		} 
	}
	
	public static void Disconnect() {		
		try {
			if(con != null)
				con.close();
			if(stmt != null)
				stmt.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public static boolean create(UserData user) throws Exception {		
		boolean success = false;		
		String id = user.getId();
		String passwd = user.getPassword();		
		String nickname = user.getNickname();		
		String sql = "INSERT INTO user(id, password, nickname) VALUES";
		try {			
			 // �ѱ�ó���� ����  ��Ŭ������ �����ͺ��̽� ��ġ�� �ѱ�ó���� �̸� ���ָ� �ڵ忡�� �ѱ�ó�� ���ص� �˴ϴ�.			 
			sql += "('" + new String(id) + "','"
					+ new String(passwd) + "','"
					//+ new String(nickname.getBytes(), "ISO-8859-1") + "', 0, 0);";					
					+ new String(nickname) + "');";
			stmt.executeUpdate(sql);
			success = true;
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		} 
		return success;
	}
	
	public static boolean searchID(String id) {		
		boolean search = true;		
		String sql = "select id from user where id='";
		
        try {
        	sql += (id + "';");               
            ResultSet rs = stmt.executeQuery(sql);           
            if(!rs.isBeforeFirst())
            	search = false;      
        } catch (Exception e) {
            e.printStackTrace();
        }
        return search;
	}
	
	public static boolean searchNick(String nick) {
		boolean search = true;		
		String sql = "select nickname from user where nickname='";
		
        try {
        	sql += (nick + "';");               
            ResultSet rs = stmt.executeQuery(sql);            
            if(!rs.isBeforeFirst())
            	search = false;      
        } catch (Exception e) {           
            e.printStackTrace();
        }
        return search;
	}
	
	public static boolean Password_Chk(String id, String password) {
		boolean search = false;		
		String sql = "select id, password from user where id = '";
		
        try {
        	sql += (id + "';");               
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {									// id�� ���� ��
            //System.out.println(rs.getString("id"));
            	if(password.equals(rs.getString("password")))		// ��й�ȣ ��ġ
            		search = true;
            	else
            		search = false;            	
            }else {											// id�� ���� ��
            	search = false;
            }
            //if(rs.getString("id").equals(id) && rs.getString("password").equals(password))
            	//search = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return search;
	}
	
	public static UserData getUser(String id) {
		String sql = "select * from user where id = '";		
        try {
        	sql += (id + "';");               
            ResultSet rs = stmt.executeQuery(sql);
            UserData user = new UserData();
            if(rs.next()) {
            	user.setId(id);            
            	user.setPassword(rs.getString("password"));
            	user.setnickName(rs.getString("nickname"));           	
            	return user;
            }else {
            	return null;
            }
            //if(rs.getString("id").equals(id) && rs.getString("password").equals(password))
            	//search = true;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}	
}