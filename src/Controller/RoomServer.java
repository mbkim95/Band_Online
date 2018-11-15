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
import java.util.concurrent.atomic.AtomicInteger;

import javax.sound.midi.Instrument;

public class RoomServer extends Thread{
	private ServerSocket serverSocket;
	private Socket socket;
	private String msg;
	private int num;
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();		// 클라이언트 저장용 맵
	private ArrayList<String> instruments = new ArrayList<String>();
	private boolean password = false;
	
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
	
	public boolean hasPassword() {
		return password;
	}
	
	public void setPassword(boolean password) {
		this.password = password;
	}

	@Override
	public void run() {
		try {
			setting();
		} catch (IOException e) {
			System.out.println("합주실 서버 종료 (포트 : " + (7777 + num) + ")");
		}
	}

	public void addClient(String nick, DataOutputStream out) throws IOException {
		clientsMap.put(nick, out);		
		sendUserList();
		sendInstrumentsImage();
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
	
	public void sendInstrumentsImage() {
		sendCmd("2 " + instruments.size());
		for(int i=0; i<instruments.size(); i++) {
			sendCmd(instruments.get(i));
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
			String nickname, title, n;
			switch(cmd) {
			case "1":										// 방 생성
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###")+3, msg.length());				
				break;
			case "2":										// 처음 악기 설정
				instruments.add(msg.substring(2, msg.length()));
				sendInstrumentsImage();
				break;				
			case "3":										// 방 나가기
				nickname = msg.substring(2, msg.indexOf("###"));
				title = msg.substring(msg.indexOf("###")+3, msg.indexOf("***"));
				n = msg.substring(msg.indexOf("***")+3, msg.length());
				instruments.remove(n);
				sendCmd("2 " + (instruments.size()+1));
				int tmp = Integer.parseInt(n) + 5;
				sendCmd(Integer.toString(tmp));
				for(int i=0; i<instruments.size(); i++) {
					sendCmd(instruments.get(i));
				}
				System.out.println("clients : " +instruments.size());
				if(clientsMap.size() == 1) {
					sendCmd("3 " + title);					
				}
				removeClient(nickname);
				break;
			case "4":									// 악기 연주
				sendCmd(msg);
				break;
			case "5":									// 악기 변경
				n = msg.substring(2, msg.indexOf("###"));
				String n2 = msg.substring(msg.indexOf("###")+3, msg.length());
				instruments.remove(n);
				instruments.add(n2);
				sendCmd("2 " + (instruments.size()+1));
				int tmp2 = Integer.parseInt(n) + 5;
				sendCmd(Integer.toString(tmp2));
				for(int i=0; i<instruments.size(); i++) {
					sendCmd(instruments.get(i));
				}
				
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