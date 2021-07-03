package com.houseledger.app.user.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.user.vo.UserVO;

@Repository("signInDAO")
public class SignInDAO extends AbstractDAO{
	
	// 로그인
	public UserVO selectUserForSignIn(String user_id) {
		return (UserVO) selectOne("signIn.selectUser", user_id);
	}

}
