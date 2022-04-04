package com.houseledger.app.ledger.service;

import java.util.List;
import java.util.Map;

public interface AssetService {
	
	public List<Map<String, Object>> getAssetDetailedList(String user_idx) throws Exception;
	public void changeAssetOrderUp(String asset_idx, String user_idx) throws Exception;
	public void changeAssetOrderDown(String asset_idx, String user_idx) throws Exception;

}
