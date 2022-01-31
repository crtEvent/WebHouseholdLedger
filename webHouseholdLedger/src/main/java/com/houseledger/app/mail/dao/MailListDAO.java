package com.houseledger.app.mail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.mail.dto.SelectMailDTO;

@Repository("mailListDAO")
public class MailListDAO extends AbstractDAO {
	
	public int countMailList(SelectMailDTO dto) throws Exception {
		return (int) selectOne("mailList.countMailList", dto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMailList(SelectMailDTO dto) throws Exception {	
		return (List<Map<String, Object>>) selectList("mailList.selectMailList", dto);
	}
	
}
