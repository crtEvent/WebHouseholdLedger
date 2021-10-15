package com.houseledger.app.qnaboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;

@Repository("qnaPostDAO")
public class QnaPostDAO extends AbstractDAO {
	
	public String checkDelete_yn(String board_idx) throws Exception {
		return (String) selectOne("qnaPost.checkDelete_yn", board_idx);
	}
	
	public QnaPostDTO selectQnaPost(String board_idx) throws Exception {
		return (QnaPostDTO) selectOne("qnaPost.selectQnaPost", board_idx);
	}
	
	public String selectNextQnaPost(String board_idx) throws Exception {
		return (String) selectOne("qnaPost.selectNextQnaPost", board_idx);
	}
	
	public String selectPrevQnaPost(String board_idx) throws Exception {
		return (String) selectOne("qnaPost.selectPrevQnaPost", board_idx);
	}

}
