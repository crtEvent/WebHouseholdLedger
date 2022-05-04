package com.houseledger.app.user.service;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import com.houseledger.app.user.vo.SignUpVO;

public interface SignUpService {
	
	public String executeSignUp(SignUpVO signUpVO) throws Exception;
	
	public void insertCashAsset(String user_idx) throws Exception;
	
	public boolean checkDuplicateUserId(String user_id) throws Exception;
	
	public boolean validateHandling(Errors errors, Model model) throws Exception;

}
