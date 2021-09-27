package com.houseledger.app.stats.dto;

public class StatsSelectDTO {
	
	private String user_idx;
	private String income_and_expenses; // "지출" or "수입"
	private String date;
	private String start_date;
	private String end_date;
	
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getIncome_and_expenses() {
		return income_and_expenses;
	}
	public void setIncome_and_expenses(String income_and_expenses) {
		this.income_and_expenses = income_and_expenses;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	

}
