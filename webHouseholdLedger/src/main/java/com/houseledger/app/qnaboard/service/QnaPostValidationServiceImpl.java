package com.houseledger.app.qnaboard.service;

import org.springframework.stereotype.Service;

@Service("qnaPostValidationService")
public class QnaPostValidationServiceImpl implements QnaPostValidationService {
	
	// 게시글 유효성 검사
		public boolean checkVaildQnaPost(String subject, String content) throws Exception {
			// 1. 제목(subject)의 글자수는 5자 이상, 100자 이하
			if(subject.length() < 5 || subject.length() > 100) {
				return false;
			}
			
			// 2. 내용(content)는 2000자 이하
			int contentLength = removeHtmlCode(replaceHtmlEntities(content)).length();
			if(contentLength > 2000) {
				return false;
			}
			
			return true;
		}
		
		public String removeHtmlCode(String content) throws Exception {
			return content.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		}
		
		public String replaceHtmlEntities(String content) throws Exception {
			return content.replaceAll("&([a-z0-9]+|#[0-9a-zA-Z]+);", "0");
		}

}
