package com.houseledger.app.ledger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LedgerDetailsContoller {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/ledger/details.do")
	public String ledger_details(Model model) throws Exception {
		
		
		
		return "ledger/ledger_main";
	}

}
