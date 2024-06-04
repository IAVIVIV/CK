import APP.UseCase;
import INF.Client;

public class test {
	public static void main(String[] args) throws Exception {
//		UseCase.register("Dung", "thnh@gmail.com", "Thien3#");
//		UseCase.register("Tuan", "hungqng@gmail.com", "Hung123@");
//		UseCase.register("Tai", "taidt@gmail.com", "Tai1@");

//		Service s = new Service();
//		s.logIn("Dung", "Thien3#");

//		UseCase.sendMessage("Dung", "Tuan", "Xin chào Thien!!!");
//		UseCase.sendMessage("Tuan", "Dung", "Ngày mới tốt lành, Dung!!!");
//		UseCase.sendMessage("Dung", "Tuan", "Hôm nay đi đá bóng không?");
//		UseCase.sendMessage("Tuan", "Dung", "Tớ bận ôn thi rồi.");
//		UseCase.sendMessage("Dung", "Tuan", "Tiếc thật!");

//		try {
//			String originalString = "Hello, world!";
//			String key = Service.createKey();
//
//			String encryptedString = Service.encrypt(key, originalString);
//			String decryptedString = Service.decrypt(key, encryptedString);
//
//			System.out.println("Original: " + originalString);
//			System.out.println("Encrypted: " + encryptedString);
//			System.out.println("Decrypted: " + decryptedString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		Client client = new Client();
		client.cloneClient("192.168.1.3", "192.168.1.3", "Dung", "Lien", "Thái nhất tâm, thái vĩnh tuyền.");
	}
}
