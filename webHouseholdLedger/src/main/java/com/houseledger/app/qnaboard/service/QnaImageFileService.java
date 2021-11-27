package com.houseledger.app.qnaboard.service;

import org.springframework.web.multipart.MultipartFile;

import com.houseledger.app.qnaboard.dto.QnaImageFileDTO;

public interface QnaImageFileService {
	
	public QnaImageFileDTO uploadImageFileTemporarily(MultipartFile multiFile, String user_idx) throws Exception;
	
	public void storeQnaImageFile(String user_idx, String content, String board_idx) throws Exception;
	
	public void editStoredQnaImageFile(String board_idx, String content) throws Exception;
	
}
