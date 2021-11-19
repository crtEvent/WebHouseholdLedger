package com.houseledger.app.qnaboard.service;

import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;

public interface QnaWritePostService {
	
	public String insertQnaPost(WriteQnaPostDTO writeQnaPostDTO) throws Exception;
	
}
