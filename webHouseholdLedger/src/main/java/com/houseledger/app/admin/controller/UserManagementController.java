package com.houseledger.app.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseledger.app.admin.dto.SelectUserListDTO;
import com.houseledger.app.admin.dto.UserManagementInfoDTO;
import com.houseledger.app.admin.service.UserManagementService;

@Controller
public class UserManagementController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="userManagementService")
	UserManagementService userManagementService;
	
	// 회원 정보 리스트
	@RequestMapping("/admin/getUserManagementList.do")
	@ResponseBody
	public List<UserManagementInfoDTO> getUserManagementTable(SelectUserListDTO selectUserListDTO) throws Exception {
		
		List<UserManagementInfoDTO> userList = userManagementService.getuserList(selectUserListDTO);
		
		return userList;
	}
	
	// 회원 관리 페이지
	@RequestMapping("/admin/userManagement.do")
	public String userManagement(SelectUserListDTO selectUserListDTO, Model model) throws Exception {
		
		if(selectUserListDTO.getStart_date() != null && selectUserListDTO.getStart_date() != "") {
			model.addAttribute("start_date", selectUserListDTO.getStart_date());
			model.addAttribute("end_date", selectUserListDTO.getEnd_date());
		}
		
		return "admin/admin_user_management";
	}
	
	
}
