package com.houseledger.app.mail.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.mail.dto.MailDTO;

@Repository("readMailDAO")
public class ReadMailDAO extends AbstractDAO {

	 public MailDTO selectMail(String mail_idx, String mail_state) throws Exception {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("mail_idx", mail_idx);
		 map.put("mail_state", mail_state);
		 return (MailDTO) selectOne("readMail.selectMail", map);
	 }
	 
	 public String selectNextMail(String mail_idx, String mail_state) throws Exception {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("mail_idx", mail_idx);
		 map.put("mail_state", mail_state);
		 return (String) selectOne("readMail.selectNextMail", map);
	 }
	 
	 public String selectPrevMail(String mail_idx, String mail_state) throws Exception {
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("mail_idx", mail_idx);
		 map.put("mail_state", mail_state);
		 return (String) selectOne("readMail.selectPrevMail", map);
	 }
}
