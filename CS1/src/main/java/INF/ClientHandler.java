package INF;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import APP.DTOMessageO;
import APP.DTO_Message;
import APP.UseCase;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ConcurrentHashMap<String, ClientHandler> clientHandlers;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ClientHandler(Socket clientSocket, ConcurrentHashMap<String, ClientHandler> clientHandlers) {
		this.clientSocket = clientSocket;
		this.clientHandlers = clientHandlers;
	}

	@Override
	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());

			String clientIP = clientSocket.getInetAddress().getHostAddress();
			clientHandlers.put(clientIP, this);

//			DTO_Message message;
			DTOMessageO message;
			while ((message = (DTOMessageO) in.readObject()) != null) {
				String destIP = message.getMessage().getDestIP();
				if (clientHandlers.containsKey(destIP)) {
					clientHandlers.get(destIP).sendMessage(message.getMessage());
				}
				UseCase.sendMessage(message.getSender(), message.getReceiver(), message.getContent());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				clientHandlers.remove(clientSocket.getInetAddress().getHostAddress());
				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(DTO_Message message) throws IOException {
		out.writeObject(message);
		out.flush();
	}
}