package com.houseledger.app.stats.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.stats.dto.StatsSelectDTO;

@Repository("statsDAO")
public class StatsDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStatsByExpensesCategory(StatsSelectDTO dto) throws Exception{
		dto.setIncome_and_expenses("지출");
		return (List<Map<String, Object>>) selectList("stats.selectStatsByCategory", dto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStatsByIncomeCategory(StatsSelectDTO dto) throws Exception{
		dto.setIncome_and_expenses("수입");
		return (List<Map<String, Object>>) selectList("stats.selectStatsByCategory", dto);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectStatsYearlyByExpenses(StatsSelectDTO dto) throws Exception{
		dto.setIncome_and_expenses("지출");
		return (Map<String, Object>) selectOne("stats.selectStatsYearly", dto);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectStatsYearlyByIncome(StatsSelectDTO dto) throws Exception{
		dto.setIncome_and_expenses("수입");
		return (Map<String, Object>) selectOne("stats.selectStatsYearly", dto);
	}
}
