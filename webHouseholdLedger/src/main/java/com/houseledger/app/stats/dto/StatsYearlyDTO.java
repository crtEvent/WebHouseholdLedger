package com.houseledger.app.stats.dto;

import java.util.Map;

public class StatsYearlyDTO {
	
	private String date;
	private Map<String, Object> statsYearlyByExpenses;
	private Map<String, Object> statsYearlyByIncome;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Object> getStatsYearlyByExpenses() {
		return statsYearlyByExpenses;
	}
	public void setStatsYearlyByExpenses(Map<String, Object> statsYearlyByExpenses) {
		this.statsYearlyByExpenses = statsYearlyByExpenses;
	}
	public Map<String, Object> getStatsYearlyByIncome() {
		return statsYearlyByIncome;
	}
	public void setStatsYearlyByIncome(Map<String, Object> statsYearlyByIncome) {
		this.statsYearlyByIncome = statsYearlyByIncome;
	}
	
}
