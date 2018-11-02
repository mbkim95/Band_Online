package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.BandGUI;
import View.LobbyGUI;

public class RoomClient {
	private Socket socket;	
	private DataOutputStream out;
	private String cmd;
	private int port;
	private String nickname;
	private BandGUI gui;
	private Receiver receiver;
	
	public RoomClient(int port) {
		this.port = port;
	}
	
	public void connect(BandGUI gui, String nickname) {
		try {
			socket = new Socket("127.0.0.1", 7777 + port);
			System.out.println("합주실 서버 연결됨 (포트 : " + (7777 + port) + ")");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("합주실서버에 닉네임 전송 완료");
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
		BandGUI band;

		public Receiver(Socket socket, BandGUI band) throws IOException {			
			in = new DataInputStream(socket.getInputStream());
			this.band = band;
		}
		
		public void checkCmd(String cmd) {								// 서버로부터 받은 명령 체크
			int chk = Integer.parseInt(cmd.substring(0, 1));			
			String msg;
			System.out.println(nickname + "'s cmd : " + cmd);
			
			switch(chk) {
			case 1:														// 유저 목록 업데이트
				try {
					band.clearUserList(); 					
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						band.appendUserList(msg);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:														// 이미지  업데이트
				try {
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						int select = Integer.parseInt(msg);
						band.changeImage(select);						
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:														// 로비에서 방 삭제
				String title = cmd.substring(2, cmd.length());
				band.deleteRoom();
				break;
			}				
		}

		public void run() {
			try {
				while(in != null) {
					cmd = in.readUTF();					
					checkCmd(cmd);
				}
			} catch (IOException e) {				
				System.out.println("합주실 클라이언트 종료");
				//System.exit(0);
			}
		}
	}	
}