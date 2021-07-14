package com.houseledger.app.ledger.dto;

public class LedgerInsertDTO {
	
	private String ledger_idx;
	private String user_idx;
	private String date;
	private String income_and_expenses;
	private String category;
	private String description;
	private String amount;
	private String asset;
	private String former_asset;
	private String latter_asset;
	
	public String getLedger_idx() {
		return ledger_idx;
	}
	public void setLedger_idx(String ledger_idx) {
		this.ledger_idx = ledger_idx;
	}
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncome_and_expenses() {
		return income_and_expenses;
	}
	public void setIncome_and_expenses(String income_and_expenses) {
		this.income_and_expenses = income_and_expenses;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getFormer_asset() {
		return former_asset;
	}
	public void setFormer_asset(String former_asset) {
		this.former_asset = former_asset;
	}
	public String getLatter_asset() {
		return latter_asset;
	}
	public void setLatter_asset(String latter_asset) {
		this.latter_asset = latter_asset;
	}
	
	@Override
	public String toString() {
		return "[LedgerInsertDTO]\nledger_idx="+ledger_idx+", user_idx="+user_idx+", date="+date+
				"\n income_and_expenses="+income_and_expenses+", category="+category+
				"\n description="+description+", amount="+amount+
				"\n former_asset="+former_asset+", latter_asset="+latter_asset;
	}
	
}
