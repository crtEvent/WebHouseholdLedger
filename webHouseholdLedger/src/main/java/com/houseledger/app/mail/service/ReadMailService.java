package com.houseledger.app.mail.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.mail.dao.ReadMailDAO;
import com.houseledger.app.mail.dto.MailDTO;

@Service("readMailService")
public class ReadMailService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "readMailDAO")
	ReadMailDAO readMailDAO;
	
	public MailDTO getMail(String mail_idx, String mail_state) throws Exception {
		
		// mail_idx 검증
		if(mail_idx == null) {
			return null;
		}
		
		// 삭제된 or 존재하지 않는 메일인지 검증
		
		MailDTO mailDTO = new MailDTO();
		mailDTO = readMailDAO.selectMail(mail_idx, mail_state);
		
		// 1. mail_idx가 숫자가 아니면? 어떻게 됨???
		// 에러나면 -> mail_idx 검증 null인지, 숫자인지 검증
		// 안나면 mailDTO.get~~가 null이면 return null하는걸로 하자!
		
		mailDTO.setNextMail_idx(readMailDAO.selectNextMail(mail_idx, mail_state));
		mailDTO.setPrevMail_idx(readMailDAO.selectPrevMail(mail_idx, mail_state));
		
		return mailDTO;
	}
}
