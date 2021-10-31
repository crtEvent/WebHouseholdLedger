package com.houseledger.app.qnaboard.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaPostDAO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;

@Service("qnaPostService")
public class QnaPostServiceImpl implements QnaPostService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaPostDAO")
	QnaPostDAO qnaPostDAO;
	
	public QnaPostDTO getQnaPost(String board_idx) throws Exception {
		
		// board_idx 검증
		if(board_idx == null) {
			return null;
		}
		// 삭제된 게시글 or 존재하지 않는 게시글 검증
		String checkDelete_yn = qnaPostDAO.checkDelete_yn(board_idx);
		
		if(checkDelete_yn.equals("Y") || checkDelete_yn == null) {
			return null;
		}
		
		QnaPostDTO qnaPostDTO = new QnaPostDTO();
		qnaPostDTO = qnaPostDAO.selectQnaPost(board_idx);
		
		qnaPostDTO.setNextBoard_idx(qnaPostDAO.selectNextQnaPost(board_idx));
		qnaPostDTO.setPrevBoard_idx(qnaPostDAO.selectPrevQnaPost(board_idx));
		
		return qnaPostDTO;
	}

}
