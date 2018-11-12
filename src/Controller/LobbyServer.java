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
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();		// 클라이언트 저장용 맵
	private Map<String, RoomServer> serverMap = new HashMap<String, RoomServer>();
	private Map<String, String> portMap = new HashMap<String, String>(); 
	private ArrayList<String> roomList = new ArrayList<String>();

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(7001);
		while (true) {
			System.out.println("로비 서버 대기중...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "에서 로비서버에 접속했습니다.");
			Receiver receiver = new Receiver(socket);
			receiver.start();			
		}
	}

	@Override
	public void run() {
		try {
			setting();
		} catch (IOException e) {
			e.printStackTrace();
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

	// 메시지 내용 전파
	public void sendCmd(String msg) {
		Iterator<String> it = clientsMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				clientsMap.get(key).writeUTF(msg);				
			} catch (IOException e) {
				e.printStackTrace();
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
				e.printStackTrace();
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
				e.printStackTrace();
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
				e.printStackTrace();
			}
		}
	}
	
	public void addRoom(String title, String nickname) {
		try {
			RoomServer room = new RoomServer(roomNum);
			RoomChatServer roomChat = new RoomChatServer(roomNum);
			room.start();
			roomChat.start();
			serverMap.put(title, room);
			portMap.put(title, Integer.toString(roomNum));
			System.out.println(title + " : " + roomNum);
			clientsMap.get(nickname).writeUTF("4 " + title + "###" + roomNum);
			roomNum++;
			roomList.add(title);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(String title) {
		roomList.remove(title);
		serverMap.get(title).closeServer();
		serverMap.remove(title);
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
			String nickname, title;
			switch(cmd) {
			case "1":										// 방 생성
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###")+3, msg.length());
				addRoom(title, nickname);
				sendRoomList();
				break;
			case "2":										// 방 제거
				title = msg.substring(2, msg.length());
				deleteRoom(title);
				sendRoomList();
				break;
			case "3":										// 방 입장
				try {
					title = msg.substring(2, msg.indexOf("###"));
					nickname = msg.substring(msg.indexOf("###") + 3, msg.length());
					int port = Integer.parseInt(portMap.get(title));
					System.out.println(title + " : " + port);
					clientsMap.get(nickname).writeUTF("3 " + title + "###" + port);
				} catch (IOException e) {
					e.printStackTrace();
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