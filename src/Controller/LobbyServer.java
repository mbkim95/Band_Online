package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LobbyServer extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private int roomNum = 0;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();		// Ŭ���̾�Ʈ ����� ��
	private Map<String, RoomServer> roomMap = new HashMap<String, RoomServer>();					// ���� ���� ��
	private Map<String, String> portMap = new HashMap<String, String>(); 							// �渶�� ��Ʈ��ȣ �ٸ��� �������ֱ����� ��
	private Map<String, String> passwdMap = new HashMap<String, String>();							// ��й�ȣ ���� ��
	private ArrayList<String> roomList = new ArrayList<String>();									// �� �̸� ����� ��

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(7001);
		while (true) {
			System.out.println("�κ� ���� �����...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "���� �κ񼭹��� �����߽��ϴ�.");
			Receiver receiver = new Receiver(socket);
			receiver.start();			
		}
	}

	@Override
	public void run() {
		try {
			setting();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("LobbyServer setting Error");
		}
	}

	public void addClient(String nick, DataOutputStream out) throws IOException {
		clientsMap.put(nick, out);
		sendUserList();
		sendRoomList();
	}

	public void removeClient(String nick) {		
		clientsMap.remove(nick);
		sendUserList();				
	}

	// �޽��� ���� ����
	public void sendCmd(String msg) {
		Iterator<String> it = clientsMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				clientsMap.get(key).writeUTF(msg);				
			} catch (IOException e) {
//				e.printStackTrace();
				System.out.println("LobbyServer sendCmd Error");
			}
		}
	}
	
	public void sendUserList() {		
		Iterator<String> it = clientsMap.keySet().iterator();				
		String key = "";
		String len = String.valueOf(clientsMap.size());		
		while (it.hasNext()) {
			key = it.next();
			try {				
				clientsMap.get(key).writeUTF("1 " + len);
				Iterator iterator = clientsMap.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry entry = (Entry)iterator.next();
					clientsMap.get(key).writeUTF((String) entry.getKey());
				}
			} catch (IOException e) {
//				e.printStackTrace();
				System.out.println("LobbyServer sendUserList Error");
			}
		}
	}
	
	public void sendMessage(String msg) {
		Iterator<String> it = clientsMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				clientsMap.get(key).writeUTF("2 " + msg);
			} catch (IOException e) {
//				e.printStackTrace();
				System.out.println("LobbyServer sendMessage Error");
			}
		}
	}
	
	public void sendRoomList() {		
		Iterator<String> it = clientsMap.keySet().iterator();				
		String key = "";
		String len = String.valueOf(roomList.size());
		while (it.hasNext()) {
			key = it.next();
			try {				
				clientsMap.get(key).writeUTF("2 " + len);
				for(int i=0; i<roomList.size(); i++) {
					clientsMap.get(key).writeUTF(roomList.get(i));
				}
			} catch (IOException e) {
//				e.printStackTrace();
				System.out.println("LobbyServer sendRoomList Error");
			}
		}
	}
	
	public void addRoom(String title, String nickname) {
		try {
			RoomServer room = new RoomServer(roomNum);
			RoomChatServer roomChat = new RoomChatServer(roomNum);
			room.start();
			roomChat.start();
			roomMap.put(title, room);
			portMap.put(title, Integer.toString(roomNum));
			System.out.println(title + " : " + roomNum);
			clientsMap.get(nickname).writeUTF("5 " + title + "###" + roomNum);
			roomNum++;
			roomList.add(title);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("LobbyServer addRoom Error");
		}
	}
	
	public void addRoom(String title, String nickname, String password) {
		try {
			RoomServer room = new RoomServer(roomNum);
			RoomChatServer roomChat = new RoomChatServer(roomNum);
			room.start();
			room.setPassword(true);
			roomChat.start();
			roomMap.put(title, room);
			portMap.put(title, Integer.toString(roomNum));
			passwdMap.put(title, password);
			System.out.println(title + " : " + roomNum);
			clientsMap.get(nickname).writeUTF("5 " + title + "###" + roomNum);
			roomNum++;
			roomList.add(title);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("LobbyServer addPasswordRooom Error");
		}
	}
	
	public void deleteRoom(String title) {
		roomList.remove(title);
		roomMap.get(title).closeServer();
		roomMap.remove(title);
//		roomNum--;
	}
	
	class Receiver extends Thread {
		private DataInputStream in;
		private DataOutputStream out;
		private String nick;

		public Receiver(Socket socket) throws IOException {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			nick = in.readUTF();	
			addClient(nick, out);
		}
		
		public void checkMsg(String msg) {
			String cmd = msg.substring(0, 1);
			String nickname, title, password;
			int port;
			
			switch(cmd) {
			case "0":										// �� ����
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###")+3, msg.length());
				addRoom(title, nickname);
				sendRoomList();
				break;
			case "1":										// ��й� ����
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###") + 3, msg.indexOf("***"));
				password = msg.substring(msg.indexOf("***")+3, msg.length());
				addRoom(title, nickname, password);
				sendRoomList();
				break;
			case "2":										// �� ����
				title = msg.substring(2, msg.length());
				deleteRoom(title);
				sendRoomList();
				break;
			case "3":										// �� ����
				try {
					title = msg.substring(2, msg.indexOf("###"));
					nickname = msg.substring(msg.indexOf("###") + 3, msg.length());
					port = Integer.parseInt(portMap.get(title));
					System.out.println(title + " : " + port);
					if(roomMap.get(title).getUserCount() < 5) {
						if(roomMap.get(title).hasPassword()) {					// ��й�ȣ�ִ��� Ȯ��
							clientsMap.get(nickname).writeUTF("4 " + title + "###" + port);
						}else {
							clientsMap.get(nickname).writeUTF("3 " + title + "###" + port);
						}
					}else {
						clientsMap.get(nickname).writeUTF("8");
					}
				} catch (IOException e) {
//					e.printStackTrace();
					System.out.println("LobbyServer checkMsg case 3 Error");
				}
				break;
			case "4":										// ��й� ��й�ȣ Ȯ��
				try {
					title = msg.substring(2, msg.indexOf("###"));
					nickname = msg.substring(msg.indexOf("###") +3, msg.indexOf("***"));
					password = msg.substring(msg.indexOf("***")+3, msg.indexOf("&&&"));
					port = Integer.parseInt(msg.substring(msg.indexOf("&&&")+3, msg.length()));		
					if(roomMap.get(title).getUserCount() < 5) {
						if(passwdMap.get(title).equals(password)) {
							clientsMap.get(nickname).writeUTF("3 " + title + "###" + port);
						}else {
							clientsMap.get(nickname).writeUTF("7");
						}						
					}else {
						clientsMap.get(nickname).writeUTF("8");
					}
				} catch (IOException e) {
//					e.printStackTrace();
					System.out.println("LobbyServer checkMsg case 4 Error");
				}
				break;
			case "5":										// �޽��� �ޱ�
				try {
					nickname = msg.substring(2, msg.indexOf("###"));
					String sender = msg.substring(msg.indexOf("###")+3, msg.indexOf("***"));
					int cnt = Integer.parseInt(msg.substring(msg.indexOf("***")+3, msg.indexOf("&&&")));
					String date = msg.substring(msg.indexOf("&&&")+3, msg.indexOf("$$$"));
					String time = msg.substring(msg.indexOf("$$$")+3, msg.length());
					clientsMap.get(nickname).writeUTF("6 " + sender + "###" + cnt + "***" + date + "&&&" + time);
					for(int i=0; i<cnt; i++) {
						String contents = in.readUTF();
						System.out.println("Letter : " + contents);
						clientsMap.get(nickname).writeUTF(contents);
					}
				}catch (IOException e) {
//					e.printStackTrace();
					System.out.println("LobbyServer checkMsg case 5 Error");
				}			
				break;
			}
		}

		public void run() {
			try {
				while (in != null) {
					msg = in.readUTF();
					checkMsg(msg);				
				}
			} catch (IOException e) {
				removeClient(nick);
			}
		}
	}
}