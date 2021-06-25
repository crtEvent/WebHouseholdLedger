package com.houseledger.app.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	// 로그인 페이지로 이동
	@RequestMapping(value="/user/signin.do")
	public String sign_in(Model model) throws Exception {
		
		return "user/sign_in";
	}

}
