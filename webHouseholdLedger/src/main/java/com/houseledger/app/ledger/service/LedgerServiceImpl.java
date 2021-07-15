package com.houseledger.app.ledger.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.ledger.dao.LedgerDAO;
import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.dto.LedgerDetailsDTO;
import com.houseledger.app.ledger.dto.LedgerInsertDTO;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="ledgerDAO")
	LedgerDAO ledgerDAO;
	
	public LedgerDetailsDTO getLedgerDetails(LedgerSelectDTO ledgerSelectDTO) throws Exception{
		
		// start_date, end_date값이 없을 경우 자동 설정
		if(ledgerSelectDTO.getStart_date() == null || ledgerSelectDTO.getEnd_date() == null) {
			LocalDate now = LocalDate.now();
			ledgerSelectDTO.setEnd_date(now.toString()); // 오늘 날짜
			ledgerSelectDTO.setStart_date(now.minusDays(30).toString()); // 30일 전 날짜
		}
		
		// 기간 내 가계부 총 수익, 지출 불러오기
		// (INCOME 총 수익, EXPENDITURE 총 지출, TOTAL 계:수입-지출)
		Map<String, Object> ledgerSummary = ledgerDAO.selectLedgerSummary(ledgerSelectDTO);
		
		// 날짜별 가계부 그룹 불러오기
		// (DATE 날짜, CNT 날짜별 내역 개수, INCOME 수익, EXPENSES 지출)
		List<Map<String, Object>> ledgerGroup = ledgerDAO.selectLedgerGroup(ledgerSelectDTO);
		
		// 날짜 그룹별 가계부 내역(ledgerByDate) 불러온 후 ledgerList에 저장
		List<List<Map<String, Object>>> ledgerList = new ArrayList();
		List<Map<String, Object>> ledgerByDate = null;
		String date = null;
		
		for(int i=0; i < ledgerGroup.size(); i++) {
			date = ledgerGroup.get(i).get("DATE").toString();
			ledgerSelectDTO.setDate(date);
			ledgerByDate = ledgerDAO.selectLedgerList(ledgerSelectDTO);
			ledgerList.add(ledgerByDate);
		}
		
		LedgerDetailsDTO ledgerDetailDTO = new LedgerDetailsDTO();
		ledgerDetailDTO.setLedgerSummary(ledgerSummary);
		ledgerDetailDTO.setLedgerGroup(ledgerGroup);
		ledgerDetailDTO.setLedgerList(ledgerList);
		
		return ledgerDetailDTO;
	}
	
	// 자산 목록 불러오기
	public List<Map<String, Object>> getAssetList(String user_idx) throws Exception {
		
		return ledgerDAO.selectAssetList(user_idx);
	}
	
	// 가계부 내역 입력
	public void insertLedger(LedgerInsertDTO dto) throws Exception {
		
		String income_and_expenses = dto.getIncome_and_expenses();
		
		switch(income_and_expenses) {
			case "수입":
				ledgerDAO.insertIncomeAndExpensesledger(dto);
				break;
			case "지출":
				ledgerDAO.insertIncomeAndExpensesledger(dto);
				break;
			case "이동":
				// description format 예시) 현금 → 신한은행
				dto.setDescription(dto.getFormer_asset()+" → "+dto.getLatter_asset());
				ledgerDAO.insertTransferledger(dto);
				break;
			default:
				log.debug("수입/지출/이동 외의 값이 들어왔음 -> 예외처리 배워서 적용하기");
				break;
		}
		
	}
}
