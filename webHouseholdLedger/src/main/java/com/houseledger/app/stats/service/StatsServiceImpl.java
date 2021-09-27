package com.houseledger.app.stats.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.stats.dao.StatsDAO;
import com.houseledger.app.stats.dto.StatsByCategoryDTO;
import com.houseledger.app.stats.dto.StatsSelectDTO;
import com.houseledger.app.stats.dto.StatsYearlyDTO;

@Service("statsService")
public class StatsServiceImpl implements StatsService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "statsDAO")
	StatsDAO statsDAO;
	
	// category별 통계
	public StatsByCategoryDTO getStatsByCategory(StatsSelectDTO dto, String user_idx) throws Exception {
		
		dto.setUser_idx(user_idx);
		
		Calendar calendar = Calendar.getInstance();
		int year, month;
		
		// date값이 없을 경우 자동 설정  || date 형식이 yyyy-MM이 아닐 경우
		if (dto.getDate() == null || !checkDateFommat(dto.getDate(),"yyyy-MM")) {
			LocalDate now = LocalDate.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
			dto.setDate(now.format(dateTimeFormatter));
		}
		
		// start_date, end_date값 설정
		year = Integer.parseInt(dto.getDate().substring(0, 4));
		month = Integer.parseInt(dto.getDate().substring(5)) - 1;
		calendar.set(year, month, 1);
		
		dto.setStart_date(dto.getDate()+"-01");
		dto.setEnd_date(dto.getDate()+"-"+calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		
		
		log.debug(dto.getStart_date()+", "+dto.getEnd_date());
		
		StatsByCategoryDTO statsByCategoryDTO = new StatsByCategoryDTO();
		
		statsByCategoryDTO.setDate(dto.getDate());
		
		List<Map<String, Object>> statsByExpensesCategoryList  = statsDAO.selectStatsByExpensesCategory(dto);
		List<Map<String, Object>> statsByIncomeCategoryList = statsDAO.selectStatsByIncomeCategory(dto);
		
		statsByCategoryDTO.setStatsByExpensesCategoryList(statsByExpensesCategoryList);
		statsByCategoryDTO.setStatsByIncomeCategoryList(statsByIncomeCategoryList);
		
		return statsByCategoryDTO;
	}
	
	// 연간 통계
	public StatsYearlyDTO getStatsYearly(StatsSelectDTO dto, String user_idx) throws Exception {
		
		dto.setUser_idx(user_idx);
		
		StatsYearlyDTO statsYearlyDTO = new StatsYearlyDTO();
		
		Map<String, Object> statsYearlyByExpenses = statsDAO.selectStatsYearlyByExpenses(dto);
		Map<String, Object> statsYearlyByIncome = statsDAO.selectStatsYearlyByIncome(dto);
		
		statsYearlyDTO.setStatsYearlyByExpenses(statsYearlyByExpenses);
		statsYearlyDTO.setStatsYearlyByIncome(statsYearlyByIncome);
		
		return statsYearlyDTO;
	}
	
	// Date 형식 체크
	public boolean checkDateFommat(String date, String fommat) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(fommat);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
}
