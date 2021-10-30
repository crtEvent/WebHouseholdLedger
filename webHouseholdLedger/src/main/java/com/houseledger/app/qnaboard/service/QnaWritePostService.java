package com.houseledger.app.qnaboard.service;

import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;

public interface QnaWritePostService {
	
	public void insertQnaPost(WriteQnaPostDTO writeQnaPostDTO) throws Exception;

}
