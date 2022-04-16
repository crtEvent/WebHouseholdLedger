package com.houseledger.app.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;

@Repository("findUserInfoDAO")
public class FindUserInfoDAO extends AbstractDAO {
	
	// 이메일로 아이디 찾기(list)
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectUserListByEmail(String user_email) throws Exception {
		return selectList("findUserInfo.selectUserListByEmail", user_email);
	}
	
	// 비밀번호 찾기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectUserToFindPassword(String user_id, String user_email) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("user_email", user_email);
		return (Map<String, Object>) selectOne("findUserInfo.selectUserToFindPassword", map);
	}
	
}
