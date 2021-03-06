package com.houseledger.app.mail.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.mail.dto.MailDTO;
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
	
	public int countStoredMailList(SelectMailDTO dto) throws Exception {
		return (int) selectOne("mailList.countStoredMailList", dto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectStoredMailList(SelectMailDTO dto) throws Exception {	
		return (List<Map<String, Object>>) selectList("mailList.selectStoredMailList", dto);
	}
	
	@SuppressWarnings("unchecked")
	public List<MailDTO> selectStoredMailListAll() throws Exception {	
		return (List<MailDTO>) selectList("mailList.selectStoredMailListAll");
	}
	
	public void deleteMailList(int[] checkedMail) throws Exception {
		update("mailList.deleteMailList", checkedMail);
	}
	
}
