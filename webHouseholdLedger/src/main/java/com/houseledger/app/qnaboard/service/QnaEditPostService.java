package com.houseledger.app.qnaboard.service;

import com.houseledger.app.qnaboard.dto.EditQnaPostDTO;
import com.houseledger.app.qnaboard.dto.QnaPostDTO;

public interface QnaEditPostService {
	
	public boolean checkWriterUserIdx(String board_idx, String user_idx) throws Exception;
	
	public QnaPostDTO getQnaPostForEdit(String board_idx) throws Exception;
	
	public void editQnaPost(EditQnaPostDTO editQnaPostDTO) throws Exception;

}
