package com.houseledger.app.qnaboard.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.houseledger.app.qnaboard.dao.QnaListDAO;
import com.houseledger.app.qnaboard.dto.PagingDTO;
import com.houseledger.app.qnaboard.dto.QnaSelectListDTO;

@Service("qnaListService")
public class QnaListServiceImpl implements QnaListService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaListDAO")
	QnaListDAO qnaListDAO;
	
	// Q&A 게시글 리스트 불러오기
	public List<Map<String, Object>> getQnaPostList(QnaSelectListDTO qnaSelectListDTO) throws Exception {
		return qnaListDAO.selectQnaPostList(qnaSelectListDTO);
	}
	
	// Paging 정보 생성
	public PagingDTO getPaging(QnaSelectListDTO qnaSelectListDTO) throws Exception {
		int totalNumberPosts = qnaListDAO.countQnaPostList(qnaSelectListDTO);
		PagingDTO pagingDTO = new PagingDTO(qnaSelectListDTO, totalNumberPosts);
		return pagingDTO;
	}
	
}
