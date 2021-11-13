package com.houseledger.app.qnaboard.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.EditQnaPostDTO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;

@Repository("qnaEditPostDAO")
public class QnaEditPostDAO extends AbstractDAO {
	
	// 게시글의 user_idx 찾기
	public String selectUserIdxOfPost(String board_idx) throws Exception {
		return (String) selectOne("qnaEditPost.selectUserIdxOfPost", board_idx);
	}
	
	// 게시글 불러오기
	public QnaPostDTO selectQnaPostForEdit(String board_idx) throws Exception {
		return (QnaPostDTO) selectOne("qnaEditPost.selectQnaPostForEdit", board_idx);
	}
	
	// 게시글 수정하기
	public void updateQnaPost(EditQnaPostDTO editQnaPostDTO) throws Exception {
		update("qnaEditPost.updateQnaPost", editQnaPostDTO);
	}
	
}
