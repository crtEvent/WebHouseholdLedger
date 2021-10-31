package com.houseledger.app.qnaboard.service;

import java.util.List;
import java.util.Map;

import com.houseledger.app.qnaboard.dto.WriteQnaCommentDTO;

public interface QnaCommentService {
	
	public List<Map<String, Object>> getQnaCommentList(String board_idx) throws Exception;
	
	public void writeQnaComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception;
	
	public void writeQnaReComment(WriteQnaCommentDTO writeQnaCommentDTO) throws Exception;

}
