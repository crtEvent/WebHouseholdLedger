package com.houseledger.app.my.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;

@Repository("editMyInfoDAO")
public class EditMyInfoDAO extends AbstractDAO{
	
	// 아이디 변경
	public String updateUserId(String user_id, String user_idx) throws Exception {
		HashMap<String, String> params = new HashMap();
		params.put("user_id", user_id);
		params.put("user_idx", user_idx);
		
		update("editUserInfo.updateUserId", params);
		
		return params.get("user_id");
	}
	
	// 이메일 변경
	public String updateUserEmail(String user_email, String user_idx) throws Exception {
		HashMap<String, String> params = new HashMap();
		params.put("user_email", user_email);
		params.put("user_idx", user_idx);
		
		update("editUserInfo.updateUserEmail", params);
		
		return params.get("user_email");
	}
	
	// 비밀번호 변경
	public String updateUserPassword(String user_password, String user_idx) throws Exception {
		HashMap<String, String> params = new HashMap();
		params.put("user_password", user_password);
		params.put("user_idx", user_idx);
		
		update("editUserInfo.updateUserPassword", params);
		
		return params.get("user_password");
	}
	
	// 기존 비밀번호 일치 여부 확인
	public String selectOldUserPassword(String user_idx) throws Exception {
		return (String) selectOne("editUserInfo.selectOldUserPassword", user_idx);
	}
	
	// 이메일 수신 여부 변경
	public void updateReceiveMail(String receive_mail, String user_idx) throws Exception {
		HashMap<String, String> params = new HashMap();
		params.put("receive_mail", receive_mail);
		params.put("user_idx", user_idx);
		
		update("editUserInfo.updateReceiveMail", params);
	}
	
}
