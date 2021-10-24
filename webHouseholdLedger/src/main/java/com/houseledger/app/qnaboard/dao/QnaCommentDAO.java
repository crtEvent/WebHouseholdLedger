package com.houseledger.app.qnaboard.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.houseledger.app.common.dao.AbstractDAO;
import com.houseledger.app.qnaboard.dto.WriteQnaCommentDTO;

@Repository("qnaCommentDAO")
public class QnaCommentDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectQnaCommentList(String board_idx) throws Exception {
		return (List<Map<String, Object>>) selectList("qnaComment.selectQnaCommentList", board_idx);
	}
	
	public String selectNextBundle_idx() throws Exception {
		return (String) selectOne("qnaComment.selectNextBundle_idx");
	}
	
	public void insertQnaComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		writeQnaCommentDTO.setDepth("1");
		insert("qnaComment.insertQnaComment", writeQnaCommentDTO);
	}
	
	public String selectParentBundle_idx(String parent_comment_idx) throws Exception {
		return (String) selectOne("qnaComment.selectParentBundle_idx", parent_comment_idx);
	}
	
	public void insertQnReComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception {
		writeQnaCommentDTO.setDepth("2");
		insert("qnaComment.insertQnaReComment", writeQnaCommentDTO);
	}
}
