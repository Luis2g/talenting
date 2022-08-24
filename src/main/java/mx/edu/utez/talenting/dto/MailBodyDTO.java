package mx.edu.utez.talenting.dto;

public class MailBodyDTO {
	private String to;
	private String subject;
	private String content;
	
	public MailBodyDTO() {
		
	}
	
	public MailBodyDTO(String to, String subject, String content) {
		super();
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MailBodyDTO [to=" + to + ", subject=" + subject + ", content=" + content + "]";
	}
	
	
	
	
}