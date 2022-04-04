package com.houseledger.app.ledger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.ledger.service.AssetService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class AssetController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AssetService assetService;
	
	// [페이지]: 자산 목록 관리 페이지
	@RequestMapping(value="/ledger/editMyAsset.do")
	public String assetMain(Model model, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		model.addAttribute("assetDetailedList", assetService.getAssetDetailedList(userVO.getUser_idx()));
		
		return "ledger/edit_my_asset";
	}
	
	// [기능]: 자산 순서 바꾸기 - 순위 Up
	@RequestMapping(value="/ledger/changeAssetOrderUp.do")
	@ResponseBody
	public boolean changeAssetOrderUp(String asset_idx, @SessionAttribute("userSession")UserVO userVO) {
		
		try {
			assetService.changeAssetOrderUp(asset_idx, userVO.getUser_idx());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	// [기능]: 자산 순서 바꾸기 - 순위 Down
	@RequestMapping(value="/ledger/changeAssetOrderDown.do")
	@ResponseBody
	public boolean changeAssetOrderDown(String asset_idx, @SessionAttribute("userSession")UserVO userVO) {
		
		try {
			assetService.changeAssetOrderDown(asset_idx, userVO.getUser_idx());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
