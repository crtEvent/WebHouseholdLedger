package com.houseledger.app.qnaboard.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.houseledger.app.qnaboard.dto.WriteQnaPostDTO;
import com.houseledger.app.qnaboard.service.QnaWritePostService;
import com.houseledger.app.user.vo.UserVO;

@Controller
public class QnaPostWriteController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "qnaWritePostService")
	QnaWritePostService qnaWritePostService;
	
	// 글쓰기 페이지로 이동
	@RequestMapping(value="/qna/writePost.do")
	public String qna_write() throws Exception {
		
		return "/qna_board/qna_post_write";
	}
	
	// 글쓰기(insert) 기능 실행
	@RequestMapping(value="/qna/insert_post.do")
	public String insert_qna_post(WriteQnaPostDTO writeQnaPostDTO, @SessionAttribute("userSession")UserVO userVO) throws Exception {
		
		writeQnaPostDTO.setUser_idx(userVO.getUser_idx());
		
		qnaWritePostService.insertQnaPost(writeQnaPostDTO);
		
		return "redirect:/qna/list.do";
	}
	
}
