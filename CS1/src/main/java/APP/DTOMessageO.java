package APP;

import java.io.Serializable;

public class DTOMessageO implements Serializable {
	private static final long serialVersionUID = 1L;
	private DTO_Message message;
	private String sender;
	private String receiver;
	private String content;

	public DTOMessageO(DTO_Message message, String sender, String receiver, String content) {
		super();
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}

	public DTO_Message getMessage() {
		return message;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "DTOMessageO [message=" + message.toString() + ", sender=" + sender + ", receiver=" + receiver
				+ ", content=" + content + "]";
	}
}
