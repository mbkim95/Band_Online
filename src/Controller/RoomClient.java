package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.BandGUI;
import View.LobbyGUI;

public class RoomClient {
	private Socket socket;	
	private DataOutputStream out;
	private String cmd;
	private int port;
	private String nickname;
	private BandGUI gui;
	private Receiver receiver;
	
	public RoomClient(int port) {
		this.port = port;
	}
	
	public void connect(String ip, BandGUI gui, String nickname) {
		try {
			socket = new Socket(ip, 7777 + port);
			System.out.println("���ֽ� ���� ����� (��Ʈ : " + (7777 + port) + ")");
			this.nickname = nickname;
			out = new DataOutputStream(socket.getOutputStream());		
			out.writeUTF(nickname);
			System.out.println("���ֽǼ����� �г��� ���� �Ϸ�");
			receiver = new Receiver(socket, gui);
			receiver.start();			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if(receiver != null) {
				receiver.in.close();
				receiver.interrupt();
			}
			if(out != null)
				out.close();
			if(socket != null)
				socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			out.writeUTF(msg);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	class Receiver extends Thread {
		private DataInputStream in;
		BandGUI band;

		public Receiver(Socket socket, BandGUI band) throws IOException {			
			in = new DataInputStream(socket.getInputStream());
			this.band = band;
		}
		
		public void playMelody(int select, int octave, int sound) {
			String file = "rsc/sound/";
			if(select == 0) {
				file += "dist_guitar/";
			}else if(select == 1) {
				file += "clean_guitar/";
			}else if(select == 2) {
				file += "bass/";
			}else if(select == 3) {
				file += "keyboard/";
			}
			
			if(octave == 1) {
				file += "high/";
			}else if(octave == -1) {
				file += "low/";
			}
			
			switch(sound) {
			case 0:
				SoundController.Play(file + "C.wav");
				break;
			case 1:
				SoundController.Play(file + "C#.wav");
				break;
			case 2:
				SoundController.Play(file + "D.wav");
				break;
			case 3:
				SoundController.Play(file + "D#.wav");
				break;
			case 4:
				SoundController.Play(file + "E.wav");
				break;
			case 5:
				SoundController.Play(file + "F.wav");
				break;
			case 6:
				SoundController.Play(file + "F#.wav");
				break;
			case 7:
				SoundController.Play(file + "G.wav");
				break;
			case 8:
				SoundController.Play(file + "G#.wav");
				break;
			case 9:
				SoundController.Play(file + "A.wav");
				break;
			case 10:
				SoundController.Play(file + "A#.wav");
				break;
			case 11:
				SoundController.Play(file + "B.wav");
				break;
			case 12:
				SoundController.Play(file + "C_high.wav");
				break;
			}			
		}
		
		public void playDrum(int sound){
			String file = "rsc/sound/drum/";
			
			switch(sound) {
			case 0:
				SoundController.Play(file + "kick.wav");
				break;
			case 2:
				SoundController.Play(file + "snare.wav");
				break;
			case 4:
				SoundController.Play(file + "hihat_closed.wav");
				break;
			case 5:
				SoundController.Play(file + "hihat_opened.wav");
				break;
			case 7:
				SoundController.Play(file + "sidestick.wav");
				break;
			case 9:
				SoundController.Play(file + "crash.wav");
				break;
			case 11:
				SoundController.Play(file + "ride.wav");
				break;
			case 12:
				SoundController.Play(file + "tom.wav");
				break;
			}
		}
		
		public void playInstruments(int select, int octave, int sound) {
			if(select < 4) {
				playMelody(select, octave, sound);
			}else {
				playDrum(sound);
			}
		}
		
		public void checkCmd(String cmd) {								// �����κ��� ���� ��� üũ
			int chk = Integer.parseInt(cmd.substring(0, 1));			
			String msg;
			System.out.println(nickname + "'s cmd : " + cmd);
			
			switch(chk) {
			case 1:														// ���� ��� ������Ʈ
				try {
					band.clearUserList(); 					
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						band.appendUserList(msg);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:														// �̹���  ������Ʈ
				try {
					int n = Integer.parseInt(cmd.substring(2, cmd.length()));
					for(int i=0; i<n; i++) {
						msg = in.readUTF();
						int select = Integer.parseInt(msg);
						band.changeImage(select);						
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:														// �κ񿡼� �� ����
				String title = cmd.substring(2, cmd.length());
				band.deleteRoom();
				break;
			case 4:
				int select = Integer.parseInt(cmd.substring(2, cmd.indexOf("###")));
				int octave = Integer.parseInt(cmd.substring(cmd.indexOf("###")+3, cmd.indexOf("***")));
				int sound = Integer.parseInt(cmd.substring(cmd.indexOf("***")+3, cmd.length()));
				playInstruments(select, octave, sound);
				break;
			}				
		}

		public void run() {
			try {
				while(in != null) {
					cmd = in.readUTF();					
					checkCmd(cmd);
				}
			} catch (IOException e) {				
				System.out.println("���ֽ� Ŭ���̾�Ʈ ����");
				//System.exit(0);
			}
		}
	}	
}