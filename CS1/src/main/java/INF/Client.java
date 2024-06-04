package INF;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import APP.DTOMessageO;
import APP.DTO_Message;
import DOMAIN.Service;
import UI.ViewChat;
import UI.ViewLogIn;

public class Client {
	private String hostname;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public static String sender;
	public static String receiver;
	public static String content;
	public static String destIP;

	public Client() {
		super();
	}

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void start() {
		try {
			socket = new Socket(hostname, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			new Thread(new Reader()).start();

//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String content = Client.content;

//			while ((content = reader.readLine()) != null) {
//			System.out.print("Enter destination IP: ");
//				String destIP = reader.readLine();
//			String dest_IP =;
			String sourceIP = socket.getLocalAddress().getHostAddress();

//			DTO_Message message = new DTO_Message(content, sourceIP, destIP);
			DTO_Message message = new DTO_Message(content, sourceIP, destIP);
			DTOMessageO message_o = new DTOMessageO(message, sender, receiver, content);
			System.out.println(message_o.toString());
			out.writeObject(message_o);
			out.flush();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Reader implements Runnable {
		@Override
		public void run() {
			try {
				DTO_Message message;
				while ((message = (DTO_Message) in.readObject()) != null) {
					System.out.println("From " + message.getSourceIP() + ": " + message.getContent());
//
//					ViewChat.jList.addMouseListener(new MouseAdapter() {
//						@Override
//						public void mouseClicked(MouseEvent e) {
//							if (e.getClickCount() == 1) {
//								System.out.println("Chaof ngay moi vui ve");
//								int index = ViewChat.jList.locationToIndex(e.getPoint());
//								if (index >= 0) {
//									ViewChat.txtrSdgd.setText("");
//									String sender = ViewLogIn.username;
//									String receiver = ViewChat.jList.getModel().getElementAt(index);
//									ViewChat.selectUser = receiver;
//									Service s = new Service();
//									try {
//										List<String> content = s.reloadMessage(sender, receiver);
//										for (String string : content) {
//											ViewChat.txtrSdgd.setText(ViewChat.txtrSdgd.getText() + string + "\n");
//										}
//									} catch (Exception e1) {
//										e1.printStackTrace();
//									}
//								}
//							}
//						}
//					});

//					MouseEvent event = new MouseEvent(ViewChat.jList, MouseEvent.MOUSE_CLICKED,
//							System.currentTimeMillis(), 0, 10, 10, 1, false);
//					ViewChat.jList.dispatchEvent(event);
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void cloneClient(String ip, String destIP, String sender, String receiver, String content) {
		Client.destIP = destIP;
		Client.sender = sender;
		Client.receiver = receiver;
		Client.content = content;
		Client client = new Client(ip, 12345);
		client.start();
	}
//
//	public static void main(String[] args) {
//		Client client = new Client("192.168.1.3", 12345);
//		client.start();
//	}
}