package com.houseledger.app.ledger.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.ledger.dto.LedgerSelectDTO;
import com.houseledger.app.ledger.service.LedgerService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class LedgerDetailsContoller {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="ledgerService")
	LedgerService ledgerService;
	
	// [페이지]: 가계부 내역 페이지
	@RequestMapping(value="/ledger/details.do")
	public String ledger_details(Model model, LedgerSelectDTO ledgerSelectDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		ledgerSelectDTO.setUser_idx(userVO.getUser_idx());
		
		model.addAttribute("ledgerDetailDTO", ledgerService.getLedgerDetails(ledgerSelectDTO));
		model.addAttribute("assetList", ledgerService.getAssetList(userVO.getUser_idx()));
		
		return "ledger/ledger_details";
	}
	
	// [기능]: 가계부 엑셀 다운로드
	@RequestMapping(value="/ledger/download_excel.do")
	public void download_excel(HttpServletResponse response, LedgerSelectDTO dto, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		dto.setUser_idx(userVO.getUser_idx());
		ledgerService.ledgerDownloadToExcel(response, dto);
		
	}

}
