package com.houseledger.app.user.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houseledger.app.user.service.SignUpService;

@Controller
public class CheckUserValidationController {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "signUpService")
	SignUpService signUpService;

	// user_id 체크
	@RequestMapping(value = "/user/checkID.do", method = RequestMethod.GET)
	@ResponseBody
	public int checkUser_id(@RequestParam String user_id) throws Exception {

		int returnData;
		// user_id: 영문자&숫자 조합, 5-20자
		String regex = "^(?=.*\\d)(?=.*[a-zA-Z]).{5,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user_id);

		if (matcher.find()) { // id 정책에 부합

			// user_id 중복 체트
			if (signUpService.checkDuplicateUserId(user_id)) {
				// user_id 중복
				returnData = 1;
			} else {
				// user_id 사용 가능
				returnData = 2;
			}

		} else { // id 정책 오류
			returnData = 0;
		}

		log.debug("returnData: " + returnData);
		// returnData - 0: 정책 오류, 1: ID중복 오류, 2: 사용가능한 ID
		return returnData;
	}

	// user_pw 체크
	@RequestMapping(value = "/user/checkPW.do", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkUser_pw(@RequestParam String user_pw) throws Exception {

		// user_pw: // 영문자&숫자&특수문자 조합, 5-25자
		String regex = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{5,25}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user_pw);

		if (matcher.find()) { // PW 정책에 부합
			return true;
		} else { // PW 정책 오류
			return false;
		}
	}

	// user_email 체크
	@RequestMapping(value = "/user/checkEmail.do", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkUser_email(@RequestParam String user_email) throws Exception {

		String regex = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(user_email);

		if (matcher.find()) { // email 형식에 부합
			return true;
		} else { // email 형식 오류
			return false;
		}
	}

}
