package com.houseledger.app.ledger.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseledger.app.ledger.dao.AssetDAO;
import com.houseledger.app.ledger.dto.AssetInsertDTO;

@Service
public class AssetServiceImpl implements AssetService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "assetDAO")
	AssetDAO assetDAO;
	
	// 자산 목록 디테일 불러오기
	public List<Map<String, Object>> getAssetDetailedList(String user_idx) throws Exception {
		
		return assetDAO.selectAssetDetailedList(user_idx);
	}
	
	// 자산 순서 바꾸기 - 순위 Up
	public void changeAssetOrderUp(String asset_idx, String user_idx) throws Exception {
		
		// 이전 asset_idx, asset_order
		Map<String, Object> prevAssetMap = assetDAO.selectPrevAssetOrder(asset_idx, user_idx);
		String prevAssetIdx = String.valueOf(prevAssetMap.get("ASSET_IDX"));
		String prevAssetOrder = String.valueOf(prevAssetMap.get("ASSET_ORDER"));
		
		// 현재 asset_order를 이전 asset_order로 바꾸기
		assetDAO.updateAssetOrder(asset_idx, prevAssetOrder, user_idx);
		
		// 이전 asset_order는 + 1
		assetDAO.updateAssetOrder(prevAssetIdx, Integer.toString(Integer.parseInt(prevAssetOrder)+1), user_idx);
	}
	
	// 자산 순서 바꾸기 - 순위 Down
	public void changeAssetOrderDown(String asset_idx, String user_idx) throws Exception {
		
		// 현재 asset_order
		Map<String, Object> currentAssetmap = assetDAO.selectCurrentAsset(asset_idx, user_idx);
		String currentAssetOrder = String.valueOf(currentAssetmap.get("ASSET_ORDER"));
		
		// 다음 asset_order
		Map<String, Object> nextAssetMap = assetDAO.selectNextAssetOrder(asset_idx, user_idx);
		String nextAssetIdx = String.valueOf(nextAssetMap.get("ASSET_IDX"));
		
		// 현재 asset_order에 + 1
		assetDAO.updateAssetOrder(asset_idx, Integer.toString(Integer.parseInt(currentAssetOrder)+1), user_idx);
		
		// 다음 asset_order는 현재 asset_order로 바꾸기
		assetDAO.updateAssetOrder(nextAssetIdx, currentAssetOrder, user_idx);
	}
	
	// 통장 목록 불러오기
	public List<Map<String, Object>> getBankAssetList(String user_idx) throws Exception {
		return assetDAO.selectBankAssetList(user_idx);
	}
	
	// 마지막 자산 순서 불러오기
	public Integer getLastAssetOrder(String user_idx) throws Exception {
		return assetDAO.selectLastAssetOrder(user_idx);
	}
	
	// AssetInsertDTO 값 설정
	public void checkAssetInsertDTO(AssetInsertDTO dto) {
		
		// initial_amount가 없을 경우 또는 asset_type이 "체크"인 경우initial_amount는 "0"
		if(dto.getInitial_amount().isEmpty() 
				|| dto.getInitial_amount() == null 
				|| dto.getAsset_type().contentEquals("체크")) {
			dto.setInitial_amount("0");
		}
		
		// asset_type이 "현금", "통장"인 경우 connection_asset_idx은 null값으로 지정
		if(dto.getAsset_type().equals("현금") || dto.getAsset_type().equals("통장")) {
			dto.setConnection_asset_idx(null);
		}
		
	}
	
	// 자산 추가
	public void insertAsset(AssetInsertDTO dto) throws Exception {
			
		checkAssetInsertDTO(dto);
		
		// 마지막 자산 순서 구하기
		String order = Integer.toString(getLastAssetOrder(dto.getUser_idx())+1);
		if(order == null) {
			order = "0";
		}
		dto.setAsset_order(order);
		
		// insert
		assetDAO.insertAsset(dto);
			
	}
	

}
