package com.houseledger.app.qnaboard.service;

import java.util.List;
import java.util.Map;

import com.houseledger.app.qnaboard.dto.PagingDTO;
import com.houseledger.app.qnaboard.dto.QnaSelectListDTO;

public interface QnaListService {
	
	// Q&A 게시글 리스트 불러오기
	public List<Map<String, Object>> getQnaPostList(QnaSelectListDTO qnaSelectListDTO) throws Exception;
	
	// Paging 정보 생성
	public PagingDTO getPaging(QnaSelectListDTO qnaSelectListDTO) throws Exception;

}
