package com.houseledger.app.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseledger.app.user.service.SignInService;
import com.houseledger.app.user.vo.SignInVO;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class SignInController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="signInService")
	SignInService signInService;
	
	// 로그인 페이지로 이동
	@RequestMapping(value="/user/signin.do")
	public String sign_in(Model model) throws Exception {
		
		return "user/sign_in";
	}
	
	// 로그인 기능
	@RequestMapping(value="/user/excute_signin.do")
	public String excute_sign_in(Model model, SignInVO signInVO, HttpSession session) throws Exception {
		
		UserVO userVO = signInService.excuteSignIn(signInVO);
		
		if(userVO != null) {
			session.setAttribute("userSession", userVO);
			log.debug("SignIn 성공, user_id: "+userVO.getUser_id());
			
			return "redirect:/ledger/details.do";
		}
		
		model.addAttribute("signInError", "ID 또는 Password가 일치하지 않습니다.");
		
		return "user/sign_in";
	}
	
	// 로그아웃
	@RequestMapping(value="/user/excute_signout.do")
	public String signOut(Model model, HttpSession session) throws Exception{
		
		session.removeAttribute("userSession");
		
		return "user/sign_in";
	}

}
