package TEST;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

	private int port;
	private Set<ClientHandler> clientHandlers = new HashSet<>();

	public ChatServer(int port) {
		this.port = port;
	}

	public void startServer() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());
				ClientHandler clientHandler = new ClientHandler(socket, this);
				clientHandlers.add(clientHandler);
				clientHandler.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void broadcast(Message message, ClientHandler excludeClient) {
		for (ClientHandler client : clientHandlers) {
			if (client != excludeClient) {
				client.sendMessage(message);
			}
		}
	}

	public synchronized void removeClient(ClientHandler clientHandler) {
		clientHandlers.remove(clientHandler);
	}

	private static class ClientHandler extends Thread {
		private Socket socket;
		private ChatServer server;
		private ObjectOutputStream out;

		public ClientHandler(Socket socket, ChatServer server) {
			this.socket = socket;
			this.server = server;
		}

		public void run() {
			try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
				out = new ObjectOutputStream(socket.getOutputStream());

				while (true) {
					String nameButton = in.readUTF();
					Message message = (Message) in.readObject();

					// Xử lý hành động theo nameButton và gửi phản hồi hoặc broadcast
					if (nameButton.equals("sendMessage")) {
						server.broadcast(message, this);
					} else {
						// Thực hiện các hành động khác theo yêu cầu
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				server.removeClient(this);
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		public void sendMessage(Message message) {
			try {
				out.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int port = 12345;
		ChatServer server = new ChatServer(port);
		server.startServer();
	}
}
