package com.houseledger.app.qnaboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaCommentDAO;

@Service("qnaCommentService")
public class QnaCommentServiceImpl {
	
Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaCommentDAO")
	QnaCommentDAO qnaCommentDAO;
	
	public List<Map<String, Object>> getQnaCommentList(String board_idx) throws Exception {
		return qnaCommentDAO.selectQnaCommentList(board_idx);
	}
	
	
}
