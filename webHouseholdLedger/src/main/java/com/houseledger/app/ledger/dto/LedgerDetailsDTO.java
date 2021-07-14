package com.houseledger.app.ledger.dto;

import java.util.List;
import java.util.Map;

public class LedgerDetailsDTO {
	
	private Map<String, Object> ledgerSummary;
	private List<Map<String, Object>> ledgerGroup;
	private List<List<Map<String, Object>>> ledgerList;
	

	public Map<String, Object> getLedgerSummary() {
		return ledgerSummary;
	}
	public void setLedgerSummary(Map<String, Object> ledgerSummary) {
		this.ledgerSummary = ledgerSummary;
	}
	public List<Map<String, Object>> getLedgerGroup() {
		return ledgerGroup;
	}
	public void setLedgerGroup(List<Map<String, Object>> ledgerGroup) {
		this.ledgerGroup = ledgerGroup;
	}
	public List<List<Map<String, Object>>> getLedgerList() {
		return ledgerList;
	}
	public void setLedgerList(List<List<Map<String, Object>>> ledgerList) {
		this.ledgerList = ledgerList;
	}
	
}
