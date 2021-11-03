package com.houseledger.app.qnaboard.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface QnaFileService {
	
	public void uploadAttachedFilesToQnaPost(MultipartHttpServletRequest multipartRequest, String board_idx, String user_idx) throws Exception;

}
