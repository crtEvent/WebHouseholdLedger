package com.houseledger.app.stats.dto;

import java.util.List;
import java.util.Map;

public class StatsByCategoryDTO {
	
	private String date;
	private List<Map<String, Object>> statsByExpensesCategoryList;
	private List<Map<String, Object>> statsByIncomeCategoryList;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<Map<String, Object>> getStatsByExpensesCategoryList() {
		return statsByExpensesCategoryList;
	}
	public void setStatsByExpensesCategoryList(List<Map<String, Object>> statsByExpensesCategoryList) {
		this.statsByExpensesCategoryList = statsByExpensesCategoryList;
	}
	public List<Map<String, Object>> getStatsByIncomeCategoryList() {
		return statsByIncomeCategoryList;
	}
	public void setStatsByIncomeCategoryList(List<Map<String, Object>> statsByIncomeCategoryList) {
		this.statsByIncomeCategoryList = statsByIncomeCategoryList;
	}
	
	

}
