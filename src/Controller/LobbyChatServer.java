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

public class LobbyChatServer extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> tmpMap = new HashMap<String, DataOutputStream>();	

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(8001);
		while (true) {
			System.out.println("�κ� ä�� ���� �����...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "���� �κ� ä�� ������ �����߽��ϴ�.");
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
		sendCmd("2 [" + nick + "]���� �����ϼ̽��ϴ�\n");
	}

	public void removeClient(String nick) {		
		clientsMap.remove(nick);			
		sendCmd("2 [" + nick + "]���� ������ �����Ͽ����ϴ�\n");	
	}
	
	public void enterRoom(String nick) {
		tmpMap.put(nick, clientsMap.get(nick));
		clientsMap.remove(nick);
	}
	
	public void exitRoom(String nick) {
		clientsMap.put(nick, tmpMap.get(nick));
		tmpMap.remove(nick);
	}

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
	
	public void sendMessage(String msg) {
		Iterator<String> it = clientsMap.keySet().iterator();
		String key = "";
		while (it.hasNext()) {
			key = it.next();
			try {
				clientsMap.get(key).writeUTF("1 " + msg);
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
			case "1":										// ä��
				sendMessage(msg.substring(2, msg.length()));
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