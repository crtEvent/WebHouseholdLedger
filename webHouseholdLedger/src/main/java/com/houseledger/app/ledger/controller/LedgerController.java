package com.houseledger.app.ledger.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.ledger.dto.LedgerInsertDTO;
import com.houseledger.app.ledger.service.LedgerService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class LedgerController {
	
Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="ledgerService")
	LedgerService ledgerService;
	
	@RequestMapping(value="/ledger/insert_ledger.do")
	public String insert_ledger(Model model, LedgerInsertDTO ledgerInsertDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		ledgerInsertDTO.setUser_idx(userVO.getUser_idx());
		
		ledgerService.insertLedger(ledgerInsertDTO);
		
		return "redirect:/ledger/details.do";
	}
	
	@RequestMapping(value="/ledger/update_ledger.do")
	public String update_ledger(Model model, LedgerInsertDTO ledgerInsertDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		ledgerInsertDTO.setUser_idx(userVO.getUser_idx());
		
		ledgerService.updateLedger(ledgerInsertDTO);
		
		return "redirect:/ledger/details.do";
	}
	
	@RequestMapping(value="/ledger/delete_ledger.do")
	public String delete_ledger(Model model, LedgerInsertDTO ledgerInsertDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		ledgerInsertDTO.setUser_idx(userVO.getUser_idx());
		
		ledgerService.deleteLedger(ledgerInsertDTO);
		
		return "redirect:/ledger/details.do";
	}
	
}
