package Controller;

import Model.Sha256;
import Model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;;

public class DB_Controller {
	
	private static Connection con = null;
	private static Statement stmt = null; // 데이터를 전송하는 객체
	
	private static String ID;
	private static String PW;
	private static String ip;				// DB서버 ip주소
	private static String url;
	
	public DB_Controller(String ip) {
		ID = "tester";
		PW = "1q2w3e4r!";
		this.ip = ip;
		url = "jdbc:mysql://" + ip + ":3306/band?serverTimezone=UTC&useSSL=false";
	}
	
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
		String sql = "INSERT INTO user(id, password, nickname, notification, online) VALUES";
		try {			
			 // 한글처리를 위해  이클립스와 데이터베이스 설치시 한글처리를 미리 해주면 코드에서 한글처리 안해도 됩니다.			 
			sql += "('" + new String(id) + "','"
					+ Sha256.encrypt(new String(passwd)) + "','"
					//+ new String(nickname.getBytes(), "ISO-8859-1") + "', 0, 0);";					
					+ new String(nickname) + "', 0, 0);";
			stmt.executeUpdate(sql);
			success = true;
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		} 
		return success;
	}
	
	public static boolean change(String nick, String password) {
		String sql = "UPDATE user SET password='";
		boolean success = false;
		try {					 
			sql += Sha256.encrypt(new String(password)) + "' where nickname='" + nick + "';";
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
            	if(Sha256.encrypt(password).equals(rs.getString("password")))		// 비밀번호 일치
            		search = true;
            	else
            		search = false;            	
            }else {											// id가 없을 때
            	search = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return search;
	}
	
	public static String getID(String nickname) {
		String id = null;		
		String sql = "select id from user where nickname='";
		
        try {
        	sql += (nickname + "';");               
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
            	id = rs.getString("id");
            	return id;
            }                  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}	
	
	public static void setOnline(String nick) {
		String sql = "UPDATE user SET online='1' where nickname='";
		try {					 
			sql += nick + "';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void setOffline(String nick) {
		String sql = "UPDATE user SET online='0' where nickname='";
		try {					 
			sql += nick + "';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static boolean isOnline(String nick) {
		boolean result = false;		
		String sql = "select online from user where nickname = '";
		
        try {
        	sql += (nick + "';");               
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {									// nickname이 있을 때
            	if(rs.getString("online").equals("1")) 		// 접속 중
            		result = true;
            	else
            		result = false;            	
            }else {											// nickname이 없을 때
            	result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}
	
	public static boolean addFriend(String nick1, String nick2) {
		boolean result = false;
		String sql = "insert into relation (user1, user2) values ('";		
        try {
        	sql += nick1 + "', '" + nick2 + "');";               
            ResultSet rs = stmt.executeQuery(sql);           
           result = true;           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public static ResultSet getFriend(String nick) {
		boolean result = false;
		ResultSet rs = null;
		String sql = "select * from relation where user1 = '";	
        try {
        	sql += nick + "');";               
            rs = stmt.executeQuery(sql);                      
        } catch (Exception e) {
            e.printStackTrace();
        }
		return rs;
		
	}
}