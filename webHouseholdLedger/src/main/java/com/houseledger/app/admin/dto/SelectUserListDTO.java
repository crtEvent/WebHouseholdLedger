package com.houseledger.app.admin.dto;

public class SelectUserListDTO {
	
	private String user_id;
	private String user_idx;
	private String start_date;
	private String end_date;
	private String start_date_extended;
	private String end_date_extended;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_idx() {
		return user_idx;
	}	
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
		start_date_extended = start_date + " 00:00:00";
	}
	
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
		end_date_extended = end_date + " 23:59:59";
	}
	
	public String getStart_date_extended() {
		return start_date_extended;
	}
	
	public String getEnd_date_extended() {
		return end_date_extended;
	}
	
}
