package com.houseledger.app.my.service;

public interface EditMyInfoService {
	
	// 유저 이미지 변경
	public String editUserImage(String user_image, String user_idx) throws Exception;
	
	// 아이디 변경
	public String editUserId(String user_id, String user_idx) throws Exception;
	
	// 이메일 변경
	public String editUserEmail(String user_email, String user_idx) throws Exception;	
	
	// 기존 비밀번호 기존 비밀번호 일치여부 확인
	public boolean checkOldUserPassword(String old_user_password, String user_idx) throws Exception;
	
	// 비밀번호 변경
	public String editUserPassword(String new_user_password, String user_idx) throws Exception;
	
	// 이메일 수신 여부 변경
	public void editReceiveMail(String receive_mail, String user_idx) throws Exception;
}
