package com.houseledger.app.user.service;

import java.util.List;
import java.util.Map;

import com.houseledger.app.mail.dto.WriteMailDTO;

public interface FindUserInfoService {
	
	public List<Map<String, Object>> getUserListByEmail(String user_email) throws Exception;
	public Map<String, Object> getUserInfo(String user_id, String user_email) throws Exception;
	public String getRamdomPassword(int size);
	public WriteMailDTO makeTempPasswordMailDTO(String user_id, String user_email, String password);
}
