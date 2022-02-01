package com.houseledger.app.mail.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.common.board.dto.PagingDTO;
import com.houseledger.app.mail.dao.MailListDAO;
import com.houseledger.app.mail.dto.SelectMailDTO;

@Service("mailListService")
public class MailListService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "mailListDAO")
	MailListDAO mailListDAO;
	
	// mail 리스트 불러오기
	public List<Map<String, Object>> getMailList(SelectMailDTO dto) throws Exception {
		return mailListDAO.selectMailList(dto);
	}
	
	// 저장된 mail 양식 불러오기
	public List<Map<String, Object>> getStoredMailList(SelectMailDTO dto) throws Exception {
		return mailListDAO.selectStoredMailList(dto);
	}
	
	// paging 정보 불러오기
	public PagingDTO getPaging(SelectMailDTO dto, String mail_state) throws Exception {
		
		int totalNumberPosts;
		
		switch(mail_state) {
		case "SENT":
			totalNumberPosts = mailListDAO.countMailList(dto);
			break;
		case "STORED":
			totalNumberPosts = mailListDAO.countStoredMailList(dto);
			break;
		default:
			totalNumberPosts = 0;
			break;
		}
		
		PagingDTO pagingDTO = new PagingDTO(dto, totalNumberPosts);
		return pagingDTO;
	}
	
	// mail 리스트 삭제
	public void deleteMailList(int[] checkedMail) throws Exception {
		mailListDAO.deleteMailList(checkedMail);
	}

}
