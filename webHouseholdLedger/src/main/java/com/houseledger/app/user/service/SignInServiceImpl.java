package com.houseledger.app.user.service;

import javax.annotation.Resource;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.user.dao.SignInDAO;
import com.houseledger.app.user.vo.SignInVO;
import com.houseledger.app.user.vo.UserVO;

@Service("signInService")
public class SignInServiceImpl implements SignInService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="signInDAO")
	SignInDAO signInDAO;
	
	// 로그인(아이디, 비밀번호 체크)
	public UserVO excuteSignIn(SignInVO signInVO) throws Exception {
		
		UserVO userVO = signInDAO.selectUserForSignIn(signInVO.getUser_id());
		
		if(userVO != null) {
			
			if(BCrypt.checkpw(signInVO.getUser_password(), userVO.getUser_password())) {
				return userVO;
			}
			
		}
		return null;
	}
	
}
