package com.houseledger.app.ledger.dto;

import java.util.List;
import java.util.Map;

public class LedgerCalendarDTO {
	
	private List<Map<String,Object>> calendarDateGroup;
	private String date;
	
	public List<Map<String, Object>> getCalendarDateGroup() {
		return calendarDateGroup;
	}
	public void setCalendarDateGroup(List<Map<String, Object>> calendarDateGroup) {
		this.calendarDateGroup = calendarDateGroup;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
