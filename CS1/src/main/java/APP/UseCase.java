package APP;

import DOMAIN.Service;

public class UseCase {
	public static void register(String username, String email, String password) throws Exception {
		Service s = new Service();
		s.register(username, email, password);
	}

	public static void sendMessage(String sender, String receiver, String content) throws Exception {
		Service s = new Service();
		s.saveMessage(sender, receiver, content);
	}
}
