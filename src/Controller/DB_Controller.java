package Controller;

import Model.Sha256;
import Model.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		String sql = "insert into user(id, password, nickname, notification, online) values";
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
		String sql = "update user set password='";
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
		String sql = "update user set online=1 where nickname='";
		try {					 
			sql += nick + "';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void setOffline(String nick) {
		String sql = "update user set online=0 where nickname='";
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
            	if(rs.getString("online").equals("1")) { 		// 접속 중
            		System.out.println(nick + " 접속중!!!");
            		result = true;
            	}else
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
            stmt.executeUpdate(sql);           
           result = true;           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public static boolean deleteFriend(String nick1, String nick2) {
		boolean result = false;
		String sql = "delete from relation where (user1='";		
        try {
        	sql += (nick1 + "' and user2='" + nick2 + "');");               
            stmt.executeUpdate(sql);           
           result = true;           
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}
	
	public static ResultSet getFriend(String nick) {
		ResultSet rs = null;
		String sql = "select * from relation where user1='";	
        try {
        	sql += nick + "';";               
            rs = stmt.executeQuery(sql);            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return rs;
	}
	
	public static boolean searchFriend(String nick1, String nick2) {
		boolean search = false;
		ResultSet rs = getFriend(nick1);
		String friend;
		try {
			while(rs.next()) {
				friend = rs.getString("user2");
				if(friend.equals(nick2)) {
					search = true;
					return search;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return search;
	}
	
	public static void saveMessage(String receiver, String sender, String date, String time, String contents) {
		boolean success = false;
		String text = contents.replace("\'", "\''").replace("\"", "\\\"");		
		String sql = "insert into message(receiver, sender, date, time, contents, chk) values";
		try {			
			 // 한글처리를 위해  이클립스와 데이터베이스 설치시 한글처리를 미리 해주면 코드에서 한글처리 안해도 됩니다.			 
			sql += "('" + receiver + "','"
					+ sender + "','"										
					+ date + "','"
					+ time + "','"
					+ text + "', 0);";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	public static ResultSet getMessage(String receiver) {
		boolean result = false;
		ResultSet rs = null;
		String sql = "select * from message where receiver='";	
        try {
        	sql += receiver+ "';";               
            rs = stmt.executeQuery(sql);            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return rs;
	}
	
	public void setCheck(String receiver, String sender, String date, String time) {
		String sql = "update message set chk=1 where receiver='";
		try {					 
			sql += receiver + "' and sender='" + sender + "' and date='" + date + "' and time='" + time + "';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getMail(String receiver) {
		int cnt = 0;
		String sql = "select receiver from message where receiver='";
		try {					 
			sql += receiver + "' and chk=0;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				cnt++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return cnt;
	}
	
	public void deleteMail(String receiver, String sender, String date, String time) {
		String sql = "delete from message where receiver='";
		try {					 
			sql += receiver + "' and sender='" + sender + "' and date='" + date + "' and time='" + time + "';";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}