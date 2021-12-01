package com.houseledger.app.qnaboard.service;

public interface QnaDeletePostService {
	
	public void deleteQnaPost(String board_idx) throws Exception;
	
	public String selectUserIdxOfPost(String board_idx) throws Exception;
}
