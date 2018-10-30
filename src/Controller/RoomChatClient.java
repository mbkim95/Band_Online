package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.BandGUI;
import View.LobbyGUI;

public class RoomChatClient{
	private int num;
	private Socket socket;	
	private DataOutputStream out;
	private String cmd;
	private String nickname;
	private BandGUI gui;
	private Receiver receiver;
	
	public RoomChatClient(int num) {
		this.num = num;
	}
	
	public void connect(BandGUI gui, String nickname) {
		try {
			socket = new Socket("127.0.0.1", 8777+num);
			System.out.println("합주실 채팅 서버 연결됨 (포트 : " + (8777+num) + ")");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("채팅서버에 닉네임 전송 완료");
			receiver = new Receiver(socket, gui);
			receiver.start();			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if(receiver != null) {
				this.receiver.in.close();
				this.receiver.interrupt();
			}
			if(out != null)
				this.out.close();
			if(socket != null)
				this.socket.close();
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
			
			switch(chk) {					
			case 1:														// 채팅 메시지
				msg = cmd.substring(2, cmd.length());
				band.appendMsg(msg);
				break;			
			case 2:														// 시스템 메시지
				msg = cmd.substring(2, cmd.length());
				band.appendSystemMsg(msg);
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
				System.out.println("합주실 채팅 클라이언트 종료");
				//System.exit(0);
			}
		}
	}
	
}
