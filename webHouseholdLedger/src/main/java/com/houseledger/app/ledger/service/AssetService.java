package com.houseledger.app.ledger.service;

import java.util.List;
import java.util.Map;

import com.houseledger.app.ledger.dto.AssetInsertDTO;
import com.houseledger.app.ledger.dto.AssetUpdateDTO;

public interface AssetService {
	
	public Map<String, Object> getAssetOne(String asset_idx, String user_idx) throws Exception;
	public List<Map<String, Object>> getAssetDetailedList(String user_idx) throws Exception;
	public void changeAssetOrderUp(String asset_idx, String user_idx) throws Exception;
	public void changeAssetOrderDown(String asset_idx, String user_idx) throws Exception;
	public List<Map<String, Object>> getBankAssetList(String user_idx) throws Exception;
	public Integer getLastAssetOrder(String user_idx) throws Exception;
	public void checkAssetInsertDTO(AssetInsertDTO dto);
	public void insertAsset(AssetInsertDTO dto) throws Exception;
	public void updateAsset(AssetUpdateDTO dto) throws Exception;
	public void deleteAsset(String asset_idx, String user_idx) throws Exception;
}
