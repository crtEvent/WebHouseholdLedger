package com.houseledger.app.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseledger.app.my.service.EditMyInfoService;

@Controller
@RequestMapping("/admin")
public class EditUserInfoController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "editMyInfoService")
	EditMyInfoService editMyInfoService;

	// 아이디 수정
	@RequestMapping("/editUserID.do")
	@ResponseBody
	public String editUserID(@RequestParam String user_id, @RequestParam String user_idx) throws Exception {

		// 아이디 수정 & 수정된 아이디 값 받아오기
		String changedUser_id = editMyInfoService.editUserId(user_id, user_idx);

		return changedUser_id;
	}

	// 이메일 수정
	@RequestMapping("/editUserEmail.do")
	@ResponseBody
	public String editUserEmail(@RequestParam String user_email, @RequestParam String user_idx) throws Exception {

		// 이메일 수정 & 수정된 이메일 값 받아오기
		String changedUser_email = editMyInfoService.editUserEmail(user_email, user_idx);

		return changedUser_email;
	}

	// 비밀번호 수정
	@RequestMapping("/editUserPassword.do")
	@ResponseBody
	public void editUserPassword(@RequestParam String new_user_password, @RequestParam String user_idx)
			throws Exception {

		// 비밀번호 변경 & 변경된 비밀번호 값(암호화 된 상태) 받아오기
		editMyInfoService.editUserPassword(new_user_password, user_idx);

	}

	// 이메일 수신 여부 변경
	@RequestMapping("/editReceiveMail.do")
	@ResponseBody
	public void editReceiveMail(@RequestParam String receive_mail, @RequestParam String user_idx) throws Exception {

		// 이메일 수신 여부 변경(receive_mail값을 DB에 그대로 입력)
		editMyInfoService.editReceiveMail(receive_mail, user_idx);

	}
}
