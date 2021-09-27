package com.houseledger.app.stats.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.stats.dto.StatsSelectDTO;
import com.houseledger.app.stats.service.StatsService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class StatsController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="statsService")
	StatsService statsService;
	
	@RequestMapping(value="/stats/category.do")
	public String stats_category(Model model, StatsSelectDTO dto, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		model.addAttribute("statsByCategoryDTO", statsService.getStatsByCategory(dto, userVO.getUser_idx()));
		
		return "/stats/stats_category";
	}
	
	@RequestMapping(value="/stats/yearly.do")
	public String stats_yearly(Model model, StatsSelectDTO dto, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		model.addAttribute("statsYearlyDTO", statsService.getStatsYearly(dto, userVO.getUser_idx()));
		
		return "/stats/stats_main";
	}

}
