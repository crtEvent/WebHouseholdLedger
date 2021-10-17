package com.houseledger.app.qnaboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;

@Repository("qnaCommentDAO")
public class QnaCommentDAO extends AbstractDAO{
	
	public List<Map<String, Object>> selectQnaCommentList(String board_idx) throws Exception {
		return (List<Map<String, Object>>) selectList("qnaComment.selectQnaCommentList", board_idx);
	}
}
