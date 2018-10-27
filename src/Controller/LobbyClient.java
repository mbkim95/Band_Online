package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.LobbyGUI;

public class LobbyClient {
	private Socket socket;	
	private DataOutputStream out;
	private String cmd;
	private String nickname;
	private LobbyGUI gui;
	private Receiver receiver;
	
	public void connect(LobbyGUI gui, String nickname) {
		try {
			socket = new Socket("127.0.0.1", 7001);
			System.out.println("로비 서버 연결됨");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("로비서버에 닉네임 전송 완료");
			receiver = new Receiver(socket, gui);
			receiver.start();			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if(receiver != null) {
				receiver.in.close();
				receiver.interrupt();
			}
			if(out != null)
				out.close();
			if(socket != null)
				socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			out.writeUTF(msg);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	class Receiver extends Thread {
		private DataInputStream in;
		LobbyGUI lobby;

		public Receiver(Socket socket, LobbyGUI lobby) throws IOException {			
			in = new DataInputStream(socket.getInputStream());
			this.lobby = lobby;
		}
		
		public void checkCmd(String cmd) {								// 서버로부터 받은 명령 체크
			int chk = Integer.parseInt(cmd.substring(0, 1));			
			String msg;
			
			switch(chk) {
			case 1:														// 유저 목록 업데이트
				try {
					lobby.clearUserList(); 					
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						lobby.appendUserList(msg);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:														// 방 목록 업데이트
				try {
					lobby.clearGameRoom();
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						lobby.addGameRoom(msg);
					}
				} catch (IOException e) {
					e.printStackTrace();					
				}
			}				
		}

		public void run() {
			try {
				while(in != null) {
					cmd = in.readUTF();					
					checkCmd(cmd);
				}
			} catch (IOException e) {				
				System.out.println("로비 클라이언트 종료");
				System.exit(0);
			}
		}
	}	
}