package com.houseledger.app.user.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.houseledger.app.mail.dto.WriteMailDTO;
import com.houseledger.app.user.dao.FindUserInfoDAO;

@Service("findUserInfoService")
public class FindUserInfoServiceImpl implements FindUserInfoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FindUserInfoDAO findUserInfoDAO;
	
	// 이메일로 아이디 찾기
	public List<Map<String, Object>> getUserListByEmail(String user_email) throws Exception {
		return findUserInfoDAO.selectUserListByEmail(user_email);
	}
	
	// 아이디와 이메일로 유저정보 찾기
	public Map<String, Object> getUserInfo(String user_id, String user_email) throws Exception {
		
		Map<String, Object> map = findUserInfoDAO.selectUserToFindPassword(user_id, user_email);
		
		if(map == null) {
			return null;
		}
		
		return map;
	}
	
	// 임시 비밀번호 생성
	public String getRamdomPassword(int size) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };
		
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		
		int idx = 0;
		int len = charSet.length;
		
		for (int i = 0; i < size; i++) {
			idx = sr.nextInt(len);
			sb.append(charSet[idx]);
		}
		
		return sb.toString();
	}
	
	public WriteMailDTO makeTempPasswordMailDTO(String user_id, String user_email, String password) {
		WriteMailDTO dto = new WriteMailDTO();
		
		dto.setMail_to(user_email);
		dto.setMail_subject("[웹가계부] "+user_id+"님의 임시 비밀번호입니다.");
		dto.setMail_content("<div style=\"text-align: center;\">"+
		        "<h1><u>웹 가계부</u></h1>"+
		        "<h4>안녕하세요 "+user_id+"님. 요청하신 임시 비밀번호는 다음과 같습니다.</h4>"+
		            "<p>비밀번호: "+password+"</p>"+
		        "</div>");
		
		return dto;
	}
	
}
