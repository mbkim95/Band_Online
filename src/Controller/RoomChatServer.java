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

public class RoomChatServer extends Thread{
	private int num;
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
	private Map<String, DataOutputStream> tmpMap = new HashMap<String, DataOutputStream>();
	
	public RoomChatServer(int num) {
		this.num = num;
	}

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(8777+num);
		while (true) {
			System.out.println("���ֽ� ä�� ���� �����... (��Ʈ : " + (8777+num) + ")");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "���� ���ֽ� ä�� ������ �����߽��ϴ�.");
			Receiver receiver = new Receiver(socket);
			receiver.start();			
		}
	}
	
	public void closeServer() {
		try {
			if(socket != null) {
				socket.close();
			}
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void run() {
		try {
			setting();
		} catch (IOException e) {
			System.out.println("���ֽ� ä�� ���� ���� (��Ʈ : " + (8777 + num) + ")");
		}
	}

	public void addClient(String nick, DataOutputStream out) throws IOException {
		clientsMap.put(nick, out);			
		sendCmd("2 [" + nick + "]���� ���ֽǿ� ���Խ��ϴ�\n");
	}

	public void removeClient(String nick) {		
		clientsMap.remove(nick);			
		sendCmd("2 [" + nick + "]���� ���ֽǿ��� �������ϴ�\n");	
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
	
	public void setInstruments(String nickname, int n) {
		String inst = "";
		switch(n) {
		case 0:
			inst += "��Ÿ1��";
			break;
		case 1:
			inst += "��Ÿ2��";
			break;
		case 2:
			inst += "���̽���";
			break;
		case 3:
			inst += "Ű���带";
			break;
		case 4:
			inst += "�巳��";
			break;			
		}
		sendCmd("2 [" + nickname + "]���� " + inst + " �����Ͽ����ϴ�\n");
	}
	
	public void changeInstruments(String nickname, int n1, int n2) {
		String inst1 = "", inst2 = "";
		switch(n1) {
		case 0:
			inst1 += "��Ÿ1���� ";
			break;
		case 1:
			inst1 += "��Ÿ2���� ";
			break;
		case 2:
			inst1 += "���̽����� ";
			break;
		case 3:
			inst1 += "Ű���忡�� ";
			break;
		case 4:
			inst1 += "�巳���� ";
			break;			
		}
		
		switch(n2) {
		case 0:
			inst2 += "��Ÿ1��";
			break;
		case 1:
			inst2 += "��Ÿ2��";
			break;
		case 2:
			inst2 += "���̽���";
			break;
		case 3:
			inst2 += "Ű�����";
			break;
		case 4:
			inst2 += "�巳����";
			break;			
		}
		sendCmd("2 [" + nickname + "]���� " + inst1 + inst2 + " �����Ͽ����ϴ�\n");
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
			String message, nickname;
			int n1, n2;
			switch(cmd) {
			case "1":										// �濡�� ������
				nickname = msg.substring(2, msg.length());
				removeClient(nickname);
				if(clientsMap.size() == 0) {
					closeServer();
				}
				break;
			case "2":										// ä��
				message = msg.substring(2, msg.length());
				sendMessage(message);
				break;
			case "3":										// �Ǳ� ����
				nickname = msg.substring(2, msg.indexOf("###"));
				n1 = Integer.parseInt(msg.substring(msg.indexOf("###") + 3, msg.length()));
				setInstruments(nickname, n1);
				break;
			case "4":										// �Ǳ� ����
				nickname = msg.substring(2, msg.indexOf("###"));
				n1 = Integer.parseInt(msg.substring(msg.indexOf("###") + 3, msg.indexOf("***")));
				n2 = Integer.parseInt(msg.substring(msg.indexOf("***") + 3, msg.length()));
				changeInstruments(nickname, n1, n2);
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