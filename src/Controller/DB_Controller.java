package Controller;

import Model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;;

public class DB_Controller {
	
	static Connection con = null;
	static Statement stmt = null; // 데이터를 전송하는 객체
	
	static String ID = "tester";
	static String PW = "1q2w3e4r!";
	static String ip = "127.0.0.1";				// DB서버 ip주소
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
			 // 한글처리를 위해  이클립스와 데이터베이스 설치시 한글처리를 미리 해주면 코드에서 한글처리 안해도 됩니다.			 
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
            if(rs.next()) {									// id가 있을 때
            //System.out.println(rs.getString("id"));
            	if(password.equals(rs.getString("password")))		// 비밀번호 일치
            		search = true;
            	else
            		search = false;            	
            }else {											// id가 없을 때
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