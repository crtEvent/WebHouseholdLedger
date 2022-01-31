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
	
	public PagingDTO getPaging(SelectMailDTO dto) throws Exception {
		int totalNumberPosts = mailListDAO.countMailList(dto);
		PagingDTO pagingDTO = new PagingDTO(dto, totalNumberPosts);
		return pagingDTO;
	}

}
