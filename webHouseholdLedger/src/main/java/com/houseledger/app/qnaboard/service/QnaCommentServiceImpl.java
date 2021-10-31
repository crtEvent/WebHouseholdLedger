package com.houseledger.app.qnaboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaCommentDAO;
import com.houseledger.app.qnaboard.dto.WriteQnaCommentDTO;

@Service("qnaCommentService")
public class QnaCommentServiceImpl implements QnaCommentService {
	
Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaCommentDAO")
	QnaCommentDAO qnaCommentDAO;
	
	public List<Map<String, Object>> getQnaCommentList(String board_idx) throws Exception {
		return qnaCommentDAO.selectQnaCommentList(board_idx);
	}
	
	public void writeQnaComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		
		// bundle_idx 구하기
		writeQnaCommentDTO.setBundle_idx(qnaCommentDAO.selectNextBundle_idx());
		
		// 댓글 등록
		qnaCommentDAO.insertQnaComment(writeQnaCommentDTO);
	}
	
	public void writeQnaReComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		
		// bundle_idx 구하기
		writeQnaCommentDTO.setBundle_idx(qnaCommentDAO.selectParentBundle_idx(writeQnaCommentDTO.getParent_comment_idx()));
		
		// 댓글 등록
		qnaCommentDAO.insertQnReComment(writeQnaCommentDTO);
	}
	
}
