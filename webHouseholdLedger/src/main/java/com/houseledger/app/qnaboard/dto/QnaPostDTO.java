package com.houseledger.app.qnaboard.dto;

public class QnaPostDTO {
	
	private String board_idx;
	private String user_idx;
	private String user_id;
	private String subject;
	private String content;
	private String hit_cnt;
	private String reg_date;
	private String delete_yn;
	private String nextBoard_idx;
	private String prevBoard_idx;
	
	public String getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}
	
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	public String getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(String hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	
	public String getNextBoard_idx() {
		return nextBoard_idx;
	}
	public void setNextBoard_idx(String nextBoard_idx) {
		this.nextBoard_idx = nextBoard_idx;
	}
	
	public String getPrevBoard_idx() {
		return prevBoard_idx;
	}
	public void setPrevBoard_idx(String prevBoard_idx) {
		this.prevBoard_idx = prevBoard_idx;
	}
	
}
