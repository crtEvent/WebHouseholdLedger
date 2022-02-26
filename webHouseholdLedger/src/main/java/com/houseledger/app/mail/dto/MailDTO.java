package com.houseledger.app.mail.dto;

public class MailDTO {
	
	private String mail_idx;
	private String mail_to;
	private String mail_subject;
	private String mail_content;
	private String mail_state;
	private String reg_date;
	private String nextMail_idx;
	private String prevMail_idx;
	
	public String getMail_idx() {
		return mail_idx;
	}
	public void setMail_idx(String mail_idx) {
		this.mail_idx = mail_idx;
	}
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getNextMail_idx() {
		return nextMail_idx;
	}
	public void setNextMail_idx(String nextMail_idx) {
		this.nextMail_idx = nextMail_idx;
	}
	public String getPrevMail_idx() {
		return prevMail_idx;
	}
	public void setPrevMail_idx(String prevMail_idx) {
		this.prevMail_idx = prevMail_idx;
	}

}
