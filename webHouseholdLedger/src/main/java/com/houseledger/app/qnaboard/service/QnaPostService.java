package com.houseledger.app.qnaboard.service;

import com.houseledger.app.qnaboard.dto.QnaPostDTO;

public interface QnaPostService {
	
	public QnaPostDTO getQnaPost(String board_idx) throws Exception;

}
