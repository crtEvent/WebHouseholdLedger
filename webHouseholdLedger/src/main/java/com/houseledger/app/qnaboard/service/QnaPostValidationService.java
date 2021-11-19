package com.houseledger.app.qnaboard.service;

public interface QnaPostValidationService {
	
	public boolean checkVaildQnaPost(String subject, String content) throws Exception;
	
	public String removeHtmlCode(String content) throws Exception;
	
	public String replaceHtmlEntities(String content) throws Exception;

}
