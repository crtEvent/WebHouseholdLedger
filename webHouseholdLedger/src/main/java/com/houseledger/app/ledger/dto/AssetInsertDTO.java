package com.houseledger.app.ledger.dto;

public class AssetInsertDTO {
	
	private String user_idx;
	private String asset_order;
	private String asset_type;
	private String connection_asset_idx;
	private String asset_name;
	private String initial_amount;
	
	public String getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(String user_idx) {
		this.user_idx = user_idx;
	}
	public String getAsset_order() {
		return asset_order;
	}
	public void setAsset_order(String asset_order) {
		this.asset_order = asset_order;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getConnection_asset_idx() {
		return connection_asset_idx;
	}
	public void setConnection_asset_idx(String connection_asset_idx) {
		this.connection_asset_idx = connection_asset_idx;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getInitial_amount() {
		return initial_amount;
	}
	public void setInitial_amount(String initial_amount) {
		this.initial_amount = initial_amount;
	}

}
