package com.houseledger.app.mail.dto;

public class WriteMailDTO {
	
	private String mail_to;
	private String mail_subject;
	private String mail_content;
	private String mail_state;
	
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	
	public String getMail_subject() {
		return mail_subject;
	}
	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}
	
	public String getMail_content() {
		return mail_content;
	}
	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}
	
	public String getMail_state() {
		return mail_state;
	}
	public void setMail_state(String mail_state) {
		this.mail_state = mail_state;
	}
	
}
