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

public class RoomServer extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private int port;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();		// Ŭ���̾�Ʈ ����� ��
	
	public RoomServer(int port) {
		this.port = port;
	}

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(7777 + port);
		while (true) {
			System.out.println("���ֽ� ���� �����... (��Ʈ��ȣ : " + (7777+port) + ")");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "���� ���ֽǼ����� �����߽��ϴ�.");
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
		
		public void checkMsg(String msg) {
			String cmd = msg.substring(0, 1);
			switch(cmd) {
			case "1":										// �� ����
				String nickname = msg.substring(2, msg.indexOf("###"));
				String title = msg.substring(msg.indexOf("###")+3, msg.length());				
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