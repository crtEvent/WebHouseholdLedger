package com.houseledger.app.qnaboard.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaEditPostDAO;
import com.houseledger.app.qnaboard.dao.QnaPostDAO;
import com.houseledger.app.qnaboard.dto.EditQnaPostDTO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;

@Service("qnaEditPostService")
public class QnaEditPostServiceImpl implements QnaEditPostService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaEditPostDAO")
	QnaEditPostDAO qnaEditPostDAO;
	
	@Resource(name = "qnaPostDAO")
	QnaPostDAO qnaPostDAO;
	
	// 게시글 글쓴이 아이디 체크
	public boolean checkWriterUserIdx(String board_idx, String user_idx) throws Exception {
		
		if(qnaEditPostDAO.selectUserIdxOfPost(board_idx).equals(user_idx)) {
			// 게시글 글쓴의 user_idx와 게시글 수정 페이지에 접근하려는 유저의 user_idx가 일치할 경우: true
			return true;
		}
		return false;
	}
	
	
	public QnaPostDTO getQnaPostForEdit(String board_idx) throws Exception {
		
		// 삭제된 게시글 or 존재하지 않는 게시글 검증
		String checkDelete_yn = qnaPostDAO.checkDelete_yn(board_idx);
		
		if(checkDelete_yn.equals("Y") || checkDelete_yn == null) {
			return null;
		}
		
		QnaPostDTO qnaPostDTO = qnaEditPostDAO.selectQnaPostForEdit(board_idx);
		
		return qnaPostDTO;
	}
	
	public void editQnaPost(EditQnaPostDTO editQnaPostDTO) throws Exception {
		
		qnaEditPostDAO.updateQnaPost(editQnaPostDTO);
		
	}
	

}
