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

public class Server {

	public void setting() throws IOException {				
		LobbyServer lobby = new LobbyServer();
		ChatServer chat = new ChatServer();
		lobby.start();
		chat.start();		
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.setting();
	}
}