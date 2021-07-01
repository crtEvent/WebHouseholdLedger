package com.houseledger.app.user.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.user.vo.SignUpVO;

@Repository("signUpDAO")
public class SignUpDAO extends AbstractDAO{
	
	// SignUp - 회원가입
	public void insertUser(SignUpVO signUpVO) {
		insert("signUp.insertUser", signUpVO);
	}
	
	// 아이디 중복 체크
	public int checkDuplicateUserId(String user_id) {
		return (Integer) selectOne("signUp.checkDuplicateUserId", user_id);
	}
	
}
