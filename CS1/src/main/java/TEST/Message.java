package TEST;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String content;
	private String sourceIP;
	private String destinationIP;

	public Message(String content, String sourceIP, String destinationIP) {
		this.content = content;
		this.sourceIP = sourceIP;
		this.destinationIP = destinationIP;
	}

	public String getContent() {
		return content;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public String getDestinationIP() {
		return destinationIP;
	}
}
