package APP;

import java.io.Serializable;

public class DTO_Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String content;
	private String sourceIP;
	private String destIP;

	public DTO_Message(String content, String sourceIP, String destIP) {
		this.content = content;
		this.sourceIP = sourceIP;
		this.destIP = destIP;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public String getDestIP() {
		return destIP;
	}

	public void setDestIP(String destIP) {
		this.destIP = destIP;
	}

	@Override
	public String toString() {
		return "DTO_Message [content=" + content + ", sourceIP=" + sourceIP + ", destIP=" + destIP + "]";
	}
}
