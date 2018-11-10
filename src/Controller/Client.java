package Controller;

import Model.*;
import View.*;

public class Client {	
	public static void main(String[] args) {
		LoginGUI login = new LoginGUI("127.0.0.1");					//  접속할 서버 ip 입력
 		login.open();
	}
}