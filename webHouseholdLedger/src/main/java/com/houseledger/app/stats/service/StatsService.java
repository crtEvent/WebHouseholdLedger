package com.houseledger.app.stats.service;

import com.houseledger.app.stats.dto.StatsByCategoryDTO;
import com.houseledger.app.stats.dto.StatsSelectDTO;
import com.houseledger.app.stats.dto.StatsYearlyDTO;

public interface StatsService {
	
	public StatsByCategoryDTO getStatsByCategory(StatsSelectDTO dto, String user_idx) throws Exception;
	
	public StatsYearlyDTO getStatsYearly(StatsSelectDTO dto, String user_idx) throws Exception;

}
