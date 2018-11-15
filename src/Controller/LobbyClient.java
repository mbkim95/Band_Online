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
	
	public void connect(String ip, LobbyGUI gui, String nickname) {
		try {
			socket = new Socket(ip, 7001);
			System.out.println("�κ� ���� �����");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("�κ񼭹��� �г��� ���� �Ϸ�");
			receiver = new Receiver(socket, gui);
			receiver.start();			
		}catch(IOException e) {
//			e.printStackTrace();
			System.out.println("LobbyClient connect Error");
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
//			e.printStackTrace();
			System.out.println("LobbyClient disconnect Error");
		}
	}
	
	public void sendMessage(String msg) {
		try {
			out.writeUTF(msg);
		}catch(IOException e) {
//			e.printStackTrace();
			System.out.println("LobbyClient sendMessage Error");
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
			String msg, title;
			int port;
			
			switch(chk) {
			case 1:														// ���� ��� ������Ʈ
				try {
					lobby.clearUserList(); 					
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						lobby.appendUserList(msg);
					}
				}catch(IOException e) {
//					e.printStackTrace();
					System.out.println("LobbyClient checkCmd case 1 Error");
				}
				break;
			case 2:														// �� ��� ������Ʈ
				try {
					lobby.clearGameRoom();
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						lobby.addGameRoom(msg);
					}
				} catch (IOException e) {
//					e.printStackTrace();			
					System.out.println("LobbyClient checkCmd case 2 Error");
				}
				break;
			case 3:													// �� ����
				title = cmd.substring(2,  cmd.indexOf("###"));
				port = Integer.parseInt(cmd.substring(cmd.indexOf("###")+3, cmd.length()));
				lobby.enterRoom(title, port);
				break;
			case 4:													// ��� �� ����
				title = cmd.substring(2, cmd.indexOf("###"));
				port = Integer.parseInt(cmd.substring(cmd.indexOf("###")+3, cmd.length()));
				lobby.enterPrivateRoom(title, port);
				break;
			case 5:													// �� ����
				title = cmd.substring(2, cmd.indexOf("###"));
				int num = Integer.parseInt(cmd.substring(cmd.indexOf("###")+3, cmd.length()));
				lobby.makeRoom(title, num);
				break;
			case 6:													// ���� �ޱ�
				try {
					nickname = cmd.substring(2, cmd.indexOf("###"));
					lobby.receiveLetter(nickname);
					int cnt = Integer.parseInt(cmd.substring(cmd.indexOf("###")+3, cmd.length()));
					for(int i=0; i<cnt; i++) {
						String contents = in.readUTF();
						lobby.appendLetter(contents);
					}
					lobby.showMessage();
				}catch(IOException e) {
//					e.printStackTrace();
					System.out.println("LobbyClient checkCmd case 6 Error");
				}
				break;
			case 7:													// password Ʋ�� ���
				lobby.passwordError();
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
				System.out.println("�κ� Ŭ���̾�Ʈ ����");
				System.exit(0);
			}
		}
	}	
}