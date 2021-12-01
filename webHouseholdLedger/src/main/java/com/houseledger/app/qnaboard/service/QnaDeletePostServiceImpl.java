package com.houseledger.app.qnaboard.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaDeletePostDAO;

@Service("qnaDeletePostService")
public class QnaDeletePostServiceImpl implements QnaDeletePostService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaDeletePostDAO")
	QnaDeletePostDAO qnaDeletePostDAO;
	
	// 게시글 삭제
	public void deleteQnaPost(String board_idx) throws Exception {
		qnaDeletePostDAO.deleteQnaPost(board_idx);
	}
	
	// 게시글 글쓴이 검색
	public String selectUserIdxOfPost(String board_idx) throws Exception {
		return qnaDeletePostDAO.selectUserIdxOfPost(board_idx);
	}
	
	
	
	
}
