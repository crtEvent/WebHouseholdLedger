package com.houseledger.app.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseledger.app.user.vo.SignUpVO;

@Controller
public class SIgnUpController {
	
Logger log = LoggerFactory.getLogger(this.getClass());
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/user/signup.do")
	public String sign_up(Model model) throws Exception {
		
		return "user/sign_up";
	}
	
	// 회원가입 기능
	@PostMapping(value="/user/excute_signup.do")
	public String excute_sign_up(Model model, @Valid SignUpVO signUpVO) throws Exception {
		
		log.debug("회원가입 Controller 진입");
		
		
		
		return "";
	}

}
