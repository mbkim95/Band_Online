package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.LobbyGUI;

public class LobbyChatClient{
	private Socket socket;	
	private DataOutputStream out;
	private String cmd;
	private String nickname;
	private LobbyGUI gui;
	private Receiver receiver;
	
	public void connect(LobbyGUI gui, String nickname) {
		try {
			socket = new Socket("127.0.0.1", 8001);
			System.out.println("ä�� ���� �����");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("ä�ü����� �г��� ���� �Ϸ�");
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
		
		public void checkCmd(String cmd) {								// �����κ��� ���� ��� üũ
			int chk = Integer.parseInt(cmd.substring(0, 1));			
			String msg;
			
			switch(chk) {					
			case 1:														// ä�� �޽���
				msg = cmd.substring(2, cmd.length());
				lobby.appendMsg(msg);
				break;			
			case 2:														// �ý��� �޽���
				msg = cmd.substring(2, cmd.length());
				lobby.appendSystemMsg(msg);
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
				System.out.println("ä�� Ŭ���̾�Ʈ ����");
				System.exit(0);
			}
		}
	}
	
}
