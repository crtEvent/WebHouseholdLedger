package com.houseledger.app.ledger.service;

import java.util.List;
import java.util.Map;

import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.dto.LedgerCalendarDTO;
import com.houseledger.app.ledger.dto.LedgerDetailsDTO;
import com.houseledger.app.ledger.dto.LedgerInsertDTO;

public interface LedgerService {
	
	public LedgerDetailsDTO getLedgerDetails(LedgerSelectDTO ledgerSelectDTO) throws Exception;
	
	public LedgerCalendarDTO getLedgerCalendar(LedgerSelectDTO ledgerSelectDTO) throws Exception;
	
	public List<Map<String, Object>> getAssetList(String user_idx) throws Exception;
	
	public void insertLedger(LedgerInsertDTO dto) throws Exception;
	
	public void updateLedger(LedgerInsertDTO dto) throws Exception;
	
	public void deleteLedger(LedgerInsertDTO dto) throws Exception;
}
