package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LobbyServer extends Thread{

	// 지금까지 한일. GUi연동시키면서 서버Gui에 메시지띄움.
	// 다음 이슈. Gui 상에서 일단 1:1 채팅을 하고 싶다.
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap); // 이걸 교통정리 해줍니다^^
		serverSocket = new ServerSocket(7001);
		while (true) {
			/** XXX 01. 첫번째. 서버가 할일 분담. 계속 접속받는것. */
			System.out.println("로비 서버 대기중...");
			socket = serverSocket.accept(); // 먼저 서버가 할일은 계속 반복해서 사용자를 받는다.
			System.out.println(socket.getInetAddress() + "에서 로비서버에 접속했습니다.");
			// 여기서 새로운 사용자 쓰레드 클래스 생성해서 소켓정보를 넣어줘야겠죠?!
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

	// 맵의내용(클라이언트) 저장과 삭제
	public void addClient(String nick, DataOutputStream out) throws IOException {
		clientsMap.put(nick, out);		
		sendUserList();		
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

		public void run() {
			try {
				while (in != null) {
					msg = in.readUTF();
					sendMessage(msg);				
				}
			} catch (IOException e) {
				removeClient(nick);
			}
		}
	}
}