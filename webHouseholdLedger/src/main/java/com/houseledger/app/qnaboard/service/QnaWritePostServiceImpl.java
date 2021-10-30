package com.houseledger.app.qnaboard.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaWritePostDAO;
import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;

@Service("qnaWritePostService")
public class QnaWritePostServiceImpl implements QnaWritePostService {
	
	@Resource(name = "qnaWritePostDAO")
	QnaWritePostDAO qnaWritePostDAO;
	
	public void insertQnaPost(WriteQnaPostDTO writeQnaPostDTO) throws Exception {
		qnaWritePostDAO.insertQnaPost(writeQnaPostDTO);
	}
	

}
