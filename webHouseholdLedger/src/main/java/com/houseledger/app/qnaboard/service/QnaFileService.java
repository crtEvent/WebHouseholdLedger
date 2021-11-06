package com.houseledger.app.qnaboard.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.houseledger.app.qnaboard.dto.QnaFileDTO;

public interface QnaFileService {
	
	public void uploadAttachedFilesToQnaPost(MultipartHttpServletRequest multipartRequest, String board_idx, String user_idx) throws Exception;
	
	public List<HashMap<String, Object>> getQnaFileList(String board_idx) throws Exception;
	
	public QnaFileDTO getQnaFile(String qna_file_idx) throws Exception;
}
