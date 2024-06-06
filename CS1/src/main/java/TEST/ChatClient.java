package TEST;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	private String serverIP;
	private int serverPort;
	private String clientIP;
	private ObjectOutputStream out;

	public ChatClient(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.clientIP = "192.168.1.3"; // Bạn có thể thay đổi IP của client nếu cần
	}

	public void start() {
		try (Socket socket = new Socket(serverIP, serverPort)) {
			out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			// Luồng lắng nghe tin nhắn từ server
			new Thread(new ReceiveMessageHandler(in)).start();

			// Luồng gửi tin nhắn tới server
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("Enter the button name:");
				String nameButton = scanner.nextLine();
				System.out.println("Enter your message:");
				String content = scanner.nextLine();

				Message message = new Message(content, clientIP, "192.168.1.4"); // Thay thế destinationIP nếu cần
				sendMessage(nameButton, message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String nameButton, Message message) {
		try {
			out.writeUTF(nameButton);
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class ReceiveMessageHandler implements Runnable {
		private ObjectInputStream in;

		public ReceiveMessageHandler(ObjectInputStream in) {
			this.in = in;
		}

		@Override
		public void run() {
			try {
				while (true) {
					Message message = (Message) in.readObject();
					System.out.println("Received message: " + message.getContent());
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String serverIP = "192.168.1.4"; // IP của server
		int serverPort = 12345; // Port của server

		ChatClient client = new ChatClient(serverIP, serverPort);
		client.start();
	}
}
