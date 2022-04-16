package com.houseledger.app.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseledger.app.mail.service.SendMailService;
import com.houseledger.app.my.service.EditMyInfoService;
import com.houseledger.app.user.service.FindUserInfoService;

@Controller
public class FindUserInfoController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="findUserInfoService")
	FindUserInfoService findUserInfoService;
	
	@Resource(name="editMyInfoService")
	EditMyInfoService editMyInfoService;
	
	@Autowired
	SendMailService sendMailService;
	
	// [페이지]: 아이디/비밀번호 찾기 페이지
	@RequestMapping(value="/user/findUserInfo.do")
	public String findUserInfo(Model model) throws Exception {
		
		return "user/find_user_info";
	}
	
	// [기능]: 아이디 찾기
	@RequestMapping("/user/findUserID.do")
	@ResponseBody
	public List<Map<String, Object>> findUserID(String user_email) throws Exception {
		
		List<Map<String, Object>> list = findUserInfoService.getUserListByEmail(user_email);
		
		if(list.size() == 0) {
			return null;
		}
		
		return list;
	}
	
	// [기능]: 비밀번호 찾기
	@RequestMapping("/user/findUserPW.do")
	@ResponseBody
	public boolean findUserPW(String user_id, String user_email) {
		
		try {
			// id, email로 유저 정보 찾기
			Map<String, Object> map = findUserInfoService.getUserInfo(user_id, user_email);
			
			// user 정보가 일치하지 않으면 false 반환
			if(map == null) {
				return false;
			}
			
			// 임시 비밀번호 생성
			String temp_pw = findUserInfoService.getRamdomPassword(10);
			
			// 비밀번호 변경
			editMyInfoService.editUserPassword(temp_pw, Integer.toString((int) map.get("USER_IDX")));
			
			// 메일 보내기
			sendMailService.sendMail(findUserInfoService.makeTempPasswordMailDTO(user_id, user_email, temp_pw));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
