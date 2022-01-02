package com.houseledger.app.my.service;

import javax.annotation.Resource;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.my.dao.EditMyInfoDAO;


@Service("editMyInfoService")
public class EditMyInfoServiceImpl implements EditMyInfoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "editMyInfoDAO")
	EditMyInfoDAO editMyInfoDAO;
	
	// 아이디 변경 & 변경된 아이디 리턴
	public String editUserId(String user_id, String user_idx) throws Exception {
		return editMyInfoDAO.updateUserId(user_id, user_idx);
	}
	
	// 이메일 변경 & 변경된 이메일 리턴
	public String editUserEmail(String user_email, String user_idx) throws Exception {
		return editMyInfoDAO.updateUserEmail(user_email, user_idx);
	}
	
	// 기존 비밀번호 기존 비밀번호 일치여부 확인
	public boolean checkOldUserPassword(String old_user_password, String user_idx) throws Exception {
		
		if(BCrypt.checkpw(old_user_password, editMyInfoDAO.selectOldUserPassword(user_idx))) {
			// 기존 비밀번호가 일치하면 - true 반환
			return true;
		}
		// 기존 비밀번호가 일치하지 않으면 - false 반환
		return false;
	}
	
	
	// 비밀번호 변경 & 변경된 비밀번호 리턴
	public String editUserPassword(String new_user_password, String user_idx) throws Exception {
		
		// 비밀번호 변경 & 변경된 비밀번호 리턴
		return editMyInfoDAO.updateUserPassword(BCrypt.hashpw(new_user_password, BCrypt.gensalt()), user_idx);
	}
	
	// 이메일 수신 여부 변경
	public void editReceiveMail(String receive_mail, String user_idx) throws Exception {
		editMyInfoDAO.updateReceiveMail(receive_mail, user_idx);
	}
	
}
