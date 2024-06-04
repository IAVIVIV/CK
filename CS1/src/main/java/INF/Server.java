package INF;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
	private int port;
	private ConcurrentHashMap<String, ClientHandler> clientHandlers;

	public Server(int port) {
		this.port = port;
		clientHandlers = new ConcurrentHashMap<>();
	}

	public void start() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

				ClientHandler clientHandler = new ClientHandler(clientSocket, clientHandlers);
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}