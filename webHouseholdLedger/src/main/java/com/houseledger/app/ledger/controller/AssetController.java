package com.houseledger.app.ledger.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.ledger.dto.AssetInsertDTO;
import com.houseledger.app.ledger.dto.AssetUpdateDTO;
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
	
	// [기능]: 자산 정보 불러오기
	@RequestMapping(value="/ledger/getAssetOne.do")
	@ResponseBody
	public Map<String, Object> getAssetOne(String asset_idx, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		return assetService.getAssetOne(asset_idx, userVO.getUser_idx());
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
	
	// [기능]: 통장 자산 목록 불러오기
	@RequestMapping(value="/ledger/getBankAssetList.do")
	@ResponseBody
	public List<Map<String, Object>> getBankAssetList(@SessionAttribute("userSession")UserVO userVO) throws Exception {
		return assetService.getBankAssetList(userVO.getUser_idx());
	}
	
	// [기능]: 자산 추가하기
	@RequestMapping(value="/ledger/insertAsset.do")
	public String insertAsset(AssetInsertDTO dto , @SessionAttribute("userSession")UserVO userVO, HttpServletRequest request) throws Exception {
		
		dto.setUser_idx(userVO.getUser_idx());
		
		assetService.insertAsset(dto);
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	// [기능]: 자산 수정하기
	@RequestMapping(value="/ledger/updateAsset.do")
	public String updateAsset(AssetUpdateDTO dto , @SessionAttribute("userSession")UserVO userVO, HttpServletRequest request) throws Exception {
		
		dto.setUser_idx(userVO.getUser_idx());
		
		assetService.updateAsset(dto);
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	// [기능]: 자산 삭제하기
	@RequestMapping(value="/ledger/deleteAsset.do")
	public String deleteAsset(String asset_idx, @SessionAttribute("userSession")UserVO userVO, HttpServletRequest request) throws Exception {
		
		assetService.deleteAsset(asset_idx, userVO.getUser_idx());
		
		// 이전 페이지 URL
		String referer = request.getHeader("Referer");
				
		return "redirect:"+referer;
	}
}
