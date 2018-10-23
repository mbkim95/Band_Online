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

	// ���ݱ��� ����. GUi������Ű�鼭 ����Gui�� �޽������.
	// ���� �̽�. Gui �󿡼� �ϴ� 1:1 ä���� �ϰ� �ʹ�.
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap); // �̰� �������� ���ݴϴ�^^
		serverSocket = new ServerSocket(7001);
		while (true) {
			/** XXX 01. ù��°. ������ ���� �д�. ��� ���ӹ޴°�. */
			System.out.println("�κ� ���� �����...");
			socket = serverSocket.accept(); // ���� ������ ������ ��� �ݺ��ؼ� ����ڸ� �޴´�.
			System.out.println(socket.getInetAddress() + "���� �κ񼭹��� �����߽��ϴ�.");
			// ���⼭ ���ο� ����� ������ Ŭ���� �����ؼ� ���������� �־���߰���?!
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

	// ���ǳ���(Ŭ���̾�Ʈ) ����� ����
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