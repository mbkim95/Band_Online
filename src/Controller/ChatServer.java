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

public class ChatServer extends Thread{

	// ���ݱ��� ����. GUi������Ű�鼭 ����Gui�� �޽������.
	// ���� �̽�. Gui �󿡼� �ϴ� 1:1 ä���� �ϰ� �ʹ�.
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap); // �̰� �������� ���ݴϴ�^^
		serverSocket = new ServerSocket(7002);
		while (true) {
			/** XXX 01. ù��°. ������ ���� �д�. ��� ���ӹ޴°�. */
			System.out.println("ä�� ���� �����...");
			socket = serverSocket.accept(); // ���� ������ ������ ��� �ݺ��ؼ� ����ڸ� �޴´�.
			System.out.println(socket.getInetAddress() + "���� ä�ü����� �����߽��ϴ�.");
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
		sendCmd("2 [" + nick + "]���� �����ϼ̽��ϴ�\n");
	}

	public void removeClient(String nick) {		
		clientsMap.remove(nick);			
		sendCmd("2 [" + nick + "]���� ������ �����Ͽ����ϴ�\n");	
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