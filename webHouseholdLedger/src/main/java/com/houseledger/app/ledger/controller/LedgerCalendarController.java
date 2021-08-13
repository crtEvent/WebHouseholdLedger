package com.houseledger.app.ledger.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.ledger.dto.LedgerCalendarDTO;
import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.service.LedgerService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class LedgerCalendarController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="ledgerService")
	LedgerService ledgerService;
	
	@RequestMapping(value="/ledger/calendar.do")
	public String ledger_calendar(Model model, LedgerSelectDTO ledgerSelectDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		ledgerSelectDTO.setUser_idx(userVO.getUser_idx());
		
		model.addAttribute("ledgerCalendarDTO", ledgerService.getLedgerCalendar(ledgerSelectDTO));
		model.addAttribute("assetList", ledgerService.getAssetList(userVO.getUser_idx()));
		
		return "ledger/ledger_calendar";
	}

}
