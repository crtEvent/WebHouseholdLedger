package com.houseledger.app.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.houseledger.app.ledger.dao.AssetDAO;
import com.houseledger.app.ledger.dto.AssetInsertDTO;
import com.houseledger.app.user.dao.SignUpDAO;
import com.houseledger.app.user.vo.SignUpVO;

@Service("signUpService")
public class SignUpServiceImpl implements SignUpService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="signUpDAO")
	SignUpDAO signUpDAO;
	
	@Resource(name = "assetDAO")
	AssetDAO assetDAO;
	
	// 회원가입
	public String executeSignUp(SignUpVO signUpVO) throws Exception{
		
		// 비밀번호 암호화
		signUpVO.setUser_password(BCrypt.hashpw(signUpVO.getUser_password(), BCrypt.gensalt()));
		
		// 회원정보 INSERT
		signUpDAO.insertUser(signUpVO);
		
		// insert 후 user_idx 추출 (useGeneratedKeys="true" keyProperty="user_idx")
		return signUpVO.getUser_idx();
		
	}
	
	// 현금 자산 넣기
	public void insertCashAsset(String user_idx) throws Exception {
		AssetInsertDTO dto = new AssetInsertDTO();
		dto.setUser_idx(user_idx);
		dto.setAsset_order("1");
		dto.setAsset_name("현금");
		dto.setAsset_type("현금");
		dto.setInitial_amount("0");
		
		assetDAO.insertAsset(dto);
	}
	
	// 아이디 중복 체크
	public boolean checkDuplicateUserId(String user_id) throws Exception {
		
		if(signUpDAO.checkDuplicateUserId(user_id) == 0) {
			// id 중복 아닐 경우 false
			return false;
		}
		// id 중복일 경우 true
		return true;
	}
	
	// 유효성 검사 에러 체크
	public boolean validateHandling(Errors errors, Model model) throws Exception {
		
		if(errors.hasErrors()) {
		
			Map<String, String> validatorResult = new HashMap<String, String>();
			
			for(FieldError error : errors.getFieldErrors()) {
				// validKeyName 형식: valid_필드명 (예시: valid_user_id)
				String validKeyName = String.format("valid_%s", error.getField());
				validatorResult.put(validKeyName, error.getDefaultMessage());
			}
			
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
				log.debug("key: "+key+", result: "+validatorResult.get(key));
			}
			
			return false;
		}
		
		// 에러 없음
		return true;
	}
}
