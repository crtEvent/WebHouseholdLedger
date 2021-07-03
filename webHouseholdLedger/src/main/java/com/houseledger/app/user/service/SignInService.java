package com.houseledger.app.user.service;

import com.houseledger.app.user.vo.SignInVO;
import com.houseledger.app.user.vo.UserVO;

public interface SignInService {
	
	public UserVO excuteSignIn(SignInVO signInVO) throws Exception;

}
