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

import javax.sound.midi.Instrument;

public class RoomServer extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private int num;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();		// 클라이언트 저장용 맵
	private ArrayList<String> instruments = new ArrayList<String>();
	
	public RoomServer(int num) {
		this.num = num;
	}

	public void setting() throws IOException {
		Collections.synchronizedMap(clientsMap);
		int portNum = 7777 + num;
		serverSocket = new ServerSocket(portNum);
		while (true) {
			System.out.println("합주실 서버 대기중... (포트 : " + portNum + ")");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "에서 합주실서버에 접속했습니다.");
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
		System.out.println("nick : " + nick);
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
				break;
			case "2":										// 악기 이미지 변경
				instruments.add(msg.substring(2, msg.length()));
				sendCmd("2 " + instruments.size());
				for(int i=0; i<instruments.size(); i++) {
					sendCmd(instruments.get(i));
				}
				break;				
			case "3":										// 방 나가기
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###")+3, msg.indexOf("***"));
				String n = msg.substring(msg.indexOf("***")+3, msg.length());
				instruments.remove(n);
				sendCmd("2 " + (instruments.size()+1));
				int tmp = Integer.parseInt(n) + 5;
				sendCmd(Integer.toString(tmp));
				for(int i=0; i<instruments.size(); i++) {
					sendCmd(instruments.get(i));
				}
				if(instruments.size() == 0) {
					sendCmd("3 " + title);					
				}
				removeClient(nickname);
				break;
			case "4":									// 악기 연주
				sendCmd(msg);
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