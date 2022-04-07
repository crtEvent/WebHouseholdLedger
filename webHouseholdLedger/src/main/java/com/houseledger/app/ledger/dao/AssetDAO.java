package com.houseledger.app.ledger.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.ledger.dto.AssetInsertDTO;

@Repository("assetDAO")
public class AssetDAO extends AbstractDAO {
	
	// 자산 목록 디테일 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAssetDetailedList(String user_idx) throws Exception{
		return (List<Map<String, Object>>) selectList("asset.selectAssetDetailedList", user_idx);
	}
	
	public Map<String, Object> selectCurrentAsset(String asset_idx, String user_idx) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asset_idx", asset_idx);
		map.put("user_idx", user_idx);
		return (Map<String, Object>) selectOne("asset.selectCurrentAsset", map);
	}
	
	// 이전 자산 순서 검색
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectPrevAssetOrder(String asset_idx, String user_idx) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asset_idx", asset_idx);
		map.put("user_idx", user_idx);
		return (Map<String, Object>) selectOne("asset.selectPrevAssetOrder", map);
	}
	
	// 다음 자산 순서 검색
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNextAssetOrder(String asset_idx, String user_idx) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asset_idx", asset_idx);
		map.put("user_idx", user_idx);
		return (Map<String, Object>) selectOne("asset.selectNextAssetOrder", map);
	}
	
	// 자산 순서 변경
	public void updateAssetOrder(String asset_idx, String asset_order, String user_idx) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("asset_order", asset_order);
		map.put("asset_idx", asset_idx);
		map.put("user_idx", user_idx);
		update("asset.updateAssetOrder", map);
	}
	
	// 통장 자산 리스트 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBankAssetList(String user_idx) throws Exception {
		return selectList("asset.selectBankAssetList", user_idx);
	}
	
	// 마지막 자산 순서 불러오기
	public Integer selectLastAssetOrder(String user_idx) throws Exception {
		return (Integer) selectOne("asset.selectLastAssetOrder", user_idx);
	}
	
	// 자산 추가
	public void insertAsset(AssetInsertDTO dto) throws Exception {
		insert("asset.insertAsset", dto);
	}
	
}
