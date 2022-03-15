package com.houseledger.app.ledger.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.dto.LedgerInsertDTO;

@Repository("ledgerDAO")
public class LedgerDAO extends AbstractDAO{
	
	// 날짜 그룹별 가계부 내역 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectLedgerList(LedgerSelectDTO dto) throws Exception{
		return (List<Map<String, Object>>) selectList("ledger.selectLedgerList", dto);
	}
	
	// 기간내 가계부 내역 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectLedgerListByPeriod(LedgerSelectDTO dto) throws Exception{
		return (List<Map<String, Object>>) selectList("ledger.selectLedgerListByPeriod", dto);
	}
	
	// 날짜별 가계부 그룹 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectLedgerGroup(LedgerSelectDTO dto) throws Exception{
		return (List<Map<String, Object>>) selectList("ledger.selectLedgerGroup", dto);
	}
	
	// 기간 내 가계부 총 수익, 지출 불러오기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectLedgerSummary(LedgerSelectDTO dto) throws Exception{
		return (Map<String, Object>) selectOne("ledger.selectLedgerSummary", dto);
	}
	
	//자산 목록 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectAssetList(String user_idx) throws Exception{
		return (List<Map<String, Object>>) selectList("asset.selectAssetList", user_idx);
	}
	
	// 수입/지출 내역 입력
	public void insertIncomeAndExpensesledger(LedgerInsertDTO dto) throws Exception{
		insert("ledger.insertIncomeAndExpensesLedger", dto);
	}
	
	// 이동 내역 입력
	public void insertTransferledger(LedgerInsertDTO dto) throws Exception{
		insert("ledger.insertTransferLedger", dto);
	}
	
	// 수입/지출 내역 수정
	public void updateIncomeAndExpensesledger(LedgerInsertDTO dto) throws Exception{
		update("ledger.updateIncomeAndExpensesLedger", dto);
	}
	
	// 이동 내역 수정
	public void updateTransferledger(LedgerInsertDTO dto) throws Exception{
		update("ledger.updateTransferLedger", dto);
	}
	
	public void deleteLedger(LedgerInsertDTO dto) throws Exception {
		delete("ledger.deleteLedger", dto);
	}
	
}
