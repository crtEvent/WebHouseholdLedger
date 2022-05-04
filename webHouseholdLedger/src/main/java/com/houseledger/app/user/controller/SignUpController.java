package com.houseledger.app.user.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.houseledger.app.user.service.SignUpService;
import com.houseledger.app.user.vo.SignUpVO;

@Controller
public class SignUpController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="signUpService")
	SignUpService signUpService;
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/user/signup.do")
	public String sign_up(Model model) throws Exception {
		
		return "user/sign_up";
	}
	
	// 회원가입 기능
	@PostMapping(value="/user/excute_signup.do")
	public String excute_sign_up(Model model, @Valid SignUpVO signUpVO, Errors errors) throws Exception {
		
		log.debug("회원가입 Controller 진입");
		
		// 아이디 중복 체크
		if(signUpService.checkDuplicateUserId(signUpVO.getUser_id())) {
			// 회원가입 실패
			model.addAttribute("valid_user_id", "중복된 아이디 입니다. 다시 입력해 주세요.");
			return "user/sign_up";
		}
		
		// SignUpVO 유효성 검사 에러 체크
		if(!signUpService.validateHandling(errors, model)) {
			// 회원가입 실패
			return "user/sign_up";
		}
		
		// insert 회원가입 후 user_idx 가져오기
		String user_idx = signUpService.executeSignUp(signUpVO);
		
		// 현금자산 추가
		signUpService.insertCashAsset(user_idx);
		
		// 회원가입 성공
		return "redirect:/user/signin.do";
	}

}
