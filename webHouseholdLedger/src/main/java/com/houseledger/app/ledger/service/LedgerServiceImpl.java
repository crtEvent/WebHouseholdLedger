package com.houseledger.app.ledger.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.ledger.dao.LedgerDAO;
import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.dto.LedgerCalendarDTO;
import com.houseledger.app.ledger.dto.LedgerDetailsDTO;
import com.houseledger.app.ledger.dto.LedgerInsertDTO;

@Service("ledgerService")
public class LedgerServiceImpl implements LedgerService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "ledgerDAO")
	LedgerDAO ledgerDAO;

	public LedgerDetailsDTO getLedgerDetails(LedgerSelectDTO ledgerSelectDTO) throws Exception {

		LedgerDetailsDTO ledgerDetailDTO = new LedgerDetailsDTO();

		// start_date, end_date값이 없을 경우 자동 설정
		if (ledgerSelectDTO.getStart_date() == null || ledgerSelectDTO.getEnd_date() == null) {
			LocalDate now = LocalDate.now();
			ledgerSelectDTO.setEnd_date(now.toString()); // 오늘 날짜
			ledgerSelectDTO.setStart_date(now.minusDays(30).toString()); // 30일 전 날짜
		}

		ledgerDetailDTO.setStart_date(ledgerSelectDTO.getStart_date());
		ledgerDetailDTO.setEnd_date(ledgerSelectDTO.getEnd_date());

		// 기간 내 가계부 총 수익, 지출 불러오기
		// (INCOME 총 수익, EXPENDITURE 총 지출, TOTAL 계:수입-지출)
		Map<String, Object> ledgerSummary = ledgerDAO.selectLedgerSummary(ledgerSelectDTO);

		// 날짜별 가계부 그룹 불러오기
		// (DATE 날짜, CNT 날짜별 내역 개수, INCOME 수익, EXPENSES 지출)
		List<Map<String, Object>> ledgerGroup = ledgerDAO.selectLedgerGroup(ledgerSelectDTO);

		// 날짜 그룹별 가계부 내역(ledgerByDate) 불러온 후 ledgerList에 저장
		List<List<Map<String, Object>>> ledgerList = new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> ledgerByDate = null;
		String date = null;

		for (int i = 0; i < ledgerGroup.size(); i++) {
			date = ledgerGroup.get(i).get("DATE").toString();
			ledgerSelectDTO.setDate(date);
			ledgerByDate = ledgerDAO.selectLedgerList(ledgerSelectDTO);
			ledgerList.add(ledgerByDate);
		}

		ledgerDetailDTO.setLedgerSummary(ledgerSummary);
		ledgerDetailDTO.setLedgerGroup(ledgerGroup);
		ledgerDetailDTO.setLedgerList(ledgerList);

		return ledgerDetailDTO;
	}

	public LedgerCalendarDTO getLedgerCalendar(LedgerSelectDTO ledgerSelectDTO) throws Exception {

		LedgerCalendarDTO ledgerCalendarDTO = new LedgerCalendarDTO();
		List<Map<String,Object>> calendarDateGroup = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> ledgerGroup;
		Map<String,Object> emptyLedgerGroup;
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		int year, month, 
			mainPartfirstDayNumber, mainPartLastDate, 
			frontPartLastDate, frontPartLastDayNumber, frontPartStartDate, 
			indexOfledgerGroup, calendarDateGroupSize, 
			backPartFirstDayNumber;

		// date값이 없을 경우 자동 설정
		if (ledgerSelectDTO.getDate() == null) {
			LocalDate now = LocalDate.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
			ledgerSelectDTO.setDate(now.format(dateTimeFormatter));
		}
		
		// Main Part(검색한 달)의 데이터 입력 - 1. 변수 설정
		year = Integer.parseInt(ledgerSelectDTO.getDate().substring(0, 4));
		month = Integer.parseInt(ledgerSelectDTO.getDate().substring(6, 7)) - 1;
		calendar.set(year, month, 1);
		mainPartLastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Main Part의 마지막 날짜
		mainPartfirstDayNumber = calendar.get(Calendar.DAY_OF_WEEK); // Main Part 1일의 요일 번호(1:일 ~ 7:토)
		ledgerSelectDTO.setStart_date(ledgerSelectDTO.getDate() + "-01");
		ledgerSelectDTO.setEnd_date(ledgerSelectDTO.getDate() + "-" + mainPartLastDate);
		ledgerGroup = ledgerDAO.selectLedgerGroup(ledgerSelectDTO);
		indexOfledgerGroup = ledgerGroup.size() - 1;
		log.debug("시작 전 indexOfledgerGroup: "+indexOfledgerGroup);
		// Main Part(검색한 달)의 데이터 입력 - 2. 입력
		for(int index = 0; index < mainPartLastDate; index++) {log.debug("indexOfledgerGroup: "+indexOfledgerGroup);
			if(Objects.equals(ledgerGroup.get(indexOfledgerGroup).get("DATE").toString()
					, simpleDateFormat.format(calendar.getTime()))) {
				calendarDateGroup.add(ledgerGroup.get(indexOfledgerGroup));
				if(indexOfledgerGroup > 0) {
					indexOfledgerGroup--;
				}
			}else {
				emptyLedgerGroup = new HashMap<String, Object>();
				emptyLedgerGroup.put("DATE", simpleDateFormat.format(calendar.getTime()));
				calendarDateGroup.add(emptyLedgerGroup);
			}
			
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			
		}
		
		
		// Front Part(달력의 앞부분)의 데이터 입력 - 1. 변수 설정
		calendar.set(Calendar.MONTH, month-1);
		frontPartLastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // Front Part의 마지막 날짜
		calendar.set(Calendar.DAY_OF_MONTH, frontPartLastDate);
		frontPartLastDayNumber = calendar.get(Calendar.DAY_OF_WEEK); // Front Part 마지막 날짜의 요일 번호(1:일 ~ 7:토)
		
		// Front Part(달력의 앞부분)의 데이터 입력 - 2. 입력
		for(int index = 0; index < frontPartLastDayNumber; index++) {
			emptyLedgerGroup = new HashMap<String, Object>();
			emptyLedgerGroup.put("DATE", simpleDateFormat.format(calendar.getTime()));
			emptyLedgerGroup.put("COLOR","GRAY");
			calendarDateGroup.add(0, emptyLedgerGroup);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		// Back Part(달력의 뒷부분)의 데이터 입력 - 1. 변수설정
		calendar.set(year, month+1, 1);
		backPartFirstDayNumber = calendar.get(Calendar.DAY_OF_WEEK); // Back Part 첫 번째 날짜의 요일 번호(1:일 ~ 7:토)
		calendarDateGroupSize = calendarDateGroup.size();
		
		// Back Part(달력의 뒷부분)의 데이터 입력 - 2. 입력
		for(int index = calendarDateGroupSize; index < 42; index++) {
			log.debug("들어옴");
			emptyLedgerGroup = new HashMap<String, Object>();
			emptyLedgerGroup.put("DATE", simpleDateFormat.format(calendar.getTime()));
			emptyLedgerGroup.put("COLOR","GRAY");
			log.debug("emptyLedgerGroup.get(DATE): "+emptyLedgerGroup.get("DATE"));
			calendarDateGroup.add(emptyLedgerGroup);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		ledgerCalendarDTO.setCalendarDateGroup(calendarDateGroup);
		
		return ledgerCalendarDTO;
	}

	// 자산 목록 불러오기
	public List<Map<String, Object>> getAssetList(String user_idx) throws Exception {

		return ledgerDAO.selectAssetList(user_idx);
	}

	// 가계부 내역 입력
	public void insertLedger(LedgerInsertDTO dto) throws Exception {

		String income_and_expenses = dto.getIncome_and_expenses();

		switch (income_and_expenses) {
		case "수입":
			ledgerDAO.insertIncomeAndExpensesledger(dto);
			break;
		case "지출":
			ledgerDAO.insertIncomeAndExpensesledger(dto);
			break;
		case "이동":
			// description format 예시) 현금 → 신한은행
			dto.setDescription(dto.getFormer_asset() + " → " + dto.getLatter_asset());
			ledgerDAO.insertTransferledger(dto);
			break;
		default:
			log.debug("수입/지출/이동 외의 값이 들어왔음 -> 예외처리 배워서 적용하기");
			break;
		}

	}

	// 가계부 내역 수정
	public void updateLedger(LedgerInsertDTO dto) throws Exception {

		String income_and_expenses = dto.getIncome_and_expenses();

		switch (income_and_expenses) {
		case "수입":
			ledgerDAO.updateIncomeAndExpensesledger(dto);
			break;
		case "지출":
			ledgerDAO.updateIncomeAndExpensesledger(dto);
			break;
		case "이동":
			// description format 예시) 현금 → 신한은행
			dto.setDescription(dto.getFormer_asset() + " → " + dto.getLatter_asset());
			ledgerDAO.updateTransferledger(dto);
			break;
		default:
			log.debug("수입/지출/이동 외의 값이 들어왔음 -> 예외처리 배워서 적용하기");
			break;
		}

	}

	// 가계부 내역 삭제
	public void deleteLedger(LedgerInsertDTO dto) throws Exception {
		ledgerDAO.deleteLedger(dto);
	}
}
