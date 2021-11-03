package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;
import com.houseledger.app.qnaboard.service.QnaFileService;
import com.houseledger.app.qnaboard.service.QnaWritePostService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaPostWriteController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaWritePostService")
	QnaWritePostService qnaWritePostService;
	
	@Resource(name = "qnaFileService")
	QnaFileService qnaFileService;
	
	// 글쓰기 페이지로 이동
	@RequestMapping(value="/qna/writePost.do")
	public String qna_write() throws Exception {
		
		return "/qna_board/qna_post_write";
	}
	
	// 글쓰기(insert) 기능 실행
	@RequestMapping(value="/qna/insert_post.do")
	public String insert_qna_post(WriteQnaPostDTO writeQnaPostDTO, @SessionAttribute("userSession")UserVO userVO, MultipartHttpServletRequest multipartRequest) throws Exception {
		
		multipartRequest.getSession().getServletContext().getRealPath("");
		
		
		// dto에 user_idx 저장
		writeQnaPostDTO.setUser_idx(userVO.getUser_idx());
		
		// 게시글 insert
		qnaWritePostService.insertQnaPost(writeQnaPostDTO);
		
		// 첨부파일 업로드
		qnaFileService.uploadAttachedFilesToQnaPost(multipartRequest, writeQnaPostDTO.getBoard_idx(), userVO.getUser_idx());
		
		return "redirect:/qna/list.do";
	}
	
}
