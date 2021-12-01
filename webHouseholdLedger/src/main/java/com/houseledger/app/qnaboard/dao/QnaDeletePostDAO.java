package com.houseledger.app.qnaboard.dao;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;

@Repository("qnaDeletePostDAO")
public class QnaDeletePostDAO extends AbstractDAO {
	
	public void deleteQnaPost(String board_idx) throws Exception {
		update("qnaDeletePost.deleteQnaPost", board_idx);
	}
	
	public String selectUserIdxOfPost(String board_idx) throws Exception {
		return (String) selectOne("qnaDeletePost.selectUserIdxOfPost", board_idx);
	}
}
